package iyte.edu.tr.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileIO {

	private Scanner fileReader;

	public FileIO() {
		fileReader = null;
	}

	public void loadProducts(ProductRepository productRepo, String fileName) {

		File file = null;

		try {

			file = new File(fileName);
			fileReader = new Scanner(file);

			// checking whether more lines exist
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, ";");
				if (tokenizer.hasMoreTokens()) {
					String productID = tokenizer.nextToken();

					tokenizeProductAndAddToRepo(productRepo, productID, tokenizer);
				}

			}

		}

		catch (FileNotFoundException e) {
			System.out.println("File with name " + fileName + " not found!!");
			System.exit(0);
		}

	}

	private void tokenizeProductAndAddToRepo(ProductRepository prodRepo, String productID, StringTokenizer tokenizer) {

		if (productID.contains(ProductCategories.ELPC.category)) {
			String title = tokenizer.nextToken();
			int RAMCapacity = Integer.parseInt(tokenizer.nextToken());
			int SSDCapacity = Integer.parseInt(tokenizer.nextToken());
			String CPU = tokenizer.nextToken();

			PC pc = new PC(title, productID, RAMCapacity, SSDCapacity, CPU);
			prodRepo.addProduct(pc);
			// System.out.println(pc);
		}

		else if (productID.contains(ProductCategories.ELMO.category)) {
			String title = tokenizer.nextToken();
			String screenSize = tokenizer.nextToken();

			Monitor monitor = new Monitor(title, productID, screenSize);
			prodRepo.addProduct(monitor);
			// System.out.println(monitor);

		}

		else if (productID.contains(ProductCategories.ELPH.category)) {

			String title = tokenizer.nextToken();
			int memoryCapacity = Integer.parseInt(tokenizer.nextToken());

			Phone phone = new Phone(title, productID, memoryCapacity);
			prodRepo.addProduct(phone);
			// System.out.println(phone);

		}

		else if (productID.contains(ProductCategories.ELHE.category)) {

			String title = tokenizer.nextToken();
			int bluetoothVersion = Integer.parseInt(tokenizer.nextToken());

			HeadPhone headPhone = new HeadPhone(title, productID, bluetoothVersion);
			prodRepo.addProduct(headPhone);
			// System.out.println(headPhone);
		}

		else if (productID.contains(ProductCategories.CLCO.category)) {
			String brand = tokenizer.nextToken();
			String size = tokenizer.nextToken();
			String color = tokenizer.nextToken();
			String coatType = tokenizer.nextToken();

			Coat coat = new Coat(productID, brand, color, size, coatType);
			prodRepo.addProduct(coat);
			// System.out.println(coat);
		}

		else if (productID.contains(ProductCategories.CLSK.category)) {
			String brand = tokenizer.nextToken();
			String size = tokenizer.nextToken();
			String color = tokenizer.nextToken();
			String hemline = tokenizer.nextToken();

			Skirt skirt = new Skirt(productID, brand, color, size, hemline);
			prodRepo.addProduct(skirt);
			// System.out.println(skirt);
		}

		else if (productID.contains(ProductCategories.COPE.category)) {
			String brand = tokenizer.nextToken();
			int ML = Integer.parseInt(tokenizer.nextToken());
			String fragranceType = tokenizer.nextToken();

			Perfume perfume = new Perfume(productID, brand, ML, fragranceType);
			prodRepo.addProduct(perfume);
			// System.out.println(perfume);
		}

		else if (productID.contains(ProductCategories.COLI.category)) {
			String brand = tokenizer.nextToken();
			int ML = Integer.parseInt(tokenizer.nextToken());
			String lipstickType = tokenizer.nextToken();
			String color = tokenizer.nextToken();

			LipStick lipstick = new LipStick(productID, brand, ML, lipstickType, color);
			prodRepo.addProduct(lipstick);
			// System.out.println(lipstick);
		}

	}

	public ArrayList<Basket<Product>> loadBaskets(String fileName) {

		File file = null;
		ArrayList<Basket<Product>> basketList = new ArrayList<Basket<Product>>();

		try {

			file = new File(fileName);
			fileReader = new Scanner(file);

			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, ";");
				// first token of each line represents basket ID
				if (tokenizer.hasMoreTokens()) {
					int basketID = Integer.parseInt(tokenizer.nextToken());
					Basket<Product> basket = new Basket<Product>(basketID);
					tokenizeBasketEntryAndAddToList(basketList, basket, tokenizer);
				}
			}

		}

		catch (FileNotFoundException e) {
			System.out.println("File with name " + fileName + " not found!!");
			System.exit(0);
		}

		return basketList;

	}

	private void tokenizeBasketEntryAndAddToList(ArrayList<Basket<Product>> basketList, Basket<Product> basket,
			StringTokenizer tokenizer) {
		while (tokenizer.hasMoreTokens()) {
			String purchaseDateOrProductID = tokenizer.nextToken();

			if (purchaseDateOrProductID.contains(".")) {
				String purchaseDate = purchaseDateOrProductID;
				basket.setPurchaseDate(purchaseDate);

				basketList.add(basket);
				break; // purchase date is last entry
			}

			else {
				String productID = purchaseDateOrProductID;
				// System.out.println(productID);
				int productPrice = Integer.parseInt(tokenizer.nextToken());
				// System.out.println(productPrice);
				int productQuantity = Integer.parseInt(tokenizer.nextToken());

				basket.addToBasket(productID, productPrice, productQuantity);
			}

		}

	}

}
