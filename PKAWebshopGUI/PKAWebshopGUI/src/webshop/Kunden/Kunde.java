package webshop.Kunden;

public class Kunde {
	
	private static int anzahlKunden = 0;
	private int kundenID;
	private String name;
	private String adresse;
	
	// TODO hashCode und equals implementieren, falls Kunde als Schlüssel 
	// in Hashtabelle verwendet wird; Paare aus Kunde und Einkaufswagen. 
	// public boolean equals(Object o)
	// public int hashCode()

	public Kunde(String name) throws UngueltigerNameException
	{
		kundenID = ++anzahlKunden;
		if ((name == null) || name.equals("")) throw new UngueltigerNameException();
		this.name = name;
	}	
	
	public int hashCode() {
		return kundenID;
	}
	
	public boolean equals (Object k) {
		if (k == null) return false;
		if (k instanceof Kunde)
		    return kundenID == ((Kunde)k).kundenID;
		else
			return false;
	}
	
	public long getKundenID() {
		return kundenID;
	}

	public String getName() {
		return name;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

}
