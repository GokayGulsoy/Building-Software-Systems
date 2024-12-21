package ceng431.iyte.edu.tr.HW4;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

	private String pizzaContent;
	private List<String> toppings;
	private String name;
	private int cost;

	public Pizza() {
		pizzaContent = null;
		name = null;
		cost = 0;
		toppings = new ArrayList<String>();
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return this.cost;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPizzaContent(String pizzaContent) {
		this.pizzaContent = pizzaContent;
	}

	public List<String> getToppings() {
		return toppings;
	}

	@Override
	public String toString() {

		String pizzaContents = this.name + " (" + pizzaContent + ", Toppings: ";

		for (int i = 0; i < toppings.size(); i++) {
			if (i == 0) {
				pizzaContents += toppings.get(i);
			}

			else {
				pizzaContents += ", " + toppings.get(i);
			}

		}

		pizzaContents += ")\n";
		pizzaContents += "Cost: " + this.cost + "₺";

		return pizzaContents;
	}

}
