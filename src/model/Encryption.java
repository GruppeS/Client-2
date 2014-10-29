package model;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

	private String encryptionKey = "cdc63491uAf24938";
	private byte key = (byte) 3.1470;

	public byte[] xorEncrypt (String gson) {

		byte[] input = gson.getBytes();

		byte[] encrypted = input;

		for (int i=0; i<encrypted.length; i++)
		{
			encrypted[i] = (byte) (encrypted[i] ^ key);
		}

		return encrypted;
	}

	public String xorDecrypt (byte[] reply) {

		byte[] decrypted = reply;

		for (int i=0; i<decrypted.length; i++)
		{
			decrypted[i] = (byte) (decrypted[i] ^ key);
		}

		return decrypted.toString();
	}

	public String aesEncrypt(String password) throws Exception {

		Key aesKey = new SecretKeySpec(encryptionKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");

		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] encrypted = cipher.doFinal(password.getBytes());

		return new String(encrypted);
	}
}