package isen.contactapp.service;

import java.io.IOException;

import isen.contactapp.MainApp;
import javafx.fxml.FXMLLoader;

/**
 * Gestion des view
 * @author rtwam
 */
public class ViewService {

    /**
     * Récupère les views
     * @param <T>
     * @param id
     * @return
     */
    public static <T> T getView(String id) {
		return getLoader(id).getRoot();
	}

	private static FXMLLoader getLoader(String id) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/" + id + ".fxml"));
			loader.load();
			return loader;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}

