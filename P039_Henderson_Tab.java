import javax.swing.JPanel;
import java.awt.GridLayout;

public class P039_Henderson_Tab extends JPanel {

	public P039_Henderson_Tab() {
		this.setLayout(new GridLayout(5, 5));
		for (int i = 0; i < 25; i ++) {
			this.add(new P01_Dang_Panel(i));
		}

	}
}