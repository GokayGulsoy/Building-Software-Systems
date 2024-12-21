package temperatureV1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class TemperatureViewFahrenheit extends TemperatureView {

	TemperatureViewFahrenheit(String label, TemperatureModel model, int h, int v) {
		super(label, model, h, v);
		DecimalFormat df = new DecimalFormat("#.##"); // display to 2 decimal places
		display.setText("" + df.format(model().getF())); // display initial value
		temperatureFrame.setVisible(true);
		addDisplayListener(new DisplayListener()); // listen for updates to text field
		addUpListener(new UpListener());
		addDownListener(new DownListener());
	}

	public void update(Observable t, Object o) { // when observers are notified by model
		DecimalFormat df = new DecimalFormat("#.##");
		display.setText("" + df.format(model().getF()));
	}

	class DisplayListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double value = getDisplay();
			model().setF(value);
		}
	}

	class UpListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model().setF(model().getF() + 1.0);
		}
	}

	class DownListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model().setF(model().getF() - 1.0);
		}
	}

}
