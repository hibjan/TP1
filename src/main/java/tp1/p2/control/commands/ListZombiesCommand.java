package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class ListZombiesCommand extends Command {

	public String getName() {
		return Messages.COMMAND_LIST_ZOMBIES_NAME;
	}

	public String getShortcut() {
		return Messages.COMMAND_LIST_ZOMBIES_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_LIST_ZOMBIES_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_LIST_ZOMBIES_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		System.out.println(Messages.AVAILABLE_ZOMBIES);
		for (Zombie z : ZombieFactory.getAvailableZombies())
			System.out.println(z.getDescription());
		System.out.println();

		return false;
	}

}