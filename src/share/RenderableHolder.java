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
		enemyRed = new Image(ClassLoader.getSystemResource("file:/res/img/enemyRed.png").toString(),100,100,false,true);
		enemyBlue = new Image(ClassLoader.getSystemResource("file:/res/img/enemyRed.png").toString(),100,100,false,true);
		enemyYellow = new Image(ClassLoader.getSystemResource("file:/res/img/enemyRed.png").toString(),100,100,false,true);
	}
}
