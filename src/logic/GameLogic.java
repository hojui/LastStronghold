package logic;

import java.util.ArrayList;
import java.util.List;

import draw.GameScreen;
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
		readInput();
		updatePosition();
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
			tmp.setPosition(tmp.getX() + tmp.velocityX, tmp.getY());
		}
		// Update RenderableHolder enemy list position
		for (IRenderable enemy : RenderableHolder.getInstance().getEnemies()) {
			Enemy tmp = (Enemy) enemy;
			tmp.setPosition(tmp.getX() + tmp.velocityX, tmp.getY());
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
				// Add bullet to remove list
				toRemove.add(bullet);
				if (((Sprite) bullet).intersects((Sprite) enemy) && isSameColor(bullet, enemy)) {
					// Add enemy to remove list
					toRemove.add(enemy);
					// Add to dead enemy list
					deadEnemyList.add(enemy);
					RenderableHolder.getInstance().addDeadEnemy(enemy);
					// Add score
					score += 100;
					RenderableHolder.getInstance().addScore(100);
					System.out.println("Score : " + score);
				}
			}
		}
		// Remove to-remove element
		this.enemyList.removeAll(toRemove);
		this.bulletList.removeAll(toRemove);
		RenderableHolder.getInstance().removeEnemy(toRemove);
		RenderableHolder.getInstance().removeBullet(toRemove);
	}
	
	private boolean isSameColor(IRenderable bullet, IRenderable enemy) {
		if (bullet instanceof BulletRed && enemy instanceof EnemyRed) return true;
		if (bullet instanceof BulletBlue && enemy instanceof EnemyBlue) return true;
		if (bullet instanceof BulletYellow && enemy instanceof EnemyYellow) return true;
		return false;
	}

	private void fireBullet() {
		player.tickIncrease();
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
		}
	}

	private void endGame() {
		// TODO fix end game alert
		Alert alert = new Alert(AlertType.CONFIRMATION, "GAME OVER", ButtonType.OK);
		alert.setContentText("Score : " + this.score);
		alert.showAndWait();
		Platform.exit();
	}

	private void readInput() {
		if (GameScreen.inputs.contains("UP")) {
			player.update(0, -7);
			RenderableHolder.getInstance().updatePlayer(0, -7);
		}
		if (GameScreen.inputs.contains("DOWN")) {
			player.update(0, 7);
			RenderableHolder.getInstance().updatePlayer(0, 7);
		}
		if (GameScreen.inputs.contains("A")) {
			fireBullet();
		}
		if (GameScreen.inputs.contains("TAB")) {
			bulletState = (bulletState + 1) % 3;
			RenderableHolder.getInstance().setBulletState(bulletState);
			GameScreen.inputs.remove("TAB");
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

}
