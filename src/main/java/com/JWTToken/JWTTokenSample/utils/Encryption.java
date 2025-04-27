/*
 * package com.JWTToken.JWTTokenSample.utils;
 * 
 * import java.io.UnsupportedEncodingException; import
 * java.security.InvalidKeyException; import
 * java.security.NoSuchAlgorithmException;
 * 
 * import javax.crypto.Mac; import javax.crypto.spec.SecretKeySpec;
 * 
 * import java.util.Base64;
 * 
 *//**
	 * Encryption class to show how to generate encoded(HMAC-x) signatures. Generate
	 * online secert key
	 * https://www.devglan.com/online-tools/hmac-sha256-online?ref=blog.tericcabrel.com
	 */
/*
 * public class Encryption {
 * 
 * public static void main(String args[]) {
 * 
 * String message = "This is my message."; String key = "Avinash"; String
 * algorithm = "HmacMD5"; // OPTIONS= HmacSHA512, HmacSHA256, HmacSHA1, HmacMD5
 * 
 * try {
 * 
 * // 1. Get an algorithm instance. Mac sha256_hmac =
 * Mac.getInstance(algorithm);
 * 
 * // 2. Create secret key. SecretKeySpec secret_key = new
 * SecretKeySpec(key.getBytes("UTF-8"), algorithm);
 * 
 * // 3. Assign secret key algorithm. sha256_hmac.init(secret_key);
 * 
 * // 4. Generate Base64 encoded cipher string. //String hash =
 * Base64.encode(sha256_hmac.doFinal(message.getBytes("UTF-8"))); String hash =
 * Base64.getEncoder().encodeToString(sha256_hmac.doFinal(message.getBytes(
 * "UTF-8"))); // You can use any other encoding format to get hash text in that
 * encoding. System.out.println(hash);
 * 
 *//**
	 * Here are the outputs for given algorithms:-
	 * 
	 * HmacMD5 = hpytHW6XebJ/hNyJeX/A2w== HmacSHA1 = CZbtauhnzKs+UkBmdC1ssoEqdOw=
	 * HmacSHA256 =gCZJBUrp45o+Z5REzMwyJrdbRj8Rvfoy33ULZ1bySXM= HmacSHA512 =
	 * OAqi5yEbt2lkwDuFlO6/4UU6XmU2JEDuZn6+1pY4xLAq/JJGSNfSy1if499coG1K2Nqz/yyAMKPIx9C91uLj+w==
	 *//*
		 * 
		 * } catch (NoSuchAlgorithmException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * } catch (UnsupportedEncodingException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * } catch (InvalidKeyException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 */