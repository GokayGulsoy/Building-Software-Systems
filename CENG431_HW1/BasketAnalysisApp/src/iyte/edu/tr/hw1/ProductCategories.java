package iyte.edu.tr.hw1;

public enum ProductCategories {
	ELPC("ELPC"), ELMO("ELMO"), ELPH("ELPH"), ELHE("ELHE"), CLCO("CLCO"), CLSK("CLSK"), COPE("COPE"), COLI("COLI");

	public final String category;

	private ProductCategories(String category) {
		this.category = category;
	}

}
