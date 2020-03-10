package isen.contactapp.view;

import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import isen.contactapp.BDD.PersonDao;
import isen.contactapp.entities.Person;
import isen.contactapp.service.PersonService;
import isen.contactapp.service.StageService;
import isen.contactapp.service.ViewService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Controller de newcontact
 * @author rtwam
 */
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
	DatePicker BirthdayField;
	
	@FXML
	TextField EmailField;
	
	@FXML
	TextField IdField;
	
	@FXML
	TextField AddresseField;
	
	@FXML
	TextField PostalCodeField;
	
	@FXML
	TextField CityField;
	
	@FXML
	TextField ContryField;
	
	@FXML
	ChoiceBox<String> categoryChoiceBox;
	

	Person currentPerson;
	private PersonDao dao = new PersonDao();
	private ObservableList obsvCat=FXCollections.observableArrayList();
	
        
	/**
        * Appui sur bouton, ajoute la personne si les champs obligatoires ont 
        * bien été remplis
        */
	@FXML
	private void handleNewButton() {
		if ((FirstnameField.getText().length()!=0) && (LastanemeField.getText().length()!=0) && (PhoneField.getText().length()!=0)) {
					Person personToAdd=new Person(this.LastanemeField.getText(),this.FirstnameField.getText(),this.PhoneField.getText());
					
					String adress;
					
					if( this.AddresseField.getText()!=null) {
						adress=(AddresseField.getText()+";");
					}
					else {
						personToAdd.setAddress(" ;");
					}
					if( this.PostalCodeField.getText()!=null) {
						adress=(personToAdd.getAddress()+PostalCodeField.getText()+";");
					}
					else {
						adress=(personToAdd.getAddress()+" ;");
					}
					if( this.CityField.getText()!=null) {
						adress=(personToAdd.getAddress()+CityField.getText()+";");
					}
					else {
						adress=(personToAdd.getAddress()+" ;");
					}
					if( this.ContryField.getText()!=null) {
						adress=(personToAdd.getAddress()+ContryField.getText()+";");
					}
					else {
						adress=(personToAdd.getAddress()+" ");
					}
					if(adress!=";;;;") {
						
					}
					
					
					if(this.NicknameField.getText()!=null) {
						personToAdd.setNickname(NicknameField.getText());
					}
					if(this.EmailField.getText()!=null) {
						personToAdd.setMail(EmailField.getText());
					}
					
					personToAdd.setBirthdate(this.BirthdayField.valueProperty().get());
					
					personToAdd.setCategory(categoryChoiceBox.getValue());
					
					
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

        /**
        * Bouton retour
        */
	@FXML
	private void handleBackButton() {
		StageService.showView(ViewService.getView("ContactPage"));
	}
	
        /**
        * initialise lechoicebox
        */
	@FXML 
	private void initialize() {
		 LoadChoiceBox();
	}
	
        /**
        * Charge le choicebox
        */
	private void LoadChoiceBox() {
		obsvCat.removeAll(obsvCat);
		obsvCat.add("Friend");
		obsvCat.add("Work");
		obsvCat.add("Family");
		obsvCat.add("Other");
		categoryChoiceBox.getItems().setAll(obsvCat);
		categoryChoiceBox.getSelectionModel().selectLast();
		categoryChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
			}
			
		});
	}
	
	
}
