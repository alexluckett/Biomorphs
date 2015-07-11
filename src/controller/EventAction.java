package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** 
 * Used for consistency across controller -> view communication.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public abstract class EventAction implements ActionListener, MouseListener {

	@Override
	public abstract void actionPerformed(ActionEvent event);
	
	/**
	 * Runs the specified action when clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		ActionEvent event = new ActionEvent(arg0.getSource(), arg0.getID(),	"mouseClick", arg0.getWhen(), arg0.getModifiers());
		actionPerformed(event);
	}

	//********************************
	// No actions are executed here
	@Override
	public void mouseEntered(MouseEvent arg0) { }

	@Override
	public void mouseExited(MouseEvent arg0) { }

	@Override
	public void mousePressed(MouseEvent arg0) {	}

	@Override
	public void mouseReleased(MouseEvent arg0) { }

}
