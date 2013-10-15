package de.fhdo.pka.webshop.helper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhdo.pka.webshop.model.Book;
import de.fhdo.pka.webshop.model.Credentials;
import de.fhdo.pka.webshop.model.Customer;
import de.fhdo.pka.webshop.model.Item;
import de.fhdo.pka.webshop.model.Step;
import de.fhdo.pka.webshop.model.Tablet;

public class CurrentSituation {

	private static final Customer C = new Customer("Ingrid Meier",
			"Opernplatz 42", new Credentials("ingrid@meier.de", "abc123"));
	private static final Customer ME = new Customer("Mett Riegel",
			"Schlossallee 1", new Credentials("mett@riegel.de", "mett"));
	private static final Customer KG = new Customer("Kevin Grosskreutz",
			"Evinger Straﬂe 286", new Credentials("kevin@bvb.de", "kevin")); // because he is awesome!
	

	private static final Item MYPAD = new Tablet("myPad", "147852369", BigDecimal.valueOf(699), 7, "Mapple", 10.1);
	private static final Item SURFFACE = new Tablet("Surfface", "369852147", BigDecimal.valueOf(599), 4, "Gigasoft", 9.8);
	private static final Item HERRYOTTER = new Book("Herry Otter", "1598741", BigDecimal.valueOf(13), 15, "K. J. Rolling", "books4free", 1021);
	private static final Item JAVA = new Book("Java ist doch keine Insel", "1533121", BigDecimal.valueOf(45), 2, "Prof. Frink", "computerscience", 99);
	private static final Item NOTFUNNY = new Book("Nichtlustig", "47896542", BigDecimal.valueOf(23), 6, "Josha Sauer", "nichtlustig", 254); 

	public static final List<Step> VIEW_ITEMS = Arrays.asList(new Step[] { Step.SELECT_ITEM, Step.SHOW_CART, Step.PAY });
	public static final List<Step> SHOW_ITEM = Arrays.asList(new Step[] { Step.VIEW_ITEMS, Step.ADD_TO_CART });
	public static final List<Step> SELECT_ITEM = Arrays.asList(new Step[] { Step.SHOW_ITEM });
	public static final List<Step> ADD_TO_CART = Arrays.asList(new Step[] { Step.VIEW_ITEMS, Step.SHOW_CART });
	public static final List<Step> SHOW_CART = Arrays.asList(new Step[] { Step.SHOW_ITEM_IN_CART, Step.VIEW_ITEMS, Step.PAY });
	public static final List<Step> SHOW_ITEM_IN_CART = Arrays.asList(new Step[] { Step.REMOVE_FROM_CART, Step.SHOW_CART, Step.PAY });
	public static final List<Step> REMOVE_FROM_CART = Arrays.asList(new Step[] { Step.SHOW_CART });
	public static final List<Step> START = Arrays.asList(new Step[] { Step.LOGIN });
	public static final List<Step> LOGIN = Arrays.asList(new Step[] { Step.VIEW_ITEMS });
	public static final List<Step> PAY = Arrays.asList(new Step[] { Step.LOGIN} );
	
	public static final Map<Step, List<Step>> navigator = new HashMap<Step, List<Step>>();
	
	public static Map<Step, List<Step>> initNavigator() {
		navigator.put(Step.VIEW_ITEMS, VIEW_ITEMS);
		navigator.put(Step.SELECT_ITEM, SELECT_ITEM);
		navigator.put(Step.ADD_TO_CART, ADD_TO_CART);
		navigator.put(Step.SHOW_CART, SHOW_CART);
		navigator.put(Step.REMOVE_FROM_CART, REMOVE_FROM_CART);
		navigator.put(Step.START, START);
		navigator.put(Step.LOGIN, LOGIN);
		navigator.put(Step.SHOW_ITEM, SHOW_ITEM);
		navigator.put(Step.SELECT_ITEM, SELECT_ITEM);
		navigator.put(Step.SHOW_ITEM_IN_CART, SHOW_ITEM_IN_CART);
		navigator.put(Step.PAY, PAY);
		return navigator;
	}
	

	public static final List<Customer> CUSTOMERS = Arrays
			.asList(new Customer[] { C, ME, KG });
	public static final List<Item> ITEMS = Arrays.asList(new Item[] { MYPAD, SURFFACE, HERRYOTTER, JAVA, NOTFUNNY });
}
