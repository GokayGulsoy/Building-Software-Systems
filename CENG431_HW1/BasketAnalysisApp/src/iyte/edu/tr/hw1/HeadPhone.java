package iyte.edu.tr.hw1;

public class HeadPhone extends Electronic {

	private int bluetoothVersion;

	public HeadPhone(String title, String productID, int bluetoothVersion) {
		super(title, productID);
		this.bluetoothVersion = bluetoothVersion;
	}

	public int getBluetoothVersion() {
		return bluetoothVersion;
	}

	public void setBluetoothVersino(int bluetoothVersion) {
		this.bluetoothVersion = bluetoothVersion;
	}

	@Override
	public String toString() {
		return super.toString() + " bluetooth version: " + bluetoothVersion;
	}

}
