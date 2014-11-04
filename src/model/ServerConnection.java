package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerConnection {

	private Encryption encrypt;

	public ServerConnection() {
		encrypt = new Encryption();
	}

	public String connect(String json) throws UnknownHostException, IOException {
		
		System.out.println("Establishing server connection");
		byte[] reply = null;
		
		Socket clientSocket = new Socket("localhost", 8888);
		System.out.println("Connection established");

		byte[] message = encrypt.xorEncrypt(json);
		DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
		dos.writeInt(message.length);
		dos.write(message);
		dos.flush();

		DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
		int length = dis.readInt();

		if(length>0) {
			reply = new byte[length];
			dis.readFully(reply, 0, reply.length);
		}
		
		clientSocket.close();
		System.out.println("Socket closed");
		return encrypt.xorDecrypt(reply);
	}
}