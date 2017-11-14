package logic;

import javafx.scene.canvas.GraphicsContext;
import share.IRenderable;
import share.RenderableHolder;

public class EnemyBlue extends Enemy implements IRenderable {

	public EnemyBlue() {
		super();
	}

	public EnemyBlue(double x, double y, double vX) {
		super(x, y, vX);
	}

	// get state of image
	protected int getState() {
		if (super.tick < 30) {
			return 0;
		} else if (super.tick < 60) {
			return 1;
		} else if (super.tick > 60) {
			super.tickReset();
			return 0;
		} else {
			return 0;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.enemyBlueImages.get(this.getState()), super.getX(), super.getY());
	}
}
