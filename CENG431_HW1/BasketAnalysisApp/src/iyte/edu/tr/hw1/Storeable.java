package iyte.edu.tr.hw1;

public interface Storeable {

	/**
	 * method that returns the ID of basket that uniquely identifies each basket
	 * object
	 * 
	 * @return int ID that uniquely identifies basket object
	 */
	public int getBasketID();

	/**
	 * method that returns the total price of all items in basket
	 * 
	 * @return int basket price that uniquely identifies basket object
	 */
	public int getBasketPrice();

	/**
	 * method that returns String array holding name of each product contained
	 * inside the basket
	 * 
	 * @return String[] array that contains the name of each product
	 */
	public String[] getContainedProductNames();

	/**
	 * method that returns the quantity of product matching the given productID
	 * 
	 * @return int that represents the quantity of product with given productID
	 */
	public int getQuantityForProduct(String productID);

	/**
	 * method that returns the purchaseDate field of basket object
	 * 
	 * @return purchaseDate String that represents the purchaseDate to be returned
	 */
	public String getPurchaseDate();

	/**
	 * method that sets the purchaseDate field of basket object to given
	 * purchaseDate
	 * 
	 * @param purchaseDate String that represents the purchaseDate to be set
	 */
	public void setPurchaseDate(String purchaseDate);

	/**
	 * method that adds the product to basket matching with productID
	 * 
	 * @param productID       String that uniquely represent the product object
	 * @param productPrice    int that represents the price of product matching with
	 *                        productID
	 * @param productQuantity int that represents the quantity of product matching
	 *                        with productID
	 */
	public void addToBasket(String productID, int productPrice, int productQuantity);

	/**
	 * method that gets the price of product matching with productID
	 * 
	 * @param productID that uniquely represent the product object
	 * @return int price of the product matching with productID
	 */
	public int getPriceForProduct(String productID);

	/**
	 * method that gets the total price of products matching with productID
	 * 
	 * @param productID String that uniquely represent the product object
	 * @return int total price of the product obtained by multiplying product
	 *         quantity with price of product
	 */
	public int getTotalPriceForProduct(String productID);

	/**
	 * method that returns the number of different products contained in basket via
	 * checking products according to their productIDs
	 * 
	 * @return int number of different products contained inside the basket
	 */
	public int getNumberOfDifferentProducts();

}
