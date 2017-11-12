package share;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	
	private List<IRenderable> enemys;
	private List<IRenderable> bullets;
	
	public static List<Image> playerImages;
	
	public static List<Image> enemyYellowImages;
	public static List<Image> enemyRedImages;
	public static List<Image> enemyBlueImages;
	
	public static Image bulletYellowImage;
	public static Image bulletRedImage;
	public static Image bulletBlueImage;
	
	static {
		loadResource();
	}
	
	public RenderableHolder() {
		enemys = new ArrayList<>();
		bullets = new ArrayList<>();
		
		enemyYellowImages = new ArrayList<>();
		enemyRedImages = new ArrayList<>();
		enemyBlueImages = new ArrayList<>();
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		// TODO set image name and location
		
		// Load player images
		for (int number = 1; number <= 3; number++) {
			Image image = new Image(ClassLoader.getSystemResource("file:/res/img/player" + number + ".png").toString(),75,75,false,true);
			playerImages.add(image);
		}
		
		// Load enemy images
		for (int number = 1; number <= 7; number++) {
			Image image = new Image(ClassLoader.getSystemResource("file:/res/img/enemyYellow" + number + ".png").toString(),75,75,false,true);
			enemyYellowImages.add(image);
		}
		for (int number = 1; number <= 7; number++) {
			Image image = new Image(ClassLoader.getSystemResource("file:/res/img/enemyRed" + number + ".png").toString(),75,75,false,true);
			enemyRedImages.add(image);
		}
		for (int number = 1; number <= 7; number++) {
			Image image = new Image(ClassLoader.getSystemResource("file:/res/img/enemyBlue" + number + ".png").toString(),75,75,false,true);
			enemyBlueImages.add(image);
		}

		// Load bullet images
		bulletYellowImage = new Image(ClassLoader.getSystemResource("file:/res/img/bulletYellow.png").toString(),30,9,false,true);
		bulletRedImage = new Image(ClassLoader.getSystemResource("file:/res/img/bulletRed.png").toString(),30,9,false,true);
		bulletBlueImage = new Image(ClassLoader.getSystemResource("file:/res/img/bulletBlue.png").toString(),30,9,false,true);
	}

	public void addEnemy(IRenderable enemy) {
		this.enemys.add(enemy);
		System.out.println("Enemy added.");
	}
	
	public void addBullet(IRenderable bullet) {
		this.bullets.add(bullet);
		System.out.println("Bullet added. Fire in the holeeee");
	}
	
}
