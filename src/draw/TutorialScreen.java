package draw;

import game.GameMain;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import window.SceneManager;

public class TutorialScreen extends Canvas {

	public TutorialScreen() {
		super(SceneManager.SCENE_WIDTH,SceneManager.SCENE_HEIGHT);
		GraphicsContext gc = this.getGraphicsContext2D();
		drawScreen(gc);
		addEvent();
	}
	
	private void drawScreen(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		gc.setFont(Font.font(30));
		gc.setTextBaseline(VPos.TOP);
		gc.setFill(Color.WHITE);
		gc.fillText("Z , C\t\t\t\tChange Bullet", 150, 50);
		gc.fillText("X\t\t\t\tShoot", 150, 150);
		gc.fillText("Arrow Keys\t\tMove Character", 150, 250);
		gc.fillText("ESC\t\t\t\tPause", 150, 350);
		gc.setFont(Font.font(20));
		gc.fillText("Back : B", 20, 415);
	}
	
	private void addEvent() {
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			public void handle(KeyEvent e) {
				
				if (e.getCode() == KeyCode.B) {
					MainScreen.getTimer().start();;
					GameMain.goToMainMenu();
				}
			}
		});
	}
}
