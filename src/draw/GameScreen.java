package draw;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import logic.Field;
import share.RenderableHolder;

public class GameScreen extends Canvas {

	private ArrayList<String> input = new ArrayList<String>();
	private Field field = new Field();

	public GameScreen() {
		super(800, 450);
		addListerner();
	}

	public void addListerner() {
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				System.out.println("Press  "+e.getCode().toString() );
				String t = e.getCode().toString();
				if (!input.contains(t)) {
					input.add(t);
				}
			}
		});

		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				System.out.println("Release  "+e.getCode().toString() );
				String code = e.getCode().toString();
				input.remove(code);
			}
		});
	}
	
	public void drawScreen() {
		GraphicsContext gc = this.getGraphicsContext2D();
		this.field.draw(gc);
	}
}
