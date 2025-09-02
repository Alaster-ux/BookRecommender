package version0;

import java.io.Serializable;
/**
 * Rappresenta un suggerimento di libro con il numero di volte in cui è stato consigliato.
 * Usato per mostrare consigli di lettura basati sulle preferenze degli utenti.
 */
public class SuggerimentoLibro implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 /** Libro suggerito */
	Libro l;
	  /** Numero di volte in cui il libro è stato suggerito */
	int numeroSuggerimenti;
	/**
     * Costruttore della classe SuggerimentoLibro.
     * @param l libro suggerito
     * @param numeroSuggerimenti numero di suggerimenti ricevuti dal libro
     */
	public SuggerimentoLibro(Libro l, int numeroSuggerimenti) {
		this.l = l;
		this.numeroSuggerimenti = numeroSuggerimenti;
	}

	/**
     * Restituisce il libro suggerito.
     * @return il libro
     */
	public Libro getL() {
		return l;
	}

	 /**
     * Restituisce il numero di suggerimenti ricevuti dal libro.
     * @return numero di suggerimenti
     */
	public int getNumeroSuggerimenti() {
		return numeroSuggerimenti;
	}

	
	
	

}
