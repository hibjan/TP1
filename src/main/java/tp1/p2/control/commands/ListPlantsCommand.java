package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class ListPlantsCommand extends Command {

	public String getName() {
		return Messages.COMMAND_LIST_NAME;
	}

	public String getShortcut() {
		return Messages.COMMAND_LIST_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_LIST_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_LIST_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		System.out.println(Messages.AVAILABLE_PLANTS);
		for (Plant p : PlantFactory.getAvailablePlants())
			System.out.println(p.getDescription());
		System.out.println();

		return false;
	}

}