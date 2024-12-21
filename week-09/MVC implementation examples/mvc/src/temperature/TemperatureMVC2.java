package temperature;

public class TemperatureMVC2 {
	public static void main(String args[]) {	
		TemperatureModel temperature = new TemperatureModel();
		new TemperatureViewFahrenheit("Fahrenheit View",temperature, 100, 100);
		new TemperatureViewCelcius("Celcius View",temperature, 100, 300);
	}
}
