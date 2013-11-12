package webshop;

import java.util.*;

import webshop.Artikel.*;
import webshop.Kunden.*;

/**
 * Schnittstelle zwischen View-/Controller und den Fachklassen
 * 
 * @author Scheben
 * 
 *         Aufgaben Verwaltet verf�gbare Artikel, Accounts mit den zugeh�rigen
 *         Kundendaten, den am Webshop angemeldeten Kunden, den Einkaufswagen
 *         des angemeldeten Kunden
 * 
 *         Vereinfachende Annahmen: - Liste der Accounts und Liste der Artikel
 *         werden mit festen Testdaten gef�llt - Zu einer Zeit kann nur ein
 *         Kunde einkaufen - Keine Vorauswahl von Kategorien m�glich
 *         getArtikelliste() liefert ALLE Artikel
 * 
 */
public class Webshop {

	private String betreiber;

	/****************** Wahl ad�quater Datenstrukturen ***********************/
	/**
	 * Im Webshop gespeicherte Accounts. Diese sind bereits erfasst. Neue kommen
	 * nicht hinzu. Set statt List, da Accounts nicht mehrfach vorkommen d�rfen.
	 */
	private Set<Account> accountListe = new HashSet<Account>();

	/**
	 * Im Webshop angebotene Artikel. Diese sind bereits erfasst. Neue kommen
	 * nicht hinzu. SortedSet statt List: Artikel sollen sortiert sein und ein
	 * Artikel darf im Bestand nicht mehrfach vorkommen.
	 */
	private SortedSet<Artikel> artikelListe = new TreeSet<Artikel>();

	/**
	 * Im Webshop angemeldeter Kunde inkl. dessen Einkaufswagen Kunden k�nnen
	 * zun�chst nur nacheinander einkaufen
	 */
	// F�r sp�ter ggf. private HashMap<Kunde, Einkaufswagen> angemeldeteKunden =
	// new HashMap<Kunde, Einkaufswagen>();
	private Kunde aktuellerKunde;
	private Einkaufswagen aktuellerEinkaufswagen;

	public Webshop(String betreiber) throws NullPointerException,
			UngueltigerNameException, AccountException,
			UngueltigeBezeichnungException, UngueltigerPreisException {
		this.betreiber = betreiber;
		fuelleAccountListe();
		fuelleArtikelbestand();

		// Test
		// show (accountListe);
		// show (artikelListe);

	}

