package iyte.edu.tr.hw1;

/*subclass of Product that represents electronic products*/
public abstract class Electronic extends Product {

	private String title;

	public Electronic(String title, String productID) {
		super(productID);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return super.toString() + " title: " + title;
	}

}
