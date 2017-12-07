package game;

import draw.GameScreen;
import draw.ScoreScreen;
import javafx.application.Platform;
import logic.GameLogic;
import share.RenderableHolder;
import window.SceneManager;

public class GameMain {

	private static GameLogic gameLogic;
	private static GameScreen gameScreen;
	private static ScoreScreen scoreScreen;
	private static Thread gameThread;
	private static boolean isGameRunning;
	private static boolean isGamePaused;

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
						if (!isGamePaused) {
							RenderableHolder.getInstance().playGameBgm();
							gameLogic.updateLogic();
							gameScreen.drawScreen();
							Thread.sleep(20);
						} else {
							if (!GameScreen.inputs.isEmpty()) {
								isGamePaused = false;
								GameScreen.inputs.clear();
							}
							RenderableHolder.getInstance().stopGameBgm();
							gameScreen.drawPauseScreen();
							Thread.sleep(20);
						}
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
		isGamePaused = false;
		RenderableHolder.getInstance().stopGameBgm();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				scoreScreen = new ScoreScreen();
				SceneManager.gotoSceneOf(scoreScreen);
			}
		});
	}
	
	public static void pauseGame() {
		isGamePaused = true;
	}
	
	public static void resumeGame() {
		isGamePaused = false;
	}

	public static void stopGame() {
		isGameRunning = false;
	}

}
