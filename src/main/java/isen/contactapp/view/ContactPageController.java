package isen.contactapp.view;

import java.time.LocalDate;

import isen.contactapp.BDD.PersonDao;
import isen.contactapp.entities.Person;
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
					Person personToAdd=new Person(this.FirstnameField.getText(),this.LastanemeField.getText(),this.PhoneField.getText());
					
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
				}
			}
		}
	}
	
	private void handleDeleteButton() {
		this.currentPerson=dao.getPersonById(this.IdField.getText()).get(0);
		dao.deletePerson(currentPerson.getId());
	}
	
	private void handleSearchButton() {}
	
	

}
