package view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Base class for all GUIs
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 */

public abstract class AbstractGUI {
	
	protected JFrame windowFrame;
	
	public AbstractGUI(String windowTitle, int width, int height) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // use native appearance

			windowFrame = new JFrame(windowTitle);
			windowFrame.setPreferredSize(new Dimension(width,height));
			windowFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			windowFrame.setResizable(true);
		
		} catch (Exception e) {
			System.err.println("Failed to set graphics mode. Reverting to Java LAF.");
		}
		
		windowFrame.addWindowListener(new WindowAdapter() {
			
		    public void windowClosing(WindowEvent e) {
		        exitApplication(); // bypass default behavior, use our own method
		    }
		    
		});
	}
	
	/** Destroy the Current Frame */
	protected void destroyGui() {
		windowFrame.setVisible(false);
		windowFrame.dispose();
	}
	
	/** Display the interface */
	public void displayGui() {
		windowFrame.setVisible(true);
	}
	
	/** Exit the application w/ responses */
	public void exitApplication()
	{
		int response = JOptionPane.showConfirmDialog(windowFrame,
				"Would you really like to quit the application?", 
				"Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		int target = JOptionPane.YES_OPTION;
		if (response == target) {
			System.exit(0);
		}
	}
	
	public JFrame getFrame() {
		return windowFrame;
	}
	
	/** Return to the User Selection screen */
	/*protected void returnUserSelect()
	{
		int response = JOptionPane.showConfirmDialog(windowFrame,
				"Would you really like to return to the User Selection Screen?", 
				"Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		int target = JOptionPane.YES_OPTION;
		
		if (response == target) {
			destroyGui();
			new GuiStartup().displayGui();
		
		}
	}*/
}
