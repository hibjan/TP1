package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie {

	private static final int EXPLOSIVE_DAMAGE = 3;
	private static final int FREQUENCY = 2;
	private static final int ENDURANCE = 5;
	
	public ExplosiveZombie() {
		
	}
	
	public ExplosiveZombie(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.cont = 0;
		this.endu = ENDURANCE;
		this.freq = FREQUENCY;
	}

	public String getSymbol() {
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL.toLowerCase();
	}

	public String getDescription() {
		return String.format(Messages.ZOMBIE_DESCRIPTION, Messages.EXPLOSIVE_ZOMBIE_NAME, FREQUENCY, DAMAGE, ENDURANCE);
	}

	public void onExit() {
		GameAction gameAction = new ExplosionAction(this.col, this.row, EXPLOSIVE_DAMAGE);
		game.pushAction(gameAction);
		game.deadZombie();
		game.addScore(10);
	}
	
	public ExplosiveZombie clone(GameWorld game, int col, int  row){
		return new ExplosiveZombie(game, col, row);
	}

	public String toString() {
		return String.format(Messages.GAME_OBJECT_STATUS, Messages.EXPLOSIVE_ZOMBIE_SYMBOL, this.endu);
	}

}
