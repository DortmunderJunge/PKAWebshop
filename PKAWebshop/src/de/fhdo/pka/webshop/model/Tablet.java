package de.fhdo.pka.webshop.model;

import java.math.BigDecimal;

/**
 * @author Team Mettriegel
 * @version 1.0
 */

public class Tablet extends Item {

	private String producer;

	public Tablet(String name, String ean, BigDecimal price, int inStock,
			String producer, double screenSize) {
		super(name, ean, price, inStock);
		this.producer = producer;
		this.screenSize = screenSize;
	}

	private double screenSize;

	@Override
	public String show() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Type:\t%s\n", getClass().getSimpleName()));
		sb.append(String.format("Name:\t%s\n", getName()));
		sb.append(String.format("in Stock:\t%d\n", getInStock()));
		sb.append(String.format("Price:\t%.2f€\n", getPrice()));
		sb.append(String.format("Producer:\t%s\n", getProducer()));
		sb.append(String.format("Screen Size:%.1f\"\n", getScreenSize()));
		sb.append(String.format("EAN:\t%s\n", getEan()));
		return sb.toString();
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public double getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(double screenSize) {
		this.screenSize = screenSize;
	}
}
