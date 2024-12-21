
public class Store {
	private ProductCatalog productCatalog = new ProductCatalog();
	private Register register = new Register(productCatalog);
	
	public Store() {
		
	}

	public Register getRegister() {
		return register;
	}

}
