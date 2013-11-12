package webshop.Artikel;

public class Buch extends Artikel {
	
	private String titel;
	private String autoren;
	private String verlag;

	public Buch (double preis, String titel, String autoren, String verlag) throws UngueltigeBezeichnungException, UngueltigerPreisException {
		super (Kategorie.Buch, titel, preis);
		this.titel = titel;
		this.autoren = autoren;
		this.verlag = verlag;
	}	

	public String getTitel() {
		return titel;
	}

	public String getAutoren() {
		return autoren;
	}

	public String getVerlag() {
		return verlag;
	}

}
