package version0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverBR {
	
	/*
	 * Attiva il server su localhost
	 * Check delle connessioni	 * Binda il ServerSlave al client
	 */
	
	private static DatabaseManager manager;
	
	public final static int PORT = 5050;
	
	@SuppressWarnings("resource")
	public static void startConnections() {
		manager = DatabaseManager.getInstance();
		 Socket client;
		 ServerSocket server = null;
		System.out.println("Started connections");
		try {
			server = new ServerSocket(PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			manager.closeConnection();
		}
		
		while(true) {
			try {
				client = server.accept();
				new ServerSlave(client, manager);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				manager.closeConnection();
				break;
			}
		}
	}
	
	public static void main(String[] args) {

		manager = null;
		ServerUI interfaccia = new ServerUI();
		interfaccia.launchUI(args);
		
		
	}

}
