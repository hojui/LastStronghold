package share;

import javafx.scene.canvas.GraphicsContext;

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
