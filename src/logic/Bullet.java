package logic;

public class Bullet extends Sprite {

	// Constructor
	public Bullet() {
		super();
	}
	
	public Bullet(double posX, double posY, double veloX) {
		super();
		setPosition(posX, posY);
		setVelocity(veloX, 0);
	}
	
}
