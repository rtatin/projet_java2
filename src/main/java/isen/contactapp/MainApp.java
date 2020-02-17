package isen.contactapp;

import isen.contactapp.service.StageService;
import isen.contactapp.service.ViewService;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

	public MainApp() {

	}

	@Override
	public void start(Stage primaryStage) {
		StageService.initPrimaryStage(primaryStage);
		StageService.showView(ViewService.getView("HomeScreen"));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
