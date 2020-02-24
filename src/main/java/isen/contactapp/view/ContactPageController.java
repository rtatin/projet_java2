package isen.contactapp.view;

import java.awt.Button;
import java.awt.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import isen.contactapp.BDD.PersonDao;
import isen.contactapp.entities.Person;
import isen.contactapp.service.PersonService;
import isen.contactapp.service.StageService;
import isen.contactapp.service.ViewService;
import isen.contactapp.utils.PersonValueFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	private void handleSaveButton() {
		if ((FirstnameField.getText().length()!=0) && (LastanemeField.getText().length()!=0) && (PhoneField.getText().length()!=0)) {			
					Person personToUpdate=new Person(this.LastanemeField.getText(),this.FirstnameField.getText(),this.PhoneField.getText());
					int result = Integer.parseInt(this.IdField.getText());
					personToUpdate.setId(result);
					
					if(this.AddresseField.getText()!=null) {
						personToUpdate.setAddress(AddresseField.getText());
						
					}
					if(this.NicknameField.getText()!=null) {
						personToUpdate.setNickname(NicknameField.getText());
					}
					if(this.EmailField.getText()!=null) {
						personToUpdate.setMail(EmailField.getText());
					}
					/*/if(this.BirthdayField.getText()!=null) {
						personToAdd.setBirthdate(LocalDate.parse(this.BirthdayField.getText()));
					}/*/
					dao.UpdatePerson(personToUpdate);
					//PersonService.addPerson(personToAdd);

					int selectedIndex = this.PersonTable.getSelectionModel().getSelectedIndex();
					PersonTable.getItems().remove(selectedIndex);
					PersonTable.getItems().add(selectedIndex, personToUpdate);
					resetView();
					
				}
		else {
			 JFrame parent = new JFrame();
			 JOptionPane.showMessageDialog(parent, "Missing mandatory informations, please fill in.");
		}
	}
	
	@FXML
	private void handleNewButton() throws Exception {
		StageService.showView(ViewService.getView("NewContact"));
	}
	
	
	@FXML
	private void handleDeleteButton() {
		this.currentPerson=dao.getPersonById(this.IdField.getText()).get(0);
		dao.deletePerson(currentPerson.getId());
		
		int selectedIndex = this.PersonTable.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	    	PersonTable.getItems().remove(selectedIndex);
	        resetView();
	    }
		resetView();
	}
	
	@FXML
	private void handleSearchButton() throws Exception {
		List<Person> listOfPersons;
		PersonTable.getItems().clear();
		if(this.searchField.getText().length()==0) {
			listOfPersons=dao.SelectAllFromPerson();
		}
		else {
			listOfPersons=dao.SelectAllWhereContain(this.searchField.getText());
		}
		for(Person var:listOfPersons) {
			PersonTable.getItems().add(var);
		}
	}
	
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
		this.populateList();
		this.refreshList();
	}
	
	private void refreshList() {
		this.PersonTable.refresh();
		this.PersonTable.getSelectionModel().clearSelection();
	}
	
	@FXML 
	private void handleNewContactButton(){
		currentPerson.setFirstname("firstname");
		currentPerson.setLastname("lastname");
		currentPerson.setPhone("phone");
		showPersonDetails(currentPerson);
		
	}
	
	public void createVcfFile() throws IOException {
		{
			  File f=new File(this.LastanemeField.getText()+"_"+this.FirstnameField.getText()+".vcf");
			  FileOutputStream fop=new FileOutputStream(f);

			  if(f.exists())
			  {
				  String str="BEGIN:VCARD\n" + 
						     "VERSION:4.0\n" +
						     "N:;"+this.LastanemeField.getText()+";;;\n" +
						     "FN:"+this.FirstnameField.getText()+" "+this.LastanemeField.getText()+"\n"+
						     "TITLE:"+NicknameField.getText()+"\n"+
						     "TEL;TYPE=home,voice;VALUE=uri:tel:"+PhoneField.getText()+"\n"+
						     "EMAIL:"+EmailField.getText()+"\n"+
						     "REV:"+IdField.getText()+"\n"+
						     "END:VCARD";
			   fop.write(str.getBytes());
			   //Now read the content of the vCard after writing data into it
			   BufferedReader br = null;
			   String sCurrentLine;
			   br = new BufferedReader(new FileReader(this.LastanemeField.getText()+"_"+this.FirstnameField.getText()+".vcf"));
			   while ((sCurrentLine = br.readLine()) != null)
			   {
			    System.out.println(sCurrentLine);
			   }
			   //close the output stream and buffer reader 
			   fop.flush();
			   fop.close();
			   System.out.println("The data has been written");
			  } else 
			   System.out.println("This file does not exist");
			 }
	}

}
