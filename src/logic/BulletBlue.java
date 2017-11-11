package logic;


import javafx.scene.canvas.GraphicsContext;
import share.IRenderable;
import share.RenderableHolder;

public class BulletBlue extends Bullet implements IRenderable {
	
	// Constructor
	public BulletBlue() {
		super();
	}
	
	public BulletBlue(double posX, double posY, double veloX) {
		super(posX, posY, veloX);
	}

	// Method
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.bulletBlueImages.get(0), super.getX(), super.getY());
	}
	
}
