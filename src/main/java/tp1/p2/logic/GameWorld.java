package tp1.p2.logic;

import java.util.Random;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;

public interface GameWorld {
	
	//Attributes

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;
	
	//Methods
	
	public void update() throws GameException;
	
	public void reset() throws GameException;
	 
	public void reset(long seed, Level level) throws GameException;
	
	public boolean addItem(GameObject gameObject);
	
	public GameItem getGameItemInPosition(int col, int row); 
	
	public boolean isFullyOccupied(int col, int row);
	
	public void pushAction(GameAction gameAction);
	
	public boolean playerQuits();
	
	public int addSuncoins(int suncoins);
	
	public boolean removeSuncoins(int suncoins);
	
	public void addSun();
	
	public boolean canAfford(int suncoins);

	public void newZombie();
	
	public void deadZombie();
	
	public void tryToBuy(int cost) throws GameException;

	public void tryToCatchObject(int col, int row) throws GameException;

	public String getLevelName();

	public int getRecord();

	public void addScore(int points);

	public long getSeed();
	
	public void checkValidObjectPosition(int col, int row) throws GameException;
}
