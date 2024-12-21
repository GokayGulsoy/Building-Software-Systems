
public class SalesLineItem {

	private int quantity;
	private ProductDescription description;

	public SalesLineItem(ProductDescription description, int quantity) {
		this.description = description;
		this.quantity = quantity;
	}

	public Money getSubtotal() {

		return description.getPrice().times(quantity);
	}

	public int getQuantity() {
		return quantity;
	}

	public ProductDescription getDescription() {
		return description;
	}

}
