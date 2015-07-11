package controller;

import java.util.LinkedList;

/**
 * Stack with a fixed size. Silently removes any item that fall outside the maximum capacity.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 * Serialises objects of type T.
 * 
 * @param <T> type of class
 */
public class FixedSizeStack<T> {
	private LinkedList<T> baseList;
	private int capacity;

	/**
	 * Initialise the stack with a maximum capacity.
	 * 
	 * @param capacity
	 */
	public FixedSizeStack(int capacity) {
		this.capacity = capacity;
		this.baseList = new LinkedList<T>();
	}
	
	/**
	 * Adds an element to the top of the stack. Silently removes any old 
	 * elements at the bottom, if the size of the stack is > capacity.
	 * 
	 * @param T element
	 */
	public void push(T element) {
		baseList.add(element);
		
		if(baseList.size() > capacity) {
			baseList.removeFirst();
		}
	}
	
	/**
	 * Remove the last (top) element from the stack.
	 * 
	 * @return T element
	 */
	public T pop() {
		if(baseList.size() == 0) {
			return null;
		}
		
		T element = baseList.removeLast();
		
		return element;
	}
	
	/**
	 * Returns the size of the stack.
	 * 
	 * @return int size of stack
	 */
	public int size(){
		return baseList.size();	
	}
}
