package tp1.p2.logic;

import static tp1.p2.view.Messages.error;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.control.exceptions.NotCatchablePositionException;
import tp1.p2.control.exceptions.NotEnoughCoinsException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.logic.actions.AddAction;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.Sun;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.view.Messages;

public class Game implements GameStatus, GameWorld {
	
	private long seed;
	private Level level;
	private Deque<GameAction> actions;
	
	public static final int INITIAL_SUNCOINS = 50;
	private Random rand;
    private boolean playerQuits;
    private int cycles;
    private int suncoins;
    private int score;
	private GameObjectContainer container;
	private ZombiesManager zombiesManager;
	private SunsManager sunsManager;
	private Record record;

	
	// Constructor
	public Game (long seed, Level level) throws GameException {
		reset(seed, level);
	}
	
	// Methods
	
	public void reset(long seed, Level level) throws GameException {
		this.seed = seed;
		this.level = level;	
		reset();
	}

	public void reset() throws GameException {
		this.score = 0;
		this.cycles = 0;
		this.actions = new ArrayDeque<>();
		this.playerQuits = false;
		this.suncoins = INITIAL_SUNCOINS;
		this.rand = new Random(seed);
		this.zombiesManager = new ZombiesManager(this, level, rand);
		this.container = new GameObjectContainer();
		this.sunsManager = new SunsManager(this, rand);
		this.record = new Record(level);
	}
	
	public boolean execute(Command command) throws GameException {
		boolean prints = command.execute(this);
		
		if(command.doesUpdate())
				update();
		
		return prints;
	}

	public void update() throws GameException {
		// 1. Execute pending actions
		executePendingActions();

		// 2. Execute game Actions
		gameAction();

		// 3. Game object updates
		container.update();

		// 4. & 5. Remove dead and execute pending actions
		boolean deadRemoved = true;
		while (deadRemoved || areTherePendingActions()) {
			// 4. Remove dead
			deadRemoved = this.container.takeDead();

			// 5. execute pending actions
			executePendingActions();
		}
		
		this.cycles++;

		// 6. Notify commands that a new cycle started
		Command.newCycle();
		
		// 7. Update record
		saveRecord();
	}
	
	public void gameAction() throws GameException {
		zombiesManager.update();
		sunsManager.update();
	}
	
	
	// Container
	/**
	 * Checks if a cell is fully occupied, that is, the position can be shared between an NPC (Plant, Zombie) and Suns .
	 * 
	 * @param col Column of the cell
	 * @param row Row of the cell
	 * 
	 * @return <code>true</code> if the cell is fully occupied, <code>false</code>
	 *         otherwise.
	 * @throws GameException 
	 */
	public boolean isFullyOccupied(int col, int row) {
		return this.container.isFullyOccupied(col, row);
	}

	public GameItem getGameItemInPosition(int col, int row) {
		return container.getGameItemInPosition(col, row);
	}

	public boolean addItem(GameObject gameObject) {
		return container.addItem(gameObject);
	}

	public void tryToCatchObject(int col, int row) throws GameException {
		if(!container.tryToCatchObject(col, row)) 
			throw new NotCatchablePositionException(String.format(Messages.NO_CATCHABLE_IN_POSITION, col, row));
	}

	// GameAction
	
	public void pushAction(GameAction gameAction) {
	    this.actions.addLast(gameAction);
	}

	private void executePendingActions() {
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}

	private boolean areTherePendingActions() {
		return this.actions.size() > 0;
	}
	
	// ZombiesManager
	
	public void newZombie() {
		zombiesManager.newZombie();
	}
	
	public void deadZombie() {
		zombiesManager.deadZombie();
	}

	public void addScore(int points) {
		this.score += points;
	}
	
	//SunsManager
	
	public void addSun() {
		sunsManager.addSun();
	}
	
	public boolean canAfford(int suncoins) {
		return (this.suncoins - suncoins) >= 0;
	}

	public int addSuncoins(int suncoins) {
		return this.suncoins += suncoins;
	}
	
	public boolean removeSuncoins(int suncoins) {
		if(canAfford(suncoins)) {
			this.suncoins -= suncoins;
			return true;
		}
		return false;
	}

	
	// Game status
	
	public int getSuncoins() {return this.suncoins;}
	
	public int getCycles() {
		return this.cycles;
	}
	
	public int getRemainingZombies() {
		return zombiesManager.getRemainingZombies();
	}
	
	public int getGeneratedSuns() {
		return sunsManager.getGeneratedSuns();
	}

	public int getCaughtSuns() {
		return sunsManager.getCaughtSuns();
	}
	
	public String positionToString(int col, int row) {
		return container.positionToString(col, row);
	}
	
	public void saveRecord() throws GameException {
		//Game is over and score isn´t zero
		if((isPlayerQuits() || isFinished()) && this.score > 0) {
			record.saveRecord(this.score);
		}
	}
	
	public boolean playerQuits() {this.playerQuits = true; return true;}
	
	public boolean isPlayerQuits() {return this.playerQuits;}
	
	public boolean isFinished() {
		return (zombieVictory() || playerVictory());
	}
	
	//Checks if zombies have won 
	//@return true if there´s any zombies inside of the house
	public boolean zombieVictory() {
		boolean houseOccupied = false;
		int rowZombie = 0;
		while(!houseOccupied && rowZombie < NUM_ROWS)
			if(container.isFullyOccupied(-1, rowZombie)) 
				houseOccupied = true;
			else 
				rowZombie++;
		return houseOccupied;
	}
	
	//Checks if the player has won
	//@return true if there aren´t any zombies left to kill
	public boolean playerVictory() {
		return (getRemainingZombies() == 0) && (!zombiesManager.anyAlive());
	}

	public void tryToBuy(int cost) throws GameException {
		//Player can afford plant
		if(canAfford(cost)) {
			removeSuncoins(cost);
		}
		//Player cannot afford it
		else {
			throw new NotEnoughCoinsException(Messages.NOT_ENOUGH_COINS);
		}
	}

	public int getScore() {
		return this.score;
	}
	
	public String getLevelName() {
		return this.level.name();
	}

	public int getRecord() {
		return record.getRecord();
	}

	public long getSeed() {
		return this.seed;
	}

	public void checkValidObjectPosition(int col, int row) throws GameException {
		container.checkValidObjectPosition(col, row);
	}
	
}
