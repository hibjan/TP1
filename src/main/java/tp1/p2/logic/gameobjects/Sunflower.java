package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sunflower extends Plant {

	private static final int COST = 20;
	private static final int DAMAGE = 0;
	private static final int ENDURANCE = 1;
	private static final int FREQUENCY = 3;
	private int cont;
	
	public Sunflower(GameWorld game, int col, int row) {
		super(game, col, row);
		this.endu = ENDURANCE;
		this.cont = 0;
		this.cost = COST;
	}
	
	public Sunflower() {
	}
	
	//Updates sunflower
	public void update() {
		if(isAlive()) {
			if(this.cont == FREQUENCY) {
				generateSun();
				this.cont = 0;
			}
			this.cont++;
		}
	}
	
	public void generateSun() {
		game.addSun();
	}
	
	public Sunflower clone(GameWorld game, int col, int row) {
		return new Sunflower(game, col, row);
	}
	
	public String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL.toLowerCase();
	}

	public String getDescription() {
		return String.format(Messages.PLANT_DESCRIPTION, Messages.SUNFLOWER_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE);
	}
	
	public String toString() {
		return String.format(Messages.GAME_OBJECT_STATUS, Messages.SUNFLOWER_SYMBOL, this.endu);
	}
	
	public String getName() {
		return Messages.SUNFLOWER_NAME;
	}

	public String getShortcut() {
		return Messages.SUNFLOWER_SYMBOL;
	}
}
