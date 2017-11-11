package logic;


import javafx.scene.canvas.GraphicsContext;
import share.IRenderable;
import share.RenderableHolder;

public class BulletYellow extends Bullet implements IRenderable {
	
	// Constructor
	public BulletYellow() {
		super();
	}
	
	public BulletYellow(double posX, double posY, double veloX) {
		super(posX, posY, veloX);
	}

	// Method
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.bulletYellowImages.get(0), super.getX(), super.getY());
	}
	
}
