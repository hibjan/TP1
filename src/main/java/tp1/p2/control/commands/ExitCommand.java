package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ExitCommand extends Command {

	public String getName() {
		return Messages.COMMAND_EXIT_NAME;
	}

	public String getShortcut() {
		return Messages.COMMAND_EXIT_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_EXIT_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_EXIT_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		game.playerQuits();
		return false;
	}

	public boolean doesUpdate() {
		return true;
	}
}