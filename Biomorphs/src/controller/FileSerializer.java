package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Serialises objects of type T.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 * 
 * @param <T> type of class
 */
public class FileSerializer<T> {

	public FileSerializer() { }
	
	public void serialiseFile(T object, String path) throws IOException {
		FileOutputStream fileOutput = new FileOutputStream(path);
		ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
		
		try {
			objectOutput.writeObject(object);
		} finally {
			objectOutput.close();
			fileOutput.close();
		}		
	}
	
	@SuppressWarnings("unchecked") // only way to work around this unfortunately. still safe as it will throw exception.
	public T deserialiseFile(String path) throws IOException, ClassNotFoundException {
		FileInputStream fileInput = new FileInputStream(path);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        
        T object = null;
        
        try {
        	object = (T) objectInput.readObject();
        } finally {
        	objectInput.close();
            fileInput.close();
        }
        
        return object;
	}
	

	
}
