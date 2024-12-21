package iyte.edu.tr.hw1;

/*subclass of Product that represents cloathing products*/
public abstract class Clothing extends Product {

	private String brand;
	private String color;
	private String size;

	public Clothing(String productID, String brand, String color, String size) {
		super(productID);
		this.brand = brand;
		this.color = color;
		this.size = size;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	public String getSize() {
		return size;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return super.toString() + " brand: " + brand + " color: " + color + " size: " + size;
	}

}
