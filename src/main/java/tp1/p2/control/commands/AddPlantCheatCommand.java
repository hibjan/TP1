package tp1.p2.control.commands;

import tp1.p2.view.Messages;

public class AddPlantCheatCommand extends AddPlantCommand {
	
	//Constructors

	public AddPlantCheatCommand() {
		super(false); 
	}
	
	//Methods

	public String getName() {
		return Messages.COMMAND_CHEAT_PLANT_NAME;
	}

	public String getShortcut() {
		return Messages.COMMAND_CHEAT_PLANT_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_CHEAT_PLANT_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_CHEAT_PLANT_HELP;
	}

}