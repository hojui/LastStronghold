package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import share.IRenderable;

public class Field implements IRenderable{
	
	private int tick;
	
	public Field() {
		this.tick = 26;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, 800, 450);
		gc.setFill(Color.WHITE);
		for (int i = 0 ; i < 10 ; i++) {
			int randomX = (int) (Math.random()*800 +1);
			int randomY = (int) (Math.random()*450 +1);
			gc.fillOval(randomX,randomY,5,5);
		}
		for (int i = 0 ; i < 5 ; i++) {
			int randomX = (int) (Math.random()*800 +1);
			int randomY = (int) (Math.random()*450 +1);
			gc.fillOval(randomX,randomY,10,10);
		}
		
	}
	
	public void tickReset() {
		this.tick = 0;
	}
	
	public void tickIncrease() {
		this.tick++;
	}
	
	public int getTick() {
		return this.tick;
	}

}
