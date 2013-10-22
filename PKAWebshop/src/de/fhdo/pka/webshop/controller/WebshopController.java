package de.fhdo.pka.webshop.controller;

import java.util.List;
import java.util.Map;

import Exceptions.EmptyCartException;
import de.fhdo.pka.webshop.helper.CurrentSituation;
import de.fhdo.pka.webshop.model.Step;
import de.fhdo.pka.webshop.view.Ui;

public class WebshopController {

	private static Map<Step, List<Step>> navigator = CurrentSituation
			.initNavigator();

	private static Ui ui = new Ui();
	private static CartController cartController;
	private static CustomerController customerController;
	private static PaymentController paymentController;
	private static ItemController itemController;
	private static Step currentStep = Step.START;

	public static void init() {
		ui = new Ui(); // ui needs to be re-initialized for the login to work properly.
		cartController = new CartController(ui);
		customerController = new CustomerController(ui);
		paymentController = new PaymentController(ui);
		itemController = new ItemController(ui);
	}
	
	public static void main(String[] args) {
		while (true) {
			nextAction();
		}
	}

	private static void nextAction() {

		currentStep = ui.selectNextStep(navigator.get(currentStep));

		switch (currentStep) {
		case PAY:
			paymentController.pay(cartController.getCart(), customerController.getCustomer());
			break;
		case REMOVE_FROM_CART:
			int removeQuantity = cartController.getCart().getItems().get(itemController.getItem());
			cartController.removeFromCart(itemController.getItem());
			itemController.put(itemController.getItem(), removeQuantity);
			break;
		case SELECT_ITEM:
			itemController.selectItem();
			break;
		case SHOW_ITEM:
			itemController.showItem();
			break;
		case ADD_TO_CART:
			int addQuantity = itemController.selectQuantity();
			cartController.addToCart(itemController.getItem(), addQuantity);
			itemController.take(itemController.getItem(), addQuantity);
			break;
		case SHOW_CART:
			try {
				cartController.showCart();
			}
			catch (EmptyCartException e) {
				ui.printException(e);
				currentStep = Step.LOGIN; // So we end up showing all items in our shop.
			}
			break;
		case VIEW_ITEMS:
			itemController.viewArticles();
			break;
		case START:
		case LOGIN:
			init();
			customerController.login();
			break;
		case SHOW_ITEM_IN_CART:
			cartController.showItem();
			break;
		default:
			break;
		}
	}
}
