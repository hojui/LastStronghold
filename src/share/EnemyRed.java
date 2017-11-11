package share;

import javafx.scene.canvas.GraphicsContext;

public class EnemyRed extends Enemy implements IRenderable{
	
	public EnemyRed () {
		super();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.enemyRed, super.getX(), super.getY());
	}
}
