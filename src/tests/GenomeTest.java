package tests;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Genome;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for genome
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 */
public class GenomeTest {
	
	private Genome genome;
	
	@Before
	public void testSetup() {
		genome = new Genome(true);
	}
	
	@Test
	public void testSetInsertGenome() {
		int currentLength = genome.getLength();
		double currentAngle = genome.getAngle();
		
		Genome newGenome = new Genome(true);
		
		int newLength = newGenome.getLength();
		double newAngle = newGenome.getAngle();
		
		genome.insert(newGenome);
		
		assertTrue(genome.getChild().getLength() == newLength);
		assertTrue(genome.getChild().getAngle() == newAngle);
		assertTrue(genome.getLength() == currentLength);
		assertTrue(genome.getAngle() == currentAngle);
	}
	
	@Test
	public void testPropertyChangeFire() {
		genome.addPropertyChangeListener(new PropertyChangeListener() {
			
			boolean genomePropertyChangeHit = false;
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals("genome")) {
					genomePropertyChangeHit = true;
				}
				
				assertTrue(genomePropertyChangeHit);
			}
		});
		
		genome.setAngle(24232); // fires a property change notification which will trigger above test
	}
	
	@Test
	public void testGet() {
		Genome genome1 = new Genome(true);
		Genome genome2 = new Genome(true);
		Genome genome3 = new Genome(true);
		Genome genome4 = new Genome(true);
		
		genome.setChild(genome1);
		genome1.setChild(genome2);
		genome2.setChild(genome3);
		
		Genome newGenome = new Genome(true);
		newGenome.setAngle(42);
		newGenome.setLength(42);
		
		genome3.setChild(newGenome);
		newGenome.setChild(genome4);
		
		System.out.println(genome.size());
		
		assertTrue(genome.get(4).getLength() == 42);
		assertTrue(genome.get(4).getAngle() == 42);
	}
	
	@Test
	public void testSize() {
		Genome genome1 = new Genome(true);
		Genome genome2 = new Genome(true);
		Genome genome3 = new Genome(true);
		
		genome.setChild(genome1);
		genome1.setChild(genome2);
		genome2.setChild(genome3);
		
		assertTrue(genome.size() == 3);
	}

}
