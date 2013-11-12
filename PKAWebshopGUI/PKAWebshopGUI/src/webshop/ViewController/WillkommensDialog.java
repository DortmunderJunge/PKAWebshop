package webshop.ViewController;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Panel;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

// TODO Änderungen ueb06 Aufgabe 2
public class WillkommensDialog extends JDialog {
		
	public WillkommensDialog (Frame owner) {
		// TODO: Modalen Dialog erzeugen,
		// mit einer Komponenten vom Typ Willkommensgrafik
		WillkommensGrafik image = new WillkommensGrafik();
		Panel p = new Panel();
		p.add(image);
		add(p);
		setAlwaysOnTop(true);
		setSize(200, 350);
	}
}
