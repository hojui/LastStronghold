package logic;

import java.util.ArrayList;
import java.util.List;

import draw.GameScreen;
import game.GameMain;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import share.IRenderable;
import share.RenderableHolder;

public class GameLogic {
	private Player player;
	private List<IRenderable> enemyList;
	private List<IRenderable> deadEnemyList;
	private List<IRenderable> bulletList;
	private int bulletState;
	private int enemyTick;
	private int currentEnemyTick;
	private int score;
	private double currentEnemySpeed;

	public GameLogic() {
		player = new Player();
		enemyList = new ArrayList<>();
		deadEnemyList = new ArrayList<>();
		bulletList = new ArrayList<>();
		bulletState = 0;
		enemyTick = 60;
		currentEnemyTick = 0;
		score = 0;
		currentEnemySpeed = -1;
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
		player.tickIncrease();
		readInput();
		updatePosition();
		updateDeadEnemy();
		checkBulletOutOfScreen();
		checkPlayerOutOfScreen();
		checkIfEnemyDestroy();
		if (currentEnemyTick++ > enemyTick)
			generateEnemy();
		updateLevel();
		checkIfEndGame();
	}

	private void updatePosition() {
		// Update logic bullet list position
		for (IRenderable bullet : bulletList) {
			Bullet tmp = (Bullet) bullet;
			tmp.setPosition(tmp.getX() + tmp.velocityX, tmp.getY());
		}
		// Update RenderableHolder bullet list position
		for (IRenderable bullet : RenderableHolder.getInstance().getBullets()) {
			Bullet tmp = (Bullet) bullet;
			tmp.setPosition(tmp.getX() + tmp.velocityX, tmp.getY());
		}
		// Update logic enemy list position
		for (IRenderable enemy : enemyList) {
			Enemy tmp = (Enemy) enemy;
			tmp.tickIncrease();
			tmp.setPosition(tmp.getX() + tmp.velocityX, tmp.getY());
		}
		// Update RenderableHolder enemy list position
		for (IRenderable enemy : RenderableHolder.getInstance().getEnemies()) {
			Enemy tmp = (Enemy) enemy;
			tmp.tickIncrease();
			tmp.setPosition(tmp.getX() + tmp.velocityX, tmp.getY());
		}
	}

	private void checkBulletOutOfScreen() {
		List<IRenderable> toRemove = new ArrayList<>();
		for (IRenderable bullet : bulletList) {
			if (((Bullet) bullet).isOutOfScreen())
				toRemove.add(bullet);
		}
		bulletList.removeAll(toRemove);
		RenderableHolder.getInstance().removeBullet(toRemove);
	}

	private void checkPlayerOutOfScreen() {
		if (this.player.getY() < -40) {
			this.player.setPosition(25, 400);
			RenderableHolder.getInstance().setPlayerPosition(25, 400);
		}
		if (this.player.getY() > 400) {
			this.player.setPosition(25, -40);
			RenderableHolder.getInstance().setPlayerPosition(25, -40);
		}
	}

	private void generateEnemy() {
		int rand = (int) (Math.random() * 3 + 1);
		int position = (int) (Math.random() * 325 + 1);
		Enemy enemy;
		switch (rand) {
		case 1:
			enemy = new EnemyRed(800, position, currentEnemySpeed);
			break;
		case 2:
			enemy = new EnemyBlue(800, position, currentEnemySpeed);
			break;
		case 3:
			enemy = new EnemyYellow(800, position, currentEnemySpeed);
			break;
		default:
			enemy = new EnemyRed(800, position, currentEnemySpeed);
			break;
		}
		enemyList.add((IRenderable) enemy);
		RenderableHolder.getInstance().addEnemy((IRenderable) enemy);
		currentEnemyTick = 0;
	}

	private void checkIfEnemyDestroy() {
		List<IRenderable> toRemove = new ArrayList<>(); // List to-remove elements
		for (IRenderable bullet : bulletList) {
			for (IRenderable enemy : enemyList) {
				if (((Sprite) bullet).intersects((Sprite) enemy)) {
					if (isSameColor(bullet, enemy)) {
						// Add enemy to remove list
						toRemove.add(enemy);
						// Add to dead enemy list
						deadEnemyList.add(enemy);
						RenderableHolder.getInstance().addDeadEnemy(enemy);
						// Add score
						score += 100;
						RenderableHolder.getInstance().addScore(100);
					} else {
						if (this.score >= 50) {
							score -= 50;
							RenderableHolder.getInstance().subtractScore(50);
						}
					}
					// Add bullet to remove list
					toRemove.add(bullet);
				}
			}
		}
		// Remove remove element
		this.enemyList.removeAll(toRemove);
		this.bulletList.removeAll(toRemove);
		RenderableHolder.getInstance().removeEnemy(toRemove);
		RenderableHolder.getInstance().removeBullet(toRemove);
	}

	private boolean isSameColor(IRenderable bullet, IRenderable enemy) {
		if (bullet instanceof BulletRed && enemy instanceof EnemyRed)
			return true;
		if (bullet instanceof BulletBlue && enemy instanceof EnemyBlue)
			return true;
		if (bullet instanceof BulletYellow && enemy instanceof EnemyYellow)
			return true;
		return false;
	}

