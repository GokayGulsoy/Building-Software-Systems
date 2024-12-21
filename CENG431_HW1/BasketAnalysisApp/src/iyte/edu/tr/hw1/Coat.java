package iyte.edu.tr.hw1;


public class Coat extends Clothing {

	private String coatType;

	public Coat(String productID, String brand, String color, String size, String coatType) {
		super(productID, brand, color, size);
		this.coatType = coatType;
	}

	public String getCoatType() {
		return coatType;
	}

	public void setCoatType(String coatType) {
		this.coatType = coatType;
	}

	@Override
	public String toString() {
		return super.toString() + " coat type: " + coatType;
	}

}
