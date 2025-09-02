package version0;

import java.io.Serializable;

/**
 * Rappresenta un libro con informazioni base come titolo, autore, anno, editore e categoria.
 * Questa classe è serializzabile per consentire lo scambio di oggetti tra client e server.
 */
public class Libro implements Serializable {
	int matricola;
	String titolo;
	String autore;
	int anno;
	String editore;
	String categoria;
	
	 /** @return ID univoco del libro */
	public int getMatricola() {
		return matricola;
	}
	 /** @return Titolo del libro */
	public String getTitolo() {
		return titolo;
	}
	/** @return Autore del libro */
	public String getAutore() {
		return autore;
	}
	 /** @return Anno di pubblicazione del libro */
	public int getAnno() {
		return anno;
	}
	 /** @return Casa editrice del libro */
	public String getEditore() {
		return editore;
	}
	 /** @return Categoria del libro */
	public String getCategoria() {
		return categoria;
	}
	/**
     * Costruttore completo di un libro.
     * @param matricola ID univoco del libro
     * @param titolo Titolo del libro
     * @param autore Autore del libro
     * @param anno Anno di pubblicazione
     * @param editore Casa editrice
     * @param categoria Categoria del libro
     */
	public Libro(int matricola, String titolo, String autore, int anno, String editore, String categoria) {
		this.matricola=matricola;
		this.titolo=titolo;
		this.autore=autore;
		this.anno=anno;
		this.editore=editore;
		this.categoria=categoria;
	}
	
	/**
     * Rappresentazione testuale del libro.
     * @return stringa con titolo, autore e anno
     */
	public String toString() {
		return titolo+", "+autore+", "+anno;
	}
	
	// La classe libro così da usare il Libro come oggetto (per interazione client-Server)
	
	
}