	private void fireBullet() {
		if (player.canShoot()) {
			Bullet bullet;
			switch (bulletState) {
			case 0:
				bullet = new BulletRed(player.getX() + 75, player.getY() + 37.5, 12);
				break;
			case 1:
				bullet = new BulletBlue(player.getX() + 75, player.getY() + 37.5, 12);
				break;
			case 2:
				bullet = new BulletYellow(player.getX() + 75, player.getY() + 37.5, 12);
				break;
			default:
				bullet = new BulletRed(player.getX() + 75, player.getY() + 37.5, 12);
				break;
			}
			addBullet((IRenderable) bullet);
			RenderableHolder.getInstance().addBullet((IRenderable) bullet);
			RenderableHolder.getInstance().playShootSound();
		}
	}
	
	private void ultimateLuckyBullet() {
		if (player.canShoot()) {
			Bullet bullet;
			switch ((int) (Math.random()*3 + 1)) {
			case 1:
				bullet = new BulletRed(player.getX() + 75, Math.random()*400 + 1, 12);
				break;
			case 2:
				bullet = new BulletBlue(player.getX() + 75, Math.random()*400 + 1, 12);
				break;
			case 3:
				bullet = new BulletYellow(player.getX() + 75, Math.random()*400 + 1, 12);
				break;
			default:
				bullet = new BulletRed(player.getX() + 75, Math.random()*400 + 1, 12);
				break;
			}
			addBullet((IRenderable) bullet);
			RenderableHolder.getInstance().addBullet((IRenderable) bullet);
			RenderableHolder.getInstance().playShootSound();
		}
	}

	private void endGame() {
		// TODO fix end game alert
//		Alert alert = new Alert(AlertType.CONFIRMATION, "GAME OVER", ButtonType.OK);
//		alert.setContentText("Score : " + this.score);
//		alert.showAndWait();
		GameMain.goToResult();
	}

	private void readInput() {
		if (!GameScreen.inputs.contains("UP") && !GameScreen.inputs.contains("DOWN")) {
			this.player.setImageIndex(0);
			RenderableHolder.getInstance().updateImagePlayer(0);
		}
		if (GameScreen.inputs.contains("UP")) {
			player.update(0, -7);
			player.setImageIndex(1);
			RenderableHolder.getInstance().updateImagePlayer(1);
			RenderableHolder.getInstance().updatePlayer(0, -7);
		}
		if (GameScreen.inputs.contains("DOWN")) {
			player.update(0, 7);
			player.setImageIndex(2);
			RenderableHolder.getInstance().updateImagePlayer(2);
			RenderableHolder.getInstance().updatePlayer(0, 7);
		}
		if (GameScreen.inputs.contains("X")) {
			fireBullet();
		}
		if (GameScreen.inputs.contains("C")) {
			bulletState = (bulletState + 1) % 3;
			RenderableHolder.getInstance().setBulletState(bulletState);
			GameScreen.inputs.remove("C");
		}
		if (GameScreen.inputs.contains("Z")) {
			bulletState = (bulletState == 0) ? 2 : (bulletState - 1) % 3;
			RenderableHolder.getInstance().setBulletState(bulletState);
			GameScreen.inputs.remove("Z");
		}
		
		//Ultimate Super Button, Don't push it!!
		if (GameScreen.inputs.contains("J") && GameScreen.inputs.contains("U")) {
			this.secretJuiButton();
		}
		if (GameScreen.inputs.contains("M") && GameScreen.inputs.contains("A")) {
			this.secretMaxButton();
		}
		if (GameScreen.inputs.contains("L") && GameScreen.inputs.contains("K")) {
			this.ultimateLuckyBullet();
		}
	}

	private void updateLevel() {
		if (score > 12000) {
			enemyTick = 10;
			currentEnemySpeed = -2.0;
		} else if (score > 8000) {
			enemyTick = 30;
			currentEnemySpeed = -1.7;
		} else if (score > 5000) {
			enemyTick = 40;
			currentEnemySpeed = -1.5;
		} else if (score > 3000) {
			enemyTick = 50;
			currentEnemySpeed = -1.2;
		}
	}

	private void checkIfEndGame() {
		for (IRenderable enemy : enemyList) {
			if (player.intersects((Enemy) enemy))
				endGame();
			if (((Enemy) enemy).isOutOfScreen())
				endGame();
		}
	}

	private void updateDeadEnemy() {
		List<IRenderable> toRemove = new ArrayList<>();
		for (IRenderable enemy : this.deadEnemyList) {
			Enemy tmp = (Enemy) enemy;
			tmp.tickDeadIncrease();
			if (tmp.getTickDead() > 15) {
				toRemove.add((IRenderable) tmp);
			}
		}
		this.deadEnemyList.removeAll(toRemove);
		RenderableHolder.getInstance().removeDeadEnemy(toRemove);

	}

	private void secretJuiButton() {
		List<IRenderable> toRemove = new ArrayList<>();
		for (IRenderable enemy : enemyList) {
			toRemove.add(enemy);
			deadEnemyList.add(enemy);
			RenderableHolder.getInstance().addDeadEnemy(enemy);
			score += 100;
			RenderableHolder.getInstance().addScore(100);
			System.out.println("Score : " + score);
		}
		this.enemyList.removeAll(toRemove);
		this.bulletList.removeAll(toRemove);
		RenderableHolder.getInstance().removeEnemy(toRemove);
		RenderableHolder.getInstance().removeBullet(toRemove);
	}

	private void secretMaxButton() {
		enemyTick = 1;
		currentEnemySpeed = -2;
	}
	
}
