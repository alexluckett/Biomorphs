package model;

import java.util.Iterator;

/**
 * An iterator for iterating through a genome tree.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
class GenomeIterator implements Iterator<Genome> {
	private Genome genome;

	public GenomeIterator(Genome genome) {
		this.genome = genome;
	}

	@Override
	public boolean hasNext() {
		return (genome.getChild() != null);
	}

	@Override
	public Genome next() {
		Genome current = genome;

		genome = genome.getChild();

		return current;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Cannot remove genome");
	}

}