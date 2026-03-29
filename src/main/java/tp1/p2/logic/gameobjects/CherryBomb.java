package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.view.Messages;

public class CherryBomb extends Plant {
	
	private static final int COST = 50;
	private static final int DAMAGE = 10;
	private static final int ENDURANCE = 2;
	private static final int FREQUENCY = 2;
	private int cont;
	
	public CherryBomb(GameWorld game, int col, int row) {
		super(game, col, row);
		this.endu = ENDURANCE;
		this.cont = 0;
		this.cost = COST;
	}
	
	public CherryBomb() {
	}
	
	//Updates sunflower
	public void update() {
		if(isAlive()) {
			if(this.cont == FREQUENCY) {
				boom();
				this.endu = 0;
			}
			this.cont++;
		}
	}
	
	public void boom() {
		for(int r = this.row-1; r < (this.row + 2); r++) {
			for(int c = this.col-1; c < (this.col + 2); c++) {
				GameItem item = game.getGameItemInPosition(c, r);
			    if(item != null && c < GameWorld.NUM_COLS) {  
			        if(item.receivePlantAttack(DAMAGE))
			        	game.addScore(10);
			    }
			}
		}
	}
	
	public CherryBomb clone(GameWorld game, int col, int row) {
		return new CherryBomb(game, col, row);
	}
	
	public String getSymbol() {
		return Messages.CHERRY_BOMB_SYMBOL.toLowerCase();
	}

	public String getDescription() {
		return String.format(Messages.PLANT_DESCRIPTION, Messages.CHERRY_BOMB_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE);
	}
	
	public String toString() {
		if(this.cont == FREQUENCY)
			return String.format(Messages.GAME_OBJECT_STATUS, Messages.CHERRY_BOMB_SYMBOL.toUpperCase(), this.endu);
		return String.format(Messages.GAME_OBJECT_STATUS, Messages.CHERRY_BOMB_SYMBOL, this.endu);
	}
	
	public String getName() {
		return Messages.CHERRY_BOMB_NAME;
	}

	public String getShortcut() {
		return Messages.CHERRY_BOMB_SYMBOL;
	}

}
