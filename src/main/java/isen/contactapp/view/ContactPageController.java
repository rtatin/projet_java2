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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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
	DatePicker BirthdayField;
	
	@FXML
	TextField EmailField;
	
	@FXML
	TextField IdField;
	
	@FXML
	ChoiceBox<String> categoryChoiceBox;
	
	@FXML
	TextField searchField;
	
	@FXML
	ChoiceBox<String> categoryChoiceBoxSearch;
	

	Person currentPerson;
	private PersonDao dao = new PersonDao();
	private ObservableList obsvCat=FXCollections.observableArrayList();
	private ObservableList obsvCatSearch=FXCollections.observableArrayList();
	

	
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
					personToUpdate.setBirthdate(this.BirthdayField.getValue());
					
					personToUpdate.setCategory(categoryChoiceBox.getValue());
					dao.UpdatePerson(personToUpdate);

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
	private void handleSearchButton() {
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
			this.BirthdayField.setValue(currentPerson.getBirthdate());
			
			String s=String.valueOf(currentPerson.getId());
			this.IdField.setText(s);
			this.categoryChoiceBox.setValue(currentPerson.getCategory());
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
		
		LoadChoiceBox();
		LoadChoiceBoxSearch();
		
		this.resetView();
	}
	
	private void LoadChoiceBox() {
		obsvCat.removeAll(obsvCat);
		obsvCat.add("Friend");
		obsvCat.add("Work");
		obsvCat.add("Family");
		obsvCat.add("Other");
		categoryChoiceBox.getItems().setAll(obsvCat);
		}

	
	private void LoadChoiceBoxSearch() {
		obsvCatSearch.removeAll(obsvCatSearch);
		obsvCatSearch.add("All");
		obsvCatSearch.add("Friend");
		obsvCatSearch.add("Work");
		obsvCatSearch.add("Family");
		obsvCatSearch.add("Other");
		categoryChoiceBoxSearch.getItems().setAll(obsvCatSearch);
		categoryChoiceBoxSearch.getSelectionModel().selectFirst();
		
			categoryChoiceBoxSearch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
				
				@Override
				public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
					
					PersonTable.getItems().clear();
					if (!arg2.equals("All")) {
						PersonTable.getSelectionModel().clearSelection();
						PersonTable.getItems().addAll(dao.getPersonByCategory(arg2));
						PersonTable.refresh();
					}
					else {
						PersonTable.getSelectionModel().clearSelection();
						PersonTable.getItems().addAll(dao.SelectAllFromPerson());
						PersonTable.refresh();
						
					}
				}
			});
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
	
	@SuppressWarnings("resource")
	public void createVcfFile() throws IOException {
		{
			File theDir = new File("vcards");
			if (!theDir.exists()) {
				theDir.mkdir();
			}
			if (theDir.exists()){
			  File f=new File(theDir,this.LastanemeField.getText()+"_"+this.FirstnameField.getText()+".vcf");
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
			   fop.flush();
			   fop.close();
			  } 
			}
		}
	}

}
