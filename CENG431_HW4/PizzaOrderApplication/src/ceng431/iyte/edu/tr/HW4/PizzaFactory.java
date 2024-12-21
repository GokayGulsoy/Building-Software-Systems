package ceng431.iyte.edu.tr.HW4;

public class PizzaFactory {

	public PizzaDecorator createSpecialPizza(int pizzaShopNumber, Pizza pizza, Client client) {

		if (pizzaShopNumber == 1) {
			client.setOrderedShopName("Pizza Globale");
			return new PizzaGlobaleDecorator(pizza);
		}

		else if (pizzaShopNumber == 2) {
			client.setOrderedShopName("Pizza Hat");
			return new PizzaHatDecorator(pizza);
		}

		else if (pizzaShopNumber == 3) {
			client.setOrderedShopName("Mama Jones' Pizza");
			return new MammaJonesPizzaDecorator(pizza);
		}

		else { // in case of invalid pizzaShopNumber is given
			return null;
		}

	}

}
