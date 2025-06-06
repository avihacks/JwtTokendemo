package com.JWTToken.JWTTokenSample.configs;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.JWTToken.JWTTokenSample.repo.RevokedTokenRepository;
import com.JWTToken.JWTTokenSample.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.servlet.HandlerExceptionResolver;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    private final HandlerExceptionResolver handlerExceptionResolver;

	 private final JwtService jwtService;
	    private final UserDetailsService userDetailsService;
	    private final RevokedTokenRepository revokedTokenRepository;


	    public JwtAuthenticationFilter(
	        JwtService jwtService,
	        UserDetailsService userDetailsService,
	        HandlerExceptionResolver handlerExceptionResolver,
	        RevokedTokenRepository revokedTokenRepository
	     
	    ) {
	        this.jwtService = jwtService;
	        this.userDetailsService = userDetailsService;
	        this.handlerExceptionResolver = handlerExceptionResolver;
	        this.revokedTokenRepository= revokedTokenRepository;
	    }

	    @Override
	    protected void doFilterInternal(
	        @NonNull HttpServletRequest request,
	        @NonNull HttpServletResponse response,
	        @NonNull FilterChain filterChain
	    ) throws ServletException, IOException {
	        final String authHeader = request.getHeader("Authorization");
	        final String path = request.getServletPath(); // Get URL path
	        // Allow public APIs (like /auth/**) without token
	       
	        if (path.startsWith("/auth/login") || path.startsWith("/auth/signup")) {
	            filterChain.doFilter(request, response);
	            return;
	        }
	        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	           // filterChain.doFilter(request, response);
	          //  return;
	        	
	        	   handlerExceptionResolver.resolveException(
	        	            request, 
	        	            response, 
	        	            null, 
	        	            new RuntimeException("Missing or invalid Authorization header.")
	        	        );
	        	        return;
	        }

	        try {
	            final String jwt = authHeader.substring(7);
	            final String userEmail = jwtService.extractUsername(jwt);

	            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	            if (userEmail != null && authentication == null) {
	                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

	                if (jwtService.isTokenValid(jwt, userDetails)) {
	                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                            userDetails,
	                            null,
	                            userDetails.getAuthorities()
	                    );

	                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                    SecurityContextHolder.getContext().setAuthentication(authToken);
	                }
	            }

	         // ⛔ Check if token is revoked
	            if (revokedTokenRepository.existsByToken(jwt)) {
	                handlerExceptionResolver.resolveException(request, response, null, new RuntimeException("Token has been revoked"));
	                return;
	            }
	            filterChain.doFilter(request, response);
	        } catch (Exception exception) {
	            handlerExceptionResolver.resolveException(request, response, null, exception);
	        }
	    }

}
