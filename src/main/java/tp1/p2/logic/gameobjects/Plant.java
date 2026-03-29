package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public abstract class Plant extends GameObject{

	protected int cost;
	
	public Plant(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.cost = 0;
	}
	
	public Plant() {
		
	}
	
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION;
	}
	
	public boolean receiveZombieAttack(int damage) {
		boolean received=false;
		
		if(isAlive()) {
			received=true;
			this.endu -= damage;
		}
			
		return received;
	}
	
	protected String getSymbol() {return "";}
	
	public void onEnter() {}

	public void update() {}
	
	public void onExit() {}

	public abstract Plant clone(GameWorld game, int col, int row);
	
	public boolean receivePlantAttack(int damage) {
		return false;
	}
	
	public int getCost() {
		return cost;
	}

	public abstract String getName();

	public abstract String getShortcut();
	
}
