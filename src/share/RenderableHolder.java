
package share;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import logic.Player;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	
	private List<IRenderable> enemies;
	private List<IRenderable> deadEnemies;
	private List<IRenderable> bullets;
	
	public static List<Image> playerImages;
	
	public static List<Image> enemyYellowImages;
	public static List<Image> enemyRedImages;
	public static List<Image> enemyBlueImages;
	
	public static Image bulletYellowImage;
	public static Image bulletRedImage;
	public static Image bulletBlueImage;
	
	private int score;
	
	private Player player;
	
	private int bulletState;
	
	static {
		try {
			loadResource();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("aslkfnlandg");
		}
	}
	
	public RenderableHolder() {
		enemies = new ArrayList<>();
		deadEnemies = new ArrayList<>();
		bullets = new ArrayList<>();
		
		enemyYellowImages = new ArrayList<>();
		enemyRedImages = new ArrayList<>();
		enemyBlueImages = new ArrayList<>();
		
		playerImages = new ArrayList<>();
		
		score = 0;
		player = new Player();
		bulletState = 0;
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		// TODO set image name and location
		
		// Load player images
		for (int number = 1; number <= 3; number++) {
			Image image = new Image(ClassLoader.getSystemResource("img/player" + number + ".png").toString(),75,75,false,true);
			playerImages.add(image);
		}
		
		// Load enemy images
		for (int number = 1; number <= 7; number++) {
			Image image = new Image(ClassLoader.getSystemResource("img/enemyYellow" + number + ".png").toString(),75,75,false,true);
			enemyYellowImages.add(image);
		}
		for (int number = 1; number <= 7; number++) {
			Image image = new Image(ClassLoader.getSystemResource("img/enemyRed" + number + ".png").toString(),75,75,false,true);
			enemyRedImages.add(image);
		}
		for (int number = 1; number <= 7; number++) {
			Image image = new Image(ClassLoader.getSystemResource("img/enemyBlue" + number + ".png").toString(),75,75,false,true);
			enemyBlueImages.add(image);
		}

		// Load bullet images
		bulletYellowImage = new Image(ClassLoader.getSystemResource("img/bulletYellow.png").toString(),30,9,false,true);
		bulletRedImage = new Image(ClassLoader.getSystemResource("img/bulletRed.png").toString(),30,9,false,true);
		bulletBlueImage = new Image(ClassLoader.getSystemResource("img/bulletBlue.png").toString(),30,9,false,true);
	}
	
	public void newGame() {
		this.enemies.clear();
		this.deadEnemies.clear();
		this.bullets.clear();
		this.score = 0;
		this.player = new Player();
		this.bulletState = 0;
	}

	public void addEnemy(IRenderable enemy) {
		enemies.add(enemy);
	}
	
	public void addDeadEnemy(IRenderable deadEnemy) {
		deadEnemies.add(deadEnemy);
	}
	
	public void addBullet(IRenderable bullet) {
		bullets.add(bullet);
	}
	
	public void removeEnemy(List<IRenderable> bullet) {
		enemies.removeAll(bullet);
	}
	
	public void removeDeadEnemy(IRenderable deadEnemy) {
		deadEnemies.remove(deadEnemy);
	}
	
	public void removeBullet(List<IRenderable> bullet) {
		bullets.removeAll(bullet);
	}
	
	public void removeDeadEnemy(List<IRenderable> deadEnemy) {
		this.deadEnemies.removeAll(deadEnemy);
	}
	
	public void addScore(int score) {
		this.score += score;
	}

	public int getScore() {
		return score;
	}

	public Player getPlayer() {
		return player;
	}

	public List<IRenderable> getEnemies() {
		return enemies;
	}

	public List<IRenderable> getDeadEnemies() {
		return deadEnemies;
	}

	public List<IRenderable> getBullets() {
		return bullets;
	}
	
	public void updatePlayer(double x, double y) {
		this.player.update(x, y);
	}
	
	public void updateImagePlayer(int i) {
		this.player.setImageIndex(i);
	}
	
	public void setPlayerPosition(double x,double y) {
		this.player.setPosition(x, y);
	}

	public int getBulletState() {
		return bulletState;
	}

	public void setBulletState(int bulletState) {
		this.bulletState = bulletState;
	}
	
}
