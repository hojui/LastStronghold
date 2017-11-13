package logic;

import java.util.ArrayList;
import java.util.List;

import draw.GameScreen;
import share.IRenderable;
import share.RenderableHolder;

public class GameLogic {
	private Player player;
	private List<IRenderable> enemyList;
	private List<IRenderable> bulletList;
	private int bulletState;
	private int enemyTick;
	private int currentEnemyTick;
	private int enemyCount;
	private int score;
	
	public GameLogic() {
		player = new Player();
		enemyList = new ArrayList<>();
		bulletList = new ArrayList<>();
		bulletState = 0;
		enemyTick = 60;
		currentEnemyTick = 0;
		score = 0;
		enemyCount = 0;
	}
	
	public void addEnemy(IRenderable enemy) {
		enemyList.add(enemy);
		RenderableHolder.getInstance().addEnemy(enemy);
	}
	
	public void addBullet(IRenderable bullet) {
		bulletList.add(bullet);
		RenderableHolder.getInstance().addBullet(bullet);
	}
	
	public void updateLogic() {
		if (GameScreen.inputs.contains("UP")) {
			player.update(0, -7);
		}
		if (GameScreen.inputs.contains("DOWN")) {
			player.update(0, 7);
		}
		if (GameScreen.inputs.contains("A")) {
			Bullet bullet;
			switch (bulletState) {
			case 0 :
				bullet = new BulletRed(player.getX() + 75, player.getY() + 37.5, 12);
				break;
			case 1 :
				bullet = new BulletBlue(player.getX() + 75, player.getY() + 37.5, 12);
				break;
			case 2 :
				bullet = new BulletYellow(player.getX() + 75, player.getY() + 37.5, 12);
				break;
			default :
				bullet = new BulletRed(player.getX() + 75, player.getY() + 37.5, 12);
				break;
			}
			addBullet((IRenderable) bullet);
			RenderableHolder.getInstance().addBullet((IRenderable) bullet);
		}
		if (GameScreen.inputs.contains("TAB")) {
			bulletState = (bulletState + 1) % 3;
		}
		
		updatePosition();
		
		if (currentEnemyTick++ > enemyTick) {
			generateEnemy();
			this.currentEnemyTick = 0;
		}
	}
	
	private void updatePosition() {
		// Update logic bullet list position
		for (IRenderable bullet : bulletList) {
			Bullet tmp = (Bullet) bullet;
			tmp.setPosition(tmp.getX() + tmp.velocityX, tmp.getY());
		}
		// Update RenderableHolder bullet list position
		for (IRenderable bullet : RenderableHolder.bullets) {
			Bullet tmp = (Bullet) bullet;
			tmp.setPosition(tmp.getX() + tmp.velocityX, tmp.getY());
		}
		// Update logic enemy list position
		for (IRenderable enemy : enemyList) {
			Enemy tmp = (Enemy) enemy;
			tmp.setPosition(tmp.getX() + tmp.velocityX, tmp.getY());
		}
		// Update RenderableHolder enemy list position
		for (IRenderable enemy : RenderableHolder.enemys) {
			Enemy tmp = (Enemy) enemy;
			tmp.setPosition(tmp.getX() + tmp.velocityX, tmp.getY());
		}
	}

	public void generateEnemy() {
		int rand = (int) (Math.random() * 3 + 1);
		int position = (int) (Math.random() * 325 + 1);
		Enemy enemy;
		switch (rand) {
		case 1 :
			enemy = new EnemyRed(450, position, -17);
			break;
		case 2 :
			enemy = new EnemyBlue(450, position, -17);
			break;
		case 3 :
			enemy = new EnemyYellow(450, position, -17);
			break;
		default :
			enemy = new EnemyRed(450, position, -17);
			break;
		}
		enemyList.add((IRenderable) enemy);
		RenderableHolder.getInstance().addEnemy((IRenderable) enemy);
	}
	
	public void isCollided() {
		for (IRenderable bullet : bulletList) {
			for (IRenderable enemy : enemyList) {
				if (((Bullet) bullet).intersects((Enemy) enemy)) {
					bulletList.remove(bullet);
					enemyList.remove(enemy);
				}
			}
		}
		for (IRenderable enemy : enemyList) {
			if (player.intersects((Enemy) enemy)) {
				/// TODO End game
			}
		}
	}
	
}
