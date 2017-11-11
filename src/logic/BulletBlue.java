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
	
	//get state of image
	protected int getState() {
		if (super.tick == 0) {
			return 0;
		}
		if (super.tick < 20) {
			return 1;
		} else if (super.tick < 40) {
			return 2;
		} else if (super.tick < 60) {
			return 3;
		} else if (super.tick > 60) {
			super.tick = 1;
			return 4;
		} else {
			return 0;
		}
	}
	// Method
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.bulletBlueImages.get(this.getState()), super.getX(), super.getY());
	}
	
}
