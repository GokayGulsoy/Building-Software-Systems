package ceng431.iyte.edu.tr.HW4;

import java.util.List;

public class PizzaHatDecorator extends PizzaDecorator {

	public PizzaHatDecorator(Pizza pizza) {
		super(pizza);

		// available pizzas
		pizzaContents.put("Ranch House Pizza", "Tomato Sauce, Mozzarella Cheese, Salami, Mushroom");
		pizzaContents.put("Margherita Pizza", "Tomato Sauce, Mozzarella Cheese, Cherry Tomato, Basil Leaves");
		pizzaContents.put("Pepperoni Pizza", "Tomato Sauce, Mozzarella Cheese, Pepperoni");
		pizzaContents.put("Barbeque Chicken Pizza", "Tomato Sauce, Mozzarella Cheese, Chicken Slices, Barbeque Sauce");

		pizzaCosts.put("Ranch House Pizza", 50);
		pizzaCosts.put("Margherita Pizza", 53);
		pizzaCosts.put("Pepperoni Pizza", 48);
		pizzaCosts.put("Barbeque Chicken Pizza", 57);

		// available toppings
		toppings.add("Parmesan Cheese – Cost: 4₺");
		toppings.add("Olive – Cost: 3₺");
		toppings.add("Sweet Corn – Cost: 3₺");
		toppings.add("Ricotta Cheese – Cost: 5₺");
		toppings.add("Sausage – Cost: 8₺");
		toppings.add("Pepperoni – Cost: 8₺");
		toppings.add("Salami – Cost 9₺");
		toppings.add("Basil Leaves – Cost 5₺");
		toppings.add("Cherry Tomato – Cost 6₺");
		toppings.add("Mushroom – Cost: 7₺");
		toppings.add("Jalapeno – Cost: 4₺");
	}

	@Override
	public boolean setContents(int pizzaNumber) {

		int totalCost = pizza.getCost();
		boolean isCorrectlySet = true;

		if (pizzaNumber == 1) {
			pizza.setPizzaContent(pizzaContents.get("Ranch House Pizza"));
			pizza.setName("Ranch House Pizza");
			totalCost += pizzaCosts.get("Ranch House Pizza");
		}

		else if (pizzaNumber == 2) {
			pizza.setPizzaContent(pizzaContents.get("Margherita Pizza"));
			pizza.setName("Margherita Pizza");
			totalCost += pizzaCosts.get("Margherita Pizza");
		}

		else if (pizzaNumber == 3) {
			pizza.setPizzaContent(pizzaContents.get("Pepperoni Pizza"));
			pizza.setName("Pepperoni Pizza");
			totalCost += pizzaCosts.get("Pepperoni Pizza");
		}

		else if (pizzaNumber == 4) {
			pizza.setPizzaContent(pizzaContents.get("Barbeque Chicken Pizza"));
			pizza.setName("Barbeque Chicken Pizza");
			totalCost += pizzaCosts.get("Barbeque Chicken Pizza");
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

			totalCost += 4;
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

			totalCost += 5;
		}

		else if (toppingNumber == 5) {
			if (toppings.contains("Sausage")) {
				existingTopping = "Sausage";
				extraTopping = "Sausage(Extra)";
			}

			else if (!toppings.contains("Sausage(Extra)")) {
				toppings.add("Sausage");
			}

			totalCost += 8;
		}

		else if (toppingNumber == 6) {
			if (toppings.contains("Pepperoni")) {
				existingTopping = "Pepperoni";
				extraTopping = "Pepperoni(Extra)";
			}

			else if (!toppings.contains("Pepperoni(Extra)")) {
				toppings.add("Pepperoni");
			}

			totalCost += 8;
		}

		else if (toppingNumber == 7) {
			if (toppings.contains("Salami")) {
				existingTopping = "Salami";
				extraTopping = "Salami(Extra)";
			}

			else if (!toppings.contains("Salami(Extra)")) {
				toppings.add("Salami");
			}

			totalCost += 9;
		}

		else if (toppingNumber == 8) {
			if (toppings.contains("Basil Leaves")) {
				existingTopping = "Basil Leaves";
				extraTopping = "Basil Leaves(Extra)";
			}

			else if (!toppings.contains("Basil Leaves(Extra)")) {
				toppings.add("Basil Leaves");
			}

			totalCost += 5;
		}

		else if (toppingNumber == 9) {
			if (toppings.contains("Cherry Tomato")) {
				existingTopping = "Cherry Tomato";
				extraTopping = "Cherry Tomato(Extra)";
			}

			else if (!toppings.contains("Cherry Tomato(Extra)")) {
				toppings.add("Cherry Tomato");
			}

			totalCost += 6;
		}

		else if (toppingNumber == 10) {
			if (toppings.contains("Mushroom")) {
				existingTopping = "Mushroom";
				extraTopping = "Mushroom(Extra)";
			}

			else if (!toppings.contains("Mushroom(Extra)")) {
				toppings.add("Mushroom");
			}

			totalCost += 7;
		}

		else if (toppingNumber == 11) {
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

		return isToppingCorrectlySet;
	}

}
