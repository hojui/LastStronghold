package logic;

import javafx.scene.canvas.GraphicsContext;
import share.IRenderable;
import share.RenderableHolder;

public class EnemyBlue extends Enemy implements IRenderable{
	
	public EnemyBlue () {
		super();
	}
	
	public EnemyBlue (double x,double y,double vX) {
		super(x,y,vX);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.enemyBlue, super.getX(), super.getY());
	}
}
