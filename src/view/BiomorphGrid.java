package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.AbstractBiomorph;

/**
 * A grid of biomorph panels, with rows and columns set in the constructor. 
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public class BiomorphGrid extends JPanel {
	
	private static final long serialVersionUID = -6158813952736333867L;
	
	private MouseListener onclickListener;
	private AbstractBiomorph selectedBiomorph;

	public BiomorphGrid(int rows, int cols) {
		super(new GridLayout(rows, cols));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	private void setupGrid(List<AbstractBiomorph> biomorphs) {		
		for(AbstractBiomorph biomorph : biomorphs) {
			final BiomorphPanel panel = new BiomorphPanel(biomorph, false, false);
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

			panel.addMouseListener(new MouseListener() {

				@Override
				public void mousePressed(MouseEvent e) { }

				@Override
				public void mouseExited(MouseEvent e) { }

				@Override
				public void mouseEntered(MouseEvent e) { }

				@Override
				public void mouseClicked(MouseEvent e) {
					selectedBiomorph = panel.getBiomorph();
				}

				@Override
				public void mouseReleased(MouseEvent e) { }
			});

			BiomorphGrid.this.add(panel);
		}
	}

	
	public void refresh() {
		for(Component component : this.getComponents()) {
			BiomorphPanel panel = (BiomorphPanel) component;

			panel.refresh();
		}
	}
	
	public void addUpdateBiomorphListener(MouseListener listener) {
		this.onclickListener = listener;
		
		for(Component component : this.getComponents()) {
			component.addMouseListener(listener);
		}
	}

	public void updateGrid(List<AbstractBiomorph> biomorphs) {
		removeAll();
		
		setupGrid(biomorphs);
		addUpdateBiomorphListener(onclickListener);
		
		refresh();
	}
	
	public AbstractBiomorph getSelectedBiomorph() {
		return selectedBiomorph;
	}
	
}
