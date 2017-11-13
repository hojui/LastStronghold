package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import draw.GameScreen;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,800,450);
			stage.setScene(scene);
			stage.setTitle("Last Stronghold I");
			stage.show();
			
			//Create GameScreen
			GameScreen gameScreen = new GameScreen();
			root.getChildren().add(gameScreen);
			
			new AnimationTimer() {

				@Override
				public void handle(long now) {
					gameScreen.drawScreen();
				}
				
			}.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
