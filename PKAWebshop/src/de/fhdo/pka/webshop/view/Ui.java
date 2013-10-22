package de.fhdo.pka.webshop.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Exceptions.EmptyCartException;
import de.fhdo.pka.webshop.model.Cart;
import de.fhdo.pka.webshop.model.Credentials;
import de.fhdo.pka.webshop.model.Customer;
import de.fhdo.pka.webshop.model.Item;
import de.fhdo.pka.webshop.model.Step;

/**
 * @author Team Mettriegel
 * @version 1.0
 */

public class Ui {

	private Scanner sc = new Scanner(System.in);

	/**
	 * @return Credentials to be validated by the controller
	 */
	public Credentials logIn() {
		System.out.println("Please enter your email-address:");
		String email = sc.nextLine();
		System.out.println("Please enter your password:");
		String password = sc.nextLine();
		return new Credentials(email, password);
	}

	/**
	 * Method showing all available items by the pattern name \t number in stock
	 * \t price
	 * 
	 * @param itemlist
	 *            List containing all items in stock
	 */
	public void showItemList(List<Item> itemlist) {
		System.out.println("The Following Articles are currently available:");
		int i = 1;
		for (Item item : itemlist) {
			System.out.printf("%d)\t%s\t%dpcs.\t%.2f€\n", i++, item.getName(),
					item.getInStock(), item.getPrice());
		}
	}

	/**
	 * See all information for a selected item
	 * 
	 * @param item
	 */
	public void showItem(Item item) {
		System.out.println("You chose the following item:");
		System.out.println(item.show());
	}

	/**
	 * @param cart
	 *            your cart
	 * @throws EmptyCartException 
	 */
	public void showCart(Cart cart) throws EmptyCartException {
		if (cart.getItems() != null) {
			System.out.println("Your cart contains the following items:");
			int i = 1;
			for (Map.Entry<Item, Integer> cartItem : cart.getItems().entrySet()) {
				System.out.printf("%d) %d x %s\n", i++, cartItem.getValue(), cartItem
						.getKey().getName());
			}
		} else {
			throw new EmptyCartException();
		}
	}

	/**
	 * Method to enable the user to decide the next step
	 * 
	 * @param navigator
	 *            possible next steps
	 * @return selected step
	 */
	public Step selectNextStep(List<Step> steps) {
		if (steps.size() > 1) {
			int key;
			showNextSteps(steps);
			while (steps.size() < (key = sc.nextInt() - 1)) {
				System.out.println("this Action is invalid!");
			}
			return steps.get(key);
		}
		return steps.get(0);
	}

	/**
	 * Method summing up the order
	 * 
	 * @param cart
	 *            cart with ALL your stuff
	 */
	public void showOrderConfirmation(Cart cart, Customer customer) {
		System.out
				.println("The following Items will be shipped to you within the next 2 - 5 days:");
		for (Map.Entry<Item, Integer> cartItem : cart.getItems().entrySet()) {
			System.out.printf("%d x %s\t%.2f", cartItem.getValue(), cartItem
					.getKey().getName(), cartItem.getKey().getPrice());
			if (cartItem.getValue() > 1) {
				System.out.printf("\t=>\t%.2f", cartItem.getKey().getPrice()
						.multiply(BigDecimal.valueOf(cartItem.getValue())));
			}
			System.out.printf("\nTotal Price: %.2f\n", cart.getPrice());
			System.out.printf("Shipping Address: %s\n\t\t%s\n",
					customer.getName(), customer.getAdress());
			System.out.println();
		}
	}

	public void showPersonalizedWelcomeMessage(Customer customer) {
		System.out.printf("Hello, %s!\n", customer.getName());
	}

	public void logInFailed() {
		System.out.println("invalid email and/or password! Please try again.");
	}

	public int selectQuantity(Item item) {
		int quantity;
		boolean isValidQuantity = true;
		System.out.printf("How many pieces of %s would you like to buy?\n",
				item.getName());
		do {
			isValidQuantity = true;
			quantity = sc.nextInt();
			if (quantity > item.getInStock()) {
				System.out
						.printf("Sorry! There are only %d pieces of %s left. Please select another value!\n",
								item.getInStock(), item.getName());
				isValidQuantity = false;
			}
			if (quantity < 0) {
				System.out.println("Seriously dude, WTF?");
				isValidQuantity = false;
			}
		} while (!isValidQuantity);
		return quantity;
	}
	
	public Item chooseItem(List<Item> itemlist) {
		System.out.println("Please choose an item by its number.");
		int index = sc.nextInt();
		while ((index) > itemlist.size() || index < 1) {
			System.out.println("There is no such item. Try again!");
			index = sc.nextInt();
		}
		return itemlist.get(index - 1);
	}

	public void addedToCart(Item item, int quantity) {
		System.out.printf("%d pieces of %s have been added to your cart!\n",
				quantity, item.getName());
	}

	public void removedFromCart(Item item, int quantity) {
		if (quantity > 0) {
			System.out.printf("%d pieces of ", quantity);
		}
		System.out.printf("%s removed from cart\n", item.getName());
	}

	public void printException(Exception e) {
		System.out.println(e.getMessage());
	}
	
	private void showNextSteps(List<Step> steps) {
		System.out.println("What would you like to do next?");
		int i = 1;
		for (Step step : steps) {
			System.out.printf("%d) %s\n", i++, step.getKey());
		}
	}

}
