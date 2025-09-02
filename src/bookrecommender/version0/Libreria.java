package version0;

import java.io.Serializable;
import java.util.LinkedList;

// per usare su client

/**
 * Rappresenta una libreria personale di un utente.
 * Contiene informazioni base come ID, nome e lista di libri.
 */
public class Libreria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int matricola;
	String userID;
	String nome;
	LinkedList<Libro> lista;
	
	
	/**
     * Costruttore base senza lista di libri.
     * @param matricola ID univoco della libreria
     * @param userID Proprietario della libreria
     * @param nome Nome della libreria
     */
	public Libreria(int matricola, String userID, String nome) {
		this.matricola = matricola;
		this.userID = userID;
		this.nome = nome;
		this.lista = new LinkedList<Libro>();
	}
	
	/**
     * Costruttore completo con lista di libri.
     * @param matricola ID univoco della libreria
     * @param userID Proprietario della libreria
     * @param nome Nome della libreria
     * @param lista Lista di libri da inizializzare
     */
	public Libreria(int matricola, String userID, String nome, LinkedList<Libro> lista) {
		this.matricola = matricola;
		this.userID = userID;
		this.lista = lista;
		this.nome = nome;
	}

	/**
     * Restituisce l'ID della libreria.
     * @return int ID
     */
	public int getMatricola() {
		return matricola;
	}

	/**
     * Restituisce l'ID dell'utente proprietario.
     * @return String userID
     */
	public String getUserID() {
		return userID;
	}

	/**
     * Restituisce il nome della libreria.
     * @return String nome
     */
	public String getNome() {
		return nome;
	}

	/**
     * Restituisce la lista dei libri contenuti nella libreria.
     * @return LinkedList<Libro> lista dei libri
     */
	public LinkedList<Libro> getLista() {
		return lista;
	}
	
}
