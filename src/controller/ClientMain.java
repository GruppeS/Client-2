package controller;

public class ClientMain {
	
	/**
	 * Main methods start client in a thread
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Thread(new Client()).start();
		
	}
}
