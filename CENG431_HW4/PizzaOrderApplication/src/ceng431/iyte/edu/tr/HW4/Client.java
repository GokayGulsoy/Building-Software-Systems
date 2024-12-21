package ceng431.iyte.edu.tr.HW4;

import java.util.ArrayList;
import java.util.List;

public class Client {

	private String orderedShopName;
	private List<Pizza> orders;

	public Client() {
		orderedShopName = null;
		orders = new ArrayList<Pizza>();
	}

	public String getOrderedShopName() {
		return this.orderedShopName;
	}

	public void setOrderedShopName(String orderedShopName) {
		this.orderedShopName = orderedShopName;
	}

	public void addOrder(Pizza order) {
		orders.add(order);
	}

	public int getNumberOfOrders() {
		return this.orders.size();
	}

	public void cancelOrder(int orderNumber) {

		if (orders.size() > 0) {
			orders.remove(orderNumber - 1);
		}
	}

	public void resetOrders() {
		// reset orders after leaving
		// current shop
		orders.clear();
	}

	public void displayOrders() {

		System.out.println("------------------------------");
		System.out.println("Pizza Shop: " + orderedShopName);
		System.out.println("Pizzas: ");

		int totalCost = 0;
		int pizzaNumber = 1;

		int numberOfPizzas = orders.size();
		for (Pizza pizza : orders) {
			System.out.println(pizzaNumber + ". " + pizza.toString());
			pizzaNumber++;
			totalCost += pizza.getCost();
		}

		System.out.println("Total Number of Pizzas: " + numberOfPizzas);
		System.out.println("Total Cost: " + totalCost + "₺");
		System.out.println("------------------------------\n");
	}

}
