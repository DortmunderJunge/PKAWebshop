package webshop.ViewController;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


import java.awt.geom.Line2D;

import javax.swing.JPanel;

//TODO Änderungen ueb06 Aufgabe 2
public class WillkommensGrafik extends JPanel {
	public WillkommensGrafik() {
		this.setPreferredSize(new Dimension(200, 100));
	}

	// TODO: Methode überschreiben, um Einkaufswagen zu malen
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Einkaufswagen malen
		g2.setStroke(new BasicStroke(5));
		g2.setBackground(Color.BLUE);
		g2.draw(new Line2D.Double(20, 45, 0,0));
	}
}
