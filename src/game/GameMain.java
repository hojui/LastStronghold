package game;

import draw.GameScreen;
import draw.ScoreScreen;
import javafx.animation.AnimationTimer;
import logic.GameLogic;
import share.RenderableHolder;
import window.SceneManager;

public class GameMain {
	
	private static GameLogic gameLogic;
	private static GameScreen gameScreen;
	private static ScoreScreen scoreScreen;
	private static AnimationTimer timer;
	
	public static void newGame() {
		gameLogic = new GameLogic();
		gameScreen = new GameScreen();
		RenderableHolder.getInstance().newGame();
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				gameScreen.getGraphicsContext2D().clearRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
				gameLogic.updateLogic();
				gameScreen.drawScreen();
			}
		};
		timer.start();
		SceneManager.gotoSceneOf(gameScreen);
	}
	
	public static void goToMainMenu() {
		SceneManager.goToMainMenu();
	}
	
	public static void goToResult() {
		timer.stop();
		scoreScreen = new ScoreScreen();
		SceneManager.gotoSceneOf(scoreScreen);
	}
	
}
