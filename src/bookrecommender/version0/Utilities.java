package version0;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Classe di utilità contenente funzioni di controllo e validazione.
 */
public class Utilities {
	
	/*
	 * Funzioni di controllo
	 */
    /** Formato standard del codice fiscale: lettere e numeri */

	final static String formatoCodiceFiscale = "aaaaaa00a00a000a";
	
	 /**
     * Controlla se un codice fiscale rispetta il formato standard.
     * @param s codice fiscale da controllare
     * @return true se il codice fiscale è valido, false altrimenti
     */
	public static boolean checkCodiceFiscale(String s) {
		char temp, form;
		if (s.length() != Utilities.formatoCodiceFiscale.length()) return false;
		for(int i=0;i<formatoCodiceFiscale.length();i++) {
			temp = s.charAt(i);
			form = Utilities.formatoCodiceFiscale.charAt(i);
			if(!(Character.isDigit(form) && Character.isDigit(temp) || Character.isLetter(form) && Character.isLetter(temp))) {
				return false;
			}
		}
		return true;
	}
	
	

}
