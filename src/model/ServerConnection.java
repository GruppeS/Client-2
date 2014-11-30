package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerConnection {

	private Socket clientSocket;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	public void connect() {
		try {
			clientSocket = new Socket("localhost", 8888);
			output = new ObjectOutputStream(clientSocket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String send(String json) {
		String reply = null;
		try {
			System.out.println(json);
			output.writeObject(json);
			output.flush();
			reply = (String) input.readObject();
			System.out.println(reply);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reply;
	}

	public void close() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}