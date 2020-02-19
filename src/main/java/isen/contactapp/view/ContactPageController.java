package isen.contactapp.view;

import java.time.LocalDate;

import isen.contactapp.BDD.PersonDao;
import isen.contactapp.entities.Person;
import isen.contactapp.service.PersonService;
import isen.contactapp.utils.PersonValueFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ContactPageController {
	
	@FXML
	TableView<Person> PersonTable;

	@FXML
	TableColumn<Person, String> PersonColumn;

	@FXML
	AnchorPane formPane;

	@FXML
	TextField LastanemeField;

	@FXML
	TextField FirstnameField;

	@FXML
	TextField PhoneField;

	@FXML
	TextField NicknameField;
	
	@FXML
	TextField AddresseField;
	
	@FXML
	TextField BirthdayField;
	
	@FXML
	TextField EmailField;
	
	@FXML
	TextField IdField;
	
	@FXML
	TextField searchField;
	

	Person currentPerson;
	private PersonDao dao = new PersonDao();

	
	@FXML
	private void handleSaveButton() {}
	
	@FXML
	private void handleNewButton() {
		if (this.FirstnameField.getText()!=null) {
			if (this.LastanemeField.getText()!=null) {
				if (this.PhoneField.getText()!=null) {
					Person personToAdd=new Person(this.LastanemeField.getText(),this.FirstnameField.getText(),this.PhoneField.getText());
					
					if(this.AddresseField.getText()!=null) {
						personToAdd.setAddress(AddresseField.getText());
					}
					if(this.NicknameField.getText()!=null) {
						personToAdd.setNickname(NicknameField.getText());
					}
					if(this.EmailField.getText()!=null) {
						personToAdd.setMail(EmailField.getText());
					}
					/*/if(this.BirthdayField.getText()!=null) {
						personToAdd.setBirthdate(LocalDate.parse(this.BirthdayField.getText()));
					}/*/
					dao.addPerson(personToAdd);
					PersonService.addPerson(personToAdd);
				}
			}
		}
	}
	
	@FXML
	private void handleDeleteButton() {
		this.currentPerson=dao.getPersonById(this.IdField.getText()).get(0);
		dao.deletePerson(currentPerson.getId());
		System.out.println(currentPerson.getId());
	}
	
	@FXML
	private void handleSearchButton() {}
	
	private void showPersonDetails(Person person) {
		if (person == null) {
			this.formPane.setVisible(false);
		} else {
			this.formPane.setVisible(true);
			this.currentPerson = person;
			this.LastanemeField.setText(currentPerson.getLastname());
			this.FirstnameField.setText(currentPerson.getFirstname());
			this.PhoneField.setText(currentPerson.getPhone());
			this.AddresseField.setText(currentPerson.getAddress());
			this.NicknameField.setText(currentPerson.getNickname());
			this.EmailField.setText(currentPerson.getMail());
			if(person.getBirthdate()!=null) {
				this.BirthdayField.setText(currentPerson.getBirthdate().toString());
			}
			else {
				this.BirthdayField.setText("")
;			}
				String s=String.valueOf(currentPerson.getId());
				this.IdField.setText(s);
		}
	}

	private void populateList() {
		this.PersonTable.setItems(PersonService.getPersons());
		this.PersonTable.refresh();
	}
	
	@FXML 
	private void initialize() {
		this.PersonColumn.setCellValueFactory(new PersonValueFactory());
		this.populateList();
		this.PersonTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {

			@Override
			public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
				showPersonDetails(newValue);

			}
		});
		this.resetView();
	}
	
	
	private void resetView() {
		this.showPersonDetails(null);
		this.refreshList();
	}
	
	private void refreshList() {
		this.PersonTable.refresh();
		this.PersonTable.getSelectionModel().clearSelection();
	}

}
