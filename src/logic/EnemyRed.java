package logic;

import javafx.scene.canvas.GraphicsContext;
import share.IRenderable;
import share.RenderableHolder;

public class EnemyRed extends Enemy implements IRenderable {

	public EnemyRed() {
		super();
	}

	public EnemyRed(double x, double y, double vX) {
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
		gc.drawImage(RenderableHolder.enemyRedImages.get(this.getState()), super.getX(), super.getY());
	}
	
	public void drawDeadEnemy(GraphicsContext gc) {
		if (super.tickDead < 3) {
			gc.drawImage(RenderableHolder.enemyRedImages.get(2), super.getX(), super.getY());
		} else if (super.tickDead < 6) {
			gc.drawImage(RenderableHolder.enemyRedImages.get(3), super.getX(), super.getY());
		} else if (super.tickDead < 9) {
			gc.drawImage(RenderableHolder.enemyRedImages.get(4), super.getX(), super.getY());
		} else if (super.tickDead < 12) {
			gc.drawImage(RenderableHolder.enemyRedImages.get(5), super.getX(), super.getY());
		} else if (super.tickDead < 15) {
			gc.drawImage(RenderableHolder.enemyRedImages.get(6), super.getX(), super.getY());
		}
	}
}
