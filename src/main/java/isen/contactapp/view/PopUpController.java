package isen.contactapp.view;

import java.awt.*;

import isen.contactapp.service.StageService;
import isen.contactapp.service.ViewService;
import javafx.fxml.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopUpController {
	 @FXML
	 Button ok;
	 
	 @FXML
	 Text Error;
	 
	 private Stage stage;
	 
	public void start(Stage primaryStage) {
		StageService.initPrimaryStage(primaryStage);
		StageService.showView(ViewService.getView("HomeScreen"));
	}

	 public void setStage(Stage stage) {
	    this.stage = stage;
	}
	 
	 public void onClickOk() {
		 stage.close();
	 }
}
