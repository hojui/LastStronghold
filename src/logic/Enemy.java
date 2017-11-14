package logic;

public class Enemy extends Sprite {
	
	public Enemy() {
		super();
	}
	
	public Enemy(double posX,double posY,double veloX) {
		super();
		super.setPosition(posX, posY);
		super.setVelocity(veloX, 0);
	}
	
	public boolean isOutOfScreen() {
		if (super.getX() <= 0) return true;
		return false;
	}

}
