package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import model.AbstractBiomorph;
import model.EvolutionaryBiomorph;
import model.FileSerializer;
import model.Genome;
import model.RandomBiomorph;
import view.AdvancedGUI;
import view.BiomorphInterface;
import view.GenomeViewUpdateModel;
import view.Help;
import view.InterfacePicker;
import view.components.LoadProjectDialog;
import view.components.SaveImageDialog;
import view.components.SaveProjectDialog;

public class BiomorphController {

	private AbstractBiomorph model;
	private BiomorphInterface view;
	
	private FixedSizeStack<Genome> history; // used for undo functionality
	public static final String HALL_OF_FAME_SUBDIRECTORY = "/hallOfFame";
	
	public BiomorphController(BiomorphInterface view, AbstractBiomorph biomorph) {
		this.view = view; 
		this.model = biomorph;
		
		this.history = new FixedSizeStack<Genome>(10);
		
		initialiseView(view);
		addListenersToView(view);
	}
	
	private void initialiseView(BiomorphInterface view) {
		view.updateMutations(getMutatedBiomorphs()); // update the mutations for the current biomorph
		
		
		view.loadHallOfFame(getHallOfFameBiomorphs());
	}
	
	private void addListenersToView(BiomorphInterface view) {
		// add listeners to the interface
		view.addMutateListener(new MutateListener());
		view.addExitListener(new ExitListener());
		view.addUpdateBiomorphListener(new UpdateBiomorphListener());
		view.addHelpListener(new HelpListener());
		view.addSaveProjectListener(new SaveProjectListener());
		view.addLoadProjectListener(new LoadProjectListener());
		view.addGenomeChangeListener(new UpdateGenomeColourListener());
		view.addSaveImageListener(new SaveImageListener());
		view.addUndoListener(new UndoActionListener());
		view.addAddHallOfFameListener(new AddToHallOfFameListener());
		view.addLoadHallOfFameBiomorphListener(new LoadBiomorphFromHallOfFameListener());
		view.addDeleteHallOfFameBiomorphListener(new DeleteBiomorphFromHallOfFameListener());
		view.addManipulateBiomorphListener(new ManipulateBiomorphListener());
		view.addLoadInterfacePickerListener(new InterfacePickerListener());
	}
	
	/**
	 * Generates a list of mutated biomorphs, based on the genome of the active biomorph.
	 * 
	 * @return List<AbstractBiomorph>
	 */
	private List<AbstractBiomorph> getMutatedBiomorphs() {
		ArrayList<AbstractBiomorph> biomorphs = new ArrayList<AbstractBiomorph>();
		
		for(int i = 0; i < 9; i++) {
			AbstractBiomorph biomorph = new EvolutionaryBiomorph(model.getGenome());
			biomorph.mutate();
			
			biomorphs.add(biomorph);
		}
		
		return biomorphs;
	}
	
	private void addHistory(Genome genome) {
		history.add(genome.clone());
	}
	
	private Genome undoAction() {
		return history.pop();
	}
	
