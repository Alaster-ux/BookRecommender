package version0;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ClientUI extends Application {
	ServerProxy proxy;
	Client currentClient;
	public ClientUI() {}
	
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
