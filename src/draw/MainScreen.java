package draw;

import game.GameMain;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import logic.Field;
import share.RenderableHolder;
import window.SceneManager;

public class MainScreen extends Canvas {
	
	private Field field = new Field();
	private static AnimationTimer timer;

	// Constructor
	public MainScreen() {
		super(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		addEventHandler();
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				RenderableHolder.getInstance().playMainMenuBgm();
				drawScreen();
			}
		};
		timer.start();
	}

	public void drawScreen() {
		GraphicsContext gc = this.getGraphicsContext2D();
		drawField(gc);
	}
	
	private void drawField(GraphicsContext gc) {
		field.tickIncrease();
		field.draw(gc);
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font(80));
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText("Last Defense", 400, 120);
		gc.setFont(Font.font(30));
		gc.fillText("Press enter to start", 400, 350);
		gc.setFont(Font.font(20));
		gc.setTextBaseline(VPos.BOTTOM);
		gc.fillText("Tutorial: T", 60, 440);
		gc.fillText("Exit: Esc", 750, 440);
	}
	
	private void addEventHandler() {
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if (e.getCode() == KeyCode.ENTER) {
					timer.stop();
					RenderableHolder.getInstance().stopMainMenuBgm();
					GameMain.newGame();
				}
				else if (e.getCode() == KeyCode.ESCAPE) {
					timer.stop();
					RenderableHolder.getInstance().stopMainMenuBgm();
					Platform.exit();
				} else if (e.getCode() == KeyCode.T) {
					GameMain.goToTutorial();
				}
			}
		});
	}
	
	public static AnimationTimer getTimer() {
		return timer;
	}
	
}
