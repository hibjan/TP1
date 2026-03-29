package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class WallNut extends Plant {
	private static final int COST = 50;
	private static final int DAMAGE = 0;
	private static final int ENDURANCE = 10;

	public WallNut(GameWorld game, int col, int row) {
		super(game, col, row);
		this.endu = ENDURANCE;
		this.cost = COST;
	}
	
	public WallNut() {
	}
	
	//Updates sunflower
	public void update() {
	}
	
	public WallNut clone(GameWorld game, int col, int row) {
		return new WallNut(game, col, row);
	}
	
	public String getSymbol() {
		return Messages.WALL_NUT_SYMBOL.toLowerCase();
	}

	public String getDescription() {
		return String.format(Messages.PLANT_DESCRIPTION, Messages.WALL_NUT_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE);
	}
	
	public String toString() {
		return String.format(Messages.GAME_OBJECT_STATUS, Messages.WALL_NUT_SYMBOL, this.endu);
	}
	
	public String getName() {
		return Messages.WALL_NUT_NAME;
	}

	public String getShortcut() {
		return Messages.WALL_NUT_SYMBOL;
	}
}
