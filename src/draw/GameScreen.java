package draw;

import java.util.ArrayList;

import com.sun.javafx.tk.Toolkit;

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

	// Constructor
	public GameScreen() {
		super(800, 450);
		addListener();
	}

	// Add key listener
	public void addListener() {
		// Add pressed key to list
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String t = e.getCode().toString();
				if (!inputs.contains(t)) {
					inputs.add(t);
				}
			}
		});
		// Remove released key from list
		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				inputs.remove(code);
			}
		});
	}

	// Main draw method
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
		// if (field.getTick() > 25) {
		// field.draw(gc);
		// field.tickReset();
		// }
		field.draw(gc);
	}

	private void drawEnemys(GraphicsContext gc) {
		for (IRenderable enemy : RenderableHolder.getInstance().getEnemies())
			enemy.draw(gc);
		// for (IRenderable deadEnemy : RenderableHolder.getInstance().getDeadEnemies())
		// deadEnemy.draw(gc); // TODO Change draw method
	}

	private void drawBullets(GraphicsContext gc) {
		for (IRenderable bullet : RenderableHolder.getInstance().getBullets())
			bullet.draw(gc);
	}

	private void drawPlayer(GraphicsContext gc) {
		RenderableHolder.getInstance().getPlayer().draw(gc);
	}

	private void drawStatus(GraphicsContext gc) {
		// Set BG
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 400, 800, 50);

		// Set bullet text
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font(30));
		gc.fillText("Bullet", 17, 436);

		// Set bullet state indicator
		int bulletState = RenderableHolder.getInstance().getBulletState();
		if (bulletState == 2) {
			gc.setFill(Color.RED);
			gc.fillOval(120, 420, 10, 10);
			gc.setFill(Color.BLUE);
			gc.fillOval(150, 420, 10, 10);
			gc.setFill(Color.YELLOW);
			gc.fillOval(172.5, 412.5, 25, 25);
		} else if (bulletState == 1) {
			gc.setFill(Color.RED);
			gc.fillOval(120, 420, 10, 10);
			gc.setFill(Color.BLUE);
			gc.fillOval(142.5, 412.5, 25, 25);
			gc.setFill(Color.YELLOW);
			gc.fillOval(180, 420, 10, 10);
		} else {
			gc.setFill(Color.RED);
			gc.fillOval(112.5, 412.5, 25, 25);
			gc.setFill(Color.BLUE);
			gc.fillOval(150, 420, 10, 10);
			gc.setFill(Color.YELLOW);
			gc.fillOval(180, 420, 10, 10);
		}

		// Set score display
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font(30));
		@SuppressWarnings("restriction")
		double fontWidth = Toolkit.getToolkit().getFontLoader()
				.computeStringWidth("Score : " + RenderableHolder.getInstance().getScore(), gc.getFont());
		gc.fillText("Score : " + RenderableHolder.getInstance().getScore(), 800 - fontWidth - 17, 436);
	}
}
