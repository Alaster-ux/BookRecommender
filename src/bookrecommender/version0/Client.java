package version0;

/**
 * Rappresenta il client dell'applicazione, eseguito come thread separato.
 * <p>
 * La classe si occupa di avviare l'interfaccia grafica {@link ClientUI}, 
 * mantenere lo stato corrente dell'utente (UserID, libreria e libro selezionati) 
 * e comunicare con il server tramite {@link ServerProxy}.
 * </p>
 *
 * <p>
 * Implementa {@link Thread}, in modo che ogni client possa funzionare in maniera 
 * indipendente e concorrente.
 * </p>
 */
public class Client extends Thread{
	ServerProxy proxy;
	String[] args;
	ClientUI interfaccia;
	String UserID;
	Libreria currentLibreria;
	Libro currentLibro;
	 /**
     * Costruttore della classe Client.
     * <p>
     * Inizializza l'interfaccia grafica e avvia il thread.
     * </p>
     *
     * @param args parametri passati al client (es. configurazioni da linea di comando)
     */
	public Client(String[] args) {
		//proxy = new ServerProxy();
		this.args=args;
		interfaccia = new ClientUI();
		interfaccia.setClient(this);
		start();
		//usa il proxy per connettersi al client
	}
	
	/**
     * Metodo principale del thread.
     * Avvia l'interfaccia grafica e mostra il menu principale.
     */
	public void run() {
		//ED E QUI CHE BISOGNA IMPLEMENTARE UI!
		//SERVE UI PER MOSTRARE IL MENU, QUA CI SARA IL MENU PRINCIPALE E TUTTO
	
		interfaccia.launchUI(args);
	}
	
}
