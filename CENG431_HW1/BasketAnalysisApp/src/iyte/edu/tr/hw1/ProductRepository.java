package iyte.edu.tr.hw1;

import java.util.ArrayList;

/* class that holds all the products which 
 * are constructed via reading the products.csv file 
*/
public class ProductRepository implements ProductManageable {

	private ArrayList<Product> productRepository;

	public ProductRepository() {
		productRepository = new ArrayList<Product>();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Product> getRepository() {
		ArrayList<Product> cloneRepository = (ArrayList<Product>) productRepository.clone();
		return cloneRepository;
	}

	public void addProduct(Product anyProduct) {
		productRepository.add(anyProduct);
	}

	public Product getProductWithID(String productID) {
		for (Product product : productRepository) {
			if (productID.equals(product.getProuctID())) {
				return (Product) product.clone();
			}
		}

		return null;
	}

	@Override
	public String toString() {
		String productRepoString = "";
		for (Product product : productRepository) {
			productRepoString += product.toString() + "\n";
		}

		return productRepoString;
	}

}
