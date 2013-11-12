package webshop.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import webshop.Webshop;
import webshop.Artikel.Artikel;


public class BuyListener implements ActionListener {

	private EinkaufswagenDialog owner;
	private Webshop webshop;
	
	public BuyListener(EinkaufswagenDialog owner) {
		this.owner = owner;
		webshop = ((Hauptfenster)owner.getOwner()).getWebshop();
	}
	
	private void showDialog() {
		HinweisFenster w = new HinweisFenster(owner);
		String items = "";
		for (Artikel a : webshop.getInhaltEinkaufswagen()) {
			items += a.toString() + "\n";
		}
		w.setText("Ihre Bestellung:\n" + webshop.getKundenName() + "\n" + items);
		w.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "jetzt kaufen!") {
			showDialog();
			webshop.leereEinkaufswagen();
			webshop.abmelden();
			((Hauptfenster)owner.getOwner()).onLogout();
		}
	}
	

}