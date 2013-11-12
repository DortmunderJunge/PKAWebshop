package webshop.Artikel;

public class TabletPC extends Artikel {

	public TabletPC (double preis, String bezeichnung) throws UngueltigeBezeichnungException, UngueltigerPreisException {
		super (Kategorie.TabletPC, bezeichnung, preis);
	}	

}
