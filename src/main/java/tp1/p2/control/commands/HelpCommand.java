package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class HelpCommand extends Command {

	public String getName() {
		return Messages.COMMAND_HELP_NAME;
	}

	public String getShortcut() {
		return Messages.COMMAND_HELP_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_HELP_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_HELP_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		StringBuilder buffer = new StringBuilder(Messages.HELP_AVAILABLE_COMMANDS);

		for (Command command : Command.getAvailableCommands()) {
			/* @formatter:off */
			buffer.append(Messages.LINE_SEPARATOR);
			
			buffer.append(command.getDetails() + ": ");
			
			buffer.append(command.getHelp());
			
			/* @formatter:on */
		}

		System.out.println(buffer.toString());

		return false;
	}

}