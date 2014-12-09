package model;

public class Encryption {

	byte key = new Config().key;

	/**
	 * XOR encrypts string json
	 * @param json
	 * @return
	 */
	public byte[] encrypt(String json) {
		byte[] input = json.getBytes();
		byte[] encrypted = input;
		for (int i = 0; i < encrypted.length; i++)
			encrypted[i] = (byte) (encrypted[i] ^ key);

		return encrypted;
	}

	/**
	 * XOR decrypts byte[] reply
	 * @param reply
	 * @return
	 */
	public String decrypt(byte[] reply) {
		for(int i = 0 ; i<reply.length ; i++)
		{
			reply[i] = (byte)(reply[i]^key);
		}
		String decrypted = new String(reply).trim();
		return decrypted;
	}
}
