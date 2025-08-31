package version0;

import java.io.Serializable;

public class SuggerimentoLibro implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Libro l;
	int numeroSuggerimenti;
	
	public SuggerimentoLibro(Libro l, int numeroSuggerimenti) {
		this.l = l;
		this.numeroSuggerimenti = numeroSuggerimenti;
	}

	/**
	 * @return the l
	 */
	public Libro getL() {
		return l;
	}

	/**
	 * @return the numeroSuggerimenti
	 */
	public int getNumeroSuggerimenti() {
		return numeroSuggerimenti;
	}

	
	
	

}
