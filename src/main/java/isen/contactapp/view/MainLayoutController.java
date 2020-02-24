package isen.contactapp.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
					File f=new File(i.getLastname()+"_"+i.getFirstname()+".vcf");
					  FileOutputStream fop=new FileOutputStream(f);

					  if(f.exists())
					  {
						  String str="BEGIN:VCARD\n" + 
								     "VERSION:4.0\n" +
								     "N:;"+i.getLastname()+";;;\n" +
								     "FN:"+i.getFirstname()+" "+i.getLastname()+"\n"+
								     "TITLE:"+i.getNickname()+"\n"+
								     "TEL;TYPE=home,voice;VALUE=uri:tel:"+i.getPhone()+"\n"+
								     "EMAIL:"+i.getMail()+"\n"+
								     "REV:"+i.getId()+"\n"+
								     "END:VCARD";
					   fop.write(str.getBytes());
					   //Now read the content of the vCard after writing data into it
					   BufferedReader br = null;
					   String sCurrentLine;
					   br = new BufferedReader(new FileReader(i.getLastname()+"_"+i.getFirstname()+".vcf"));
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
				  

}
