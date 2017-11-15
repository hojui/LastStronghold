package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import display.GameDisplay;

public class Main extends Application {
	
	public static int gameState = 0;
	
	@Override
	public void start(Stage stage) {
		try {
			
			GameDisplay gameDisplay = new GameDisplay();
			Scene scene = new Scene(gameDisplay,800,450);
			gameDisplay.requestScreenFocus();
			stage.setScene(scene);
			stage.setTitle("Last Stronghold I");
			stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
