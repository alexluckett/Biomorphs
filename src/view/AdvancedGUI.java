package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.AbstractBiomorph;
import controller.EventAction;

/**
 * Provides the advanced Graphical User Interface which let's the user manipulate the biomorph
 * 
 * 	1. Create the JFrame
 * 	2. Create the Generate button, label and panel
 * 	3. Create the label for the Sliders and add it to a Panel
 * 	4. Create the different Sliders, labels, Listeners, CheckBox
 * 	5. Create a Panel that will display the Current Biomorph
 * 	6. Create a Panel that displays the different Evolutionary Biomorphs
 * 	7. Create navigation buttons at the bottom of the Panel
 * 	8. Create a Panel that will hold all the components on the Frame
 * 
 * 
 * @author Nouman Mehmood <mehmoodn@aston.ac.uk>
 * @author Mohammed Hussain Ahmed<ahmedmh@aston.ac.uk>
 * @author Jurgen Hajdini<hajdinij@aston.ac.uk>
 * @author Kelvin Chui <chuikll@aston.ac.uk>
 */
public class AdvancedGUI extends AbstractGUI implements BiomorphInterface {
	
	private AbstractBiomorph model;
	
	private BiomorphPanel currentBiomorphPanel;
	private BiomorphGrid biomorphGrid;
	
	private JSlider redSlider;
	private JSlider blueSlider;
	private JSlider greenSlider;
	private JLabel redLabel;
	private JLabel blueLabel;
	private JLabel greenLabel;
	private JPanel colorPanel;
	private JPanel labelPanel;
	
	private JButton generateBtn;
	private JButton undoBtn;
	private JButton saveBtn;
	private JButton loadBtn;
	private JButton exitBtn;
	private JButton helpBtn;
	private JButton userSelectionBtton;
	private JButton addToHoFButton;
	private JButton updateColorBtn;
	
	private JButton saveasfile;
	private JButton saveasproject;
	private JButton cancel;
	private JPanel switchPanel;
	private JCheckBox randomColor;
	
	private List<BiomorphPanel> hallOfFameBiomorphs;
	private List<JButton> hallOfFameDeleteButtons;
	private BiomorphPanel biomorphPanelToDelete;
	private BiomorphPanel biomorphPanelToLoad;
	
	private GenomeViewUpdateModel genomeUpdate;
	
	public AdvancedGUI(AbstractBiomorph model) {
		

		/** 1. Create the JFrame */ 
		
		super("Biomorph Mutation: Advanced User", 1200, 640);
		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null); 
		windowFrame.setResizable(false);
		windowFrame.setVisible(true);
		
		this.model = model;
		genomeUpdate = new GenomeViewUpdateModel();
		
		/** 2. Create the Generate button, label and panel */
		
		/** Generate Button : */
		generateBtn = new JButton("GENERATE");
		generateBtn.setBounds(11, 11, 130, 31);
		generateBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		generateBtn.setToolTipText("Click here to generate a new biomorph! ");
		
		undoBtn = new JButton("UNDO");
		undoBtn.setBounds(150, 11, 80, 31);
		undoBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		undoBtn.setForeground(Color.red);
		undoBtn.setToolTipText("Click here to generate a new biomorph! ");
		
		/** Label */
		JLabel GenerateLabel = new JLabel(" Click here to generate a new biomorph! ");
		GenerateLabel.setBounds(20, 49, 211, 14);	
		
		/** Panel : */
		JPanel generatePanel = new JPanel();
		generatePanel.setBounds(10, 11, 255, 74);
		generatePanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		generatePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		generatePanel.setLayout(null);
		
		/** Add the button and label to the Panel */
		generatePanel.add(generateBtn);
		generatePanel.add(undoBtn);
		generatePanel.add(GenerateLabel);
	
		
		
		
		
		/** 3. Create the label for the Sliders and add it to a Panel */
		
		/** Create the Panel */
		JPanel BiomorphValueChanger = new JPanel();
		BiomorphValueChanger.setBorder(new EmptyBorder(15, 15, 15, 15));
		BiomorphValueChanger.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		BiomorphValueChanger.setBounds(10, 90, 255, 26);
		BiomorphValueChanger.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		/** Create the label and add it to the Panel */
		JLabel ValueLabel = new JLabel("Change the colour by moving the Sliders");
		BiomorphValueChanger.add(ValueLabel);
		
		
		
		
		
		/** 4. Create the different Sliders, labels, Listeners, CheckBox */
		
		/** Create the different sliders */
		redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		redSlider.setMinorTickSpacing(34);
	    redSlider.setPaintTicks(true);
		
