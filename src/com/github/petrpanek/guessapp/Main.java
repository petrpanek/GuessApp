package com.github.petrpanek.guessapp;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Třída slouží ke spuštění aplikace.
 * 
 * @author Petr Panek
 */
public class Main extends Application {
	
	/**
	 * Metoda, ve které se konstruuje okno.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Home.fxml"));
			Parent root = loader.load();
			
			primaryStage.setTitle("GuessApp");
	        primaryStage.setScene(new Scene(root));
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Spouštěcí metoda pro aplikaci
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
