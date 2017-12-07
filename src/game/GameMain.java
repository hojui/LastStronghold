package game;

import draw.GameScreen;
import draw.ScoreScreen;
import draw.TutorialScreen;
import javafx.application.Platform;
import logic.GameLogic;
import share.RenderableHolder;
import window.SceneManager;

public class GameMain {
	
	private static GameLogic gameLogic;
	private static GameScreen gameScreen;
	private static ScoreScreen scoreScreen;
	private static TutorialScreen tutorialScreen;
	private static Thread gameThread;
	private static boolean isGameRunning;
	
	public static void newGame() {
		gameLogic = new GameLogic();
		gameScreen = new GameScreen();
		RenderableHolder.getInstance().newGame();
		gameThread = new Thread(new Runnable() {
			@Override
			public void run() {
				isGameRunning = true;
				while (isGameRunning) {
					try {
						RenderableHolder.getInstance().playGameBgm();
						gameScreen.getGraphicsContext2D().clearRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
						gameLogic.updateLogic();
						gameScreen.drawScreen();
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		gameThread.start();

		SceneManager.gotoSceneOf(gameScreen);
	}
	
	public static void goToMainMenu() {
		SceneManager.goToMainMenu();
	}
	
	public static void goToResult() {
		isGameRunning = false;
		RenderableHolder.getInstance().stopGameBgm();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				scoreScreen = new ScoreScreen();
				SceneManager.gotoSceneOf(scoreScreen);
			}
		});
	}
	
	public static void goToTutorial() {
		tutorialScreen = new TutorialScreen();
		SceneManager.gotoSceneOf(tutorialScreen);
	}
	
	public static void stopGame() {
		isGameRunning = false;
	}
	
}
