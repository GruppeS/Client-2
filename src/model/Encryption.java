package model;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

	private String encryptionKey = "cdc63491uAf24938";

	public String aesEncrypt(String password) throws Exception {

		Key aesKey = new SecretKeySpec(encryptionKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");

		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] encrypted = cipher.doFinal(password.getBytes());

		return new String(encrypted);
	}
}