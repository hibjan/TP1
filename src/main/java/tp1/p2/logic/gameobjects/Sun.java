package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sun extends GameObject {
	
	private int cont;
	private static final int CYCLES = 10;
	public static final int SUNCOINS = 10;
	private static int generatedSuns;
	private static int caughtSuns;

	public Sun(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col; 
		this.row = row;
		this.cont = 0;
		this.endu = CYCLES;
	}

	public static void resetStatus() {
		generatedSuns = 0;
		caughtSuns = 0;
	}
	
	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	public boolean receivePlantAttack(int damage) {
		return false;
	}

	public boolean fillPosition() {
		return false;
	}

	public boolean catchObject() {
		boolean caught = false;
		
		if(this.isAlive()) {
			this.endu = 0;
			this.cont = CYCLES;
			caughtSuns++;
			game.addSuncoins(SUNCOINS);
			caught = true;
		}
		
		return caught;
	}

	public String getSymbol() {
		return Messages.SUN_SYMBOL.toLowerCase();
	}

	public void update() {
		if(cont == CYCLES) {
			this.endu = 0;
		}
		else {
			cont++;
			endu--;
		}
	}

	public void onEnter() {
		generatedSuns++;
	}

	public void onExit() {
	}

	public String getDescription() {
		return null;
	}
	
	public String toString() {
		return String.format(Messages.GAME_OBJECT_STATUS, Messages.SUN_SYMBOL, this.endu);
	}
	
	public static int getCaughtSuns() {
		return caughtSuns;
	}

	public static int getGeneratedSuns() {
		return generatedSuns;
	}
	
}
