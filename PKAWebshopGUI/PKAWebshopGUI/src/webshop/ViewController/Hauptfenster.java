package webshop.ViewController;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import webshop.Webshop;
import webshop.Artikel.Artikel;
import webshop.Artikel.UngueltigeBezeichnungException;
import webshop.Artikel.UngueltigerPreisException;
import webshop.Kunden.AccountException;
import webshop.Kunden.UngueltigerNameException;

public class Hauptfenster extends Frame implements ActionListener {

	private Webshop webshop;

	private AnmeldeDialog anmeldeDialog;
	private EinkaufswagenDialog einkaufswagenDialog;
	private HinweisFenster hinweisFenster;
	private Button login;
	private Button logout;
	private Button select;
	private Button cart;
	private JTable list;

	public Hauptfenster(String webshopBetreiber) throws NullPointerException,
			UngueltigerNameException, AccountException,
			UngueltigeBezeichnungException, UngueltigerPreisException {
		super("Webshop-Anwendung");
		this.webshop = new Webshop(webshopBetreiber);

		setSize(600, 300);
		setLayout(new BorderLayout());

		einkaufswagenDialog = new EinkaufswagenDialog(this, "Einkaufswagen");
		WillkommensDialog welcome = new WillkommensDialog(this);
		welcome.setVisible(true);
		hinweisFenster = new HinweisFenster(this);

		fuelleButtonPanel();
		fuelleArtikelliste();
		disableButtons();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
		setVisible(true);
	}

	public Hauptfenster(String webshopBetreiber, int maxVersuche)
			throws NullPointerException, UngueltigerNameException,
			AccountException, UngueltigeBezeichnungException,
			UngueltigerPreisException {
		this(webshopBetreiber);
	}

	private void fuelleButtonPanel() {
		login = new Button("anmelden");
		login.addActionListener(this);
		logout = new Button("abmelden");
		logout.addActionListener(this);
		select = new Button("auswählen");
		select.addActionListener(this);
		cart = new Button("zum Einkaufswagen");
		cart.addActionListener(this);
		Panel buttonContainer = new Panel(new GridLayout(1, 4));
		buttonContainer.add(login);
		buttonContainer.add(logout);
		buttonContainer.add(select);
		buttonContainer.add(cart);
		add("North", buttonContainer);
	}

	private void disableButtons() {
		logout.setEnabled(false);
		select.setEnabled(false);
		cart.setEnabled(false);
	}

	// Artikel des Webshops in das List-Element
	// des Hauptfensters einfügen
	private void fuelleArtikelliste() {
		Object[][] data = new Object[4][4];
		int i = 0;
		for (Artikel a : webshop.getArtikelliste()) {
			data[i][0] = a.getKategorie();
			data[i][1] = a.getArtikelnummer();
			data[i][2] = a.getBezeichnung();
			data[i][3] = a.getPreis();
			i++;
		}
		
		MyTableModel model = new MyTableModel(data, new String[] { "Kategorie", "Artikelnummer","Bezeichnung", "Preis" });
		list = new JTable(model);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setEnabled(false);
		list.setVisible(true);
		list.setRowSorter( new TableRowSorter<MyTableModel>(model));
		JScrollPane scrollarea = new JScrollPane(list);
		add("Center", scrollarea);
	}

	public void onLoginFailed() {
		anmeldeDialog.setVisible(false);
		select.setVisible(false);
	}

	public void onLogout() {
		disableButtons();
		list.setEnabled(false);
		login.setEnabled(true);
		for (Window w : this.getOwnedWindows()) {
			w.setVisible(false);
		}
	}

	public void onLogin() {
		anmeldeDialog.setVisible(false);
		login.setEnabled(false);
		logout.setEnabled(true);
	}

	public void anzeigenString(String st) {
		hinweisFenster.setText(st);
		hinweisFenster.setVisible(true);
	}

	/*********** Fuer Subfenster ************/

	public Webshop getWebshop() {
		return webshop;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			anmeldeDialog = new AnmeldeDialog(this, "Anmelden");
			anmeldeDialog.setMaxAnmeldeVersuche(3);
			anmeldeDialog.setVisible(true);
			select.setEnabled(true);
		}
		if (e.getSource() == logout) {
			onLogout();
		}
		if (e.getSource() == cart) {
			zumEinkaufswagen();
			list.setEnabled(false);
			select.setEnabled(true);
			cart.setEnabled(false);
		}
		if (e.getSource() == select) {
			list.setEnabled(true);
			cart.setEnabled(true);
			select.setEnabled(false);
		}
	}

	/**
	 * Hilfsmethode zum Zurücksetzen der Selektionen in der Artikelliste
	 * 
	 * @return Gibt Indizes der Einträge zurück, die ausgewählt waren und jetzt
	 *         zurückgesetzt sind
	 */
	private int[] resetListSelections() {
		int[] selected = list.getSelectedRows();
		list.clearSelection();
		return selected;
	}

	/**
	 * Hilfsmethode für zumEinkaufswagen()
	 * 
	 * @param ausgewaehlteArtikel
	 *            Indizes der ausgewaehlten Artikel aus der Artikelliste
	 * @return Liste der zugehörige Artikel
	 */
	private java.util.List<Artikel> ausgewaehlteArtikel(
			int[] ausgewaehlteArtikel) {
		java.util.List<Artikel> aliste = webshop.getArtikelliste();
		ArrayList<Artikel> auswahl = new ArrayList<Artikel>();
		for (int i = 0; i < ausgewaehlteArtikel.length; i++)
			auswahl.add(aliste.get(ausgewaehlteArtikel[i]));
		return auswahl;
	}

	private void zumEinkaufswagen() {
		int[] selected = resetListSelections();
		webshop.inEinkaufswagen(ausgewaehlteArtikel(selected));
		einkaufswagenDialog.setInhalt(webshop.getInhaltEinkaufswagen());
		einkaufswagenDialog.setVisible(true);
	}

}
