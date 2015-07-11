package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Abstract class for all models, designed to deal with 
 * 
 * @author Alex Luckett
 */
public abstract class AbstractModel {

	protected PropertyChangeSupport propertyChangeSupport;

	public AbstractModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	/**
	 * Adds a property change listener.
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * Removes a property change listener.
	 * 
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	/**
	 * Informs any listeners that a value has changed.
	 * 
	 * @param propertyName
	 * @param oldValue
	 * @param newValue
	 */
	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	
	/**
	 * Informs any listeners that a value has changed.
	 * 
	 * @param propertyName
	 * @param oldValue
	 * @param newValue
	 */
	protected void firePropertyChange(String propertyName, Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, null, newValue);
	}
	
}
