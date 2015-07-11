package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.AbstractBiomorph;
import controller.EventAction;

/**
 * Provides a graphical user interface
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Mohammed Hussain Ahmed<ahmedmh@aston.ac.uk>
 * @author Jurgen Hajdini<hajdinij@aston.ac.uk>
 * @author Kelvin Chui <chuikll@aston.ac.uk>
 */
public class BasicGUI extends AbstractGUI implements BiomorphInterface {
	
	private JButton newBiomorphBtn;
	private JButton newSaveBtn;
	private JButton newLoadBtn;
	private JButton newHelpBtn;
	private JButton newEndBtn;
	private JButton saveasjpeg;
	private JButton savetohof;
	private JButton undoButton;
	private JButton chooseInterfacePicker;
	
	private BiomorphPanel panel;
	
	private List<BiomorphPanel> hallOfFameBiomorphs;
	private List<JButton> hallOfFameDeleteButtons;
	private BiomorphPanel biomorphPanelToDelete;
	private BiomorphPanel biomorphPanelToLoad;

	public BasicGUI(AbstractBiomorph model) {
		super("Biomorph Mutation: Beginner User", 800, 600);

		/** Panel to hold the components */
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(9, 1, 20, 20));

		/** Create the buttons */ 
		newBiomorphBtn = new JButton(" Generate Biomorph ");
		newBiomorphBtn.setToolTipText("Create a new biomorph by clicking here"); 
		
		newSaveBtn = new JButton(" Save as Project ");
		newSaveBtn.setToolTipText("Save your project to return to it later");
		
		saveasjpeg = new JButton(" Save as Image ");
		saveasjpeg.setToolTipText("Save your current biomorph mutation to your local drive");
		
		newLoadBtn = new JButton(" Load ");
		newLoadBtn.setToolTipText("Open up a previously saved biomorph mutation");
		
		newHelpBtn = new JButton(" Help ");
		newHelpBtn.setToolTipText("Click here for Instructions on how to use the application");
		
		savetohof = new JButton(" Save to Hall of Fame ");
		savetohof.setToolTipText("Add to hall of fame");
		
		undoButton = new JButton(" Undo ");
		undoButton.setToolTipText("Undo previous action");
		
		chooseInterfacePicker = new JButton(" Main menu ");
		chooseInterfacePicker.setToolTipText("Change interface to basic/advanced");
		
		newEndBtn = new JButton(" Exit ");
		newEndBtn.setToolTipText("Quit the application by clicking here");

		hallOfFameBiomorphs = new ArrayList<BiomorphPanel>();
		hallOfFameDeleteButtons = new ArrayList<JButton>();
		
		/** Add buttons to the panel */
		button_panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		button_panel.add(newBiomorphBtn);
		button_panel.add(undoButton);
		button_panel.add(newSaveBtn);
		button_panel.add(saveasjpeg);
		button_panel.add(savetohof);
		button_panel.add(newLoadBtn);
		button_panel.add(newHelpBtn);
		button_panel.add(chooseInterfacePicker);
		button_panel.add(newEndBtn);
		
		/** Defining the draw canvas */
		this.panel = new BiomorphPanel(model, true, true);
		
		
		Box box = Box.createVerticalBox();
		JPanel hallOfFame = new JPanel();
		hallOfFame.add(box);
		
		for(int i = 0; i < 3; i++) {
			Box subBox = Box.createVerticalBox();
			JPanel panel = new JPanel();
			panel.add(subBox);
			
			BiomorphPanel hofSubpanel = new BiomorphPanel();
			hofSubpanel.setBorder(new EmptyBorder(15, 15, 15, 15));
			hofSubpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			hofSubpanel.addMouseListener(new HallOfFameLoadListener(hofSubpanel));
			hofSubpanel.setPreferredSize(new Dimension(200, 200));
			
			JButton hofRemoveBtn = new JButton("Remove");
			hofRemoveBtn.setFont((new Font("Tahoma", Font.PLAIN, 12)));
			hofRemoveBtn.addMouseListener(new HallOfFameDeleteListener(hofSubpanel));
			
			subBox.add(hofSubpanel);
			subBox.add(hofRemoveBtn);
			
			hallOfFameBiomorphs.add(hofSubpanel);
			hallOfFameDeleteButtons.add(hofRemoveBtn);
			
			box.add(panel);
		}

