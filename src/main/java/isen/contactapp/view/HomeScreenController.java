package isen.contactapp.view;

import isen.contactapp.service.StageService;
import isen.contactapp.service.ViewService;
import javafx.fxml.FXML;

public class HomeScreenController {

	
	@FXML
	public void handleLaunchButton() throws Exception {
		StageService.showView(ViewService.getView("QuizOverview"));
	}
}
