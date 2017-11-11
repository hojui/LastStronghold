package share;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import logic.*;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	
	private List<Enemy> enemys;
	private List<Bullet> bullets;
	
	public static List<Image> playerImages;
	
	public static List<Image> enemyYellowImages;
	public static List<Image> enemyRedImages;
	public static List<Image> enemyBlueImages;
	
	public static List<Image> bulletYellowImages;
	public static List<Image> bulletRedImages;
	public static List<Image> bulletBlueImages;
	
	static {
		loadResource();
	}
	
	public RenderableHolder() {
		enemys = new ArrayList<>();
		bullets = new ArrayList<>();
		
		enemyYellowImages = new ArrayList<>();
		enemyRedImages = new ArrayList<>();
		enemyBlueImages = new ArrayList<>();
		
		bulletYellowImages = new ArrayList<>();
		bulletRedImages = new ArrayList<>();
		bulletBlueImages = new ArrayList<>();
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		// TODO set number of images
		// TODO set image name and location
		
		// Load player images
		for (int number = 1; number < 5; number++) {
			Image image = new Image(ClassLoader.getSystemResource("file:/res/img/player" + number + ".png").toString(),100,100,false,true);
			playerImages.add(image);
		}
		
		// Load enemy images
		for (int number = 1; number < 5; number++) {
			Image image = new Image(ClassLoader.getSystemResource("file:/res/img/enemyYellow" + number + ".png").toString(),75,75,false,true);
			enemyYellowImages.add(image);
		}
		for (int number = 1; number < 5; number++) {
			Image image = new Image(ClassLoader.getSystemResource("file:/res/img/enemyRed" + number + ".png").toString(),75,75,false,true);
			enemyRedImages.add(image);
		}
		for (int number = 1; number < 5; number++) {
			Image image = new Image(ClassLoader.getSystemResource("file:/res/img/enemyBlue" + number + ".png").toString(),75,75,false,true);
			enemyBlueImages.add(image);
		}

		// Load bullet images
		for (int number = 1; number < 5; number++) {
			Image image = new Image(ClassLoader.getSystemResource("file:/res/img/bulletYellow" + number + ".png").toString(),20,20,false,true);
			bulletYellowImages.add(image);
		}
		for (int number = 1; number < 5; number++) {
			Image image = new Image(ClassLoader.getSystemResource("file:/res/img/bulletRed" + number + ".png").toString(),20,20,false,true);
			bulletRedImages.add(image);
		}
		for (int number = 1; number < 5; number++) {
			Image image = new Image(ClassLoader.getSystemResource("file:/res/img/bulletBlue" + number + ".png").toString(),20,20,false,true);
			bulletBlueImages.add(image);
		}
	}

	public void addEnemy(Enemy enemy) {
		this.enemys.add(enemy);
		System.out.println("Enemy added.");
	}
	
	public void addBullet(Bullet bullet) {
		this.bullets.add(bullet);
		System.out.println("Bullet added. Fire in the holeeee");
	}
	
}
