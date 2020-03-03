package isen.contactapp.utils;

import isen.contactapp.entities.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Listener qui vérifie sur quelle personne on appuie
 * @author rtwam
 */
public abstract class PersonChangeListener implements ChangeListener<Person>{
	
    /**
     * Change la value
     * @param observable
     * @param oldValue
     * @param newValue
     */
    @Override
	public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
		handleNewValue(newValue);

	}

    /**
     * 
     * @param newValue
     */
    public abstract void handleNewValue(Person newValue);

}

