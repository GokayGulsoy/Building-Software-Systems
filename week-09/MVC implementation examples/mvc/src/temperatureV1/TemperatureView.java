package temperatureV1;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("deprecation")
public abstract class TemperatureView implements java.util.Observer {
	protected TemperatureModel model;
	protected Frame temperatureFrame;
	protected TextField display;
	protected Button upButton;
	protected Button downButton;

	TemperatureView(String label, TemperatureModel model, int h, int v) {
		this.model = model;
		display = new TextField();
		temperatureFrame = new Frame(label);
		temperatureFrame.add("Center", display);
		temperatureFrame.addWindowListener(new CloseListener());
		model.addObserver(this); // Connect the View to the Model
		upButton = new Button(" > ");
		downButton = new Button(" <");
		Panel buttons = new Panel();
		buttons.add(upButton);
		buttons.add(downButton);
		temperatureFrame.add("South", buttons);
		temperatureFrame.setSize(200, 150);
		temperatureFrame.setLocation(h, v);
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

	public void addUpListener(ActionListener a) {
		upButton.addActionListener(a);
	}

	public void addDownListener(ActionListener a) {
		downButton.addActionListener(a);
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

}
