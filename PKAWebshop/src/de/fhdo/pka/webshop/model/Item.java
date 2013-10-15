package de.fhdo.pka.webshop.model;

import java.math.BigDecimal;

/**
 * Superclass for all available items in our shop
 * 
 * @author Lars
 * @version 1.0
 */

public abstract class Item {

	private String name, ean;

	private BigDecimal price;

	private int inStock;

	public abstract String show();
	
	protected Item(String name, String ean, BigDecimal price, int inStock) {
		this.name = name;
		this.ean = ean;
		this.price = price;
		this.inStock = inStock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}
}