	// Testausgabe um zu pr�fen, dass keine doppelten Elemente in Listen/Mengen
	// Da diese Funktion nur zum Test da ist, nicht in UI verlagert
	private <E> void show(Set<E> liste) {
		System.out.println("\n******* Testausgabe *********");
		Iterator<E> it = liste.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	/**
	 * Accountliste mit festen Testdaten f�llen
	 * 
	 * @throws UngueltigerNameException
	 * @throws AccountException
	 * @throws NullPointerException
	 */
	private void fuelleAccountListe() throws UngueltigerNameException,
			NullPointerException, AccountException {
		Kunde kunde;
		Account account;
		kunde = new Kunde("Anton Meyer");
		account = new Account("a.meyer@web.de", "derKluge", kunde);
		accountListe.add(account);

		kunde = new Kunde("Hansi Mueller");
		account = new Account("hansi.m�ller@web.de", "2H355sRe", kunde);
		accountListe.add(account);

		// F�r Test; kurze Eingaben
		kunde = new Kunde("Ernst Otto");
		account = new Account("eo", "xx", kunde);
		accountListe.add(account);

		// Darf nicht nochmal gespeichert werden, da Emailadresse
		// bereits vergeben! F�r Testzwecke
		kunde = new Kunde("Erich Oman");
		account = new Account("eo", "zz", kunde);
		accountListe.add(account);

		/*
		 * Test auf Exceptions kunde = new Kunde(""); // --> UngueltigerName
		 * //kunde = new Kunde(null); // --> UngueltigerName account = new
		 * Account("a", "b", kunde); accountListe.add(account);
		 * 
		 * kunde = new Kunde("Emilia Katz"); account = new Account("", "b",
		 * kunde); // --> AccountException: Ungueltige Emailadresse
		 * accountListe.add(account);
		 */
	}

	/**
	 * Artikelliste mit festen Testdaten f�llen
	 * 
	 * @throws UngueltigerPreisException
	 * @throws UngueltigeBezeichnungException
	 */
	private void fuelleArtikelbestand() throws UngueltigeBezeichnungException,
			UngueltigerPreisException {
		artikelListe.add(new Buch(39.25, "Bl�tenzauber", "Berta Kruse",
				"Addison Wesley"));
		artikelListe.add(new TabletPC(118.50, "Acer Iconia B1"));
		artikelListe.add(new Buch(22.40, "Der goldene Drache", "Berta Kruse",
				"Addison Wesley"));
		artikelListe.add(new TabletPC(159.00, "Kindle Fire"));

		/* F�r Test auf Exceptions */
		// artikelListe.add(new TabletPC(200.00, "")); //-->
		// UngueltigeBezeichnung
		// artikelListe.add(new TabletPC(-8.00, "Kindle Fire")); //-->
		// UngueltigerPreis
	}

	/**
	 * Liste der im Webshop verf�gbaren Artikel zur�ckgeben
	 * 
	 * @return Artikelliste
	 */
	public List<Artikel> getArtikelliste() {
		LinkedList<Artikel> artikelLi = new LinkedList<Artikel>();
		Iterator<Artikel> it = artikelListe.iterator();
		while (it.hasNext()) {
			artikelLi.add(it.next());
		}
		return artikelLi;
	}

	/**
	 * Der zum Account geh�rende Kunde wird ermittelt und im Attribut
	 * aktuellerKunde gespeichert. Zudem wird dem Kunden ein leerer
	 * Einkaufswagen zugeordnet
	 * 
	 * @param account
	 */
	public void anmelden(Account account) {
		Iterator<Account> it = accountListe.iterator();
		boolean found = false;
		Account ac = null;
		while (it.hasNext() && !found) {
			ac = it.next();
			found = ac.match(account);
		}
		if (found) {
			aktuellerKunde = ac.getKunde();
			aktuellerEinkaufswagen = new Einkaufswagen();
		}
	}

	/**
	 * Pr�ft, ob der Account dem Webshop bekannt ist, ob er also in der Liste
	 * der Accounts vorkommt
	 * 
	 * @param account
	 * @return true, wenn Account dem Webshop bekannt ist, false sonst
	 */
	public boolean istGueltig(Account account) {
		Iterator<Account> it = accountListe.iterator();
		boolean found = false;
		Account ac = null;
		while (it.hasNext() && !found) {
			ac = it.next();
			found = ac.match(account);
		}
		return found;
	}

	/**
	 * Einkaufswagen freigeben und aktuellen Kunden l�schen
	 */
	public void abmelden() {
		aktuellerKunde = null;
		aktuellerEinkaufswagen = null;
	}

	public String getKundenName() {
		return aktuellerKunde.getName();
	}

	/**
	 * Gibt Liste der Artikel zur�ck, die sich im Einkaufswagen befinden
	 * 
	 * @return Artikel aus Einkaufswagen
	 */
	public List<Artikel> getInhaltEinkaufswagen() {
		return aktuellerEinkaufswagen.getInhalt();
	}

	public boolean einkaufswagenIstLeer() {
		return aktuellerEinkaufswagen.istLeer();
	}

	public void leereEinkaufswagen() {
		aktuellerEinkaufswagen.allesLoeschen();
	}

	/**
	 * Alle vom Kunden ausgew�hlten Artikel dem Einkaufswagen hinzuf�gen
	 * 
	 * @param ausgewaehlteArtikel
	 *            Liste der vom Kunden ausgew�hlten Artikel
	 */
	public void inEinkaufswagen(List<Artikel> ausgewaehlteArtikel) {
		for (Artikel artikel : ausgewaehlteArtikel) {
			aktuellerEinkaufswagen.einfuegen(artikel);
		}
	}

	/**
	 * �bergebenen Artikel aus Einkaufswagen nehmen
	 * 
	 * @param zuLoeschenderArtikel
	 */
	public void ausEinkaufswagenNehmen(Artikel zuLoeschenderArtikel) {
		aktuellerEinkaufswagen.loeschen(zuLoeschenderArtikel);
	}

	public String getBetreiber() {
		return betreiber;
	}

}
