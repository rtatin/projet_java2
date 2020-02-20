package isen.contactapp.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import isen.contactapp.BDD.PersonDao;
import isen.contactapp.service.StageService;
import isen.contactapp.service.ViewService;

public class MainLayoutController {
	

		public void closeApplication() {
			StageService.closeStage();
		}

		public void gotoHome() {
			
			StageService.showView(ViewService.getView("HomeScreen"));

		}

}
