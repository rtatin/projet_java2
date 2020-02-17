package isen.contactapp.view;

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
