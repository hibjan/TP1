package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sporty extends Zombie {
	
	private static final int FREQUENCY = 1;
	private static final int ENDURANCE = 2;
	
	public Sporty() {
		
	}
	
	public Sporty(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.cont = 0;
		this.endu = ENDURANCE;
		this.freq = FREQUENCY;
	}

	public String getSymbol() {
		return Messages.SPORTY_ZOMBIE_SYMBOL.toLowerCase();
	}

	public String getDescription() {
		return String.format(Messages.ZOMBIE_DESCRIPTION, Messages.SPORTY_ZOMBIE_NAME, FREQUENCY, DAMAGE, ENDURANCE);
	}
	
	public Sporty clone(GameWorld game, int col, int  row){
		return new Sporty(game, col, row);
	}

	public String toString() {
		return String.format(Messages.GAME_OBJECT_STATUS, Messages.SPORTY_ZOMBIE_SYMBOL, this.endu);
	}
	
}
