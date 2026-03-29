package tp1.p2.logic.gameobjects;

import tp1.p2.view.Messages;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;

public class Zombie extends GameObject {

	protected static final int DAMAGE = 1;
	private static final int FREQUENCY = 2;
	private static final int ENDURANCE = 5;
	protected int cont;
	protected int freq;
	
	public Zombie () {
		this.endu = ENDURANCE;
	}
	
	public Zombie(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.cont = 0;
		this.endu = ENDURANCE;
		this.freq = FREQUENCY;
	}

	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	public String getSymbol() {
		return Messages.ZOMBIE_SYMBOL.toLowerCase();
	}

	public String getDescription() {
		return String.format(Messages.ZOMBIE_DESCRIPTION, Messages.ZOMBIE_NAME, FREQUENCY, DAMAGE, ENDURANCE);
	}

	public void onEnter() {
		game.newZombie();
	}

	public void onExit() {
		game.deadZombie();
		game.addScore(10);
	}
	
	public Zombie clone(GameWorld game, int col, int  row){
		return new Zombie(game, col, row);
	}

	public void hit() {
		GameItem item = game.getGameItemInPosition(this.col - 1, this.row);
	    if(item != null) {  
	        if(item.receiveZombieAttack(DAMAGE))
	        	this.cont = 0;
	    }
	}
	
	//Moves zombie
	public void move() {
		if(this.cont == freq) {
			this.col--;
			this.cont = 0;
		}
		this.cont++;
	}
	
	//Update zombie
	public void update() {
		if(isAlive()) {
			//Either move or hit
			//If there´s smth on the position next to the zombie, and it´s not a sun, hit
			if(game.isFullyOccupied((this.col - 1), this.row))
				hit();
			else
				move();
		}
	}
	
	public String toString() {
		return String.format(Messages.GAME_OBJECT_STATUS, Messages.ZOMBIE_SYMBOL, this.endu);
	}
	
	public boolean receivePlantAttack(int damage) {
		boolean received = false;
		
		if(isAlive()) {
			received = true;
			this.endu -= damage;
		}
			
		return received;
	}
	
}
