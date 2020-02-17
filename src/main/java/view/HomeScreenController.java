package view;

import isen.quiz.service.StageService;
import isen.quiz.service.ViewService;
import javafx.fxml.FXML;

public class HomeScreenController {

	
	@FXML
	public void handleLaunchButton() throws Exception {
		StageService.showView(ViewService.getView("QuizOverview"));
	}
}
