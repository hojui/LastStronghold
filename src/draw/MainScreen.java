package draw;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import logic.Field;

public class MainScreen extends Canvas {
	
	private Field field = new Field();
	
	// Constructor
	public MainScreen() {
		super(800, 450);
	}

	public void drawScreen() {
		GraphicsContext gc = this.getGraphicsContext2D();
		drawField(gc);
		
	}
	
	private void drawField(GraphicsContext gc) {
		field.tickIncrease();
		// if (field.getTick() > 25) {
		// field.draw(gc);
		// field.tickReset();
		// }
		field.draw(gc);
	}
	
}
