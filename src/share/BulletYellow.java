package share;


import javafx.scene.canvas.GraphicsContext;

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
		gc.drawImage(RenderableHolder.bulletYellow, super.getX(), super.getY());
	}
	
}
