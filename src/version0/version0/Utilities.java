package version0;

public class Utilities {
	
	/*
	 * Funzioni di controllo
	 */
	
	final static String formatoCodiceFiscale = "aaaaaa00a00a00a0";
	
	
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
