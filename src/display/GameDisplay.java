package display;

import draw.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import logic.GameLogic;
import share.RenderableHolder;

public class GameDisplay extends Group {

	private GameScreen gameScreen;
	private GameLogic gameLogic;
	
	public GameDisplay() {
		super();
		gameScreen = new GameScreen();
		gameLogic = new GameLogic();
		this.getChildren().add(gameScreen);
		RenderableHolder.getInstance().resetGame();
		
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				gameScreen.getGraphicsContext2D().clearRect(0, 0, 800, 450);
				gameLogic.updateLogic();
				gameScreen.drawScreen();
			}
		}.start();
	}
	
	public void requestScreenFocus() {
		this.gameScreen.requestFocus();
	}
}
