package tp1.p2.logic.actions;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.GameObject;

public class AddAction implements GameAction {
	
	private GameObject gameObject;

	public AddAction(GameObject gameObject) {
		this.gameObject = gameObject;
	}

	public void execute(GameWorld game) {
		game.addItem(gameObject);
	}
}
