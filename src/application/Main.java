package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import share.RenderableHolder;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import draw.GameScreen;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,800,450);
			
			// Create GameScreen
			GameScreen gameScreen = new GameScreen();
			root.getChildren().add(gameScreen);
			gameScreen.requestFocus();
			
			GameLogic gameLogic = new GameLogic();
			
			stage.setScene(scene);
			stage.setTitle("Last Stronghold I");
			stage.show();

			new AnimationTimer() {
				@Override
				public void handle(long now) {
					gameScreen.getGraphicsContext2D().clearRect(0, 0, 800, 450);
					gameLogic.updateLogic();
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
