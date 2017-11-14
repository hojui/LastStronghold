package draw;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.Field;
import share.IRenderable;
import share.RenderableHolder;

public class GameScreen extends Canvas {

	public static ArrayList<String> inputs = new ArrayList<String>();
	private Field field = new Field();

	public GameScreen() {
		super(800, 450);
		addListener();
	}

	public void addListener() {
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				System.out.println("Press  "+e.getCode().toString() );
				String t = e.getCode().toString();
				if (!inputs.contains(t)) {
					inputs.add(t);
				}
			}
		});

		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				System.out.println("Release  "+e.getCode().toString() );
				String code = e.getCode().toString();
				inputs.remove(code);
			}
		});
	}
	
	public void drawScreen() {
		GraphicsContext gc = this.getGraphicsContext2D();
		drawField(gc);
		drawEnemys(gc);
		drawBullets(gc);
		drawPlayer(gc);
		drawStatus(gc);
	}
	
	private void drawField(GraphicsContext gc) {
		field.tickIncrease();
		if (field.getTick() > 25) {
			field.draw(gc);
			field.tickReset();
		}
	}
	
	private void drawEnemys(GraphicsContext gc) {
		for (IRenderable enemy : RenderableHolder.getInstance().getEnemies()) enemy.draw(gc);
		//for (IRenderable deadEnemy : RenderableHolder.getInstance().getDeadEnemies()) deadEnemy.draw(gc); // TODO Change draw method
	}
	
	private void drawBullets(GraphicsContext gc) {
		for (IRenderable bullet : RenderableHolder.getInstance().getBullets()) bullet.draw(gc);
	}

	private void drawPlayer(GraphicsContext gc) {
		RenderableHolder.getInstance().getPlayer().draw(gc);
	}
	
	private void drawStatus(GraphicsContext gc) {
		// Set BG
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 400, 800, 50);
		// Set bullet state color
		switch (RenderableHolder.getInstance().getBulletState()) {
		case 0 :
			gc.setFill(Color.RED); break;
		case 1 :
			gc.setFill(Color.BLUE); break;
		case 2 : 
			gc.setFill(Color.YELLOW); break;
		default :
			gc.setFill(Color.RED); break;
		}
		gc.fillRect(30, 410, 30, 60);
		// Set score display
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font(20));
		gc.fillText("Score : " + RenderableHolder.getInstance().getScore(), 120, 440);
	}
}
