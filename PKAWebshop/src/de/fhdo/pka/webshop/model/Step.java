package de.fhdo.pka.webshop.model;

public enum Step {
	
	START(0, "Start over"), VIEW_ITEMS(1, "View all items in this shop"), SELECT_ITEM(2, "Select an item for further action"), ADD_TO_CART(3, "Buy this item"), SHOW_CART(4, "Show your cart"), REMOVE_FROM_CART(5, "Delete this item from your cart"), PAY(6, "Buy the items in your cart"), LOGIN(7, "Login"), SHOW_ITEM(8, "Show this item"), SHOW_ITEM_IN_CART(10, "Show an item from your cart");

	private int value;
	private String key;
	
	private Step(int value, String key) {
		this.value = value;
		this.key = key;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getKey() {
		return key;
	}

}
