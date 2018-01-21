import java.awt.*; 
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
* P01_CHANGEME_Panel describes a panel consisting of two rows of JLabels.
* Row one is the last name of the author of this class. Row two is a counter
* that is either incrementing upward from 0, or decrementing downward from 9.
* The mode of the counter, as well as the background color, are determined by
* PanelType of the given instance.
*
* @author Christopher Henderson, chris@chenderson.org
* @version 1.0
*/ 
public class P01_CHANGEME_Panel extends JPanel {

	public static final String name = "CHANGEME";
	public final PanelType type;

	/**
	* PanelType describes the possible background/counter modes of P01_CHANGEME_Panel.
	*/
	public enum PanelType {
		WhiteIncrementor,
		LightBlueDecrementor,
	};

	/**
	* Class constructor.
	* @param	typeSwitch	Describes the background and counter mode of the panel. Even numbers result in a PanelType.WhiteIncrementor, odd give a PanelType.LightBlueDecrementor.
	*/
	public P01_CHANGEME_Panel(int typeSwitch) {
		// Even is white and counts up, odd is light blue and counts down.
		type = (typeSwitch % 2 == 0) ? PanelType.WhiteIncrementor : PanelType.LightBlueDecrementor;
		// Two rows, one column. Row 1 is the name, row 2 is the counter.
		this.setLayout(new GridLayout(2, 1));
		this.setBackground((type == PanelType.WhiteIncrementor) ? Color.white : new Color(90,150,255));
		JLabel nameLabel = new JLabel(name);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setFont(new Font("Papyrus", Font.BOLD, 16));
		this.add(nameLabel);

		// Fire up an anonymous inner class to handle the counter. The thread does its own
		// error and handling and logging.
		Thread thread = new Thread(new Runnable() {
		    @Override
		    public void run() {
		        int value = (P01_CHANGEME_Panel.this.type == PanelType.WhiteIncrementor) ? 0 : 9;
				int step = (P01_CHANGEME_Panel.this.type == PanelType.WhiteIncrementor) ? 1 : -1;
				JLabel counter = new JLabel(new Integer(value).toString());
				counter.setHorizontalAlignment(JLabel.CENTER);
				counter.setFont(new Font("Papyrus", Font.BOLD, 16));
				P01_CHANGEME_Panel.this.add(counter);
				while (true) {
					// Guard the increment with an error handler/continue. If there is problem,
					// then the counter will merely skip a beat rather than rapidly
					// count.
					try {
						Thread.sleep(1000);	
					} catch (InterruptedException e) {
						System.err.println(e.toString());
						continue;
					}
					value = value + step;
					counter.setText(new Integer(value).toString())	;
				}
		    }         
		});
		thread.start();
	}
}