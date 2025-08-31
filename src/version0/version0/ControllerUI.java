package version0;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ControllerUI implements Initializable {
	
	// RELATIVE A UTENTE
	// CURRENT CLIENT - Libro, Libreria, UserID, Proxy
	private ServerProxy currentProxy = null;
	private String currentFXML = "Home.fxml";
	
	// INFO DI UTILITA
	private String currentUserID = null;
	private LinkedList<Libro> currentListaLibri;
	private LinkedList<Libreria> currentListaLibreria;
	private Libreria currentLibreria;
	private Libro currentLibro;
	private String modalitaAperturaLibro;
	private DatabaseManager currentDB;
	
	// LOGIN.FXML
	
	@FXML
	private TextField Login_UserID;
	
	@FXML
	private TextField Login_Password;
	
	// REGISTER.FXML
	
	@FXML
	private TextField Register_UserID;
	
	@FXML
	private TextField Register_Password;
	
	@FXML
	private TextField Register_Nome;
	
	@FXML
	private TextField Register_Cognome;
	
	@FXML
	private TextField Register_CodiceFiscale;
	
	@FXML
	private TextField Register_Email;
	
	// MENULOGGED.FXML
	
	@FXML
	private Label Menu_Benvenuto;
	
	@FXML
	private Label Menu_Benvenuto2;
	
	// CercaLibro.FXML
	
	@FXML
	private TextField CercaLibro_Titolo;
	
	@FXML
	private TextField CercaLibro_Autore;
	
	@FXML
	private TextField CercaLibro_Author;
	
	@FXML
	private TextField CercaLibro_Anno;
	
	@FXML
	private ListView<String> CercaLibro_Lista;
	
	// InfoLibro + InfoLibroGuest
	
	@FXML
	private Text InfoLibro_Titolo;
	
	@FXML
	private Text InfoLibro_Autore;
	
	@FXML
	private Text InfoLibro_Anno;
	
	@FXML
	private Text InfoLibro_Editore;
	
	@FXML
	private Text InfoLibro_Categoria;
	
	@FXML
	private Text InfoLibro_Stile;
	
	@FXML
	private Text InfoLibro_Contenuto;
	
	@FXML
	private Text InfoLibro_Originalita;
	
	@FXML
	private Text InfoLibro_Gradevolezza;
	
	@FXML
	private Text InfoLibro_Edizione;
	
	@FXML
	private Text InfoLibro_VotoFinale;
	
	@FXML
	private ListView<String> InfoLibro_Commenti;
	
	@FXML
	private ListView<String> InfoLibro_Consigliati;
	
	// AggiungiCommento.fxml
	
	@FXML
	private TextArea Commento;
	
	// Valutazione.Fxml
	
	@FXML
	private Text ValLibroInfo;
	@FXML
	private TextField nota_s;
	@FXML
	private TextField nota_o;
	@FXML
	private TextField nota_g;
	@FXML
	private TextField nota_e;
	@FXML
	private TextField nota_c;
	@FXML
	private ChoiceBox<Integer> stile;
	@FXML
	private ChoiceBox<Integer> contenuto;
	@FXML
	private ChoiceBox<Integer> gradevolezza;
	@FXML
	private ChoiceBox<Integer> originalita;
	@FXML
	private ChoiceBox<Integer> edizione;
	
	// PATHNAMES
	
	private static final String LoginDatabase = "LoginDatabase.fxml";
	private static  final String AggiungiCommento = "AggiungiCommento.fxml";
	private static  final String CercaLibro = "CercaLibro.fxml";
	private static  final String Home = "Home.fxml";
	private static  final String InfoLibro = "InfoLibro.fxml";
	private static  final String InfoLibroGuest = "InfoLibroGuest.fxml";
	private static  final String Libreria = "Libreria.fxml";
	private static  final String LibrerieUtente = "LibrerieUtente.fxml";
	private static  final String Login = "LoginUser.fxml";
	private static  final String MenuGuest = "MenuGuest.fxml";
	private static  final String MenuLogged = "MenuLogged.fxml";
	private static  final String NomeLibreria = "NomeLibreria.fxml";
	private static  final String Register = "RegisterUser.fxml";
	private static  final String Valutazione = "Valutazione.fxml";
	
	// LibrerieUtente.fxml
	
	@FXML
	private ListView<String> ListViewLibrerieUtente;
	
	// Libreria.fxml
	
	@FXML
	private ListView<String> ListViewLibriLibreria;
	@FXML 
	private Text NomeInfoLibreria;
	
	// CreaLibreria.fxml
	
	@FXML
	private TextField NomeSceltoLibreria;
	
	// FUNZIONI DI UTILITA
	
	public void setDB(DatabaseManager db) {
		this.currentDB=db;
	}
	
	private void setModalitaApertura(String modApr) {
		this.modalitaAperturaLibro = modApr;
	}
	
	public void setProxy(ServerProxy client) {
		currentProxy = client;
	}
	
	private void setCurrentUserID(String s) {
		this.currentUserID = s;
	}
	
	private void setFXML(String s) {
		this.currentFXML=s;
	}
	
	private void setCurrentLibro(Libro l) {
		this.currentLibro=l;
	}
	
	private void setCurrentLibreria(Libreria l) {
		this.currentLibreria = l;
	}
	
	public void openFXML(String fxml) throws IOException {
		
		
		
		 FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		 Parent root;
		 
		 root = loader.load();
		 
		 ControllerUI controller = loader.getController();
		 controller.setProxy(this.currentProxy);
		 controller.setCurrentUserID(this.currentUserID);
		 controller.setModalitaApertura(this.modalitaAperturaLibro);
		 controller.setCurrentLibro(currentLibro);
		 controller.setCurrentLibreria(currentLibreria);
		 controller.setFXML(fxml);
         Stage stage = new Stage();
         
         stage.setTitle("BookRecommender");
         stage.setResizable(false);
         stage.setScene(new Scene(root));
         stage.show();
         
        if(!this.currentProxy.isConnected()) {
 			warning("Errore","Impossibile connettersi al server.");
 			stage.close();
 		}
         
         
         if(fxml.equals(MenuLogged)) {
        	 controller.initializeMenuLogged();
         }
         if(fxml.equals(InfoLibro) || fxml.equals(InfoLibroGuest)) {
        	 controller.initializeInfoLibro();
         }
         if(fxml.equals(Valutazione)) {
        	 controller.initializeValutazione();
         }
         if(fxml.equals(LibrerieUtente)) {
        	 controller.initializeLibrerieUtente();
         }
         if(fxml.equals(Libreria)) {
        	 controller.inizializzaLibreria();
         }
	}
	
	public void closeFXML(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
	}	
	
	public void warning(String titolo, String descrizione) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(descrizione);
        alert.showAndWait();
	}
	
	private String getPreviousFXML() {
		if(currentFXML.equals(Login) || currentFXML.equals(Register) || currentFXML.equals(MenuGuest)|| currentFXML.equals(MenuLogged)){
			return Home;
		}
		return null;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	// FUNZIONI RELATIVE A "HOME.FXML"
	
	public void enterLogin(ActionEvent event) throws IOException {
		this.closeFXML(event);
		this.openFXML(Login);
	}
	public void enterRegister(ActionEvent event) throws IOException {
		this.closeFXML(event);
		this.openFXML(Register);
	}
	public void closeConnection(ActionEvent event) throws IOException {
		this.closeFXML(event);
		this.currentProxy.closeConnection();
	}
	public void enterGuestMode(ActionEvent event) throws IOException {
		this.closeFXML(event);
		this.openFXML(MenuGuest);
		this.currentUserID = null;
	}
	
	// FUNZIONI - LOGIN.FXML
	
	public void loginConferma(ActionEvent event) throws IOException {
		String userID = this.Login_UserID.getText().trim();
		String pass = this.Login_Password.getText();
		
		if(userID.equals("") || pass.trim().equals("")) {
			warning("Errore","Perfavore immettere i dati.");
			return;
		}
		this.currentUserID = this.currentProxy.login(userID, pass);
		if(currentUserID!=null) {
			this.closeFXML(event);
			this.openFXML(MenuLogged);
		}else {
			warning("Errore","Non esiste alcun utente con queste credenziali.");
			return;
		}
	}
	
	// FUNZIONI - REGISTER.FXML
	
	public void registrazioneConferma(ActionEvent event) throws IOException {
		String userID = this.Register_UserID.getText().trim().toLowerCase();
		String password = this.Register_Password.getText();
		String email = this.Register_Email.getText();
		String nome = this.Register_Nome.getText();
		String cognome = this.Register_Cognome.getText();
		String codiceFiscale = this.Register_CodiceFiscale.getText().trim();
		
		if(userID.equals("")||password.trim().equals("")||email.trim().equals("")||nome.trim().equals("")||cognome.trim().equals("")||codiceFiscale.trim().equals("")||email.trim().equals("")) {
			warning("Errore","Controlla i campi.");
			return;
		}
		
		int code = this.currentProxy.registrazione(userID, password, nome, cognome, codiceFiscale, email);
		switch(code) {
		case 0:
			this.currentUserID=userID;
			this.closeFXML(event);
			this.openFXML(MenuLogged);
			break;
		case -1:
			warning("Errore","UserID non disponibile.");
			break;
		case -2:
			warning("Errore","CodiceFiscale errato.");
			break;
		case -3:
			warning("Errore","UserID non disponibile e Codice Fiscale errato.");
			break;
		case -4:
			warning("Errore","Errore generico SQL.");
			break;
		}
	}
	
	// FUNZIONI - CercaLibro.FXML
	
	private void setCercaLibroList() {
		this.CercaLibro_Lista.getItems().clear();
		for(Libro l:this.currentListaLibri) {
			this.CercaLibro_Lista.getItems().add(l.toString());
		}
		if(this.currentListaLibri.size()==0) {
			this.CercaLibro_Lista.getItems().add("Non ho trovato Libri.");
		}
	}
	
	public void confermaSceltaLibro(ActionEvent event) throws IOException {
		int index = this.CercaLibro_Lista.getSelectionModel().getSelectedIndex();
		if(index<0) {
			warning("Errore","Seleziona un Libro.");
		}else {
			Libro tmpLibro = this.currentListaLibri.get(index);
			String nextFXML = this.modalitaAperturaLibro;
			if(nextFXML.equals(InfoLibroGuest)) {
				this.currentLibro = tmpLibro;
				this.closeFXML(event);
				this.openFXML(InfoLibroGuest);
			}
			if(nextFXML.equals(InfoLibro)) {
				this.currentLibro = tmpLibro;
				this.closeFXML(event);
				this.openFXML(InfoLibro);
			}
			if(nextFXML.equals(Valutazione)) {
				int check = this.currentProxy.aggiungiSuggerimentoLibro(this.currentUserID, this.currentLibro, tmpLibro);
				this.closeFXML(event);
				if(check==0) {
					warning("Notifica","Il Libro "+tmpLibro+" e' stato suggerito per "+this.currentLibro);
				}else {
					warning("Errore","Errore SQL.");
				}
			}
			if(nextFXML.equals(Libreria)) {
				int check = this.currentProxy.aggiungiLibroLibreria(this.currentLibreria, tmpLibro);
				if(check==0) {
					warning("Notifica","Il Libro "+tmpLibro+" e' stato aggiunto alla Libreria.");
					this.closeFXML(event);
				}else {
					warning("Errore","Errore generico SQL. (Il Libro e' gia presente nella Libreria)");
				}
			}
		}
	}
	
	public void cercaTitolo(ActionEvent event) throws IOException {
		String titolo = this.CercaLibro_Titolo.getText().trim();
		if(titolo.equals("")) {
			warning("Errore","Inserire un Titolo.");
			return;
		}
		this.currentListaLibri = this.currentProxy.cercaTitolo(titolo);
		this.setCercaLibroList();
	}
	
	public void cercaAutore(ActionEvent event) throws IOException {
		String autore = this.CercaLibro_Autore.getText().trim();
		if(autore.equals("")) {
			warning("Errore","Inserire un Autore.");
			return;
		}
		this.currentListaLibri = this.currentProxy.cercaAutore(autore);
		this.setCercaLibroList();
	}
	
	public void cercaAutoreAnno(ActionEvent event) throws IOException {
		String autore = this.CercaLibro_Autore.getText().trim();
		if(autore.equals("")) {
			warning("Errore","Inserire un Autore.");
			return;
		}
		int anno;
		try{
			anno = Integer.parseInt(this.CercaLibro_Anno.getText());
		}catch(NumberFormatException e){
			warning("Errore","Inserire un Anno.");
			return;
		}
		this.currentListaLibri = this.currentProxy.cercaAutoreAnno(autore, anno);
		this.setCercaLibroList();
	}
	
	// FUNZIONI InfoLibro.FXML + InfoLibroGuest.FXML
	
	private void initializeInfoLibro() {
		this.InfoLibro_Titolo.setText(this.currentLibro.getTitolo());
		this.InfoLibro_Autore.setText("Autore: "+this.currentLibro.getAutore());
		this.InfoLibro_Anno.setText("Anno: "+this.currentLibro.getAnno());
		this.InfoLibro_Editore.setText("Editore: "+this.currentLibro.getEditore());
		this.InfoLibro_Categoria.setText("Categoria: "+this.currentLibro.getCategoria());
		
		ProspettoLibro prospetto = this.currentProxy.visualizzaLibro(currentLibro);
		
		this.InfoLibro_Stile.setText("Stile: "+(prospetto.checkVal()?prospetto.getStile():"n/a"));
		this.InfoLibro_Contenuto.setText("Contenuto: "+(prospetto.checkVal()?prospetto.getContenuto():"n/a"));
		this.InfoLibro_Gradevolezza.setText("Gradevolezza: "+(prospetto.checkVal()?prospetto.getGradevolezza():"n/a"));
		this.InfoLibro_Edizione.setText("Edizione: "+(prospetto.checkVal()?prospetto.getEdizione():"n/a"));
		this.InfoLibro_Originalita.setText("Originalita: "+(prospetto.checkVal()?prospetto.getOriginalita():"n/a"));
		this.InfoLibro_VotoFinale.setText("Voto Finale: "+(prospetto.checkVal()?prospetto.getVoto_finale() +" ("+prospetto.getNumero_val()+" valutazioni)":"n/a"));
		
		this.InfoLibro_Commenti.getItems().clear();
		
		for(String comm:prospetto.getCommenti()) {
			this.InfoLibro_Commenti.getItems().add(comm);
		}
		if(!prospetto.checkCommenti()) {
			this.InfoLibro_Commenti.getItems().add("Non ci sono commenti.");
		}
		
		this.InfoLibro_Consigliati.getItems().clear();
		
		for(SuggerimentoLibro sug:prospetto.getSuggerimenti()) {
			String comm = sug.getL()+" consigliato "+sug.getNumeroSuggerimenti()+" volte";
			this.InfoLibro_Consigliati.getItems().add(comm);
		}
		
		if(!prospetto.checkSug()) {
			this.InfoLibro_Consigliati.getItems().add("Non ci sono Libri Consigliati.");
		}
		
	}
	
	public void refreshInfo(ActionEvent event) {
		this.initializeInfoLibro();
	}
	
	public void apriCommento(ActionEvent event) throws IOException {
		
		this.openFXML(AggiungiCommento);
		System.out.println("Commento...?");	
	}
	
	public void openValutazione(ActionEvent event) throws IOException {
		this.openFXML(Valutazione);
	}
	
	public void openConsigliLibro(ActionEvent event) throws IOException {
		this.modalitaAperturaLibro = Valutazione;
		this.openFXML(CercaLibro);
	}
	
	// FUNZIONI - MenuGuest.FXML
	
	public void apriCercaLibro(ActionEvent event) throws IOException{
		//this.closeFXML(event);
		this.modalitaAperturaLibro = InfoLibroGuest;
		this.openFXML(CercaLibro);
	}
	
	// FUNZIONI - MenuLoggato.FXML
	
	public void apriLibrerieUtente(ActionEvent event) throws IOException {
		this.openFXML(LibrerieUtente);
	}
	
	private void initializeMenuLogged() {
		String tmp = "Benvenuto/a, "+this.currentUserID;
		this.Menu_Benvenuto.setText(tmp);
		this.Menu_Benvenuto2.setText(tmp);
	}
	
	// FUNZIONI - AggiungiCommento.fxml
	
	public void confermaCommento(ActionEvent event) throws IOException {
		String comm = this.Commento.getText();
		int check = this.currentProxy.aggiungiCommento(this.currentUserID, currentLibro, comm);
		if(check==0) {
			warning("Notifica","Commento aggiunto.");
			this.closeFXML(event);
		}else {
			warning("Errore","Errore SQL (Max 256 chars).");
		}
	}
	
	// FUNZIONI - Valutazione.fxml
	
	private void initializeValutazione() {
		stile.getItems().addAll(1,2,3,4,5);
		contenuto.getItems().addAll(1,2,3,4,5);
		originalita.getItems().addAll(1,2,3,4,5);
		edizione.getItems().addAll(1,2,3,4,5);
		gradevolezza.getItems().addAll(1,2,3,4,5);
		stile.setValue(1);
		contenuto.setValue(1);
		originalita.setValue(1);
		edizione.setValue(1);
		gradevolezza.setValue(1);
	}
	
	public void confermaValutazione(ActionEvent event) {
		int check = this.currentProxy.inserisciValutazioneLibro(currentLibro, currentUserID, stile.getValue(), contenuto.getValue(), gradevolezza.getValue(), originalita.getValue(), edizione.getValue(), nota_s.getText(), nota_c.getText(), nota_g.getText(), nota_o.getText(), nota_e.getText());
		if(check>0) {
			warning("Notifica","Valutazione inserita correttamente");
			this.closeFXML(event);
		}else {
			warning("Errore","Errore SQL. (Note troppo lunghe...");
		}
	}
	
	// FUNZIONI LibrerieUtente.fxml
	
	private void initializeLibrerieUtente() {
		this.currentListaLibreria = this.currentProxy.getLibrerieUtente(this.currentUserID);
		this.ListViewLibrerieUtente.getItems().clear();
		
		for(Libreria l:this.currentListaLibreria) {
			this.ListViewLibrerieUtente.getItems().add(l.getNome());
		}
		if(this.currentListaLibreria.size()==0) {
			this.ListViewLibrerieUtente.getItems().add("Non hai Librerie. Creane una!");
		}
	}
	
	public void RefreshLibrerie(ActionEvent event) {
		this.initializeLibrerieUtente();
	}
	
	public void CreaLibreria(ActionEvent event) throws IOException {
		this.openFXML(NomeLibreria);
	}
	
	public void ApriLibreria(ActionEvent event) throws IOException {
		int index = this.ListViewLibrerieUtente.getSelectionModel().getSelectedIndex();
		if(index<0) {
			warning("Errore","Seleziona una Libreria.");
		}else {
			this.currentLibreria = this.currentListaLibreria.get(index);
			this.openFXML(Libreria);
			this.closeFXML(event);
		}
	}
	
	// FUNZIONI - Libreria.fxml
	
	private void inizializzaLibreria() {
		this.ListViewLibriLibreria.getItems().clear();
		this.currentLibreria = this.currentProxy.getLibreriaByID(this.currentLibreria.getMatricola());
		this.currentListaLibri = this.currentLibreria.getLista();
		this.NomeInfoLibreria.setText(this.currentLibreria.getNome());
		for(Libro l:this.currentListaLibri) {
			this.ListViewLibriLibreria.getItems().add(l.toString());
		}
		if(this.currentListaLibreria.size()==0) {
			this.ListViewLibriLibreria.getItems().add("Non ci sono Libri qui... Aggiungine uno!");
		}
	}
	
	public void refreshLibriLibreria(ActionEvent event) throws IOException {
		this.inizializzaLibreria();
	}
	
	public void aggiungiLibroLibreria(ActionEvent event) throws IOException {
		this.modalitaAperturaLibro = Libreria;
		this.openFXML(CercaLibro);
	}
	
	public void apriLibroLibreria(ActionEvent event) throws IOException {
		int index = this.ListViewLibriLibreria.getSelectionModel().getSelectedIndex();
		if(index <0) {
			warning("Errore","Seleziona un Libro.");
		}else {
			this.currentLibro = this.currentListaLibri.get(index);
			this.openFXML(InfoLibro);
		}
	}
	
	// FUNZIONI - NomeLibreria
	
	public void ConfermaCreaLibreria(ActionEvent event) {
		String nomeLib = this.NomeSceltoLibreria.getText();
		Libreria check = this.currentProxy.creaLibreria(this.currentUserID, nomeLib);
		if(check==null) {
			warning("Errore","Errore generico SQL.");
		}else {
			warning("Notifica","Libreria creata con successo.");
			this.closeFXML(event);
		}
	}
	
	// FUNZIONI - LoginDatabase
	
	public void ConfermaLoginDatabase(ActionEvent event) {
		String host = this.Login_UserID.getText();
		String pass = this.Login_Password.getText();
		
		boolean check = this.currentDB.connect(host, pass);
		if(check) {
			warning("Notifica","Connessione al Database avvenuta con successo");
			this.closeFXML(event);
			serverBR.startConnections();
		}else {
			warning("Errore","Errore di connessione. (Credenziali sbagliate...)");
		}
	}
	
	// FUNZIONI - Generali
	
	public void exit(ActionEvent event) throws IOException {
		String tmpFXML = this.currentFXML;
		String tmpUSERID = this.currentUserID;
		this.closeFXML(event);
		String nextFXML = getPreviousFXML();
		System.out.println(nextFXML);
		if(nextFXML!=null) {
			this.openFXML(nextFXML);
		}else {
			currentFXML = (tmpUSERID!=null?"MenuLogged.fxml":"MenuGuest.fxml");
		}
	}
}
