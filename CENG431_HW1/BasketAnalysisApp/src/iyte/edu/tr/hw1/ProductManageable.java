package iyte.edu.tr.hw1;

import java.util.ArrayList;

public interface ProductManageable {

	/**
	 * method that returns ArrayList of Product objects that is available in the
	 * ProductRepository object
	 * 
	 * @return ArrayList<Product> that contains all the products available in
	 *         products.csv file
	 */
	public ArrayList<Product> getRepository();

	/**
	 * method that adds the product object given as an argument to ProductRepository
	 * object
	 * 
	 * @param anyProduct Product object that is to be added to ProductRepository
	 *                   object
	 */
	public void addProduct(Product anyProduct);

	/**
	 * method that returns the Product object matching with productID argument
	 * 
	 * @param productID String that uniquely represents the Product object
	 * @return Product that matches with productID argument
	 */
	public Product getProductWithID(String productID);

}
