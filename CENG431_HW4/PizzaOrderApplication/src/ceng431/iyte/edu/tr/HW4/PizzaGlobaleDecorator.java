package ceng431.iyte.edu.tr.HW4;

import java.util.List;

public class PizzaGlobaleDecorator extends PizzaDecorator {

	public PizzaGlobaleDecorator(Pizza pizza) {
		super(pizza);

		// available pizzas
		pizzaContents.put("Goat Cheese Pizza", "Tomato Sauce, Mozzarella Cheese, Goat Cheese");
		pizzaContents.put("Margherita Pizza", "Tomato Sauce, Mozzarella Cheese, Cherry Tomato, Basil Leaves");
		pizzaContents.put("4 Cheeses Pizza",
				"Tomato Sauce, Mozzarella Cheese, Roquefort Cheese, Parmesan Cheese, Feta Cheese");
		pizzaContents.put("Barbeque Chicken Pizza", "Tomato Sauce, Mozzarella Cheese, Chicken Slices, Barbeque Sauce");
		pizzaContents.put("Smoked Ribs Pizza",
				"Tomato Sauce, Mozzarella Cheese, Smoked Ribs, Arugula Leaves, Parmesan Cheese");

		pizzaCosts.put("Goat Cheese Pizza", 60);
		pizzaCosts.put("Margherita Pizza", 60);
		pizzaCosts.put("4 Cheeses Pizza", 55);
		pizzaCosts.put("Barbeque Chicken Pizza", 54);
		pizzaCosts.put("Smoked Ribs Pizza", 70);

		// available toppings
		toppings.add("Parmesan Cheese – Cost: 5₺");
		toppings.add("Olive – Cost: 4₺");
		toppings.add("Sweet Corn – Cost: 3₺");
		toppings.add("Ricotta Cheese – Cost: 4₺");
		toppings.add("Red Pepper – Cost: 8₺");
		toppings.add("Sausage – Cost: 9₺");
		toppings.add("Pepperoni – Cost: 8₺");
		toppings.add("Pastrami – Cost 10₺");
		toppings.add("Basil Leaves – Cost 6₺");
		toppings.add("Cherry Tomato – Cost 7₺");
		toppings.add("Mushroom – Cost: 6₺");
	}

	@Override
	public boolean setContents(int pizzaNumber) {

		int totalCost = pizza.getCost();
		boolean isCorrectlySet = true;

		if (pizzaNumber == 1) {
			pizza.setPizzaContent(pizzaContents.get("Goat Cheese Pizza"));
			pizza.setName("Goat Cheese Pizza");
			totalCost += pizzaCosts.get("Goat Cheese Pizza");
		}

		else if (pizzaNumber == 2) {
			pizza.setPizzaContent(pizzaContents.get("Margherita Pizza"));
			pizza.setName("Margherita Pizza");
			totalCost += pizzaCosts.get("Margherita Pizza");
		}

		else if (pizzaNumber == 3) {
			pizza.setPizzaContent(pizzaContents.get("4 Cheeses Pizza"));
			pizza.setName("4 Cheeses Pizza");
			totalCost += pizzaCosts.get("4 Cheeses Pizza");
		}

		else if (pizzaNumber == 4) {
			pizza.setPizzaContent(pizzaContents.get("Barbeque Chicken Pizza"));
			pizza.setName("Barbeque Chicken Pizza");
			totalCost += pizzaCosts.get("Barbeque Chicken Pizza");
		}

		else if (pizzaNumber == 5) {
			pizza.setPizzaContent(pizzaContents.get("Smoked Ribs Pizza"));
			pizza.setName("Smoked Ribs Pizza");
			totalCost += pizzaCosts.get("Smoked Ribs Pizza");
		}

		else {
			System.out.println("No such pizza exists matching with given number!!\n");
			isCorrectlySet = false;
		}

		pizza.setCost(totalCost);

		return isCorrectlySet;
	}

