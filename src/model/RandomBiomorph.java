/**
 * 
 */
package model;

/**
 * Represents the default biomorph type, which does not mutate. Each evolution
 * generates an entirely new biomorph at random.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public class RandomBiomorph extends AbstractBiomorph {

	public RandomBiomorph() {
		super();
	}

	@Override
	protected void evolve(Genome genome) {
		super.generateChildren();
	}
}
