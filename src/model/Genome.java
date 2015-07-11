package model;
import java.awt.Color;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;

import static model.Constants.*;

/**
 * Represents a genome structure, to be used within a biomorph.
 * 
 * Contains a history of each child biomorph's genome.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public class Genome extends AbstractModel implements Iterable<Genome>, Cloneable, Serializable {
	private static final long serialVersionUID = -705113420866471080L;
	
	private double angle;
	private int length;
	private Color colour;
	private boolean isHighlighted;

	private Genome child; // chain from parent to final child

	public Genome(boolean random) {
		if(random) {
			Random rand = new Random();

			angle = rand.nextInt() * rand.nextDouble();
			length = rand.nextInt(GENOME_MAX_LENGTH) + 10;
			colour = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
			child = null;
			isHighlighted = false;
		} else {
			angle = GENOME_DEFAULT_ANGLE;
			length = GENOME_DEFAULT_LENGTH;
			colour = GENOME_DEFAULT_COLOUR;
			child = null;
			isHighlighted = false;
		}
	}

	public Genome(Genome genome) {
		this.angle = genome.angle;
		this.length = genome.length;
		this.colour = genome.colour;

		if(genome.child != null) {
			this.child = genome.child.clone();
		} else {
			this.child = null;
		}	
	}

	public void setAngle(double angle) {
		this.angle = angle;
		firePropertyChange("genome", this);
	}
	
	public double getAngle() {	return angle; }
	
	public void setLength(int length) {
		this.length = length;
		firePropertyChange("genome", this);
	}
	
	public int getLength() { return length; }
	
	public void setColour(Color colour) {
		this.colour = colour;
		firePropertyChange("genome", this);
	}
	
	public Color getColour() {
		if(isHighlighted) {
			return Color.BLACK;
		}
		
		return colour;
	}
	
	public void setHighlighted(boolean isHighlighted) {	this.isHighlighted = isHighlighted; } 
	public boolean isHighlighted() { return isHighlighted; }
	
	public void setChild(Genome child) {
		this.child = child;
		firePropertyChange("genome", this);
	}
	
	public Genome getChild() { return child; }

	public int size() {
		int size = 0;

		for(@SuppressWarnings("unused") Genome genome : this) { 
			size++;
		}

		return size;
	}

	@Override
	public String toString() {
		String str = "A" + angle + ", L" + length + ", C" + colour.getRGB();
		
		if(child != null) {
			str += "\n {" + child.toString() + "}";
		}
		
		return str;
	}

	/**
	 * Returns a deep clone of the current genome, including children.
	 */
	@Override
	public Genome clone() {		
		return new Genome(this);
	}

	@Override
	public Iterator<Genome> iterator() {
		return new GenomeIterator(this);
	}
	
	/**
	 * Inserts a genome into the sequence as a child of the current.
	 * 
	 * @param Genome genome to insert
	 */
	public void insert(Genome genome) {
		Genome finalMerging = genome;
		
		Genome oldChild = getChild(); // keep track of current child
		finalMerging.setChild(oldChild); // place the old child behind the new entry
				
		setChild(finalMerging); // insert the new genome between the current and child
	}
	
	/**
	 * Returns a genome <code>index</code> positions down the sequence.
	 * 
	 * @param index of genome
	 * @return Genome
	 * @throws InvalidGenomePositionException
	 */
	public Genome get(int index) throws IndexOutOfBoundsException {
		if(index > size()) {
			throw new IndexOutOfBoundsException("Genome position cannot be greater than value returned by size()");
		}
		
		Genome lastGenome = this;
		
		for(int i = 0; i < index; i++) {
			if(lastGenome.getChild() != null) {
				lastGenome = lastGenome.getChild();
			} else {
				throw new IndexOutOfBoundsException();
			}
		}

		return lastGenome;
	}
}