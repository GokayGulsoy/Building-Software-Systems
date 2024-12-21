package temperatureV1;

@SuppressWarnings("deprecation")
public class TemperatureModel extends java.util.Observable {
	// A simple model for storing and converting temperatures
	private double minTempF = -100; // minimum limit
	private double maxTempF = 200; // maximum limit
	private double temperatureF = 32.0; // current temperature
	private double minReachedF = temperatureF; // minimum reached since initial state
	private double maxReachedF = temperatureF; // maximum reached since intitial state

	public double getF() {
		return temperatureF;
	}

	public double getC() {
		return (temperatureF - 32.0) * 5.0 / 9.0;
	}

	public double getMaxF() {
		return maxTempF;
	}

	public double getMinF() {
		return minTempF;
	}

	public double getMaxReachedF() {
		return maxReachedF;
	}

	public double getMinReachedF() {
		return minReachedF;
	}

	public void setC(double tempC) {
		setF(tempC * 9.0 / 5.0 + 32.0);
	}

	public void setF(double tempF) {

		temperatureF = tempF;

		if (temperatureF > maxTempF)
			temperatureF = maxTempF;
		else if (temperatureF < minTempF)
			temperatureF = minTempF;

		if (temperatureF > maxReachedF)
			maxReachedF = temperatureF;
		else if (temperatureF < minReachedF)
			minReachedF = temperatureF;

		setChanged();
		notifyObservers();
	}

}
