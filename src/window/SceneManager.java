package window;

import draw.MainScreen;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class SceneManager {
	
	private static Stage primaryStage;
	private static Canvas mainScreen = new MainScreen();
	private static Scene mainMenuScene = new Scene(new Pane(mainScreen));
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 450;
	
	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}
	
	public static void goToMainMenu() {
		mainScreen.requestFocus();
		primaryStage.setScene(mainMenuScene);
	}
	
	public static void gotoSceneOf(Canvas canvas) {
		Scene newScene = new Scene(new Pane(canvas));
		canvas.requestFocus();
		primaryStage.setScene(newScene);
	}
	
	
}
