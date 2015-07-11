
/**
 * Serialises objects of type T.
 * 
 * @author  Jurgen Hajdini<hajdinij@aston.ac.uk>
 * 
 * @param <T> type of class
 */

package view.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class SaveImageDialog<T> extends SaveFileDialog<T> {

	public SaveImageDialog(JFrame frame, T element, JPanel panel) {
		super(frame, element, panel);
		
	}

	@Override
	protected void processFile(T element, String path, JPanel panel) {
	   try{ int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.paint(g);
	    File outputfile = new File (path);
	    
			ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
		
	
}

	
	

