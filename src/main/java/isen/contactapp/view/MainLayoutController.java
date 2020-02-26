package isen.contactapp.view;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import isen.contactapp.BDD.PersonDao;
import isen.contactapp.entities.Person;
import isen.contactapp.service.StageService;
import isen.contactapp.service.ViewService;

public class MainLayoutController {
	

		public void closeApplication() {
			StageService.closeStage();
		}

		public void gotoHome() {
			
			StageService.showView(ViewService.getView("HomeScreen"));

		}
		public void exportVcf() throws Exception {
			{
				PersonDao pers= new PersonDao();
				List<Person>contacts=pers.SelectAllFromPerson();
				for(Person i : contacts) {
					File theDir = new File("vcards");
					if (!theDir.exists()) {
						theDir.mkdir();
					}
					if (theDir.exists()){
					  File f=new File(theDir,i.getLastname()+"_"+i.getFirstname()+".vcf");
					  FileOutputStream fop=new FileOutputStream(f);
					  if(f.exists())
					  {
						  String str="BEGIN:VCARD\n" + 
								     "VERSION:4.0\n" +
								     "N:;"+i.getLastname()+";;;\n" +
								     "FN:"+i.getFirstname()+" "+i.getLastname()+"\n"+
								     "TITLE:"+i.getNickname()+"\n"+
								     "ADR;WORK;PREF;QUOTED-PRINTABLE:;Bruxelles 1200=Belgique;"+i.getAddress()+
								     "TEL;TYPE=home,voice;VALUE=uri:tel:"+i.getPhone()+"\n"+
								     "EMAIL:"+i.getMail()+"\n"+
								     "REV:"+i.getId()+"\n"+
								     "END:VCARD";
					   fop.write(str.getBytes());
					   fop.flush();
					   fop.close();
					  }
					}
				}
				JFrame parent = new JFrame();
				JOptionPane.showMessageDialog(parent, "Export successful, please check the vcards folder");	 		 
			}
		}
		
		public void clickWork() throws Exception {
			exportVcfByCategory("Work");
			JFrame parent = new JFrame();
			JOptionPane.showMessageDialog(parent, "Save by Work category successful, please check the vcards folder");	 
		}
		
		public void clickFamily() throws Exception {
			exportVcfByCategory("Family");
			JFrame parent = new JFrame();
			JOptionPane.showMessageDialog(parent, "Save by Family category successful, please check the vcards folder");	 
		}
		
		public void clickFriend() throws Exception {
			exportVcfByCategory("Friend");
			JFrame parent = new JFrame();
			JOptionPane.showMessageDialog(parent, "Save by Friend category successful, please check the vcards folder");	 
		}
		
		public void clickOther() throws Exception {
			exportVcfByCategory("Other");
			JFrame parent = new JFrame();
			JOptionPane.showMessageDialog(parent, "Save by Other category successful, please check the vcards folder");	 
		}
		
		
		@SuppressWarnings("null")
		public void exportVcfByCategory(String category) throws Exception {
			
				PersonDao pers= new PersonDao();
				List<Person>contacts=pers.SelectAllFromPerson();
				ArrayList<Person>contactsCategory=new ArrayList<Person>();
				for(Person j : contacts) {
					if (j.getCategory().equals(category)) {
						contactsCategory.add(j);		
					}
				}
				for(Person i : contactsCategory) {
					File theDir = new File("vcards");
					if (!theDir.exists()) {
						theDir.mkdir();
					}
					if (theDir.exists()){
					  File f=new File(theDir,i.getLastname()+"_"+i.getFirstname()+".vcf");
					  FileOutputStream fop=new FileOutputStream(f);
					  if(f.exists())
					  {
						  String str="BEGIN:VCARD\n" + 
								     "VERSION:4.0\n" +
								     "N:;"+i.getLastname()+";;;\n" +
								     "FN:"+i.getFirstname()+" "+i.getLastname()+"\n"+
								     "TITLE:"+i.getNickname()+"\n"+
								     "ADR;WORK;PREF;QUOTED-PRINTABLE:;Bruxelles 1200=Belgique;"+i.getAddress()+
								     "TEL;TYPE=home,voice;VALUE=uri:tel:"+i.getPhone()+"\n"+
								     "EMAIL:"+i.getMail()+"\n"+
								     "REV:"+i.getId()+"\n"+
								     "END:VCARD";
					   fop.write(str.getBytes());
					   fop.flush();
					   fop.close();
					  } 					
				}
			}
		}
}
