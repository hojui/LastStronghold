package share;


import javafx.scene.canvas.GraphicsContext;

public class BulletRed extends Bullet implements IRenderable {
	
	// Constructor
	public BulletRed() {
		super();
	}
	
	public BulletRed(int posX, int posY, int veloX) {
		super(posX, posY, veloX);
	}

	// Method
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.bulletRed, super.getX(), super.getY());
	}
	
}
