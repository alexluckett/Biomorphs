package model;
import static model.Constants.DEFAULT_GENOME_SIZE;
import static model.Constants.GENOME_MINIMUM_EVOLUTIONS;

import java.io.File;
import java.util.Random;


/**
 * Represents a biomorph
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public abstract class AbstractBiomorph extends AbstractModel {
	
	protected Genome genome;
	private File file; // used when loaded from a file, to keep track of path/name
	
	public AbstractBiomorph(Genome genome) {
		this.genome = genome.clone();
	}
	
	/**
	 * Creates a biomorph at the default position (0, 0)
	 */
	public AbstractBiomorph() {
		genome = new Genome(true);
	}
	
	public void setGenome(Genome genome) {
		this.genome = genome;
		
		firePropertyChange("genome", genome);
	}
	
	public Genome getGenome() {
		return genome;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public File getFile() {
		return file;
	}
	
	/**
	 * Generates a random amount of children to the biomorph's genome
	 */
	public void generateChildren() {
		Random rand = new Random();
		int evolutions = rand.nextInt(DEFAULT_GENOME_SIZE) + GENOME_MINIMUM_EVOLUTIONS; // number of iterations
		
		Genome current = genome;			
		for(int i = 0; i < evolutions; i++) {
			Genome newChild = new Genome(true);
			current.setChild(newChild); // randomly generated child
			
			current = newChild;
		}
		
		firePropertyChange("genome", genome);
	}
	
	/**
	 * Controls how a biomorph evolves, given a genome.
	 * 
	 * @param genome
	 */
	protected abstract void evolve(Genome genome);
	
	/**
	 * Mutates the biomorph using a random genome
	 */
	public void mutate() {
		evolve(new Genome(true));
		
		firePropertyChange("genome", genome);
	}
	
}