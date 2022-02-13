package com.devwork.common.util;

import sun.misc.*;

/**
 * com.devwork.common.util 
 *    |_ Base64Utils.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public class Base64Utils {

	// base64 인코딩
	@SuppressWarnings("restriction")
	public String base64Encoding(String value) {
		String retVal = "";
		try {
			byte[] plainText = null; // 평문
			plainText = value.getBytes();
			BASE64Encoder encoder = new BASE64Encoder();
			retVal = encoder.encode(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	// base64 디코딩
	@SuppressWarnings("restriction")
	public String base64decoding(String encodedString) {
		String retVal = "";
		try {
			byte[] plainText = null; // 해쉬 값
			BASE64Decoder decoder = new BASE64Decoder();
			plainText = decoder.decodeBuffer(encodedString);
			retVal = new String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	// Base64 + Seed 암호화
	@SuppressWarnings("restriction")
	public String encrypt(String str, String key) {
		if (key.length() != 24) {
			return "";
		}
		try {
			String strResult;
			String strTemp = "";
			strResult = "";
			BASE64Encoder encoder = new BASE64Encoder();
			SeedAlg seedAlg = new SeedAlg(key.getBytes());
			strTemp = new String(encoder.encode(seedAlg.encrypt(str.getBytes())));
			for (int i = 0; i < strTemp.length(); i++) {
				if (strTemp.charAt(i) != '\n' && strTemp.charAt(i) != '\r') {
					strResult = strResult + strTemp.charAt(i);
				}
			}
			return strResult;
		} catch (Exception ex) {
			return null;
		}
	}

	// Base64 + Seed 복호화
	@SuppressWarnings("restriction")
	public String decrypt(String str, String key) {
		if (key.length() != 24) {
			return "";
		}
		try {
			String strResult;
			String strTemp = "";
			strResult = "";
			BASE64Decoder decoder = new BASE64Decoder();
			SeedAlg seedAlg = new SeedAlg(key.getBytes());
			strTemp = new String(seedAlg.decrypt(decoder.decodeBuffer(str)));
			for (int i = 0; i < strTemp.length() && strTemp.charAt(i) != 0;) {
				if (strTemp.charAt(i) != '\n' && strTemp.charAt(i) != '\r') {
					strResult = strResult + strTemp.charAt(i);
					i++;
				}
			}
			return strResult;
		} catch (Exception ex) {
			return null;
		}
	}

}
