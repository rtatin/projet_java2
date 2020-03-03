package isen.contactapp.service;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Gestion de la stage
 * @author rtwam
 */
public class StageService {

	private StageService() {
		mainLayout = ViewService.getView("MainLayout");
	}

	private static class StageServiceHolder {
		private static final StageService INSTANCE = new StageService();
	}

	private Stage primaryStage;

	private BorderPane mainLayout;

    /**
     * Récupère l'id de la stage
     * @return
     */
    public static Stage getPrimaryStage() {
		return StageServiceHolder.INSTANCE.primaryStage;
	}

    /**
     * Init la primaryStage
     * @param primaryStage
     */
    public static void initPrimaryStage(Stage primaryStage) {
		primaryStage.setTitle("Contact App");
		primaryStage.setScene(new Scene(StageServiceHolder.INSTANCE.mainLayout));
		primaryStage.show();

		StageServiceHolder.INSTANCE.primaryStage = primaryStage;
	}

    /**
     * Permet d'afficher le bon view
     * @param rootElement
     */
    public static void showView(Node rootElement) {
		StageServiceHolder.INSTANCE.mainLayout.setCenter(rootElement);
	}

    /**
     * Ferme le stage
     */
    public static void closeStage() {
		StageServiceHolder.INSTANCE.primaryStage
				.fireEvent(new WindowEvent(StageServiceHolder.INSTANCE.primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));

	}

}


