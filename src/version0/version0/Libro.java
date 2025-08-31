package version0;

import java.io.Serializable;

public class Libro implements Serializable {
	int matricola;
	String titolo;
	String autore;
	int anno;
	String editore;
	String categoria;
	public int getMatricola() {
		return matricola;
	}
	public String getTitolo() {
		return titolo;
	}
	public String getAutore() {
		return autore;
	}
	public int getAnno() {
		return anno;
	}
	public String getEditore() {
		return editore;
	}
	public String getCategoria() {
		return categoria;
	}
	public Libro(int matricola, String titolo, String autore, int anno, String editore, String categoria) {
		this.matricola=matricola;
		this.titolo=titolo;
		this.autore=autore;
		this.anno=anno;
		this.editore=editore;
		this.categoria=categoria;
	}
	
	public String toString() {
		return titolo+", "+autore+", "+anno;
	}
	
	// La classe libro così da usare il Libro come oggetto (per interazione client-Server)
	
	
}
