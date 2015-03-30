package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Base class for all GUIs
 * 
 * @author Alexander Luckett <lucketta@aston.ac.uk>
 */
public abstract class AbstractGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public AbstractGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // use native appearance
		} catch (Exception e) {
			System.err.println("Failed to set graphics mode. Reverting to Java LAF.");
		}
	}
	
	protected void displayGui(JFrame existingFrame, AbstractGUI gui) {
		existingFrame.setVisible(false);
		existingFrame.dispose();
		gui.setVisible(true);
	}
	
	protected void displayGui() {
		this.setVisible(true);
	}
	
	protected void exitApplication(JFrame frame)
	{
		int response = JOptionPane.showConfirmDialog(frame,
				"Would you really like to quit the application?", 
				"Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
}
