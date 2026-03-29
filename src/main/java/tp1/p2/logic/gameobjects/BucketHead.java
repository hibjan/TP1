package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class BucketHead extends Zombie {
	
	private static final int FREQUENCY = 4;
	private static final int ENDURANCE = 8;
	
	public BucketHead () {
		
	}
	
	public BucketHead(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.cont = 0;
		this.endu = ENDURANCE;
		this.freq = FREQUENCY;
	}

	public String getSymbol() {
		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL.toLowerCase();
	}

	public String getDescription() {
		return String.format(Messages.ZOMBIE_DESCRIPTION, Messages.BUCKET_HEAD_ZOMBIE_NAME, FREQUENCY, DAMAGE, ENDURANCE);
	}
	
	public BucketHead clone(GameWorld game, int col, int  row){
		return new BucketHead(game, col, row);
	}
	
	public String toString() {
		return String.format(Messages.GAME_OBJECT_STATUS, Messages.BUCKET_HEAD_ZOMBIE_SYMBOL, this.endu);
	}


}
