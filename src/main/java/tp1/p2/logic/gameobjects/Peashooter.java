package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Plant {
	
	public static final int COST = 50;
	private static final int DAMAGE = 1;
	private static final int ENDURANCE = 3;
	private int cont;
		
	public Peashooter(GameWorld game, int col, int row) {
		super(game, col, row);
		this.endu = ENDURANCE;
		this.cont = 0;
		this.cost = COST;
	}
	public Peashooter() {
		
	}

	//Shoots a pea 
	private void shoot() {
		int i = this.col + 1;
		boolean found = false;
		//The pea finds a target, or gets out of the board
		while(!found && i < GameWorld.NUM_COLS) {
			GameItem item = game.getGameItemInPosition(i, this.row);
		    if(item != null) {  
		        found = item.receivePlantAttack(DAMAGE);
		    }
			i++;
		}
	}
	//Updates peashooter
	public void update() {
		if(isAlive())
			shoot();
	}
	
	public Peashooter clone(GameWorld game, int col, int row) {
		return new Peashooter(game, col, row);
	}
	
	public String getSymbol() {
		return Messages.PEASHOOTER_SYMBOL.toLowerCase();
	}

	public String getDescription() {
		return String.format(Messages.PLANT_DESCRIPTION, Messages.PEASHOOTER_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE);
	}
	
	public String toString() {
		return String.format(Messages.GAME_OBJECT_STATUS, Messages.PEASHOOTER_SYMBOL, this.endu);
	}
	
	public String getName() {
		return Messages.PEASHOOTER_NAME;
	}

	public String getShortcut() {
		return Messages.PEASHOOTER_SYMBOL;
	}
}
