package de.fhdo.pka.webshop.controller;

import de.fhdo.pka.webshop.model.Cart;
import de.fhdo.pka.webshop.model.Customer;
import de.fhdo.pka.webshop.view.Ui;

public class PaymentController {

	private Ui ui;
	
	public PaymentController(Ui ui) {
		this.ui = ui;
	}
	
	public void pay(Cart cart, Customer customer) {
		ui.showOrderConfirmation(cart, customer);
	}

}
