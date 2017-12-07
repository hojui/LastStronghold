package draw;

import java.util.ArrayList;

import com.sun.javafx.tk.Toolkit;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.Enemy;
import logic.EnemyBlue;
import logic.EnemyRed;
import logic.EnemyYellow;
import logic.Field;
import share.IRenderable;
import share.RenderableHolder;
import window.SceneManager;

@SuppressWarnings("restriction")
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
		drawDeadEnemy(gc);
	}

	public void drawPauseScreen() {
		GraphicsContext gc = this.getGraphicsContext2D();
		drawEnemys(gc);
		drawBullets(gc);
		drawPlayer(gc);
		drawStatus(gc);
		drawDeadEnemy(gc);
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font(35));
		double fontWidth = Toolkit.getToolkit().getFontLoader().computeStringWidth("PAUSE", gc.getFont());
		gc.fillText("PAUSE", SceneManager.SCENE_WIDTH / 2 - fontWidth / 2, SceneManager.SCENE_HEIGHT / 2 - 15);
		gc.setFont(Font.font(15));
		double fontWidth2 = Toolkit.getToolkit().getFontLoader().computeStringWidth("Press any key to resume",
				gc.getFont());
		gc.fillText("Press any key to resume", SceneManager.SCENE_WIDTH / 2 - fontWidth2 / 2,
				SceneManager.SCENE_HEIGHT / 2 + 10);
	}

	private void drawField(GraphicsContext gc) {
		field.tickIncrease();
		field.draw(gc);
	}

	private void drawEnemys(GraphicsContext gc) {
		for (IRenderable enemy : RenderableHolder.getInstance().getEnemies())
			enemy.draw(gc);
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
		double fontWidth = Toolkit.getToolkit().getFontLoader()
				.computeStringWidth("Score : " + RenderableHolder.getInstance().getScore(), gc.getFont());
		gc.fillText("Score : " + RenderableHolder.getInstance().getScore(), 800 - fontWidth - 17, 436);
	}

	private void drawDeadEnemy(GraphicsContext gc) {
		for (IRenderable enemy : RenderableHolder.getInstance().getDeadEnemies()) {
			Enemy tmp = (Enemy) enemy;
			tmp.tickDeadIncrease();

			if (tmp instanceof EnemyRed) {
				((EnemyRed) tmp).drawDeadEnemy(gc);
			} else if (tmp instanceof EnemyBlue) {
				((EnemyBlue) tmp).drawDeadEnemy(gc);
			} else if (tmp instanceof EnemyYellow) {
				((EnemyYellow) tmp).drawDeadEnemy(gc);
			}
		}
	}
}
