package model;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ServerConnection {

	private Encryption encrypt;
	private Socket clientSocket;
	private DataInputStream is;
	private PrintStream os;

	public ServerConnection() {
		encrypt = new Encryption();
	}

	public void connect() throws IOException {

		Socket clientSocket = new Socket("localhost", 8888);
		System.out.println("Connection established");

		DataInputStream is = new DataInputStream(clientSocket.getInputStream());
		PrintStream os = new PrintStream(clientSocket.getOutputStream());
	}

	public String login(String json) throws IOException {
		String encrypted = encrypt.xorDecrypt(json);

		os.println(encrypted);

		String reply = is.readLine();

		System.out.println("Encrypted: " + reply);
		System.out.println("Decrypted: " + encrypt.xorDecrypt(reply));
		return encrypt.xorDecrypt(reply);
	}

	public void close() throws IOException {
		System.out.println("Closing socket");
		clientSocket.close();
	}
}