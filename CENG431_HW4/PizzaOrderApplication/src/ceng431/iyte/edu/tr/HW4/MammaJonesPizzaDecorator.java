package ceng431.iyte.edu.tr.HW4;

import java.util.List;

public class MammaJonesPizzaDecorator extends PizzaDecorator {

	public MammaJonesPizzaDecorator(Pizza pizza) {
		super(pizza);

		// available pizzas
		pizzaContents.put("Aegean Pizza", "Tomato Sauce, Mozzarella Cheese, Olive, Red Onion, Red Pepper");
		pizzaContents.put("Margherita Pizza", "Tomato Sauce, Mozzarella Cheese, Cherry Tomato, Basil Leaves");
		pizzaContents.put("Mixed Pizza", "Tomato Sauce, Mozzarella Cheese, Pepperoni, Sausage, Salami, Green Pepper");
		pizzaContents.put("Barbeque Chicken Pizza",
				"Tomato Sauce, Mozzarella Cheese, Chicken Slices, Barbeque Sauce, Jalapeno");
		pizzaContents.put("Smoked Ribs Pizza",
				"Tomato Sauce, Mozzarella Cheese, Smoked Ribs, Arugula Leaves, Parmesan Cheese");

		pizzaCosts.put("Aegean Pizza", 55);
		pizzaCosts.put("Margherita Pizza", 58);
		pizzaCosts.put("Mixed Pizza", 63);
		pizzaCosts.put("Barbeque Chicken Pizza", 68);
		pizzaCosts.put("Smoked Ribs Pizza", 72);

		// available toppings
		toppings.add("Parmesan Cheese – Cost: 5₺");
		toppings.add("Olive – Cost: 3₺");
		toppings.add("Ricotta Cheese – Cost: 5₺");
		toppings.add("Sausage – Cost: 7₺");
		toppings.add("Pepperoni – Cost: 7₺");
		toppings.add("Salami – Cost 8₺");
		toppings.add("Basil Leaves – Cost 7₺");
		toppings.add("Cherry Tomato – Cost 7₺");
		toppings.add("Jalapeno – Cost: 4₺");
	}

	@Override
	public boolean setContents(int pizzaNumber) {

		int totalCost = pizza.getCost();
		boolean isContentsCorrectlySet = true;

		if (pizzaNumber == 1) {
			pizza.setPizzaContent(pizzaContents.get("Aegean Pizza"));
			pizza.setName("Aegean Pizza");
			totalCost += pizzaCosts.get("Aegean Pizza");
		}

		else if (pizzaNumber == 2) {
			pizza.setPizzaContent(pizzaContents.get("Margherita Pizza"));
			pizza.setName("Margherita Pizza");
			totalCost += pizzaCosts.get("Margherita Pizza");
		}

		else if (pizzaNumber == 3) {
			pizza.setPizzaContent(pizzaContents.get("Mixed Pizza"));
			pizza.setName("Mixed Pizza");
			totalCost += pizzaCosts.get("Mixed Pizza");
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
			isContentsCorrectlySet = false;
		}

		pizza.setCost(totalCost);

		return isContentsCorrectlySet;
	}

	@Override
	public boolean addTopping(int toppingNumber) {

		List<String> toppings = pizza.getToppings();
		int totalCost = pizza.getCost();
		boolean isToppingsCorrectlySet = false;

		String extraTopping = "";
		String existingTopping = "";

		if (toppingNumber == 0) {
			isToppingsCorrectlySet = true;
			return isToppingsCorrectlySet;
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

			totalCost += 3;
		}

		else if (toppingNumber == 3) {
			if (toppings.contains("Ricotta Cheese")) {
				existingTopping = "Ricotta Cheese";
				extraTopping = "Ricotta Cheese(Extra)";
			}

			else if (!toppings.contains("Ricotta Cheese(Extra)")) {
				toppings.add("Ricotta Cheese");
			}

			totalCost += 5;
		}

		else if (toppingNumber == 4) {
			if (toppings.contains("Sausage")) {
				existingTopping = "Sausage";
				extraTopping = "Sausage(Extra)";
			}

			else if (!toppings.contains("Sausage(Extra)")) {
				toppings.add("Sausage");
			}

			totalCost += 7;
		}

		else if (toppingNumber == 5) {
			if (toppings.contains("Pepperoni")) {
				existingTopping = "Pepperoni";
				extraTopping = "Pepperoni(Extra)";
			}

			else if (!toppings.contains("Pepperoni(Extra)")) {
				toppings.add("Pepperoni");
			}
			totalCost += 7;
		}

		else if (toppingNumber == 6) {
			if (toppings.contains("Salami")) {
				existingTopping = "Salami";
				extraTopping = "Salami(Extra)";
			}

			else if (!toppings.contains("Salami(Extra)")) {
				toppings.add("Salami");
			}

			totalCost += 8;
		}

		else if (toppingNumber == 7) {
			if (toppings.contains("Basil Leaves")) {
				existingTopping = "Basil Leaves";
				extraTopping = "Basil Leaves(Extra)";
			}

			else if (!toppings.contains("Basil Leaves(Extra)")) {
				toppings.add("Basil Leaves");
			}

			totalCost += 7;
		}

		else if (toppingNumber == 8) {
			if (toppings.contains("Cherry Tomato")) {
				existingTopping = "Cherry Tomato";
				extraTopping = "Cherry Tomato(Extra)";
			}

			else if (!toppings.contains("Cherry Tomato(Extra)")) {
				toppings.add("Cherry Tomato");
			}

			totalCost += 7;
		}

		else if (toppingNumber == 9) {
			if (toppings.contains("Jalapeno")) {
				existingTopping = "Jalapeno";
				extraTopping = "Jalapeno(Extra)";
			}

			else if (!toppings.contains("Jalapeno(Extra)")) {
				toppings.add("Jalapeno");
			}

			totalCost += 4;
		}

		else {
			System.out.println("No such topping exists matching with given number!!\n");
		}

		if ((!extraTopping.equals("")) && (!existingTopping.equals(""))) {
			int existingIndex = toppings.indexOf(existingTopping);
			toppings.set(existingIndex, extraTopping);
		}

		pizza.setCost(totalCost);

		return isToppingsCorrectlySet;
	}

}
