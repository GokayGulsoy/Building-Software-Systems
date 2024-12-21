package iyte.edu.tr.hw1;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/* class that performs the queries on baskets */
public class BasketAnalyzer {

	ArrayList<Basket<Product>> basketList;

	public BasketAnalyzer() {
		FileIO fileReader = new FileIO();
		this.basketList = fileReader.loadBaskets("baskets.csv");
	}

	public void displayAnalysisResults() {
		System.out.println("Analysis Results");
		System.out.println("1- " + getTotalRevenueFromCOLI1Product());
		System.out.println("2- " + getTopSellingProductByQuantity());
		System.out.println("3- Basket " + getMostPaidBasketID());
		System.out.println("4- Basket " + getBasketWithLargestNumberOfDifferentProducts());
		System.out.println("5- " + getMonthWithELPC1SoldMostExpensive());
		System.out.println("6- " + getPriceIncreaseRateOfELHE2InJuly());
	}

	private int getTotalRevenueFromCOLI1Product() {

		int totalRevenueFromCOLI1 = 0;
		for (Basket<Product> basket : basketList) {
			int priceOfCOLI1 = basket.getTotalPriceForProduct("COLI-1");
			totalRevenueFromCOLI1 += priceOfCOLI1;
		}

		return totalRevenueFromCOLI1;
	}

	private Product getTopSellingProductByQuantity() {

		// HashMap to hold quantity of each specific item according to it's ID
		Map<String, Integer> specificProductQuantityMap = new HashMap<String, Integer>();
		FileIO fileReader = new FileIO();
		ProductRepository productRepository = new ProductRepository();
		fileReader.loadProducts(productRepository, "products.csv");

		for (Basket<Product> basket : basketList) {

			String[] productNames = basket.getContainedProductNames();
			for (int i = 0; i < productNames.length; i++) {
				String productName = productNames[i];

				if (specificProductQuantityMap.containsKey(productName)) {
					int quantity = specificProductQuantityMap.get(productName);
					quantity += basket.getQuantityForProduct(productName);

					specificProductQuantityMap.put(productName, quantity);
				}

				else { // if entry does not already exist in the HashMap
					specificProductQuantityMap.put(productName, basket.getQuantityForProduct(productName));
				}

			}
		}

		int highestQuantity = 0;
		String topSellingProductName = null;
		boolean isEntryInitialized = false;
		for (String productName : specificProductQuantityMap.keySet()) {
			if (!isEntryInitialized) { // only executed once to initialize highestQuantity and topSellingProductName
				topSellingProductName = productName;
				highestQuantity = specificProductQuantityMap.get(productName);
				isEntryInitialized = true;
			}

			else {
				int currentEntryQuantity = specificProductQuantityMap.get(productName);
				if (currentEntryQuantity > highestQuantity) {
					highestQuantity = currentEntryQuantity;
					topSellingProductName = productName;
				}
			}
		}

		Product productWithHighestQuantity = productRepository.getProductWithID(topSellingProductName);
		return productWithHighestQuantity;
	}

	private int getMostPaidBasketID() {

		int highestBasketPrice = 0;
		int mostPaidBasketID = 1;

		for (Basket<Product> basket : basketList) {
			int basketPrice = basket.getBasketPrice();

			if (basketPrice > highestBasketPrice) {
				highestBasketPrice = basketPrice;
				mostPaidBasketID = basket.getBasketID();
			}
		}

		return mostPaidBasketID;
	}

	private int getBasketWithLargestNumberOfDifferentProducts() {
		int differentProductCountOfBasket = 0;
		int basketID = 0;

		for (Basket<Product> basket : basketList) {
			int numOfDifferentProducts = basket.getNumberOfDifferentProducts();

			if (numOfDifferentProducts > differentProductCountOfBasket) {
				differentProductCountOfBasket = numOfDifferentProducts;
				basketID = basket.getBasketID();
			}
		}

		return basketID;
	}

	private String getMonthWithELPC1SoldMostExpensive() {

		int highestELPC1Price = 0;
		Month monthMostExpensiveELPC1Sold = null;

		for (Basket<Product> basket : basketList) {
			int priceOfELPC1 = basket.getPriceForProduct("ELPC-1");

			if (priceOfELPC1 == 0) {
				continue; // if basket does not contain ELPC-1 skip that basket
			}

			if (priceOfELPC1 > highestELPC1Price) {
				highestELPC1Price = priceOfELPC1;
				String purchaseDate = basket.getPurchaseDate();
				StringTokenizer tokenizer = new StringTokenizer(purchaseDate, ".");
				int day = Integer.parseInt(tokenizer.nextToken());
				String monthString = tokenizer.nextToken();
				int year = Integer.parseInt(tokenizer.nextToken());

				if (monthString.contains("0")) {
					monthString = monthString.charAt(1) + "";
				}

				int month = Integer.parseInt(monthString);

				LocalDate dateOfPurchase = LocalDate.of(year, month, day);
				monthMostExpensiveELPC1Sold = dateOfPurchase.getMonth();
			}

		}

		String mostExpensiveMonth = monthMostExpensiveELPC1Sold.toString();
		return mostExpensiveMonth.charAt(0)
				+ mostExpensiveMonth.substring(1, mostExpensiveMonth.length()).toLowerCase();
	}

	private String getPriceIncreaseRateOfELHE2InJuly() {

		int priceBeforeIncrease = 0;
		int priceAfterIncrease = 0;

		for (Basket<Product> basket : basketList) {
			String purchaseDate = basket.getPurchaseDate();
			StringTokenizer tokenizer = new StringTokenizer(purchaseDate, ".");
			tokenizer.nextToken();

			String month = tokenizer.nextToken();
			if (month.contains("0")) {
				month = month.charAt(1) + "";
			}

			int monthInt = Integer.parseInt(month);

			if (monthInt != 7) {
				continue; // skip all the months apart from July
			}

			else if (priceBeforeIncrease == 0) {
				priceBeforeIncrease = basket.getPriceForProduct("ELHE-2");

			}

			else {
				priceAfterIncrease = basket.getPriceForProduct("ELHE-2");
			}

		}

		int increaseRate = priceAfterIncrease - priceBeforeIncrease;
		return increaseRate + "%";
	}

}
