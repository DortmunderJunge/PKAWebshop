package de.fhdo.pka.webshop.controller;

import java.util.LinkedList;

import de.fhdo.pka.webshop.model.Cart;
import de.fhdo.pka.webshop.model.Item;
import de.fhdo.pka.webshop.view.Ui;

public class CartController {

	private Cart cart;
	private Ui ui;

	public CartController(Ui ui) {
		cart = new Cart();
		this.ui = ui;
	}

	public void showCart() throws EmptyCartException {
		if (cart.getItems().isEmpty()) {
			throw new EmptyCartException();
		}
		ui.showCart(cart);
	}

	public void removeFromCart(Item item) {
		cart.remove(item);
	}

	public void removedFromCart(Item item) {
		removedFromCart(item, 0);
	}

	public void removeFromCart(Item item, int quantity) {
		cart.remove(item, quantity);
	}

	public void removedFromCart(Item item, int quantity) {
		ui.removedFromCart(item, quantity);
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void showItem() {
		Item item = ui.chooseItem(new LinkedList<Item>(cart.getItems().keySet()));
		ui.showItem(item);
	}

	public void addToCart(Item item, int quantity) {
		cart.put(item, quantity);
		ui.addedToCart(item, quantity);
	}
}
