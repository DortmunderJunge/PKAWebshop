package de.fhdo.pka.webshop.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lars
 * @version 1.0
 */

public class Cart {

	private Map<Item, Integer> items;
	
	public Cart() {
		this.items = new HashMap<Item, Integer>();
	}

	public void put(Item item, int quantity) {
		if (items.containsKey(item)) {
			items.put(item, items.get(item) + quantity);
		} else {
			items.put(item, quantity);
		}
	}

	public void remove(Item item) {
		items.remove(item);
	}

	/**
	 * This method removes a specified number of a selected item from the
	 * shopping cart
	 * 
	 * @param item
	 *            The Item to be removed
	 * @param count
	 *            quantity of items to be removed
	 */
	public void remove(Item item, int count) {
		int newCount = count - items.get(item);
		if (newCount > 0) {
			items.put(item, newCount);
		} else {
			remove(item);
		}
	}

	/**
	 * clears the whole shopping cart
	 */
	public void clear() {
		items.clear();
	}

	/**
	 * 
	 * @return the current value of all items in the cart
	 */
	public BigDecimal getPrice() {
		BigDecimal price = BigDecimal.ZERO;
		for (Map.Entry<Item, Integer> item : items.entrySet()) {
			price = price.add(item.getKey().getPrice().multiply(BigDecimal.valueOf(item.getValue())));
		}
		return price;
	}

	public Map<Item, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Item, Integer> items) {
		this.items = items;
	}
}
