package version0;

import java.io.Serializable;
import java.util.LinkedList;

public class ProspettoLibro implements Serializable {
	
	//CLASSE PER VALUTAZIONE, COMMENTI 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int stile;
	int contenuto;
	int gradevolezza;
	int originalita;
	int edizione;
	int voto_finale;
	String nota_s;
	String nota_c;
	String nota_g;
	String nota_o;
	String nota_e;
	
	Libro libro;
	
	LinkedList<String> commenti;
	
	LinkedList<SuggerimentoLibro> suggerimenti;
	
	int numero_val=0;
	
	public ProspettoLibro(Libro libro, int stile, int contenuto, int gradevolezza, int originalita, int edizione, int voto_finale,String nota_s,String nota_c,String nota_g,String nota_o,String nota_e,LinkedList<String> commenti, LinkedList<SuggerimentoLibro> suggerimenti, int numero_val) {
		this.libro = libro;
		this.stile = stile;
		this.contenuto = contenuto;
		this.gradevolezza = gradevolezza;
		this.originalita = originalita;
		this.edizione = edizione;
		this.nota_s = nota_s;
		this.nota_c = nota_c;
		this.nota_g = nota_g;
		this.nota_o = nota_o;
		this.nota_e = nota_e;
		this.commenti = commenti;
		this.suggerimenti = suggerimenti;
		this.numero_val = numero_val;
		this.voto_finale=voto_finale;
	}

	public ProspettoLibro() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the stile
	 */
	public int getStile() {
		return stile;
	}

	/**
	 * @return the contenuto
	 */
	public int getContenuto() {
		return contenuto;
	}

	/**
	 * @return the gradevolezza
	 */
	public int getGradevolezza() {
		return gradevolezza;
	}

	/**
	 * @return the originalita
	 */
	public int getOriginalita() {
		return originalita;
	}

	/**
	 * @return the edizione
	 */
	public int getEdizione() {
		return edizione;
	}

	/**
	 * @return the voto_finale
	 */
	public int getVoto_finale() {
		return voto_finale;
	}

	/**
	 * @return the nota_s
	 */
	public String getNota_s() {
		return nota_s;
	}

	/**
	 * @return the nota_c
	 */
	public String getNota_c() {
		return nota_c;
	}

	/**
	 * @return the nota_g
	 */
	public String getNota_g() {
		return nota_g;
	}

	/**
	 * @return the nota_o
	 */
	public String getNota_o() {
		return nota_o;
	}

	/**
	 * @return the nota_e
	 */
	public String getNota_e() {
		return nota_e;
	}

	/**
	 * @return the libro
	 */
	public Libro getLibro() {
		return libro;
	}

	/**
	 * @return the commenti
	 */
	public LinkedList<String> getCommenti() {
		return commenti;
	}

	/**
	 * @return the suggerimenti
	 */
	public LinkedList<SuggerimentoLibro> getSuggerimenti() {
		return suggerimenti;
	}

	/**
	 * @return the numero_val
	 */
	public int getNumero_val() {
		return numero_val;
	}	
	
	public boolean checkVal() {
		return numero_val!=0;
	}
	
	public boolean checkSug() {
		return suggerimenti.size()!=0;
	}
	
	public boolean checkCommenti() {
		return commenti.size()!=0;
	}
}
