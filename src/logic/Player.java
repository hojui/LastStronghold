package logic;

import javafx.scene.canvas.GraphicsContext;
import share.IRenderable;
import share.RenderableHolder;

public class Player extends Sprite implements IRenderable {
	int shootTick;
	int imageIndex; // 0 - Not Move, 1 - Up, 2 - Down
	
	// Constructor
	public Player() {
		super();
		shootTick = 30; // 0.5 second
		super.tick = 0;
		super.setPosition(75, 225);
	}
	
	// Method
	public void addShootTick() {
		shootTick++;
	}
	
	public boolean shoot() {
		if (tick >= shootTick) {
			tick = 0;
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

}
