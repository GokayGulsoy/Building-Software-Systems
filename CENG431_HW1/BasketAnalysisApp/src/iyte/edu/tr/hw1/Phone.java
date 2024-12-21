package iyte.edu.tr.hw1;

public class Phone extends Electronic {

	private int memoryCapacity;

	public Phone(String title, String productID, int memoryCapacity) {
		super(title, productID);
		this.memoryCapacity = memoryCapacity;
	}

	public int getMemoryCapacity() {
		return memoryCapacity;
	}

	public void setMemoryCapacity(int memoryCapacity) {
		this.memoryCapacity = memoryCapacity;
	}

	@Override
	public String toString() {
		return super.toString() + " memory capacity: " + memoryCapacity;
	}

}
