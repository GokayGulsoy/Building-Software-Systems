package iyte.edu.tr.hw1;

public class PC extends Electronic {

	private int RAMCapacity;
	private int SSDCapacity;
	private String CPU;

	public PC(String title, String productID, int RAMCapacity, int SSDCapacity, String CPU) {
		super(title, productID);
		this.RAMCapacity = RAMCapacity;
		this.SSDCapacity = SSDCapacity;
		this.CPU = CPU;
	}

	public int getRAMCapacity() {
		return RAMCapacity;
	}

	public int SSDCapacity() {
		return SSDCapacity;
	}

	public String getCPU() {
		return CPU;
	}

	public void setRAMCapacity(int RAMCapacity) {
		this.RAMCapacity = RAMCapacity;
	}

	public void setSSDCapacity(int SSDCapacity) {
		this.SSDCapacity = SSDCapacity;
	}

	public void setCPU(String CPU) {
		this.CPU = CPU;
	}

	@Override
	public String toString() {
		return super.toString() + " RAM capacity: " + RAMCapacity + " SSD Capacity: " + SSDCapacity + " CPU: " + CPU;
	}

}
