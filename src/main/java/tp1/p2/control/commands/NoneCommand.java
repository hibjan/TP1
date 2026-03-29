package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class NoneCommand extends Command {

	public NoneCommand() {
		// default command
		super(true);
	}

	public String getName() {
		return Messages.COMMAND_NONE_NAME;
	}

	public String getShortcut() {
		return Messages.COMMAND_NONE_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_NONE_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_NONE_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		return true;
	}
	
	public boolean doesUpdate() {
		return true;
	}

}