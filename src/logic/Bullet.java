package logic;

public class Bullet extends Sprite {

	public Bullet() {
		super();
		super.width = 30;
		super.height = 9;
	}
	
	public Bullet(double posX, double posY, double veloX) {
		super();
		setPosition(posX, posY);
		setVelocity(veloX, 0);
		super.width = 30;
		super.height = 9;
	}
	
	public boolean isOutOfScreen() {
		if (this.getX() >= 800) return true;
		return false;
	}
	
}
