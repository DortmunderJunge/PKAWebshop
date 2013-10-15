package de.fhdo.pka.webshop.model;

import java.math.BigDecimal;

/**
 * @author Lars
 * @version 1.0
 */

public class Book extends Item {

	private String author, publisher;

	private int pages;

	@Override
	public String show() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Type:\t%s\n", getClass().getSimpleName()));
		sb.append(String.format("Name:\t%s\n", getName()));
		sb.append(String.format("in Stock:\t%d\n", getInStock()));
		sb.append(String.format("Price:\t%.2f€\n", getPrice()));
		sb.append(String.format("Author:\t%s\n", getAuthor()));
		sb.append(String.format("Publisher:\t%s\n", getPublisher()));
		sb.append(String.format("Pages:\t%d\n", getPages()));
		sb.append(String.format("EAN:\t%s\n", getEan()));
		return sb.toString();
	}

	public Book(String name, String ean, BigDecimal price, int inStock,
			String author, String publisher, int pages) {
		super(name, ean, price, inStock);
		this.author = author;
		this.publisher = publisher;
		this.pages = pages;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
}
