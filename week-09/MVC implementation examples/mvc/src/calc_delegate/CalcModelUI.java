// Fred Swartz -- December 2004

//   Calculator with separation of presentation and model.
// Program Organization: Separate View+Controller and Model

package calc_delegate;

import javax.swing.*;

public class CalcModelUI {
    public static void main(String[] args) {
        JFrame presentation = new CalcViewController();
        presentation.setVisible(true);
    }
}