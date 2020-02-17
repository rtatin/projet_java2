package mainapp;


import java.io.IOException;

import service.StageService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainApp extends Application {
	
	
	private void showHomeScreen() throws IOException {
		// Load our first file
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("src/main/ressources/view/HomeScreen.fxml"));
		// get it into a object file and create a scene from it
		AnchorPane rootAnchorPane = loader.load();
		Scene scene = new Scene(rootAnchorPane);
		// get the primary stage and put the first scene on the stage
		Stage ps = StageService.getInstance().getPrimaryStage();
		ps.setScene(scene);
		// Curtains up !
		ps.show();
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Contact app");
		// All the magic happens here : we store the stage gratefully given to us by
		// JavaFX into a singleton, which allows us to always get the same stage when we
		// will change the scenes
		StageService.getInstance().setPrimaryStage(primaryStage);
		try {
			this.showHomeScreen();
		} catch (IOException e) {
			System.out.println("too bad !");
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