	@Override
	public boolean addTopping(int toppingNumber) {

		List<String> toppings = pizza.getToppings();
		int totalCost = pizza.getCost();
		boolean isToppingCorrectlySet = false;

		String extraTopping = "";
		String existingTopping = "";

		if (toppingNumber == 0) {
			isToppingCorrectlySet = true;
			return isToppingCorrectlySet;
		}

		else if (toppingNumber == 1) {
			if (toppings.contains("Parmesan Cheese")) {
				existingTopping = "Parmesan Cheese";
				extraTopping = "Parmesan Cheese(Extra)";
			}

			else if (!toppings.contains("Parmesan Cheese(Extra)")) {
				toppings.add("Parmesan Cheese");
			}

			totalCost += 5;
		}

		else if (toppingNumber == 2) {
			if (toppings.contains("Olive")) {
				existingTopping = "Olive";
				extraTopping = "Olive(Extra)";
			}

			else if (!toppings.contains("Olive(Extra)")) {
				toppings.add("Olive");
			}

			totalCost += 4;
		}

		else if (toppingNumber == 3) {
			if (toppings.contains("Sweet Corn")) {
				existingTopping = "Sweet Corn";
				extraTopping = "Sweet Corn(Extra)";
			}

			else if (!toppings.contains("Sweet Corn(Extra)")) {
				toppings.add("Sweet Corn");
			}

			totalCost += 3;
		}

		else if (toppingNumber == 4) {
			if (toppings.contains("Ricotta Cheese")) {
				existingTopping = "Ricotta Cheese";
				extraTopping = "Ricotta Cheese(Extra)";
			}

			else if (!toppings.contains("Ricotta Cheese(Extra)")) {
				toppings.add("Ricotta Cheese");
			}

			totalCost += 4;
		}

		else if (toppingNumber == 5) {
			if (toppings.contains("Red Pepper")) {
				existingTopping = "Red Pepper";
				extraTopping = "Red Pepper(Extra)";
			}

			else if (!toppings.contains("Red Pepper(Extra)")) {
				toppings.add("Red Pepper");
			}

			totalCost += 8;
		}

		else if (toppingNumber == 6) {
			if (toppings.contains("Sausage")) {
				existingTopping = "Sausage";
				extraTopping = "Sausage(Extra)";
			}

			else if (!toppings.contains("Sausage(Extra)")) {
				toppings.add("Sausage");
			}

			totalCost += 9;
		}

		else if (toppingNumber == 7) {
			if (toppings.contains("Pepperoni")) {
				existingTopping = "Pepperoni";
				extraTopping = "Pepperoni(Extra)";
			}

			else if (!toppings.contains("Pepperoni(Extra)")) {
				toppings.add("Pepperoni");
			}

			totalCost += 8;
		}

		else if (toppingNumber == 8) {
			if (toppings.contains("Pastrami")) {
				existingTopping = "Pastrami";
				extraTopping = "Pastrami(Extra)";
			}

			else if (!toppings.contains("Pastrami(Extra)")) {
				toppings.add("Pastrami");
			}

			totalCost += 10;
		}

		else if (toppingNumber == 9) {
			if (toppings.contains("Basil Leaves")) {
				existingTopping = "Basil Leaves";
				extraTopping = "Basil Leaves(Extra)";
			}

			else if (!toppings.contains("Basil Leaves(Extra)")) {
				toppings.add("Basil Leaves");
			}

			totalCost += 6;
		}

		else if (toppingNumber == 10) {
			if (toppings.contains("Cherry Tomato")) {
				existingTopping = "Cherry Tomato";
				extraTopping = "Cherry Tomato(Extra)";
			}

			else if (!toppings.contains("Cherry Tomato(Extra)")) {
				toppings.add("Cherry Tomato");
			}

			totalCost += 7;
		}

		else if (toppingNumber == 11) {
			if (toppings.contains("Mushroom")) {
				existingTopping = "Mushroom";
				extraTopping = "Mushroom(Extra)";
			}

			else if (!toppings.contains("Mushroom(Extra)")) {
				toppings.add("Mushroom");
			}
			totalCost += 6;
		}

		else {
			System.out.println("No such topping exists matching with given number!!\n");
		}

		if ((!extraTopping.equals("")) && (!existingTopping.equals(""))) {
			int existingIndex = toppings.indexOf(existingTopping);
			toppings.set(existingIndex, extraTopping);
		}

		pizza.setCost(totalCost);

		return isToppingCorrectlySet;
	}

}
