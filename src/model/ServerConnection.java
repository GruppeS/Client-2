package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerConnection {

	private Encryption encryption = new Encryption();
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private String serverIP = new Config().serverIP;
	private int port = new Config().port;

	/**
	 * Connects to server
	 */
	public void connect() {
		try {			
			clientSocket = new Socket(serverIP, port);
			output = new ObjectOutputStream(clientSocket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.exit(0);
		}
	}

	/**
	 * Sends an encrypted json
	 * Recieves a decrypted json
	 * @param json
	 * @return replyDecrypted
	 */
	public String send(String json) {

		String replyDecrypted = null;

		try {
			System.out.println("Outgoing: " + json);
			output.writeObject(encryption.encrypt(json));
			output.flush();
			byte[] replyEncrypted = (byte[]) input.readObject();
			replyDecrypted = encryption.decrypt(replyEncrypted);
			System.out.println("Incomming: " + replyDecrypted);
		} catch (IOException | ClassNotFoundException e) {
			System.exit(0);
		}
		return replyDecrypted;
	}

	/**
	 * Closes connection (LogOut)
	 */
	public void close() {
		try {
			input.close();
			output.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}