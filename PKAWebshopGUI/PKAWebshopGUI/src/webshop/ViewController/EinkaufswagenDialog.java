package webshop.ViewController;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import webshop.Webshop;
import webshop.Artikel.Artikel;

public class EinkaufswagenDialog extends Dialog {

	private Hauptfenster owner;
	private java.awt.List list = new List();

	// TODO Weitere, außerhalb des Konstruktors benötigte Attribute

	public EinkaufswagenDialog(final Hauptfenster owner, String titel) {
		super(owner, titel);
		this.owner = owner;
		// setModal(true);

		setSize(300, 200);
		setLocation(310, 60);
		setLayout();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				setVisible(false);
			}
		});
	}

	private void setLayout() {
		Panel panel = new Panel(new BorderLayout());
		panel.add("North", new Label("Inhalt Ihres Einkaufwagens:"));
		panel.add("Center", list);
		Button buy = new Button("jetzt kaufen!");
		buy.addActionListener(new BuyListener(this));
		panel.add("South", buy);
		add(panel);
	}

	public void setInhalt(java.util.List<Artikel> inhalt) {
		for (Artikel a : inhalt) {
			list.add(a.toString());
		}
	}
}
