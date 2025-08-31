package version0;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;

public class ServerProxy {
	
	/* 
	 * E un insieme di comandi e si connette al server in modo silenzioso
	 */
	
	final int port = serverBR.PORT;
	Socket connection;
	ObjectInputStream input;
	ObjectOutputStream output;
	boolean connected = false;
	
	/* kuesto è il proxy non toccate diamine */
	public ServerProxy() {
		try {
			connection = new Socket(InetAddress.getByName(null),port);
			output = new ObjectOutputStream(connection.getOutputStream());
			input = new ObjectInputStream(connection.getInputStream());
			connected=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	@SuppressWarnings("finally")
	public int registrazione(String userID, String chiave, String nome, String cognome,String codiceFiscale,String email) {
		try {
			if(userID.trim().equals("") || chiave.trim().equals("")||nome.trim().equals("")||cognome.trim().equals("")||codiceFiscale.trim().equals("")||email.trim().equals("")) {
				return -4;
			}
			
			output.writeObject("registrazione");
			/*String nome = (String) input.readObject();
					String cognome = (String) input.readObject();
					String email = (String) input.readObject();
					String codiceFiscale = (String) input.readObject();
					String userID = (String) input.readObject();
					String password = (String) input.readObject();*/
			output.writeObject(nome);
			output.writeObject(cognome);
			output.writeObject(email);
			output.writeObject(codiceFiscale);
			output.writeObject(userID);
			output.writeObject(chiave);
			
			
			int code = (Integer) input.readObject();
			return code;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return -4;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return -4;
		}
	}
	
	@SuppressWarnings("finally")
	public String login(String userID, String chiave) {
		try {
			output.writeObject("login");
			output.writeObject(userID);
			output.writeObject(chiave);
			String result = (String) input.readObject();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public LinkedList<Libro> cercaTitolo(String titolo){
		
		try {
			output.writeObject("cercaTitolo");
			output.writeObject(titolo);
			LinkedList<Libro> lista = (LinkedList<Libro>) input.readObject();
			return lista;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public Libro getLibroByID(int ID) {
		try {
			output.writeObject("getLibroByID");
			output.writeObject(ID);
			@SuppressWarnings("unchecked")
			Libro lista = (Libro) input.readObject();
			return lista;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public Libreria getLibreriaByID(int ID) {
		try {
			output.writeObject("getLibreriaByID");
			output.writeObject(ID);
			@SuppressWarnings("unchecked")
			Libreria lista = (Libreria) input.readObject();
			return lista;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	@SuppressWarnings("finally")
	public LinkedList<Libro> cercaAutoreAnno(String autore, int anno){
		
		try {
			output.writeObject("cercaAutoreAnno");
			output.writeObject(autore);
			output.writeObject(anno);
			@SuppressWarnings("unchecked")
			LinkedList<Libro> lista = (LinkedList<Libro>) input.readObject();
			return lista;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public LinkedList<Libro> cercaAutore(String autore){
		
		try {
			output.writeObject("cercaAutore");
			output.writeObject(autore);
			LinkedList<Libro> lista = (LinkedList<Libro>) input.readObject();
			return lista;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	@SuppressWarnings("finally")
	public int inserisciValutazioneLibro(Libro l,String userID, int stile, int contenuto, int gradevolezza, int originalita, int edizione, String nota_s,String nota_c,String nota_g, String nota_o,String nota_e) {
		try {
			output.writeObject("valutazione");
			output.writeObject(l.getMatricola());
			output.writeObject(userID);
			output.writeObject(stile);
			output.writeObject(contenuto);
			output.writeObject(gradevolezza);
			output.writeObject(originalita);
			output.writeObject(edizione);
			output.writeObject(nota_s);
			output.writeObject(nota_c);
			output.writeObject(nota_g);
			output.writeObject(nota_o);
			output.writeObject(nota_e);
			int result = (Integer) input.readObject();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	@SuppressWarnings("finally")
	public int aggiungiCommento(String userID, Libro libro, String nota) {
		try {
			output.writeObject("aggiungiCommento");
			
			output.writeObject(libro.getMatricola());
			output.writeObject(userID);
			output.writeObject(nota);
			int result = (Integer) input.readObject();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return -1;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return -1;

		}
	}
	
	@SuppressWarnings("finally")
	public Libreria creaLibreria(String userID, String nomeLibreria) {
		try {
			output.writeObject("creaLibreria");
			
			output.writeObject(nomeLibreria);
			output.writeObject(userID);
			Libreria check = (Libreria) input.readObject();
			return check;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("finally")
	public int aggiungiSuggerimentoLibro(String userID, Libro libro_o, Libro libro_s) {
		try {
			output.writeObject("aggiungiSuggerimento");
			output.writeObject(userID);
			output.writeObject(libro_o.getMatricola());
			output.writeObject(libro_s.getMatricola());
			int check = (Integer) input.readObject();
			return check;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return -1;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return -1;
		}
	}
	
	@SuppressWarnings("finally")
	public int aggiungiLibroLibreria(Libreria libreria, Libro libro) {
		try {
			output.writeObject("aggiungiLibro");
			output.writeObject(libreria.getMatricola());
			output.writeObject(libro.getMatricola());
			int check = (Integer) input.readObject();
			return check;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return -1;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return -1;
		}
	}
	
	@SuppressWarnings("finally")
	public LinkedList<Libreria> getLibrerieUtente(String userID){
		
		//SE E NULL, ERRORE, SE E VUOTA, NO LIBRERIE UTENTE
		try {
			output.writeObject("getLibreria");
			output.writeObject(userID);
			@SuppressWarnings("unchecked")
			LinkedList<Libreria> listaLib = (LinkedList<Libreria>) input.readObject();
			return listaLib;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void closeConnection() {
		try {
			output.writeObject("end");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public ProspettoLibro visualizzaLibro(Libro l) {
		try {
			output.writeObject("visualizzaLibro");
			output.writeObject(l);
			ProspettoLibro prospetto = (ProspettoLibro) input.readObject();
			return prospetto;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
