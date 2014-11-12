package model;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ServerConnection {

	private Socket clientSocket;
	private DataInputStream is;
	private PrintStream os;

	public void connect() throws IOException {
		clientSocket = new Socket("localhost", 8888);
		is = new DataInputStream(clientSocket.getInputStream());
		os = new PrintStream(clientSocket.getOutputStream());
	}

	public String send(String json) throws IOException {
		os.println(json);
		System.out.println("Sending: " + json);
		String reply = is.readLine();
		System.out.println("Answer: " + reply);
		return reply;
	}

	public void close() throws IOException {
		clientSocket.close();
	}
}