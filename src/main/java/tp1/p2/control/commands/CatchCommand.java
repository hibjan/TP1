package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.NotCatchablePositionException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CatchCommand extends Command {
	
	//Attributes

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;
	
	//Constructors

	public CatchCommand() {
	}
	
	private CatchCommand(int col, int row) {
		this.col = col;
		this.row = row;
	}

	//Methods
	
	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}

	public String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	public String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		boolean success = false;
		
		//No suns have been caught this cycle
		if(!caughtSunThisCycle) {
			//Try to catch it
			game.tryToCatchObject(this.col, this.row);
			caughtSunThisCycle = true;
			success = true;
			//if caught then prints board but doesn´t update game
			//if not caught then doesn´t print board
		}
		//Suns have already been caught
		else {
			throw new CommandExecuteException(Messages.SUN_ALREADY_CAUGHT);
		}
		
		return success;
	}

	public Command create(String[] parameters) throws GameException {
		
		CatchCommand command = new CatchCommand();
		
		//It has to have 3 instruction parameters... catch x y
		if(parameters.length == 3) {
			try {
				this.col = Integer.parseInt((parameters[1]));
				this.row = Integer.parseInt((parameters[2]));
				//Coordinates have to be inside board
				if(col < GameWorld.NUM_COLS && col >= 0 && 
						row < GameWorld.NUM_ROWS && row >= 0)
					command = new CatchCommand(col, row);
				else {
					throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[1], parameters[2]));
				}
			}
			catch(NumberFormatException nfe){
				throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[1], parameters[2]), nfe);
			}
		}
		else {
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
			
		return command;
	}
	
}