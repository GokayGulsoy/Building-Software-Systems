package ceng431.iyte.edu.tr.HW4;

import java.util.Scanner;

public class PizzaShop {

	// starting the application
	public void runPizzaShopApp() {

		Client client = new Client();
		Scanner inputReader = new Scanner(System.in);
		displayPizzaShopSelectionMenu(client, inputReader);
	}

	private void displayPizzaShopSelectionMenu(Client client, Scanner inputReader) {

		boolean invalidShopNumber = true;
		PizzaDecorator specializedPizza = null;

		boolean isExiting = false;
		while (invalidShopNumber) {
			System.out.println("------------------------------------------------------------");
			System.out.println("Please choose pizza shop you want to give an order or exit: ");
			System.out.println("1. Pizza Globale");
			System.out.println("2. Pizza Hat");
			System.out.println("3. Mama Jone's Pizza");
			System.out.println("4. Exit Application");
			System.out.println("------------------------------------------------------------");

			int shopNumber;
			System.out.print("\nEnter shop number or exit: ");
			shopNumber = inputReader.nextInt();

			if (shopNumber == 4) {
				isExiting = true;
				inputReader.close();
				System.out.println("\nEXITED FROM APPLICATION");
				break; // Exit from application
			}

			PizzaFactory pizzaFactory = new PizzaFactory();
			Pizza defaultPizza = new Pizza();
			specializedPizza = pizzaFactory.createSpecialPizza(shopNumber, defaultPizza, client);

			if (specializedPizza != null) {
				invalidShopNumber = false;
			}

			else {
				System.out.println("\nYou have provided an invalid pizza shop number please enter in range(1-3)\n");
			}

		}

		if (isExiting) {
			return;
		}

		displayPizzaOrderMenu(specializedPizza, client, inputReader);
	}

	private void displayPizzaOrderMenu(PizzaDecorator specializedPizza, Client client, Scanner inputReader) {

		while (true) {
			System.out.println("\n1. See or cancel your orders");
			System.out.println("2. Order another pizza");
			System.out.println("3. Go back to pizza shop selection");

			int optionNumber;
			System.out.print("\nEnter option number: ");
			optionNumber = inputReader.nextInt();
			System.out.println();

			if (optionNumber == 1) {
				seeOrCancelOrders(client, inputReader);
			}

			else if (optionNumber == 2) {
				orderPizza(specializedPizza, client, inputReader);
			}

			else if (optionNumber == 3) {
				System.out.println("Completed orders: ");
				client.displayOrders();
				client.resetOrders();

				break;
			}

			else {
				System.out.println("You have provided an invalid option number please enter in range(1-3)\n");
			}

		}

		// going back to pizza shop selection menu
		displayPizzaShopSelectionMenu(client, inputReader);

	}

	private void orderPizza(PizzaDecorator specializedPizza, Client client, Scanner inputReader) {

		Pizza pizza = new Pizza();
		specializedPizza.setPizza(pizza);
		// display menu for specialized pizza
		System.out.println(specializedPizza.toString());
		boolean invalidContent = true;

		// setting pizza content
		while (invalidContent) {
			int pizzaNumber;
			System.out.print("Enter the pizza number: ");
			pizzaNumber = inputReader.nextInt();
			System.out.println();

			boolean isContentSet = specializedPizza.setContents(pizzaNumber);
			if (isContentSet) {
				invalidContent = false;
			}

		}

		specializedPizza.displayToppingList();
		boolean invalidTopping = true;
		// adding pizza toppings
		while (invalidTopping) {

			int toppingNumber;
			System.out.print("Enter the topping number to add a topping or 0 to stop adding new topping: ");
			toppingNumber = inputReader.nextInt();
			System.out.println();

			boolean isToppingSet = specializedPizza.addTopping(toppingNumber);

			if (isToppingSet && toppingNumber == 0) {
				invalidTopping = false;
			}

		}

		client.addOrder(specializedPizza.getPizza());
	}

	private void seeOrCancelOrders(Client client, Scanner inputReader) {

		while (true) {
			System.out.println("Your orders: ");
			client.displayOrders();

			System.out.println("If you want to cancel any of your orders enter order number or 0 if not: ");
			int orderNumber;
			System.out.print("Enter order number or exit: ");
			orderNumber = inputReader.nextInt();
			System.out.println();

			if (orderNumber == 0) {
				break;
			}

			else if (orderNumber >= 1 && orderNumber <= client.getNumberOfOrders()) {
				client.cancelOrder(orderNumber);
			}

			else if (orderNumber >= 1 && orderNumber > client.getNumberOfOrders()) {
				System.out.println("There is no order available to cancel\n");
				break;
			}

			else {
				System.out.println("You have provided an invalid order number\n");
			}
		}

	}

}
