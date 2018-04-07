package com.sunshine.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class AESCrypto {

	public static byte[] encrypt_16(byte[] src, byte[] password)
			throws Exception {
		if (password.length != 16) {
			byte[] key = new byte[16];
			for (int i = 0; i < 16; i++)
				key[i] = 0;
			int len = password.length >= 16 ? 16 : password.length;
			System.arraycopy(password, 0, key, 0, len);
			password = key;
		}

		SecretKeySpec secSpec = new SecretKeySpec(password, "AES");
		IvParameterSpec iv = new IvParameterSpec(password);
		Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secSpec, iv);
		return cipher.doFinal(src);
	}

	public static byte[] decrypt_16(byte[] src, byte[] password)
			throws Exception {
		try {
			if (password.length != 16) {
				byte[] key = new byte[16];
				for (int i = 0; i < 16; i++)
					key[i] = 0;
				int len = password.length >= 16 ? 16 : password.length;
				System.arraycopy(password, 0, key, 0, len);
				password = key;
			}

			SecretKeySpec secSpec = new SecretKeySpec(password, "AES");
			IvParameterSpec iv = new IvParameterSpec(password);
			Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, secSpec, iv);
			return cipher.doFinal(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt_simple(String value, String privateKey) {
		try {
			byte[] raw = privateKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			return byteToHexString(cipher.doFinal(value.getBytes("utf-8")));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String decrypt_simple(String value, String privateKey) {
		try {
			byte[] raw = privateKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			return new String(cipher.doFinal(hexStringToByte(value)));
		} catch (Exception ex) {
		}
		return null;
	}

	public static String byteToHexString(byte[] bytes) {
		return String.valueOf(Hex.encodeHex(bytes));
	}

	public static byte[] hexStringToByte(String hexString) {
		try {
			return Hex.decodeHex(hexString.toCharArray());
		} catch (Exception e) {
		}
		return null;
	}

}
