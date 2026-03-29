package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ShowRecordCommand extends Command {

	public ShowRecordCommand(){
		
	}

	protected String getName() {
		return Messages.COMMAND_SHOW_RECORD_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_SHOW_RECORD_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_SHOW_RECORD_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_SHOW_RECORD_HELP;
	}


	
	public boolean execute(GameWorld game) throws GameException {
		System.out.print(game.getLevelName() + " record is " + game.getRecord() + "\n");
		return false;
	}
	
}
