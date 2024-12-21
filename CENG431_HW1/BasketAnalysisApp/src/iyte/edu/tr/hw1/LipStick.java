package iyte.edu.tr.hw1;

public class LipStick extends Cosmetic {

	private String lipstickType;
	private String color;

	public LipStick(String productID, String brand, int ML, String lipstickType, String color) {
		super(productID, brand, ML);
		this.lipstickType = lipstickType;
		this.color = color;
	}

	public String getLipstickType() {
		return lipstickType;
	}

	public String getColor() {
		return color;
	}

	public void setLipStickType(String lipstickType) {
		this.lipstickType = lipstickType;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return super.toString() + " lipstick type: " + lipstickType + " color: " + color;
	}

}
