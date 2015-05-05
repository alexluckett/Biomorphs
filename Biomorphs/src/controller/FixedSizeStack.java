package controller;

import java.util.LinkedList;

/**
 * Stack with a fixed size. Silently removes any item that fall outside the maximum capacity.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk>
 *
 * @param <T> contained element type
 */
public class FixedSizeStack<T> {
	private LinkedList<T> baseList;
	private int capacity;

	public FixedSizeStack(int capacity) {
		this.capacity = capacity;
		this.baseList = new LinkedList<T>();
	}
	
	public void add(T element) {
		baseList.add(element);
		
		if(baseList.size() > capacity) {
			baseList.removeFirst();
		}
	}
	
	public T pop() {
		if(baseList.size() == 0) {
			return null;
		}
		
		T element = baseList.removeLast();
		
		return element;
	}
	
}
