import java.util.LinkedHashMap;
import java.util.Map;

public class ProductCatalog {

	private Map<ItemID, ProductDescription> descriptions = new LinkedHashMap<ItemID, ProductDescription>();

	public ProductCatalog() {
		ItemID id1 = new ItemID(100);
		ItemID id2 = new ItemID(200);

		Money price = new Money(3.0);
		
		ProductDescription desc = new ProductDescription(id1, price, "product 1");		
		ProductDescription desc2 = new ProductDescription(id2, price, "product 2");
		
		descriptions.put(id1, desc);
		descriptions.put(id2, desc2);
	}

	public ProductDescription getProductDescription(ItemID id) {
		return descriptions.get(id);
	}

}
