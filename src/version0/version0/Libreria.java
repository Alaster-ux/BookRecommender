package version0;

import java.io.Serializable;
import java.util.LinkedList;

// per usare su client

public class Libreria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int matricola;
	String userID;
	String nome;
	LinkedList<Libro> lista;
	
	public Libreria(int matricola, String userID, String nome) {
		this.matricola = matricola;
		this.userID = userID;
		this.nome = nome;
		this.lista = new LinkedList<Libro>();
	}
	
	public Libreria(int matricola, String userID, String nome, LinkedList<Libro> lista) {
		this.matricola = matricola;
		this.userID = userID;
		this.lista = lista;
		this.nome = nome;
	}

	/**
	 * @return the matricola
	 */
	public int getMatricola() {
		return matricola;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the lista
	 */
	public LinkedList<Libro> getLista() {
		return lista;
	}
	
}
