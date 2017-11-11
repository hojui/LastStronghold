package logic;

import javafx.scene.canvas.GraphicsContext;
import share.IRenderable;
import share.RenderableHolder;

public class EnemyRed extends Enemy implements IRenderable{
	
	public EnemyRed () {
		super();
	}
	
	public EnemyRed (double x,double y,double vX) {
		super(x,y,vX);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.enemyRedImages.get(0), super.getX(), super.getY());
	}
}
