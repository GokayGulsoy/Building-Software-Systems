package iyte.edu.tr.hw1;

public class Monitor extends Electronic {

	private String screenSize;

	public Monitor(String title, String productID, String screenSize) {
		super(title, productID);
		this.screenSize = screenSize;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	@Override
	public String toString() {
		return super.toString() + " screen size: " + screenSize;
	}

}
