package draw;

import java.awt.Event;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import game.GameMain;
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
import share.RenderableHolder;
import window.SceneManager;

public class ScoreScreen extends Canvas{

	public ScoreScreen() {
		super(SceneManager.SCENE_WIDTH,SceneManager.SCENE_HEIGHT);
		GraphicsContext gc = this.getGraphicsContext2D();
		drawScoreScreen(gc);
		addEvent();
	}
	
	public void drawScoreScreen(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		int score = RenderableHolder.getInstance().getScore();
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font(60));
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double w = fontLoader.computeStringWidth(score + "" ,gc.getFont());
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText("Your Score", 400, 100);
		gc.fillText(score+"", 400, 200);
		gc.setFont(Font.font(30));
		gc.fillText("press Enter to retry",400,400);
	}
	
	public void addEvent() {
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					GameMain.newGame();
				}
				if (event.getCode() == KeyCode.ESCAPE) {
					Platform.exit();
				}
			}
			
		});
	}
	
}
