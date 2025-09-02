package version0;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Classe responsabile della gestione dell'interfaccia grafica lato client.
 * <p>
 * Estende {@link Application} di JavaFX e si occupa di caricare la scena
 * principale da file FXML, inizializzare il controller e stabilire la
 * comunicazione con il server tramite {@link ServerProxy}.
 * </p>
 */
public class ClientUI extends Application {
	ServerProxy proxy;
	Client currentClient;
	
	/**
     * Costruttore di default.
     * <p>
     * Non inizializza il {@link ServerProxy}: questo viene creato al momento
     * dell'avvio dell'interfaccia grafica se non è già stato impostato.
     * </p>
     */
	public ClientUI() {}
	
	/**
     * Avvia la finestra principale dell'applicazione.
     * <p>
     * Il metodo carica il file {@code Home.fxml}, associa il controller
     * {@link ControllerUI}, gli passa il {@link ServerProxy}, e mostra la scena
     * principale.
     * </p>
     *
     * @param stage finestra principale JavaFX
     * @throws Exception se il caricamento della GUI fallisce
     */
	public void start(Stage stage) throws Exception {
	    try {
	        if (proxy == null) {
	            proxy = new ServerProxy(); // crea solo se non è già stato settato
	        }

	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
	        Parent root;
			 
			root = loader.load();
	        ControllerUI controller = loader.getController();
	        controller.setProxy(this.proxy);
	        
	        stage.setScene(new Scene(root));
	        stage.show();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void setClient(Client client) {
		this.currentClient = client;
	}
	
	 /**
     * Lancia l'applicazione JavaFX.
     *
     * @param args argomenti passati da riga di comando
     */
	public void launchUI(String[] args) {
		launch(args);
	}
}

//COMMENTI
/*
 *-chiedi a Santo nel controller nel metodo register per gli if
 *
 *-
 *
 */
