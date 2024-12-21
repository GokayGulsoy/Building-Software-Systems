package ceng431.iyte.edu.tr.HW4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class PizzaDecorator {

	protected Pizza pizza;
	protected Map<String, String> pizzaContents;
	protected Map<String, Integer> pizzaCosts;
	protected List<String> toppings;

	public PizzaDecorator(Pizza pizza) {
		this.pizza = pizza;
		pizzaContents = new LinkedHashMap<String, String>();
		pizzaCosts = new LinkedHashMap<String, Integer>();
		toppings = new ArrayList<String>();
	}

	public Pizza getPizza() {
		return this.pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	/**
	 * contents are specific to pizza shop's specific pizza type, so contents of
	 * each pizza are provided by specific decorator class representing specific
	 * pizza shop
	 */
	public abstract boolean setContents(int pizzaNumber);

	/**
	 * toppigns are specific to pizza shop type as each pizza shop provides
	 * different pizza toppings
	 */
	public abstract boolean addTopping(int toppingNumber);

	public void displayToppingList() {

		int toppingNumber = 1;

		for (String topping : toppings) {
			System.out.println(toppingNumber + ". " + topping);
			toppingNumber++;
		}

		System.out.println();
	}

	@Override
	public String toString() {

		int pizzaNumber = 1;
		String pizzaInfo = "";
		for (Map.Entry<String, String> pizzaEntry : pizzaContents.entrySet()) {
			pizzaInfo += pizzaNumber + "." + pizzaEntry.getKey() + " - Info: " + pizzaEntry.getValue() + " - Cost: "
					+ pizzaCosts.get(pizzaEntry.getKey()) + "₺\n";
			pizzaNumber++;
		}

		return pizzaInfo;
	}

}
