package draw;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import logic.Field;

public class GameScreen extends Canvas {

	public static ArrayList<String> inputs = new ArrayList<String>();
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
		
		//draw field
		field.tickIncrease();
		if (field.getTick() > 25) {
			field.draw(gc);
			field.tickReset();
		}
		
		
	}
}
