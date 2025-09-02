package version0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Gestione della connessione e operazioni sul database della piattaforma BookRecommender.
 * Fornisce metodi per aggiungere utenti, libri, librerie, commenti, suggerimenti e valutazioni.
 * Tutti i metodi che ritornano oggetti {@link Libreria}, {@link Libro} o {@link ProspettoLibro}
 * contengono link cliccabili alla rispettiva documentazione.
 */
public class DatabaseManager {
	Connection conn;
	
	
	
	/**
     * Costruttore che permette la connessione al database mediante credenziali.
     * Notifica mediante stampa su console la riuscita della connessione.
     * @param user nome utente del database
     * @param password password dell'utente del database
     */
	private DatabaseManager() {
		
	}
	
	private static DatabaseManager instance = new DatabaseManager();


    public static DatabaseManager getInstance() {
        return instance;
    }
	
	public boolean connect(String user, String password) {
		String url = "jdbc:postgresql://localhost:5432/bookrecommender";

	       try {
	           conn = DriverManager.getConnection(url, user, password);
	           System.out.println("✅ Connessione al DB riuscita!");
	           return true;
	       } catch (SQLException e) {
	           System.out.println("❌ Errore di connessione: " + e.getMessage());
	           return false;
	       }
	}
	
	 /**
     * Controlla se esiste un utente con lo stesso {@code userID} e password.
     * @param userID identificativo univoco dell'utente
     * @param password password dell'utente
     * @return true se l'utente esiste e la password corrisponde, false altrimenti
     */
	public synchronized boolean checkUser(String userID, String password) {
	    String sql = "SELECT chiave FROM utentiregistrati WHERE userID = ?";
	    try (PreparedStatement ps = conn.prepareStatement(sql)) { // chiude automaticamente
	        ps.setString(1, userID);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return password.equals(rs.getString("chiave"));
	            } else {
	                return false; // utente non trovato
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	 /**
     * Aggiunge un nuovo utente al database.
     * @param userID identificativo univoco dell'utente
     * @param pass password dell'utente
     * @param email indirizzo email dell'utente
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente
     * @param cf codice fiscale dell'utente
     * @return true se l'inserimento è avvenuto con successo, false altrimenti
     */
	public synchronized boolean addUser(String userID, String pass, String email, String nome, String cognome, String cf) {
		String sql = "INSERT INTO utentiregistrati (indirizzo, cf, userid, nome, cognome, chiave) VALUES (?, ?, ?, ?, ?, ?)";
	    
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, email);
	        ps.setString(2, cf);
	        ps.setString(3, userID);
	        ps.setString(4, nome);
	        ps.setString(5, cognome);
	        ps.setString(6, pass);
	        
	        int righe = ps.executeUpdate();  // ritorna quante righe sono state inserite
	        ps.close();
	        
	        return righe > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	 /**
     * Aggiunge un suggerimento di libro per un utente.
     * @param userID identificativo dell'utente
     * @param libro_original ID del libro originale
     * @param libro_consigliato ID del libro suggerito
     * @return 0 se tutto è andato bene, -1 altrimenti
     */
	public synchronized int aggiungiSuggerimentoLibro(String userID, int libro_original, int libro_consigliato) {
		//return 0 tutto apposto, -1 se no
		String sql = "INSERT INTO consiglilibri (id_utente,id_libro_originale,id_libro_suggerito) VALUES (?, ?, ?)";
	    
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, userID);
	        ps.setInt(2, libro_original);
	        ps.setInt(3, libro_consigliato);
	        int righe = ps.executeUpdate();  // ritorna quante righe sono state inserite
	        ps.close();
	        if(righe>0) return 0;
	        else return -1;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // troppi libri
	        return -1;
	    }
	}
	
	/**
     * Aggiunge un commento su un libro.
     * @param userID identificativo dell'utente
     * @param id_libro ID del libro
     * @param commento testo del commento
     * @return 0 se tutto è andato bene, -1 altrimenti
     */
	public synchronized int aggiungiCommento(String userID, int id_libro, String commento) {
		//return 0 tutto apposto, -1 se no
				String sql = "INSERT INTO commento (nota,userid,matricola_libro) VALUES (?, ?, ?)";
			    
			    try (PreparedStatement ps = conn.prepareStatement(sql)) {
			        ps.setString(1, commento);
			        ps.setString(2, userID);
			        ps.setInt(3, id_libro);
			        
			        int righe = ps.executeUpdate();  // ritorna quante righe sono state inserite
			        ps.close();
			        if(righe>0) return 0;
			        else return -1;
			    } catch (SQLException e) {
			        e.printStackTrace();
			        // troppi libri
			        return -1;
			    }
	}
	
