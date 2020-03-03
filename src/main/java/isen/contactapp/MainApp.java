package isen.contactapp;

import isen.contactapp.service.StageService;
import isen.contactapp.service.ViewService;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe qui lance l'ex�cution du logiciel
 * @author rtwam
 */
public class MainApp extends Application {

    public MainApp() {

	}

    /**
     * M�thode start qui permet de lancer la fen�tre d'accueil de l'app
     * @param primaryStage Fen�tre de base
     */
    @Override
	public void start(Stage primaryStage) {
		StageService.initPrimaryStage(primaryStage);
		StageService.showView(ViewService.getView("HomeScreen"));
	}

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
		launch(args);
	}

}
