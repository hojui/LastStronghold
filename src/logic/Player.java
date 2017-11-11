package logic;

import javafx.scene.canvas.GraphicsContext;
import share.IRenderable;
import share.RenderableHolder;

public class Player extends Sprite implements IRenderable {
	int shootTick;
	int currentShootTick;
	int imageIndex; // 0 - Not Move, 1 - Up, 2 - Down
	
	// Constructor
	public Player() {
		super();
		shootTick = 30;
		currentShootTick = 0;
	}
	
	// Method
	public void addShootTick() {
		shootTick++;
	}

	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}

	@Override
	public void draw(GraphicsContext gc) {
		RenderableHolder.getInstance();
		gc.drawImage(RenderableHolder.playerImages.get(imageIndex), this.getX(), this.getY());
	}

}