	private List<AbstractBiomorph> getHallOfFameBiomorphs() {
		ArrayList<AbstractBiomorph> biomorphs = new ArrayList<AbstractBiomorph>();
		
		File hallOfFameDirectory = new File(System.getProperty("user.dir") + BiomorphController.HALL_OF_FAME_SUBDIRECTORY);
		
		if(!hallOfFameDirectory.exists()) {
			hallOfFameDirectory.mkdir(); // create directory if it doesn't exist - need to be able to save
		}
		
		FileSerializer<Genome> serialiser = new FileSerializer<Genome>();
		
		for(File file : hallOfFameDirectory.listFiles()) {
			
			try {
				Genome genome = serialiser.deserialiseFile(file.getAbsolutePath());
				AbstractBiomorph biomorph = new EvolutionaryBiomorph(genome);
				biomorph.setFile(file); // keep track of filename
				
				biomorphs.add(biomorph);
			} catch (ClassNotFoundException | IOException e) {
				String message = "Failed to load hall of fame biomorph: " + file.getName();
				
				JOptionPane.showMessageDialog(view.getFrame(), message, "Hall of fame error", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
		return biomorphs;
	}
	
	/**
	 * Action to run when the active biomorph is mutated.
	 */
	class MutateListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			addHistory(model.getGenome());
			
			model.mutate();
			view.updateMutations(getMutatedBiomorphs());
		}
		
	}
	
	/**
	 * Action to run when exiting the application.
	 */
	class ExitListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.exitApplication();
		}
		
	}
	
	/**
	 * Action to run when an active biomorph has been updated with a new genome.
	 */
	class UpdateBiomorphListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			addHistory(model.getGenome());
			
			model.setGenome(view.getMutatedBiomorph().getGenome());
			
			view.updateMutations(getMutatedBiomorphs());
		}
		
	}
	
	class UpdateGenomeColourListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent event) {
			addHistory(model.getGenome());
			
			GenomeViewUpdateModel genomeUpdate = view.getGenomeUpdate();
			
			for(Genome genome : model.getGenome()) {
				Color color;
				
				if(genomeUpdate.isRandomColours()) {
					color = getRandomColor();
				} else {
					color = new Color(genomeUpdate.getRed(), genomeUpdate.getGreen(), genomeUpdate.getBlue());
				}
				
				genome.setColour(color);
			}
			
			view.updateMutations(getMutatedBiomorphs());
		}
		
		private Color getRandomColor() {
			Random rand = new Random();
			
			return new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
		}
		
	}
	
	/**
	 * Action to run when a help button is pressed.
	 */
	class HelpListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			new Help().displayGui();
		}
		
	}
	
	
	/**
	 * Action to save when a biomorph when save button is pressed.
	 */
	class SaveProjectListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			new SaveProjectDialog<Genome>(view.getFrame(), model.getGenome(), view.getBiomorphPanel()).saveFile();
		}
		
	}
	
	/**
	 * Action to save when a biomorph when save button is pressed.
	 */
	class SaveImageListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			new SaveImageDialog<Genome>(view.getFrame(), model.getGenome(), view.getBiomorphPanel()).saveFile();
		}
		
	}
	
	/**
	 * Action to load a biomorph from file.
	 */
	class LoadProjectListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			Genome genome = new LoadProjectDialog<Genome>(view.getFrame()).loadFile();
			
			model.setGenome(genome);
			view.updateMutations(getMutatedBiomorphs());
		}
		
	}
	
	class UndoActionListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent event) {
			Genome genome = undoAction();
			
			if(genome != null) {
				model.setGenome(genome);
				
				view.updateMutations(getMutatedBiomorphs());
			}
		}
		
	}
	
	class AddToHallOfFameListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent event) {
			String currentDirectory = System.getProperty("user.dir");
			
			File file = new File(currentDirectory + BiomorphController.HALL_OF_FAME_SUBDIRECTORY);
			
			int savedBiomorphCount = file.listFiles().length;
			
			if(savedBiomorphCount < 3) { // can only save 3 biomorphs
				try {
					new FileSerializer<Genome>().serialiseFile(model.getGenome(), file.getAbsolutePath() + "/biomorph" + System.currentTimeMillis());
				} catch (IOException e) {
					String message = "Unable to add to hall of fame. "
							+ "Please ensure you have permission to write to your biomorph application folder.";
					
					JOptionPane.showMessageDialog(view.getFrame(), message, "Error adding to Hall of Fame", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				String message = "No more biomorphs can be added to the hall of fame. Please delete some existing biomorphs.";
				
				JOptionPane.showMessageDialog(view.getFrame(), message, "Error adding to Hall of Fame", JOptionPane.WARNING_MESSAGE);
			}
			
			view.loadHallOfFame(getHallOfFameBiomorphs());
		}
		
	}
	
	class LoadBiomorphFromHallOfFameListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent event) {
			Genome genome = view.getHofBiomorphToLoad().getGenome().clone();
			model.setGenome(genome);
			
			view.updateMutations(getMutatedBiomorphs());
		}
		
	}
	
	class DeleteBiomorphFromHallOfFameListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent event) {
			File fileToDelete = view.getHofBiomorphToDelete().getFile();
			
			fileToDelete.delete();
			
			List<AbstractBiomorph> updatedBiomorphs = getHallOfFameBiomorphs();
			
			view.loadHallOfFame(updatedBiomorphs); // refresh hall of fame
		}
		
	}
	
	class ManipulateBiomorphListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) { }

		@Override
		public void mouseEntered(MouseEvent e) { }

		@Override
		public void mouseExited(MouseEvent e) { }

		@Override
		public void mousePressed(MouseEvent e) { }

		@Override
		public void mouseReleased(MouseEvent e) {
			view.updateMutations(getMutatedBiomorphs());
		}
		
	}
	
	private InterfacePicker interfacePicker;
	
	class InterfacePickerListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent event) {
			view.getFrame().setVisible(false);
			view.getFrame().dispose();
			
			interfacePicker = new InterfacePicker(model);
			interfacePicker.addInterfacePickedListener(new InterfacePickedListener());
			interfacePicker.displayGui();
		}
		
	}
	
	class InterfacePickedListener extends EventAction {

		@Override
		public void actionPerformed(ActionEvent event) {
			view = interfacePicker.getInterfaceToLoad();
			
			initialiseView(view);
			addListenersToView(view);
		}
		
	}
	
	/**
	 * Creates an instance of the controller, with a view and model.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractBiomorph model = new RandomBiomorph();
		BiomorphInterface view = new AdvancedGUI(model);
		
		new BiomorphController(view, model);
	}
	
}