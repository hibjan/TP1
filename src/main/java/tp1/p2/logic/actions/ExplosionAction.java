package tp1.p2.logic.actions;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;

public class ExplosionAction implements GameAction {

	private int col;

	private int row;

	private int damage;

	public ExplosionAction(int col, int row, int damage) {
		this.col = col;
		this.row = row;
		this.damage = damage;
	}

	public void execute(GameWorld game) {
		for(int r = this.row - 1; r < (this.row + 2); r++) {
			for(int c = this.col - 1; c < (this.col + 2); c++) {
				GameItem item = game.getGameItemInPosition(c, r);
			    if(item != null) {  
			        item.receiveZombieAttack(this.damage);
			    }
			}
		}
	}

}
