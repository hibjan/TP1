package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ResetCommand extends Command {

	private Level level;

	private long seed;

	public ResetCommand() {
	}

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}

	public String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	public String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		if(level != null)
			game.reset(this.seed, this.level);
		else 
			game.reset();
		
		System.out.println(String.format(Messages.CONFIGURED_LEVEL, game.getLevelName()));
		System.out.println(String.format(Messages.CONFIGURED_SEED, game.getSeed()));
		
		return true;
	}

	public Command create(String[] parameters) throws GameException {
		
		ResetCommand resetCommand = null;
		
		if(parameters.length == 1) {
			resetCommand = new ResetCommand();
		}
		
		else if(parameters.length == 3) {
			try {
				long seed = Long.parseLong(parameters[2]);
				Level level = Level.valueOfIgnoreCase(parameters[1]);
				if (level == null) {
					throw new CommandParseException(Messages.ALLOWED_LEVELS);
				}
				resetCommand = new ResetCommand(level, seed);
			}
			catch(NumberFormatException nfe){
				throw new CommandParseException(Messages.SEED_NOT_A_NUMBER_ERROR.formatted(parameters[2]), nfe);
			}
		}
		
		else {
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		
		return resetCommand;
	}

}