package version0;


import javax.print.DocFlavor.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class ServerUI extends Application {
	DatabaseManager manager;
	public ServerUI() {}
	
	public DatabaseManager getManager() {
		return manager;
	}
	
	public void start(Stage stage) throws Exception {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginDatabase.fxml"));
	        Parent root;
	        manager = DatabaseManager.getInstance();
			root = loader.load();
	        ControllerUI controller = loader.getController();
	        controller.setDB(manager);
	        
	        stage.setScene(new Scene(root));
	        stage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
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
