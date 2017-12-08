package logic;

import javafx.scene.canvas.GraphicsContext;
import share.IRenderable;
import share.RenderableHolder;

public class Player extends Sprite implements IRenderable {
	protected int shootTick;
	protected int imageIndex; // 0 - Not Move, 1 - Up, 2 - Down
	
	// Constructor
	public Player() {
		super();
		shootTick = 15; // 0.25 second
		super.tick = 30;
		super.setPosition(25, 165);
		super.width = 75;
		super.height = 75;
	}
	
	// Method	
	public boolean canShoot() {
		if (tick >= shootTick) {
			tickReset();
			return true;
		}
		return false;
	}

	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.playerImages.get(imageIndex), this.getX(), this.getY());
	}
	
	public int getShootTick() {
		return this.shootTick;
	}

}
