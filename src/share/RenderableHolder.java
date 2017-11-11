package share;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	
	private List<Sprite> enemy;
	public static Image enemyYellow;
	public static Image enemyRed;
	public static Image enemyBlue;
	public static Image bulletYellow;
	public static Image bulletRed;
	public static Image bulletBlue;
	
	static {
		loadResource();
	}
	
	public RenderableHolder() {
		enemy = new ArrayList<Sprite>();
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		// Load enemy images
		enemyRed = new Image(ClassLoader.getSystemResource("file:/res/img/enemyRed.png").toString(),100,100,false,true);
		enemyBlue = new Image(ClassLoader.getSystemResource("file:/res/img/enemyRed.png").toString(),100,100,false,true);
		enemyYellow = new Image(ClassLoader.getSystemResource("file:/res/img/enemyRed.png").toString(),100,100,false,true);
	
		// Load bullet images
		bulletRed = new Image(ClassLoader.getSystemResource("file:/res/img/enemyRed.png").toString(),100,100,false,true);
		bulletBlue = new Image(ClassLoader.getSystemResource("file:/res/img/enemyRed.png").toString(),100,100,false,true);
		bulletYellow = new Image(ClassLoader.getSystemResource("file:/res/img/enemyRed.png").toString(),100,100,false,true);
	
	}
}
