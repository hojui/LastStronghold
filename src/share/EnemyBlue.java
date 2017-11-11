package share;

import javafx.scene.canvas.GraphicsContext;

public class EnemyBlue extends Enemy implements IRenderable{
	
	public EnemyBlue () {
		super();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.enemyBlue, super.getX(), super.getY());
	}
}
