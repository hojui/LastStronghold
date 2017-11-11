package logic;

import javafx.scene.canvas.GraphicsContext;
import share.IRenderable;
import share.RenderableHolder;

public class EnemyYellow extends Enemy implements IRenderable {

	public EnemyYellow() {
		super();
	}

	public EnemyYellow(double x, double y, double vX) {
		super(x, y, vX);
	}

	// get state of image
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

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.enemyYellowImages.get(0), super.getX(), super.getY());
	}
}
