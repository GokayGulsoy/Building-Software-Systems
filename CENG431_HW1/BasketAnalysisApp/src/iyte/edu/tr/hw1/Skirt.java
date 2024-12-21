package iyte.edu.tr.hw1;

public class Skirt extends Clothing {

	private String hemline;

	public Skirt(String productID, String brand, String color, String size, String hemline) {
		super(productID, brand, color, size);
		this.hemline = hemline;

	}

	public String getHemline() {
		return hemline;
	}

	public void setHemline(String hemline) {
		this.hemline = hemline;
	}

	@Override
	public String toString() {
		return super.toString() + " hemline: " + hemline;
	}

}
