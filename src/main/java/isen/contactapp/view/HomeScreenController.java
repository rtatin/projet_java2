package isen.contactapp.view;

import isen.contactapp.BDD.PersonDao;
import isen.contactapp.entities.Person;
import isen.contactapp.service.StageService;
import isen.contactapp.service.ViewService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller de la view homescreen
 * @author rtwam
 */
public class HomeScreenController {
	
    /**
     * Lorsqu'on appuie sur launch, ouvre le tableau et donc la contactpage
     * @throws Exception
     */
    @FXML
	public void handleLaunchButton() throws Exception {
		StageService.showView(ViewService.getView("ContactPage"));
	}
}
