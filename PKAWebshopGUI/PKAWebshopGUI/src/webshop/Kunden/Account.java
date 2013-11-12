package webshop.Kunden;

public class Account {
	
	private Kunde kunde = null;
	private String emailAdresse = null;
	private String passwort = null;
	
	public Account(String emailAdresse, String passwort) throws AccountException {
		// Es k�nnte auch auf weitergehende Bedingungen gepr�ft werden wie z.B. 
		// ob in Emailadresse ein @ enthalten ist o.�.
		// Bei Passw�rtern k�nnte auf eine Mindestl�nge, Mix von Buchstaben und Ziffern etc. gepr�ft werden
		if (emailAdresse.equals("")) throw new AccountException("Ungueltige Emailadresse");
		this.emailAdresse = emailAdresse;
		if (passwort.equals("")) throw new AccountException("Ungueltiges Passwort");
		this.passwort = passwort;
	}
	
	public Account(String emailAdresse, String passwort, Kunde kunde) throws NullPointerException, AccountException {
		this(emailAdresse, passwort);
		if (kunde == null) throw new NullPointerException("Beim Anlegen eines Accounts wurde kein gueltiges Kundenobjekt �bergeben");
		this.kunde = kunde;
	} 
	
	public Kunde getKunde() {
		return kunde;
	}
	
	public String getEmailAdresse() {
		return emailAdresse;
	}
	
	public void setEmailAdresse(String emailAdresse) throws AccountException {
		if (emailAdresse.equals("")) throw new AccountException("Emailadresse �ndern: Ungueltige Emailadresse");
		this.emailAdresse = emailAdresse;
	}
	
	public String getPasswort() {
		return passwort;
	}
	
	public void setPasswort(String passwort) throws AccountException {
		if (passwort.equals("")) throw new AccountException("Passwort �ndern: Ungueltiges Passwort");
		this.passwort = passwort;
	}
	
	// Um in der Menge der Accounts �ber "match" das Objekt
	// mit dem passenden Account zu finden, �ber das der zugeh�rige
	// Kunde ermittelt werden kann
	public boolean match(Account k) {
		String st = emailAdresse + passwort;
		String kst = ((Account) k).emailAdresse + ((Account) k).passwort;
		return st.equals(kst);
	}

	// Um zu gew�hrleisten, dass keine 2 Accounts mit derselben
	// Emailadresse gespeichert werden
	public boolean equals(Object k) {
		if (k == null)
			return false;
		if (k instanceof Account) {
			String st = emailAdresse;
			String kst = ((Account) k).emailAdresse;
			return st.equals(kst);
		} else
			return false;
	}

	// Accounts werden in HashSet gespeichert,
	// also hashCode n�tig. Dieser muss f�r inhaltsgleiche
	// Objekte denselben Wert ergeben
	public int hashCode() {
		String st = emailAdresse;
		return st.hashCode();
	}

	public String toString() {
		return "Emailadresse = " + emailAdresse + ", Passwort = " + passwort;
	}

}
