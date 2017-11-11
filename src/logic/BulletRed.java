package logic;


import javafx.scene.canvas.GraphicsContext;
import share.IRenderable;
import share.RenderableHolder;

public class BulletRed extends Bullet implements IRenderable {
	
	// Constructor
	public BulletRed() {
		super();
	}
	
	public BulletRed(double posX, double posY, double veloX) {
		super(posX, posY, veloX);
	}

	// Method
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.bulletRedImages.get(0), super.getX(), super.getY());
	}
	
}