	 /**
     * Aggiunge un libro a una libreria di un utente.
     * @param id_libreria ID della libreria
     * @param id_libro ID del libro
     * @return 0 se tutto è andato bene, -1 altrimenti
     */
	public synchronized int aggiungiLibro(int id_libreria, int id_libro) {
		String sql = "INSERT INTO lista (id_libreria, id_libro) VALUES (?, ?)";
	    
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, id_libreria);
	        ps.setInt(2, id_libro);
	        
	        int righe = ps.executeUpdate();  // ritorna quante righe sono state inserite
	        ps.close();
	        if(righe>0) return 0;
	        else return -1;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // libro gia presente
	        return -1;
	    }
	}
	
	 /**
     * Restituisce la matricola (ID) di una libreria data il nome e l'utente proprietario.
     * @param nomeLibreria nome della libreria
     * @param userID identificativo dell'utente proprietario
     * @return ID della libreria se trovato, -1 altrimenti
     */
	public synchronized int getMatricolaLibreria(String nomeLibreria, String userID) {
		String sql = "SELECT id_libreria FROM librerie WHERE nome_libreria = ? AND userid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			int matr = 0;
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				matr = rs.getInt("id_libreria");
			}
			ps.close();
			rs.close();
			return matr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
     * Crea una nuova libreria per un utente e la salva nel database.
     * @param nomeLibreria nome della libreria
     * @param userID identificativo dell'utente proprietario
     * @return un oggetto {@link Libreria} se la creazione ha successo, null altrimenti
     */
	public synchronized Libreria creaLibreria(String nomeLibreria, String userID) {
		String sql = "INSERT INTO librerie (nome_libreria, userid) VALUES (?, ?)";
	    
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, nomeLibreria);
	        ps.setString(2, userID);
	        int matricola = -1;
	        int righe = ps.executeUpdate();  // ritorna quante righe sono state inserite
	        ps.close();
	        if(righe>0) { 

	        	// GET MATRICOLA
	        	matricola = getMatricolaLibreria(nomeLibreria, userID);
	        	return new Libreria(matricola,userID, nomeLibreria);
	        }
	        else return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // libro gia presente
	        return null;
	    }
	}
	
	 /**
     * Crea un oggetto {@link Libro} a partire dal suo ID univoco.
     * @param id_libro ID del libro
     * @return un oggetto {@link Libro} se trovato, null altrimenti
     */
	public synchronized Libro createLibroByID(int id_libro) {
		String sql = "SELECT * FROM libri WHERE matricola = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			Libro l = null;
			ps.setInt(1, id_libro);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				String titolo = rs.getString("titolo");
				String autore = rs.getString("autore");
				int anno = rs.getInt("anno");
				String editore = rs.getString("editore");
				String categoria = rs.getString("categoria");
				l = new Libro(id_libro, titolo, autore, anno, editore, categoria);
				
			}
			ps.close();
			rs.close();
			return l;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	  /**
     * Restituisce tutti i libri contenuti in una libreria.
     * @param id_libreria ID della libreria
     * @return una {@link LinkedList} di {@link Libro} contenente tutti i libri associati
     */
	public synchronized LinkedList<Libro> initListaLibro(int id_libreria){
		String sql = "SELECT id_libro FROM lista WHERE id_libreria = ?";
		try {
			LinkedList<Libro> lista = new LinkedList<Libro>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id_libreria);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Libro l = createLibroByID(rs.getInt("id_libro"));
				lista.add(l);
				System.out.println("Aggiunto "+l);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	/**
     * Restituisce tutte le librerie associate a un utente.
     * @param userID identificativo dell'utente
     * @return una {@link LinkedList} di {@link Libreria} contenente tutte le librerie dell'utente
     */
	public synchronized LinkedList<Libreria> getLibrerieUtente(String userID){
		String sql = "SELECT id_libreria, nome_libreria FROM librerie WHERE userid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			LinkedList<Libreria> listaLibreria = new LinkedList<Libreria>();
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			Libreria l = null;
			while(rs.next()) {
				int id_libreria = rs.getInt("id_libreria");
				String nome_libreria = rs.getString("nome_libreria");
				LinkedList<Libro> lista = initListaLibro(id_libreria);
				l = new Libreria(id_libreria, userID, nome_libreria, lista);
				listaLibreria.add(l);
			}
			ps.close();
			rs.close();
			return listaLibreria;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * Controlla se un userID è già presente nel database.
     * @param userID identificativo dell'utente
     * @return true se l'userID non esiste, false se esiste già
     */
	public synchronized boolean checkUserID(String userID) {
		String sql = "SELECT * FROM utentiregistrati WHERE userID = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			boolean check = !rs.next();
			return check;
			// ritorna true se non esiste nessun'altro userID uguale
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
     * Cerca libri per titolo.
     * @param titolo titolo da cercare
     * @return una {@link LinkedList} di {@link Libro} con i libri trovati
     */
	public synchronized LinkedList<Libro> cercaTitolo(String titolo){
		String sql = "SELECT * FROM libri WHERE titolo ILIKE ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, titolo+"%");
			ResultSet rs = ps.executeQuery();

			Libro cur;
			LinkedList<Libro> lista = new LinkedList<Libro>();
			while(rs.next()) {
				cur = new Libro(rs.getInt("matricola"),rs.getString("titolo"),rs.getString("autore"),rs.getInt("anno"),rs.getString("editore"),rs.getString("categoria"));
				lista.add(cur);
			}
			ps.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	 /**
     * Cerca libri per autore.
     * @param autore autore da cercare
     * @return una {@link LinkedList} di {@link Libro} con i libri trovati
     */
	public synchronized LinkedList<Libro> cercaAutore(String autore){
		String sql = "SELECT * FROM libri WHERE autore ILIKE ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, autore+"%");
			ResultSet rs = ps.executeQuery();
			Libro cur;
			LinkedList<Libro> lista = new LinkedList<Libro>();
			while(rs.next()) {
				cur = new Libro(rs.getInt("matricola"),rs.getString("titolo"),rs.getString("autore"),rs.getInt("anno"),rs.getString("editore"),rs.getString("categoria"));
				lista.add(cur);
			}
			ps.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
     * Cerca libri per autore e anno.
     * @param autore autore da cercare
     * @param anno anno di pubblicazione
     * @return una {@link LinkedList} di {@link Libro} con i libri trovati
     */
	public synchronized LinkedList<Libro> cercaAutoreAnno(String autore, int anno){
		String sql = "SELECT * FROM libri WHERE autore ILIKE ? AND anno = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, autore+"%");
			ps.setInt(2, anno);
			ResultSet rs = ps.executeQuery();

			Libro cur;
			LinkedList<Libro> lista = new LinkedList<Libro>();
			while(rs.next()) {
				cur = new Libro(rs.getInt("matricola"),rs.getString("titolo"),rs.getString("autore"),rs.getInt("anno"),rs.getString("editore"),rs.getString("categoria"));
				lista.add(cur);
			}
			ps.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * Restituisce l'ID massimo delle valutazioni esistenti nel database.
     * @return ID massimo, -1 se errore
     */
	
	public synchronized int getMaxMatricola() {
		String sql = "SELECT MAX(id_valutazione) as maxMatricola FROM valutazionilibri";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Libro cur;
			LinkedList<Libro> lista = new LinkedList<Libro>();
			int max = 0;
			while(rs.next()) {
				max = rs.getInt("maxMatricola");
			}
			ps.close();
			return max;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	
	 /**
     * Inserisce o aggiorna la valutazione di un libro da parte di un utente.
     * @param matricolaLibro ID del libro
     * @param userID ID dell'utente
     * @param stile voto stile
     * @param contenuto voto contenuto
     * @param gradevolezza voto gradevolezza
     * @param originalita voto originalità
     * @param edizione voto edizione
     * @param nota_s note sullo stile
     * @param nota_c note sul contenuto
     * @param nota_g note sulla gradevolezza
     * @param nota_o note sull'originalità
     * @param nota_e note sull'edizione
     * @return 1 se inserita, 2 se aggiornata, 0 se errore
     */
	public synchronized int inserisciValutazioneLibro(int matricolaLibro, String userID, int stile, int contenuto, int gradevolezza, int originalita, int edizione, String nota_s, String nota_c, String nota_g, String nota_o, String nota_e) {
		/*int matricola = getMaxMatricola()+1;
		System.out.println("Matricola generata: " + matricola);
		String sql = "insert into valutazionilibri(id_valutazione, userid, libro_id, stile, contenuto, gradevolezza, originalita, edizione, nota_stile, nota_contenuto, nota_gradevolezza, nota_originalita, nota_edizione) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, matricola);
			ps.setString(2, userID);
			ps.setInt(3, matricolaLibro);
			ps.setInt(4, stile);
			ps.setInt(5, contenuto);
			ps.setInt(6, gradevolezza);
			ps.setInt(7, originalita);
			ps.setInt(8, edizione);
			ps.setString(9, nota_s);
			ps.setString(10, nota_c);
			ps.setString(11, nota_g);
			ps.setString(12, nota_o);
			ps.setString(13, nota_e);
			int righe = ps.executeUpdate();
			ps.close();
			return (righe > 0) ? 1 : 0;      // 1 se successo, 0 se no
		} catch (SQLException e) {
			
			return 0;
		}*/
		
		 try {
		        // 1. Verifica se esiste già
		        String checkSql = "SELECT id_valutazione FROM valutazionilibri WHERE userid = ? AND libro_id = ?";
		        PreparedStatement checkPs = conn.prepareStatement(checkSql);
		        checkPs.setString(1, userID);
		        checkPs.setInt(2, matricolaLibro);
		        ResultSet rs = checkPs.executeQuery();

		        if (rs.next()) {
		            // Esiste già: esegui UPDATE
		            int existingId = rs.getInt("id_valutazione");
		            String updateSql = """
		                UPDATE valutazionilibri SET
		                    stile = ?, contenuto = ?, gradevolezza = ?, originalita = ?, edizione = ?,
		                    nota_stile = ?, nota_contenuto = ?, nota_gradevolezza = ?, nota_originalita = ?, nota_edizione = ?
		                WHERE id_valutazione = ?
		            """;
		            PreparedStatement updatePs = conn.prepareStatement(updateSql);
		            updatePs.setInt(1, stile);
		            updatePs.setInt(2, contenuto);
		            updatePs.setInt(3, gradevolezza);
		            updatePs.setInt(4, originalita);
		            updatePs.setInt(5, edizione);
		            updatePs.setString(6, nota_s);
		            updatePs.setString(7, nota_c);
		            updatePs.setString(8, nota_g);
		            updatePs.setString(9, nota_o);
		            updatePs.setString(10, nota_e);
		            updatePs.setInt(11, existingId);
		            int updated = updatePs.executeUpdate();
		            updatePs.close();
		            return (updated > 0) ? 2 : 0; // 2 = aggiornato
		        } else {
		            // Non esiste: esegui INSERT
		            int newMatricola = getMaxMatricola() + 1;
		            String insertSql = """
		                INSERT INTO valutazionilibri(
		                    id_valutazione, userid, libro_id, stile, contenuto, gradevolezza, originalita, edizione,
		                    nota_stile, nota_contenuto, nota_gradevolezza, nota_originalita, nota_edizione
		                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
		            """;
		            PreparedStatement insertPs = conn.prepareStatement(insertSql);
		            insertPs.setInt(1, newMatricola);
		            insertPs.setString(2, userID);
		            insertPs.setInt(3, matricolaLibro);
		            insertPs.setInt(4, stile);
		            insertPs.setInt(5, contenuto);
		            insertPs.setInt(6, gradevolezza);
		            insertPs.setInt(7, originalita);
		            insertPs.setInt(8, edizione);
		            insertPs.setString(9, nota_s);
		            insertPs.setString(10, nota_c);
		            insertPs.setString(11, nota_g);
		            insertPs.setString(12, nota_o);
		            insertPs.setString(13, nota_e);
		            int inserted = insertPs.executeUpdate();
		            insertPs.close();
		            return (inserted > 0) ? 1 : 0; // 1 = inserito
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return 0;
		    }
	}
	
	 /**
     * Restituisce tutte le informazioni relative a un libro, incluse valutazioni, commenti e suggerimenti.
     * @param libro oggetto {@link Libro} di riferimento
     * @return oggetto {@link ProspettoLibro} con tutte le informazioni, null se errore
     */
	public synchronized ProspettoLibro getInfoLibro(Libro libro) {
		/*
		 * VALUTAZIONE ;
		 * TUTTE MEDIE, CON NUMERO DI VALUTAZIONI PER LIBRO.
		 * 
		 * COMMENTI ;
		 * LISTA DI STRINGE @<userID>: "..."
		 * 
		 * SUGGERIMENTI ;
		 * LISTA DI SuggerimentoLibro.java
		 * 
		 * CREAZIONE DI OGGETTO PROSPETTOLIBRO
		 */
		
		int id_libro = libro.getMatricola();
		
		String sql = "SELECT AVG(stile) as media_s, AVG(contenuto) as media_c, AVG(gradevolezza) as media_g,AVG(originalita) as media_o,AVG(edizione) as media_e, AVG(voto_finale) as media_vf, COUNT(userid) as conta from valutazionilibri where libro_id = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_libro);
			ResultSet rs = ps.executeQuery();
			int stile = -1;
			int contenuto = -1;
			int gradevolezza = -1;
			int originalita = -1;
			int edizione = -1;
			int voto_finale = -1;
			int count = 0;
			while(rs.next()) {
				stile = rs.getInt("media_s");
				contenuto = rs.getInt("media_c");
				gradevolezza = rs.getInt("media_g");
				originalita = rs.getInt("media_o");
				edizione = rs.getInt("media_e");
				voto_finale = rs.getInt("media_vf");
				count = rs.getInt("conta");
			}
			ps.close();
			rs.close();
			
			sql = "SELECT id_valutazione, nota_stile, nota_contenuto, nota_gradevolezza, nota_originalita, nota_edizione FROM valutazionilibri WHERE libro_id = ? and id_valutazione = (SELECT MAX(id_valutazione) from valutazionilibri where libro_id = ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_libro);
			ps.setInt(2, id_libro);
			rs = ps.executeQuery();
			String nota_s="",nota_c="",nota_g="",nota_o="",nota_e="";
			while(rs.next()) {
				nota_s = rs.getString("nota_stile");
				nota_c = rs.getString("nota_contenuto");
				nota_g = rs.getString("nota_gradevolezza");
				nota_o = rs.getString("nota_originalita");
				nota_e = rs.getString("nota_edizione");
			}
			rs.close();
			ps.close();
			
			// seleziona commenti
			sql = "SELECT userid, nota from commento where matricola_libro = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_libro);
			rs = ps.executeQuery();
			LinkedList<String> listaComm = new LinkedList<String>();
			while(rs.next()) {
				String userID = rs.getString("userid");
				String nota = rs.getString("nota");
				listaComm.add("@"+userID+": "+nota+"\n");
			}
			rs.close();
			ps.close();
			
			// seleziona suggerimenti
			
			sql = "SELECT id_libro_suggerito, COUNT(*) as conta from consiglilibri where id_libro_originale = ? group by id_libro_suggerito order by conta desc limit 10";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_libro);
			rs = ps.executeQuery();
			LinkedList<SuggerimentoLibro> listaSug = new LinkedList<SuggerimentoLibro>();
			while(rs.next()) {
				Libro l = createLibroByID(rs.getInt("id_libro_suggerito"));
				int countSug = rs.getInt("conta");
				listaSug.add(new SuggerimentoLibro(l,countSug));
			}
			rs.close();
			ps.close();
			
			ProspettoLibro prospetto = new ProspettoLibro(libro, stile, contenuto, gradevolezza, originalita, edizione, voto_finale, nota_s,nota_c,nota_g,nota_o,nota_e,listaComm,listaSug,count);
			return prospetto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * Chiude la connessione al database.
     */
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 /**
     * Restituisce una libreria a partire dal suo ID.
     * @param id_libreria ID della libreria
     * @return oggetto {@link Libreria} se trovato, null altrimenti
     */
	public synchronized Libreria getLibreriaByID(int id_libreria) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM librerie WHERE id_libreria = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			Libreria l = null;
			ps.setInt(1, id_libreria);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String nome_libreria = rs.getString("nome_libreria");
				String userID = rs.getString("userid");
				LinkedList<Libro> lista = initListaLibro(id_libreria);
				l = new Libreria(id_libreria, userID, nome_libreria, lista);
			}
			return l;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
