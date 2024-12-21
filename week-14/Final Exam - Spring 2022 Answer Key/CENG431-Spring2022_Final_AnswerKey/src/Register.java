
public class Register {
	private ProductCatalog productCatalog;
	private Sale currentSale;
	
	public Register(ProductCatalog productCatalog) {
		this.productCatalog = productCatalog;
	}
	
	public void endSale() {
		currentSale.becomeComplete();
	}
	
	public void enterItem(ItemID id, int quantity) {
		ProductDescription productDescription = productCatalog.getProductDescription(id);
		currentSale.makeLineItem(productDescription, quantity);
		
	}
	
	public void makeNewSale() {
		currentSale  = new Sale();
	}

	public ProductCatalog getProductCatalog() {
		return productCatalog;
	}
	
	public void makePayment(Money cashTendered) {
		currentSale.makePayment(cashTendered);
	}


	public Sale getCurrentSale() {
		return currentSale;
	}

}
