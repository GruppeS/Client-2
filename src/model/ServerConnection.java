package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {

	private Socket clientSocket;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	public void connect() throws IOException {
		clientSocket = new Socket("localhost", 8888);
		output = new ObjectOutputStream(clientSocket.getOutputStream());
		output.flush();
		input = new ObjectInputStream(clientSocket.getInputStream());
	}

	public String send(String json) throws IOException, ClassNotFoundException {
		output.writeObject(json);
		output.flush();
		System.out.println("Sending: " + json);
		String reply = (String) input.readObject();
		System.out.println("Answer: " + reply);
		return reply;
	}

	public void close() throws IOException {
		clientSocket.close();
	}
}