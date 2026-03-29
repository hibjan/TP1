package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ZombieFactory {

	/* @formatter:off */
	private static final List<Zombie> AVAILABLE_ZOMBIES = Arrays.asList(
			new Zombie(),
			new BucketHead(),
			new Sporty(),
			new ExplosiveZombie()
	);
	/* @formatter:on */
	
	public static boolean isValidZombie(int zombieIdx) {
		return zombieIdx >= 0 && zombieIdx < AVAILABLE_ZOMBIES.size();
	}
	
	public static Zombie spawnZombie(int zombieIdx, GameWorld game, int col, int row) throws GameException {
		int i = 0;
		for (Zombie zombie : AVAILABLE_ZOMBIES) {
			if(i == zombieIdx)
					return zombie.clone(game, col, row);
			i++;
		}
		throw new GameException(Messages.INVALID_GAME_OBJECT);
	}
	
	public static Zombie spawnZombie(int zombieIdx, GameWorld game, int row) throws GameException {
		return spawnZombie(zombieIdx, game, GameWorld.NUM_COLS, row);
	}
	
	public static List<Zombie> getAvailableZombies() {
		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}
	/*
	 * Avoid creating instances of this class
	 */
	private ZombieFactory() {
	}
	
}
