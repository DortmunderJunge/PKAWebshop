package de.fhdo.pka.webshop.controller;

import java.util.List;

import de.fhdo.pka.webshop.helper.CurrentSituation;
import de.fhdo.pka.webshop.model.Item;
import de.fhdo.pka.webshop.view.Ui;

public class ItemController {

	private Item item;
	private List<Item> items;
	private Ui ui;
	
	public ItemController(Ui ui) {
		items = CurrentSituation.ITEMS;
		this.ui = ui;
	}
	
	public void viewArticles() {
		ui.showItemList(items);
	}

	public int selectQuantity() {
		return ui.selectQuantity(item);
	}

	public void showItem(){
		ui.showItem(item);
	}
	
	public void selectItem() {
		item = ui.chooseItem(items);		
	}
	
	public void take(Item item, int quantity) {
		int pos = items.indexOf(item);
		items.get(pos).setInStock(items.get(pos).getInStock() - quantity);
	}
	
	public void put(Item item, int quantity) {
		int pos = items.indexOf(item);
		items.get(pos).setInStock(items.get(pos).getInStock() + quantity);
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
