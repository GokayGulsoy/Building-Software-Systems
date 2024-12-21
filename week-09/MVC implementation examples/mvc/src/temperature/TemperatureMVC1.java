package temperature;

public class TemperatureMVC1 {	
	public static void main(String args[]) {	
		TemperatureModel temperature = new TemperatureModel();
		new TemperatureViewFahrenheit("Fahrenheit View",temperature, 100, 100);
	}
}

