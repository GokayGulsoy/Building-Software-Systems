package temperature;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class TemperatureViewFahrenheit implements java.util.Observer {
	private TemperatureModel model;
	private Frame temperatureFrame;
	private TextField display;

	TemperatureViewFahrenheit(String label, TemperatureModel model, int h, int v) {
		this.model = model;
		display = new TextField();
		DecimalFormat df = new DecimalFormat("#.##"); // display to 2 decimal places
		display.setText("" + df.format(model().getF())); // display initial value
		temperatureFrame = new Frame(label);
		temperatureFrame.add("Center", display);
		temperatureFrame.addWindowListener(new CloseListener());
		model.addObserver(this); // Connect the View to the Model
		temperatureFrame.setSize(200, 150);
		temperatureFrame.setLocation(h, v);
		temperatureFrame.setVisible(true);
		addDisplayListener(new DisplayListener()); // listen for updates to text field
	}

	public void update(Observable t, Object o) { // when observers are notified by model
		DecimalFormat df = new DecimalFormat("#.##");
		display.setText("" + df.format(model().getF()));
	}

	public double getDisplay() {
		double result = 0.0; // default when edit nonsensical
		try {
			result = Double.valueOf(display.getText()).doubleValue();
		} catch (NumberFormatException e) {
		}
		return result;
	}

	public void addDisplayListener(ActionListener a) {
		display.addActionListener(a);
	}

	protected TemperatureModel model() {
		return model;
	}

	public static class CloseListener extends WindowAdapter { // close all related windows
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}

	class DisplayListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double value = getDisplay();
			model().setF(value);
		}
	}

}
