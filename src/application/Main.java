package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import window.SceneManager;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			
			SceneManager.initialize(stage);
			SceneManager.goToMainMenu();
			stage.setTitle("Last Stronghold I beta 0.0347");
			stage.centerOnScreen();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
