package tp1.p2.logic;

public interface GameStatus {
	
	/**
	 * Get game cycles.
	 * 
	 * @return the game cycles
	 */
	public int getCycles();
	/**
	 * Get available suncoins
	 * 
	 * @return the available suncoins
	 */
	int getSuncoins();
	/**
	 * Get number of remaining zombies.
	 * 
	 * @return the number of remaining zombies
	 */
	public int getRemainingZombies();
	/**
	 * Draw a cell of the game.
	 * 
	 * @param col Column of the cell to draw.
	 * @param row Row of the cell to draw.
	 * 
	 * @return a string that represents the content of the cell (col, row).
	 */
	public String positionToString(int col, int row);
	/**
	 * Get the number of generated suns.
	 * 
	 * @return the number of generated suns
	 */
	public int getGeneratedSuns();
	/**
	 * Get the number of caught suns.
	 * 
	 * @return the number of caught suns
	 */
	public int getCaughtSuns();
	
	public int getScore();

	public boolean isPlayerQuits();
	public boolean isFinished();
	public boolean playerVictory();
	public boolean zombieVictory();
	
}