		windowFrame.add(button_panel, BorderLayout.WEST);
		windowFrame.add(panel, BorderLayout.CENTER);
		windowFrame.add(hallOfFame, BorderLayout.EAST);
		windowFrame.setVisible(true);		

		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); // centre aligned
		
		model.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				
				if (event.getPropertyName().equals("genome")) {
					BasicGUI.this.panel.refresh();
				}
				
			}
		});
	}

	@Override
	public void addGenerateListener(EventAction listener) {
		newBiomorphBtn.addActionListener(listener);
	}

	public void addMutateListener(EventAction listener) {
		newBiomorphBtn.addActionListener(listener);
	}
	
	public void addExitListener(EventAction listener) {
		newEndBtn.addActionListener(listener);
	}
	
	public void addSaveProjectListener(EventAction listener) {
		newSaveBtn.addActionListener(listener);
	}
	
	public void addLoadProjectListener(EventAction listener) {
		newLoadBtn.addActionListener(listener);
	}
	
	public void addHelpListener(EventAction listener) {
		newHelpBtn.addActionListener(listener);
	}

	@Override
	public void addUpdateBiomorphListener(EventAction listener) { } // not needed for this gui

	@Override
	public void updateMutations(List<AbstractBiomorph> biomorphs) { }

	@Override
	public AbstractBiomorph getMutatedBiomorph() { return null; }

	@Override
	public void addSaveImageListener(EventAction listener) { 
		saveasjpeg.addActionListener(listener);
	}

	@Override
	public JFrame getFrame() {
		return windowFrame;
	}

	@Override
	public void addGenomeChangeListener(EventAction listener) { }

	@Override
	public GenomeViewUpdateModel getGenomeUpdate() {
		return null;
	}

	@Override
	public BiomorphPanel getBiomorphPanel() {
		return panel;
	}

	@Override
	public void addUndoListener(EventAction listener) {
		undoButton.addActionListener(listener);
	}

	@Override
	public void addLoadInterfacePickerListener(EventAction listener) {
		chooseInterfacePicker.addActionListener(listener);
	}

	@Override
	public void loadHallOfFame(List<AbstractBiomorph> biomorphs) {
		for(BiomorphPanel panel : hallOfFameBiomorphs) {
			panel.setBiomorph(null);
		}
		
		if(biomorphs.size() <= 3) {
			for(int i = 0; i < biomorphs.size(); i++) {
				hallOfFameBiomorphs.get(i).setBiomorph(biomorphs.get(i));
			}
		}
		
		for(BiomorphPanel panel : hallOfFameBiomorphs) {
			panel.refresh(); // ensure all panels are painted with new biomorph
		}
	}

	@Override
	public void addAddHallOfFameListener(EventAction listener) {
		savetohof.addActionListener(listener);
	}

	@Override
	public void addLoadHallOfFameBiomorphListener(EventAction listener) {
		for(BiomorphPanel panel : hallOfFameBiomorphs) {
			panel.addMouseListener(listener);
		}
	}

	@Override
	public void addDeleteHallOfFameBiomorphListener(EventAction listener) {
		for(JButton button : hallOfFameDeleteButtons) {
			button.addMouseListener(listener);
		}
	}

	@Override
	public AbstractBiomorph getHofBiomorphToDelete() {
		return biomorphPanelToDelete.getBiomorph();
	}

	@Override
	public AbstractBiomorph getHofBiomorphToLoad() {
		return biomorphPanelToLoad.getBiomorph();
	}

	@Override
	public void addManipulateBiomorphListener(MouseListener listener) {
		panel.addMouseListener(listener);
	}
	
	class HallOfFameDeleteListener extends EventAction {
		
		private BiomorphPanel biomorphPanel;
		
		public HallOfFameDeleteListener(BiomorphPanel biomorphPanel) {
			this.biomorphPanel = biomorphPanel;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			biomorphPanelToDelete = biomorphPanel;
		}
	}
	
	class HallOfFameLoadListener extends EventAction {
		
		private BiomorphPanel biomorphPanel;
		
		public HallOfFameLoadListener(BiomorphPanel biomorphNumber) {
			this.biomorphPanel = biomorphNumber;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			biomorphPanelToLoad = biomorphPanel;
		}
	}
}