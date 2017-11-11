package share;

import javafx.scene.canvas.GraphicsContext;

public class EnemyYellow extends Enemy implements IRenderable{
	
	public EnemyYellow () {
		super();
	}
	
	public EnemyYellow (double x,double y,double vX) {
		super(x,y,vX);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.enemyYellow, super.getX(), super.getY());
	}
}
