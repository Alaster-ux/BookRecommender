package version0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverBR {
	
	/*
	 * Attiva il server su localhost
	 * Check delle connessioni	 * Binda il ServerSlave al client
	 */
	
	
	
	
	
	private DatabaseManager manager;
	public final static int PORT = 5050;
	private ServerSocket server;
	private Socket client;
	
	public serverBR() {
		System.out.println("server -> "+this.server);
		try {
			
			server = new ServerSocket(PORT);
			manager = DatabaseManager.getInstance();
			
			System.out.println("server -> "+this.server);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		ServerUI interfaccia = new ServerUI();
		interfaccia.launchUI(args);
		//new serverBR();
	}

}
