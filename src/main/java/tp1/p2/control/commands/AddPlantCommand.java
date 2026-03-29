package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	//Attributes
	
	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;

	//Constructors
	
	public AddPlantCommand() {
		this(true);
	}

	public AddPlantCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}

	//Methods 
	
	public String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	public String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		boolean draw = false;
		
		//No object in that position except sun
		game.checkValidObjectPosition(this.col, this.row);
		Plant plant = PlantFactory.spawnPlant(this.plantName, game, this.col, this.row);
		//Plant cost coins
		if(this.consumeCoins) {
			game.tryToBuy(plant.getCost());
		}
		//Plant doesn't cost coins
		draw = game.addItem(plant);
		
		return draw;
	}

	public Command create(String[] parameters) throws GameException {
		AddPlantCommand command = new AddPlantCommand();
		
		//It has to have 4 instruction parameters... add plantName x y
		if(parameters.length == 4) {
			try {
				this.col = Integer.parseInt((parameters[2]));
				this.row = Integer.parseInt((parameters[3]));
				//Coordinates have to be inside board
				if(col < GameWorld.NUM_COLS && col >= 0 && 
						row < GameWorld.NUM_ROWS && row >= 0) {
					this.plantName = parameters[1];
					//Plant tag has to be valid
					if(PlantFactory.isValidPlant(this.plantName)) 
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
		//There aren't 4 parameters
		else {	
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
			
		return command;
	}

	public boolean doesUpdate() {
		return true;
	}
}
