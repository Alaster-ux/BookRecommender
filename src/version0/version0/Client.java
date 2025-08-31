package version0;

/**
 * La classe Client serve a Implementare il Client come singolo Thread.
 */
public class Client extends Thread{
	ServerProxy proxy;
	String[] args;
	ClientUI interfaccia;
	String UserID;
	Libreria currentLibreria;
	Libro currentLibro;
	/**
	 * Il costruttore del thread Client.
	 * @param args
	 */
	public Client(String[] args) {
		//proxy = new ServerProxy();
		this.args=args;
		interfaccia = new ClientUI();
		interfaccia.setClient(this);
		start();
		//usa il proxy per connettersi al client
	}
	
	public void run() {
		//ED E QUI CHE BISOGNA IMPLEMENTARE UI!
		//SERVE UI PER MOSTRARE IL MENU, QUA CI SARA IL MENU PRINCIPALE E TUTTO
	
		interfaccia.launchUI(args);
	}

	public ServerProxy getProxy() {
		return proxy;
	}

	public String[] getArgs() {
		return args;
	}

	public ClientUI getInterfaccia() {
		return interfaccia;
	}

	public String getUserID() {
		return UserID;
	}

	public Libreria getCurrentLibreria() {
		return currentLibreria;
	}

	public Libro getCurrentLibro() {
		return currentLibro;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public void setCurrentLibreria(Libreria currentLibreria) {
		this.currentLibreria = currentLibreria;
	}

	public void setCurrentLibro(Libro currentLibro) {
		this.currentLibro = currentLibro;
	}
	
	
}
