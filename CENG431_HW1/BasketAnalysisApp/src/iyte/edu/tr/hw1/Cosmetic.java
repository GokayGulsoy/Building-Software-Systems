package iyte.edu.tr.hw1;

/*subclass of Product that represents cosmetic products*/
public abstract class Cosmetic extends Product {

	private String brand;
	private final int ML;

	public Cosmetic(String productID, String brand, int ML) {
		super(productID);
		this.brand = brand;
		this.ML = ML;
	}

	public String getBrand() {
		return brand;
	}

	public int getML() {
		return ML;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return super.toString() + " brand: " + brand + " ML: " + ML;
	}

}
