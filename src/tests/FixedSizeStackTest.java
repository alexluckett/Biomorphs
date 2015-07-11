package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import controller.FixedSizeStack;

/**
 * Test cases for the Fixed size stack
 * 
 * @author Mohammed Hussain Ahmed<ahmedmh@aston.ac.uk>
 */
public class FixedSizeStackTest {

	private FixedSizeStack<String> stack;
	
	@Before
	public void setUp(){
		stack = new FixedSizeStack<String>(10);
	}

	@Test
	public void testFixeedSizeStack() {
		stack.push("one");
		stack.push("two");
		stack.push("three");
		stack.push("four");
		stack.push("five");
		
		assertEquals(stack.size(), 5);
	}

	@Test
	public void testPush() {
		stack.push("one");
		assertEquals(stack.size(), 1);
		
		stack.push("two");
		assertEquals(stack.size(), 2);
	}

	@Test
	public void testPop(){
		stack.push("one");
		assertEquals(stack.size(), 1); 
		
		stack.pop();
		assertEquals(stack.size(), 0); 

	}

	@Test
	public void testPushingMoreThanCapacity(){
		stack.push("one");
		stack.push("two");
		stack.push("three");
		stack.push("four");
		stack.push("five");
		stack.push("six");
		stack.push("seven");
		stack.push("eight");
		stack.push("nine");
		stack.push("ten");
		assertEquals(stack.size(), 10);
		
		stack.push("eleven");
		assertEquals(stack.size(), 10);

		assertEquals(stack.pop(), "eleven"); // check element removed is the same as the one we just added

	}



}





