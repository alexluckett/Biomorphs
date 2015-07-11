package view;

import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;

import model.AbstractBiomorph;
import controller.EventAction;

/**
 * Models all interfaces that contain biomorphs.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 * @author Mohammed Hussain Ahmed<ahmedmh@aston.ac.uk>
 * @author Jurgen Hajdini<hajdinij@aston.ac.uk>
 * @author Kelvin Chui <chuikll@aston.ac.uk>
 */
public interface BiomorphInterface {
	
	void addGenerateListener(EventAction listener);
	void addMutateListener(EventAction listener);
	void addUpdateBiomorphListener(EventAction listener);
	void addExitListener(EventAction listener);
	void addHelpListener(EventAction listener);
	void addUndoListener(EventAction listener);
	
	void addLoadInterfacePickerListener(EventAction listener);
	
	void loadHallOfFame(List<AbstractBiomorph> biomorphs);
	void addAddHallOfFameListener(EventAction listener);
	void addDeleteHallOfFameBiomorphListener(EventAction listener);
	void addLoadHallOfFameBiomorphListener(EventAction listener);
	
	AbstractBiomorph getHofBiomorphToDelete(); // delete of fame biomorph
	AbstractBiomorph getHofBiomorphToLoad(); // load hall of fame biomorph
	
	void addManipulateBiomorphListener(MouseListener listener); // must be a mouse listener - need the "on release" method
	
	void addLoadProjectListener(EventAction listener);
	
	void addSaveProjectListener(EventAction listener);
	void addSaveImageListener(EventAction listener);
	
	void addGenomeChangeListener(EventAction listener);
		
	void updateMutations(List<AbstractBiomorph> biomorphs);
	AbstractBiomorph getMutatedBiomorph();
	
	GenomeViewUpdateModel getGenomeUpdate();
	
	JFrame getFrame();
	
	void exitApplication();
	
	BiomorphPanel getBiomorphPanel();
}