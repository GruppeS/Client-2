import controller.Client;

public class Test {
	
	public static void main(String[] args) {
		
		new Thread(new Client()).start();
		
	}
}