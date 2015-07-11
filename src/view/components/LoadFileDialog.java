package view.components;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Load the project into the application 
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 */

public abstract class LoadFileDialog<T> {

	protected JFrame frame;
	
	private JFileChooser fileChooser;
	private JButton openButton;

	public LoadFileDialog(JFrame frame){

		openButton = new JButton();

		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory	(new java.io.File("."));

		fileChooser.setDialogTitle("Open your existing Biomorph Project");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		this.frame = frame;

	}	

	public T loadFile() {
		T file = null;
		
		if (fileChooser.showOpenDialog(openButton) == JFileChooser.APPROVE_OPTION){
			file = processFile(fileChooser.getSelectedFile());
		}
		
		return file;
	}
	
	protected abstract T processFile(File file);
}


