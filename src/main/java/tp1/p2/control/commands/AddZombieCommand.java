package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class AddZombieCommand extends Command {

	//Attributes
	
	private int zombieIdx;

	private int col;

	private int row;

	//Constructors
	
	public AddZombieCommand() {

	}

	//Methods
	
	public String getName() {
		return Messages.COMMAND_ADD_ZOMBIE_NAME;
	}

	public String getShortcut() {
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_ADD_ZOMBIE_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		boolean draw = false;
		
		//No object in that position except suns
		game.checkValidObjectPosition(this.col, this.row);
		Zombie zombie = ZombieFactory.spawnZombie(this.zombieIdx, game, this.col, this.row);
		//Prints board if zombie is added
		draw = game.addItem(zombie);
		
		return draw; 
	}

	public Command create(String[] parameters) throws GameException {
		
		AddZombieCommand command = new AddZombieCommand();
		
		//It has to have 4 instruction parameters... addzombie zombieId x y
		if(parameters.length == 4) {
			try {
				this.zombieIdx = Integer.parseInt(parameters[1]);
				this.col = Integer.parseInt((parameters[2]));
				this.row = Integer.parseInt((parameters[3]));
				//Coordinates have to be inside board or in 8th column
				if(col <= GameWorld.NUM_COLS && col >= 0 && 
						row < GameWorld.NUM_ROWS && row >= 0) {
						if(ZombieFactory.isValidZombie(this.zombieIdx)) 
							command = this;
						else {
							throw new CommandParseException(Messages.INVALID_GAME_OBJECT);
						}
				}
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

	public boolean doesUpdate() {
		return true;
	}
}
