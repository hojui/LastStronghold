package logic;

public class Enemy extends Sprite {
	
	protected int tickDead;
	
	public Enemy() {
		super();
		super.width = 75;
		super.height = 75;
		this.tickDead = 0;
	}
	
	public Enemy(double posX,double posY,double veloX) {
		super();
		super.setPosition(posX, posY);
		super.setVelocity(veloX, 0);
		super.width = 75;
		super.height = 75;
	}
	
	public boolean isOutOfScreen() {
		if (super.getX() <= 0) return true;
		return false;
	}
	
	public void tickDeadIncrease() {
		this.tickDead++;
	}
	
	public void tickDeadReset() {
		this.tickDead = 0;
	}
	
	public int getTickDead() {
		return this.tickDead;
	}

}
