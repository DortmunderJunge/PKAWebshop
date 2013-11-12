package webshop.ViewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;

import webshop.Kunden.Account;
import webshop.Kunden.AccountException;

public class AnmeldeDialog extends Dialog {

	private int maxAnmeldeVersuche = 1;
	private int anzVersuche = 0; // Zum Zählen der bereits erfolgten
									// Anmeldeversuche
	private boolean anmeldeFehler = false; // Für Haupfenster: Anmeldung
											// erfolgreich?
	private Button cont;
	private TextField email;
	private JPasswordField password;

	public AnmeldeDialog(Hauptfenster owner, String titel) {
		super(owner, titel);
		setModal(true);
		setLayout();
	}

	private void setLayout() {
		setSize(300, 150);
		setLocation(10, 120);
		Panel panel = new Panel(new GridLayout(5, 1));
		email = new TextField();
		password = new JPasswordField();
		panel.add(new Label("Email-Adresse:"));
		panel.add(email);
		panel.add(new Label("Passwort:"));
		panel.add(password);
		cont = new Button("weiter");
		panel.add(cont);
		cont.addActionListener(new ContinueButtonListener());
		add(panel);
	}

	public void setMaxAnmeldeVersuche(int maxVersuche) {
		maxAnmeldeVersuche = maxVersuche;
	}

	public void setAnmeldeFehler(boolean fehler) {
		anmeldeFehler = fehler;
	}

	public boolean istAnmeldeFehler() {
		return anmeldeFehler;
	}
	
	private void validateLogin() {
		Hauptfenster main = (Hauptfenster)getOwner();
		try {
			Account account = new Account(email.getText(), String.valueOf(password.getPassword()));
			if (main.getWebshop().istGueltig(account)) {
				main.getWebshop().anmelden(account);
				setAnmeldeFehler(false);
				main.onLogin();
			} else {
				setAnmeldeFehler(true);
				main.anzeigenString("Login fehlgeschlagen!\nVerbleibende Versuche: " + (maxAnmeldeVersuche - ++anzVersuche));
				if (maxAnmeldeVersuche - anzVersuche == 0) {
					main.onLoginFailed();
				}
			}
		} catch (AccountException e) {

			(main).anzeigenString("Email und/oder Passwort dürfen nicht leer sein!");
		}
	}
	
	private class ContinueButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cont) 
			{
				validateLogin();
			}
			
		}
		
	}
}
