package iyte.edu.tr.hw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Basket<T extends Product> implements Storeable {

	private ArrayList<T> basket;
	private Map<String, Integer> productQuantityMap;
	private Map<String, Integer> productPriceMap;

	private final int BASKET_ID;
	private String purchaseDate;

	public Basket(int basketID) {
		this.BASKET_ID = basketID;
		this.basket = new ArrayList<T>();
		this.productQuantityMap = new HashMap<String, Integer>();
		this.productPriceMap = new HashMap<String, Integer>();
		this.purchaseDate = null;
	}

	public int getBasketID() {
		return BASKET_ID;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	private ProductRepository getProductRepository() {
		FileIO fileReader = new FileIO();
		ProductRepository productRepository = new ProductRepository();
		fileReader.loadProducts(productRepository, "products.csv");

		return productRepository;
	}

	public int getQuantityForProduct(String productID) {
		return productQuantityMap.get(productID);
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public void addToBasket(String productID, int productPrice, int productQuantity) {
		ProductRepository productRepository = getProductRepository();
		@SuppressWarnings("unchecked")
		T newProduct = (T) productRepository.getProductWithID(productID);

		if (newProduct != null) {
			basket.add(newProduct);
		}

		else {
			System.out.println("product with given ID does not exist in the product repository!!");
			System.exit(0);
		}

		ProductCategories[] productCategories = ProductCategories.values();
		ProductCategories hashMapProductEntry = null;

		for (ProductCategories productCategory : productCategories) {
			if (productID.contains(productCategory.category)) {
				hashMapProductEntry = productCategory;
				break;
			}
		}

		if (hashMapProductEntry == null) {
			System.out.println("provided product is not recognized as a valid product!!");
			System.exit(0);
		}

		productQuantityMap.put(productID, productQuantity);
		productPriceMap.put(productID, productPrice);

	}

	public int getPriceForProduct(String productID) {
		boolean isFound = false;

		for (String product : productPriceMap.keySet()) {
			if (productID.equals(product)) {
				isFound = true;
				break;
			}
		}

		if (!isFound) {
			return 0;
		}

		else {
			int pricePerProduct = productPriceMap.get(productID);
			return pricePerProduct;

		}
	}

	public int getTotalPriceForProduct(String productID) {
		boolean isFound = false;

		for (String product : productPriceMap.keySet()) {
			if (productID.equals(product)) {
				isFound = true;
				break;
			}
		}

		if (!isFound) {
			return 0;
		}

		else {
			int pricePerProduct = productPriceMap.get(productID);
			int quantity = productQuantityMap.get(productID);
			return pricePerProduct * quantity;

		}
	}

	public String[] getContainedProductNames() {

		int size = productPriceMap.size();
		String[] keys = new String[size];

		int index = 0;
		for (String key : productPriceMap.keySet()) {
			keys[index] = key;
			index++;
		}

		return keys;
	}

	public int getBasketPrice() {

		int totalBasketPrice = 0;
		for (String productName : productPriceMap.keySet()) {
			int productPrice = getTotalPriceForProduct(productName);
			totalBasketPrice += productPrice;
		}

		return totalBasketPrice;
	}

	public int getNumberOfDifferentProducts() {
		int numOfDifferentEntries = 0;

		for (@SuppressWarnings("unused")
		String product : productPriceMap.keySet()) {
			numOfDifferentEntries++;
		}

		return numOfDifferentEntries;
	}

	@Override
	public String toString() {
		String basketContent = "Basket content for basket " + BASKET_ID + "\n";
		for (T product : basket) {
			basketContent += product.toString() + "\n";
		}

		basketContent += productQuantityMap.toString() + "\n";
		basketContent += productPriceMap.toString() + "\n";
		basketContent += "Purchase Date: " + purchaseDate + "\n";
		return basketContent;
	}

}
