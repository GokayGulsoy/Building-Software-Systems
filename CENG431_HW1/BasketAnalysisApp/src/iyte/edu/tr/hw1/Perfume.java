package iyte.edu.tr.hw1;

public class Perfume extends Cosmetic {

	private String fragranceType;

	public Perfume(String productID, String brand, int ML, String fragranceType) {
		super(productID, brand, ML);
		this.fragranceType = fragranceType;

	}

	public String getFragranceType() {
		return fragranceType;
	}

	public void setFragranceType(String fragranceType) {
		this.fragranceType = fragranceType;
	}

	@Override
	public String toString() {
		return super.toString() + " fragrance type: " + fragranceType;
	}

}
