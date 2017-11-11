package logic;

import java.util.ArrayList;
import java.util.List;

import share.IRenderable;

public class GameLogic {
	private List<IRenderable> enemyList;
	private List<IRenderable> bulletList;
	
	public GameLogic() {
		enemyList = new ArrayList<>();
		bulletList = new ArrayList<>();
	}
}
