package isen.contactapp.service;

import java.util.List;

import isen.contactapp.BDD.PersonDao;
import isen.contactapp.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonService {
	private PersonDao dao = new PersonDao();
	
	private ObservableList<Person> contacts;

	private PersonService() {
		contacts = FXCollections.observableArrayList();
		List<Person> Allperson;
		try {
			Allperson = dao.SelectAllFromPerson();
			for(Person var:Allperson) 
			{
				contacts.add(var);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ObservableList<Person> getPersons() {
		return PersonServiceHolder.INSTANCE.contacts;
	}

	public static void addPerson(Person person) {
		PersonServiceHolder.INSTANCE.contacts.add(person);
	}

	private static class PersonServiceHolder {
		private static final PersonService INSTANCE = new PersonService();
	}

}
