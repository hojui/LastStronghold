package share;


public class Bullet extends Sprite {

	// Constructor
	public Bullet() {
		super();
	}
	
	public Bullet(int posX, int posY, int veloX) {
		super();
		setPosition(posX, posY);
		setVelocity(veloX, 0);
	}
	
}
