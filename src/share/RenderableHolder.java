package share;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	
	private List<Sprite> enemy;
	public static Image EnemyYellow;
	public static Image EnemyRed;
	public static Image EnemyBlue;
	
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
		
	}
}