	    blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		blueSlider.setMinorTickSpacing(34);
	    blueSlider.setPaintTicks(true);
		
	    greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		greenSlider.setMinorTickSpacing(34);
	    greenSlider.setPaintTicks(true); 
	     
		/** Create the different Labels for the Sliders */
		redLabel = new JLabel(" Red : 0 ");
		blueLabel = new JLabel(" Blue : 0 ");
		greenLabel = new JLabel(" Green : 0 ");
		
		/** Create the Panel where the colour will be displayed */
		colorPanel = new JPanel();
		colorPanel.setBounds(170, 6, 74, 73);
		colorPanel.setBackground(Color.black);
		
		/** Create a Panel to hold the Labels */
		labelPanel = new JPanel();
		labelPanel.setBounds(110, 6, 60, 73);
		labelPanel.setLayout(new GridLayout(3, 1, 2, 2));
		labelPanel.add(redLabel);
		labelPanel.add(blueLabel);
		labelPanel.add(greenLabel);
		
		/** Create a Panel to hold the Sliders */
		JPanel sliders	= new JPanel();
		sliders.setBounds(10, 6, 96, 73);
		sliders.setLayout(new GridLayout(3, 1, 3, 3));
		sliders.add(redSlider);
		sliders.add(blueSlider);
		sliders.add(greenSlider);
		
		/** Change Listeners for the Sliders */
		EventChangeListener e = new EventChangeListener();
		redSlider.addChangeListener(e);
		blueSlider.addChangeListener(e);
		greenSlider.addChangeListener(e);
		
		
		/** Create a CheckBox which on default it checked */
		randomColor = new JCheckBox("Use Random Colours");
		randomColor.addItemListener(new RandomColorItemListener());
		randomColor.setBounds(10, 86, 150, 23);
		randomColor.setSelected(true);
		
		/** Create the Panel that will hold all of the the Slider components*/
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		sliderPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		sliderPanel.setBounds(10, 122, 255, 120);
		sliderPanel.setLayout(null);
		
		/** Create a button to save the colour combination */
		updateColorBtn = new JButton("Update");
		updateColorBtn.setBounds(170, 86, 70, 20);
		updateColorBtn.setToolTipText("Save your current biomorph mutation to your local drive");
		
		
		
		/** Add the different components to the sliderPanel() */
		sliderPanel.add(sliders);
		sliderPanel.add(colorPanel);
		sliderPanel.add(labelPanel);
		sliderPanel.add(randomColor);
		sliderPanel.add(updateColorBtn);
		
		
		
		
		
		/** 5. Create a Panel that will display the Current Biomorph */
		
		/** Create a Label above the Current Biomorph and a Panel to hold it */
		JLabel CurrentBiomorphLabel = new JLabel("Current Biomorph :");
		JPanel CBholdPanel = new JPanel();
		CBholdPanel.setBounds(10, 248, 255, 26);
		CBholdPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		CBholdPanel.add(CurrentBiomorphLabel);
		
		/** Add the Biomorph to the Panel */
		currentBiomorphPanel = new BiomorphPanel(model, true, true);
		//panel.setSize(882, 591);
		//currentBiomorphPanel.add(panel);
		currentBiomorphPanel.setLayout(null);
		currentBiomorphPanel.setBounds(10, 279, 255, 238);
		currentBiomorphPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		currentBiomorphPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		
		/** 6. Create a Panel that displays the different Evolutionary Biomorphs */
		biomorphGrid = new BiomorphGrid(3,3);
		biomorphGrid.setBounds(275, 11, 646, 591);
		
		
		/** 7. Create navigation buttons at the bottom of the Panel */
		
		/** Create panel #1 that will hold the different Buttons */
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		buttonPanel.setBounds(10, 528, 255, 74);
		buttonPanel.setLayout(null);
		
		
		/** Create Panel #2 that will have the save buttons */
		JPanel savePanel = new JPanel();
		savePanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		savePanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		savePanel.setBounds(10, 528, 255, 74);
		savePanel.setLayout(null);
	
		/** Different components to add to Button Panel */
		saveBtn = new JButton(" Save/Load");
		saveBtn.setBounds(10, 10, 90, 23);
		saveBtn.setToolTipText("Save your current biomorph mutation to your local drive");
		
		userSelectionBtton = new JButton(" Main Menu ");
		userSelectionBtton.setToolTipText("Pick a new interface type");
		userSelectionBtton.setBounds(10, 38, 90, 23);
	
		
		
