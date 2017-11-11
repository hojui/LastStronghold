package logic;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
	private List<Enemy> enemyList;
	private List<Bullet> bulletList;
	
	public GameLogic() {
		enemyList = new ArrayList<>();
		bulletList = new ArrayList<>();
	}
}
