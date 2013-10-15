package de.fhdo.pka.webshop.model;

/**
 * @author Lars
 * @version 1.0
 */

public class Customer {

	private String name, address;
	
	private Credentials credentials;
	
	public Customer(String name, String address, Credentials credentials) {
		this.name = name;
		this.address = address;
		this.credentials = credentials;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return address;
	}

	public void setAdress(String adress) {
		this.address = adress;
	}

	public Credentials getCredentials() {
		return credentials;
	}
	
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
}
