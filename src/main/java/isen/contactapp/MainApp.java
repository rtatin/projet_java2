package isen.contactapp;

import isen.contactapp.service.StageService;
import isen.contactapp.service.ViewService;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe qui lance l'exécution du logiciel
 * @author rtwam
 */
public class MainApp extends Application {

    public MainApp() {

	}

    /**
     * Méthode start qui permet de lancer la fenêtre d'accueil de l'app
     * @param primaryStage Fenêtre de base
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