		/** Add these different components to the Panel */
		buttonPanel.add(saveBtn);
		buttonPanel.add(userSelectionBtton);
		
		
		/** Different components to add to save options Panel */
		saveasfile = new JButton(" Save as image ");
		saveasfile.setBounds(10, 10, 110, 23);
		saveasfile.setToolTipText("Save the Biomorph as an image to your disk");
			
		saveasproject = new JButton(" Save as Project ");
		saveasproject.setBounds(10, 40, 115, 23);
		saveasproject.setToolTipText("Save the project so you can return to it later");
		
		cancel = new JButton(" Cancel ");
		cancel.setBounds(172, 40, 70, 23);
		cancel.setToolTipText("Return to the biomorph project");
		
		/** Add these different components to the Panel */
		savePanel.add(saveasfile);
		savePanel.add(saveasproject);
		savePanel.add(cancel);
		
		/** Create the panel that will hold the other panels*/
		switchPanel = new JPanel();
		final CardLayout c = new CardLayout();
		switchPanel.setLayout(c);
		
		switchPanel.add(buttonPanel, "card 1");
		
		helpBtn = new JButton(" Help ");
		helpBtn.setBounds(110, 10, 59, 23);
		buttonPanel.add(helpBtn);
		helpBtn.setToolTipText("Click here for Instructions on how to use the application");
		
		exitBtn = new JButton(" Exit ");
		exitBtn.setBounds(179, 10, 57, 23);
		buttonPanel.add(exitBtn);
		exitBtn.setToolTipText("Quit the application by clicking here");
		
		addToHoFButton = new JButton(" Add to Hall of Fame ");
		addToHoFButton.setBounds(110, 38, 132, 23);
		buttonPanel.add(addToHoFButton);
		addToHoFButton.setForeground(Color.red);
		addToHoFButton.setToolTipText("Add biomorph to the hall of fame");
		switchPanel.add(savePanel, "card 2");
		
		loadBtn = new JButton(" Load Project");
		loadBtn.setBounds(147, 10, 95, 23);
		savePanel.add(loadBtn);
		loadBtn.setToolTipText("Open up a previously saved biomorph mutation");
		
		c.show(switchPanel, "card 1");
		
		switchPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		switchPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		switchPanel.setBounds(10, 528, 255, 74);
		
		saveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				c.show(switchPanel, "card 2");	
			}
		});
		
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				c.show(switchPanel, "card 1");

			}
		});
		
		
		
		/** 9. Create the Hall of Fame big Panel*/
		hallOfFameBiomorphs = new ArrayList<BiomorphPanel>();
		hallOfFameDeleteButtons = new ArrayList<JButton>();
		
		JPanel hofPanel = new JPanel();
		hofPanel.setBounds(919, 12, 270, 590);
		hofPanel.setLayout(null);
		
		JLabel hofLabel = new JLabel(" Hall of Fame ");
		hofLabel.setBounds(80, -10, 150, 40);
		hofLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
			
		hofPanel.add(hofLabel);
		
		/* start point to position components at */
		int previousPanelY = 35;
		int previousBtnY = 190;
		
		for(int i = 0; i < 3; i++) {
			BiomorphPanel hofSubpanel = new BiomorphPanel();
			hofSubpanel.setBorder(new EmptyBorder(15, 15, 15, 15));
			hofSubpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			hofSubpanel.setBounds(10, previousPanelY, 250, 150);
			hofSubpanel.setLayout(null);
			hofSubpanel.addMouseListener(new HallOfFameLoadListener(hofSubpanel));
			
			JButton hofRemoveBtn = new JButton("Remove");
			hofRemoveBtn.setFont((new Font("Tahoma", Font.PLAIN, 12)));
			hofRemoveBtn.setBounds(95, previousBtnY, 90, 20);
			hofRemoveBtn.addMouseListener(new HallOfFameDeleteListener(hofSubpanel));
			
			previousPanelY += 185; // height of each component
			previousBtnY += 185;
			
			hofPanel.add(hofSubpanel);
			hofPanel.add(hofRemoveBtn);
			
			hallOfFameBiomorphs.add(hofSubpanel);
			hallOfFameDeleteButtons.add(hofRemoveBtn);
		}				
		
		/** 9. Create a Panel that will hold all the components on the Frame */
		JPanel windowPanel = new JPanel();
		windowFrame.getContentPane().add(windowPanel);
		windowPanel.setSize(1364, 640);
		windowPanel.setLayout(null);
		windowPanel.add(sliderPanel);
		windowPanel.add(generatePanel);
		windowPanel.add(BiomorphValueChanger);
		windowPanel.add(biomorphGrid);
		windowPanel.add(currentBiomorphPanel);
		windowPanel.add(CBholdPanel);
		windowPanel.add(switchPanel);
		windowPanel.add(hofPanel);
		
		windowFrame.setVisible(true);
		windowFrame.pack();
		
		addPropertyChangeListeners();
	}
	
	private void addPropertyChangeListeners() {
		model.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				
				checkGenomeAlteration(event);
				
			}
		});
		
		model.getGenome().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				
				checkGenomeAlteration(event); // make view listen to genome object to check if that has changed also
				
			}
		});
	}
	
	private void checkGenomeAlteration(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("genome")) {
			AdvancedGUI.this.currentBiomorphPanel.refresh();
			AdvancedGUI.this.biomorphGrid.refresh();
		}
	}
	
	public void stateChanged(ChangeEvent e) {
		int r = redSlider.getValue();
		int g = greenSlider.getValue();
		int b = blueSlider.getValue();

		redLabel.setText("Red : " + r);
		blueLabel.setText("Blue : " + b);
		greenLabel.setText("Green : " + g);

		colorPanel.setBackground(new Color(r,g,b));
		
		
	}

	
	public class RandomColorItemListener implements ItemListener {

		public void itemStateChanged(ItemEvent ie) {
			if (randomColor.isSelected()) {
				redSlider.setValue(0);
				blueSlider.setValue(0);
				greenSlider.setValue(0);

				redSlider.setEnabled(false);
				blueSlider.setEnabled(false);
				greenSlider.setEnabled(false);
				
				genomeUpdate.setRandomColours(true);
			} else {
				redSlider.setEnabled(true);
				blueSlider.setEnabled(true);
				greenSlider.setEnabled(true);
				
				genomeUpdate.setRandomColours(false);
			};
		}
		
	}
		
	public class EventChangeListener implements ChangeListener {
		
		public void stateChanged(ChangeEvent e) {
			
			int r = redSlider.getValue();
			int g = greenSlider.getValue();
			int b = blueSlider.getValue();
			
			redLabel.setText("Red : " + r);
			blueLabel.setText("Blue : " + b);
			greenLabel.setText("Green : " + g);
			
			colorPanel.setBackground(new Color(r,g,b));

			genomeUpdate.setRed(r);
			genomeUpdate.setGreen(g);
			genomeUpdate.setBlue(b);
		}
		
	}
	
	
	@Override
	public void addMutateListener(EventAction listener) {
		generateBtn.addActionListener(listener);
	}

	@Override
	public void addExitListener(EventAction listener) {
		exitBtn.addActionListener(listener);
	}

	@Override
	public void addHelpListener(EventAction listener) {
		helpBtn.addActionListener(listener);
	}

	@Override
	public void addUpdateBiomorphListener(EventAction listener) {
		biomorphGrid.addUpdateBiomorphListener(listener);
	}

	@Override
	public void addGenerateListener(EventAction listener) {
		generateBtn.addActionListener(listener);
	}

	@Override
	public AbstractBiomorph getMutatedBiomorph() {
		return biomorphGrid.getSelectedBiomorph();
	}
	
	@Override
	public void updateMutations(List<AbstractBiomorph> biomorphs) {
		biomorphGrid.updateGrid(biomorphs);
	}

	@Override
	public void addSaveProjectListener(EventAction listener) {
		saveasproject.addActionListener(listener);
	}

	@Override
	public void addSaveImageListener(EventAction listener) {
		saveasfile.addActionListener(listener);
	}
	
	@Override
	public void addLoadProjectListener(EventAction listener) {
		loadBtn.addActionListener(listener);
	}

	@Override
	public void addGenomeChangeListener(EventAction listener) {
		updateColorBtn.addActionListener(listener);
	}

	@Override
	public GenomeViewUpdateModel getGenomeUpdate() {
		return genomeUpdate;
	}
	
	@Override
	public void addAddHallOfFameListener(EventAction listener) {
		addToHoFButton.addActionListener(listener);
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
	public BiomorphPanel getBiomorphPanel() {
		return currentBiomorphPanel;
	}

	@Override
	public void addUndoListener(EventAction listener) {
		undoBtn.addActionListener(listener);
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

	@Override
	public AbstractBiomorph getHofBiomorphToDelete() {
		return biomorphPanelToDelete.getBiomorph(); // files don't use 0 based numbering
	}

	@Override
	public AbstractBiomorph getHofBiomorphToLoad() {
		return biomorphPanelToLoad.getBiomorph();
	}

	@Override
	public void addManipulateBiomorphListener(MouseListener listener) {
		currentBiomorphPanel.addMouseListener(listener);
	}

	@Override
	public void addLoadInterfacePickerListener(EventAction listener) {
		userSelectionBtton.addActionListener(listener);
	}
	
}
