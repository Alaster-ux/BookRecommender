package version0;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.LinkedList;

public class ServerSlave extends Thread {
	
	Socket client;
	ObjectInputStream input;
	ObjectOutputStream output;
	DatabaseManager manager;
	
	public ServerSlave(Socket client, DatabaseManager manager) {
		// TODO Auto-generated constructor stub
		try {
			this.client = client;
			this.manager = manager;
			System.out.println("ServerSlave -> "+manager);
			input = new ObjectInputStream(client.getInputStream());
			output = new ObjectOutputStream(client.getOutputStream());
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		String command = "";
		try {
			
			command = (String) input.readObject();
			while(!command.equals("end")) {
				if(command.equals("registrazione")) {
					/*Questo sara' il bottone riservato alla registrazione.
					 * Si dovrebbe avere il modo per il quale le stringhe dopo saranno riempite
					 * dall'input fatto dall'utente con UI
					 * 
					 * USERID, PASSWORD, EMAIL, CODICE FISCALE, NOME, COGNOME
					 */
					
					// QUESTE STRINGHE DEVONO ESSERE CATTURATE DA UTENTE NELL'UI
					// DEVONO ESSERE SVILUPPATE NEL SERVERPROXY
					// QUESTO E' IL LATO SERVER, UI IMPLEMENTATO NEL LATO CLIENT!
					String nome = (String) input.readObject();
					String cognome = (String) input.readObject();
					String email = (String) input.readObject();
					String codiceFiscale = (String) input.readObject();
					String userID = (String) input.readObject();
					String password = (String) input.readObject();
					
					//bisogna controllare che tutto sia apposto. 
					//return -1; userID non disponibile
					//return -2; codiceFiscale errato
					//return -3; -1 e -2
					//return -4; nome, cognome o email troppo lungo. (bho?) | errore generico SQL
					//return  0; tutto apposto, utente registrato
					
					int errorCode = 0;
					userID = userID.toLowerCase();
					
					if(!Utilities.checkCodiceFiscale(codiceFiscale)) {errorCode = -2;}
					if(!manager.checkUserID(userID)) {
						if(errorCode==-2) errorCode=-3;
						errorCode=-1;
					}
					
					if(errorCode==0) {
						//si puo procedere all'aggiunta al DB
						boolean check = manager.addUser(userID, password, email, nome, cognome, codiceFiscale);
						if(!check) errorCode = -4;
					}
					
					output.writeObject(errorCode);
				}
				if(command.equals("login")) {
					// FARE UI QUI
					// PER PRENDERE L'USERID e Password
					
					String userID = (String) input.readObject();
					String password = (String) input.readObject();
					
					userID = userID.toLowerCase();
					// ritorna il valore userID se e true, se la pass e giusta e userid esiste
					// altrimenti NULL. bisogna notificare al cliente che non esiste userid o sbagliata password
					boolean check = manager.checkUser(userID, password);
					String ret=null;
					if(check) {
						ret = userID;
					}
					output.writeObject(ret);
				}
				if(command.equals("cercaTitolo")) {
					String titolo = (String)input.readObject();
					titolo = titolo.toLowerCase();
					LinkedList<Libro> lista = manager.cercaTitolo(titolo);
					output.writeObject(lista);
				}
				if(command.equals("getLibreriaByID")) {
					int ID = (Integer)input.readObject();
					Libreria lista = manager.getLibreriaByID(ID);
					output.writeObject(lista);
				}
				if(command.equals("getLibroByID")) {
					int ID = (Integer)input.readObject();
					Libro lista = manager.createLibroByID(ID);
					output.writeObject(lista);
				}
				if(command.equals("cercaAutore")) {
					String autore = (String)input.readObject();
					autore = autore.toLowerCase();
					LinkedList<Libro> lista = manager.cercaAutore(autore);
					output.writeObject(lista);
				}
				if(command.equals("cercaAutoreAnno")) {
					String autore = (String)input.readObject();
					int anno = (Integer) input.readObject();
					autore = autore.toLowerCase();
					LinkedList<Libro> lista = manager.cercaAutoreAnno(autore, anno);
					output.writeObject(lista);
				}
				if(command.equals("valutazione")) {
					int matricolaLibro = (Integer) input.readObject();
					String userID = (String) input.readObject();
					int v1 = (Integer) input.readObject();
					int v2 = (Integer) input.readObject();
					int v3 = (Integer) input.readObject();
					int v4 = (Integer) input.readObject();
					int v5 = (Integer) input.readObject();
					String n1 = (String) input.readObject();
					String n2 = (String) input.readObject();
					String n3 = (String) input.readObject();
					String n4 = (String) input.readObject();
					String n5 = (String) input.readObject();
					int check = manager.inserisciValutazioneLibro(matricolaLibro, userID, v1, v2, v3, v4, v5, n1, n2, n3, n4, n5);
					output.writeObject(check);
				}
				if(command.equals("aggiungiCommento")) {
					int matricolaLibro = (Integer) input.readObject();
					String userID = (String) input.readObject();
					String commento = (String) input.readObject();
					int check = manager.aggiungiCommento(userID, matricolaLibro, commento);
					output.writeObject(check);
				}
				if(command.equals("creaLibreria")) {
					String nomeLibreria = (String) input.readObject();
					String userID = (String) input.readObject();
					Libreria check = manager.creaLibreria(nomeLibreria, userID);
					output.writeObject(check);
				}
				if(command.equals("aggiungiLibro")) {
					int id_libreria = (Integer) input.readObject();
					int id_libro = (Integer) input.readObject();
					int check = manager.aggiungiLibro(id_libreria, id_libro);
					output.writeObject(check);
				}
				if(command.equals("aggiungiSuggerimento")) {
					String userID = (String) input.readObject();
					int id_original = (Integer) input.readObject();
					int id_suggerito =(Integer) input.readObject();
					int check = manager.aggiungiSuggerimentoLibro(userID, id_original, id_suggerito);
					output.writeObject(check);
				}
				if(command.equals("getLibreria")) {
					// prende tutte le libreire utente e le inizializza
					String userID = (String) input.readObject();
					LinkedList<Libreria> lista = manager.getLibrerieUtente(userID);
					output.writeObject(lista);
					
				}
				if(command.equals("visualizzaLibro")) {
					// copia le specifiche 
					Libro l = (Libro) input.readObject();
					ProspettoLibro prosp = manager.getInfoLibro(l);
					output.writeObject(prosp);
				}
				command = (String) input.readObject();
			}
			output.flush();
			output.close();
			input.close();
			client.close();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * - Parla con il client, alla fine e' lui che si interfaccia all'eventuale DatabaseManager, 
	 * riceve segnali dal proxy
	 * - Riceve input da client
	 * - Esegue 
	 * - Ritorna esito
	 * 
	 */

}
