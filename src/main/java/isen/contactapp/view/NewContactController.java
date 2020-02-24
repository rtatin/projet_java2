package isen.contactapp.view;

import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import isen.contactapp.BDD.PersonDao;
import isen.contactapp.entities.Person;
import isen.contactapp.service.PersonService;
import isen.contactapp.service.StageService;
import isen.contactapp.service.ViewService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewContactController {
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
	

	Person currentPerson;
	private PersonDao dao = new PersonDao();
	
	@FXML
	private void handleNewButton() {
		if ((FirstnameField.getText().length()!=0) && (LastanemeField.getText().length()!=0) && (PhoneField.getText().length()!=0)) {
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
					
					if(dao.CheckAlreadyExist(personToAdd)==false) {
						dao.addPerson(personToAdd);
						PersonService.addPerson(personToAdd);
						StageService.showView(ViewService.getView("ContactPage"));
			    	}
				}
		else {
			 JFrame parent = new JFrame();
			 JOptionPane.showMessageDialog(parent, "Missing mandatory information, please fill in.");
		}
	}


	@FXML
	private void handleBackButton() {
		StageService.showView(ViewService.getView("ContactPage"));
	}
	
}
