package view.components;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.FileSerializer;

public class LoadProjectDialog<T> extends LoadFileDialog<T> {

	public LoadProjectDialog(JFrame frame) {
		super(frame);
	}

	@Override
	protected T processFile(File file) {
		T loadedObject = null;
		
		try {
			loadedObject = new FileSerializer<T>().deserialiseFile(file.getAbsolutePath());
		} catch (ClassNotFoundException | IOException e) {
			String message = "Unable to load file. Please ensure the file was not created with a newer version of this software.";
			
			JOptionPane.showMessageDialog(frame, message, "Unable to load", JOptionPane.WARNING_MESSAGE);
		}
		
		return loadedObject;
	}

}
