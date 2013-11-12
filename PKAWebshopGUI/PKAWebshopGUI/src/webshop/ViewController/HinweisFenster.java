package webshop.ViewController;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Label;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HinweisFenster extends Dialog {

	// Außerhalb des Konstruktors benötigtes Label --> setText()
	private Label hinweisLabel;

	public HinweisFenster(Window owner) {

		super(owner);
		setModal(true);

		hinweisLabel = new Label("", Label.CENTER);
		add(hinweisLabel, BorderLayout.CENTER);

		Button ok = new Button("ok");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);

			}
		});

		add(ok, BorderLayout.SOUTH);
		setSize(400, 100);
		setLocation(30, 270);
	}

	public void setText(String s) {
		hinweisLabel.setText(s);
	}
}
