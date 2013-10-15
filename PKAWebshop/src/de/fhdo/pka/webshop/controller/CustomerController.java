package de.fhdo.pka.webshop.controller;

import java.util.List;

import de.fhdo.pka.webshop.helper.CurrentSituation;
import de.fhdo.pka.webshop.model.Credentials;
import de.fhdo.pka.webshop.model.Customer;
import de.fhdo.pka.webshop.view.Ui;

public class CustomerController {

	private Customer customer;
	private List<Customer> customers;
	private Ui ui;
	
	public CustomerController(Ui ui) {
		customers = CurrentSituation.CUSTOMERS;
		this.ui = ui;
	}
	
	public void login()
	{
		while (customer == null) {
			Credentials login = ui.logIn();
			customer = login.findCustomer(customers);
			if (customer == null) {
				ui.logInFailed();
			}
		}
		ui.showPersonalizedWelcomeMessage(customer);
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
