package share;

import javafx.scene.canvas.GraphicsContext;

public class EnemyYellow extends Enemy implements IRenderable{
	
	public EnemyYellow () {
		super();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.enemyYellow, super.getX(), super.getY());
	}
}
