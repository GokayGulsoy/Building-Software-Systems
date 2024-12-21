package iyte.edu.tr.hw1;

/*Base class for all products*/
public abstract class Product implements Cloneable {

	private final String PRODUCT_ID;

	public Product(String productID) {
		this.PRODUCT_ID = productID;
	}

	public String getProuctID() {
		return PRODUCT_ID;
	}

	@Override
	public String toString() {
		return PRODUCT_ID;
	}

	@Override
	public Object clone() {

		try {
			return super.clone();
		}

		catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return null;
	}

}
