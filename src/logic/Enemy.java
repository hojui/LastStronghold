package logic;

import javafx.scene.canvas.GraphicsContext;

public class Enemy extends Sprite {
	
	public Enemy() {
		super();
	}
	
	public Enemy(double posX,double posY,double veloX) {
		super();
		super.setPosition(posX, posY);
		super.setVelocity(veloX, 0);
	}

}
