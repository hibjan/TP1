package tp1.p2.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.view.Messages;
import tp1.p2.control.Level;

public class Record {

	// Attributes
	private int levelRecord;
	private int[] records = { 0, 0, 0 };
	private Level level;

	// Constructors
	public Record() {
	}

	public Record(Level level) throws GameException {
		this.level = level;
		this.levelRecord = loadRecord();
	}
	/*
	 * @param int score
	 * updates the internal record array accordingly
	 */

	private void update(int score) {
		switch (this.level.name()) {
			case ("EASY"):
				records[0] = score;
				break;
			case ("HARD"):
				records[1] = score;
				break;
			case ("INSANE"):
				records[2] = score;
				break;
			default:
				break;
		}
	}

	/*
	 * @param int score
	 * overwrites the RECORD file with the new record state from every level
	 */

	public void saveRecord(int score) throws GameException {
		BufferedWriter outChars = null;
		String line;
		if (score > levelRecord) {
			update(score);
			try {
				outChars = new BufferedWriter(new FileWriter(Messages.RECORD_FILENAME));
				if (records[0] > 0) {
					line = "EASY:" + records[0] + "\n";
					outChars.write(line);
				}
				if (records[1] > 0) {
					line = "HARD:" + records[1] + "\n";
					outChars.write(line);
				}
				if (records[2] > 0) {
					line = "INSANE:" + records[2] + "\n";
					outChars.write(line);
				}
				if (outChars != null)
					outChars.close();
			} catch (IOException ioe) {
				throw new RecordException(Messages.RECORD_WRITE_ERROR);
			}
		}
	}
	/*
	 * loads from the RECORD file the information about every level record
	 */

	private int loadRecord() throws GameException {
		int temp = 0;
		int level = 0;
		try (BufferedReader inChars = new BufferedReader(new FileReader(Messages.RECORD_FILENAME))) {
			String line;
			String[] words;
			while ((line = inChars.readLine()) != null) {
				words = line.trim().split(":");
				if (words.length == 2) {
					try {
						temp = Integer.parseInt(words[1]);
					} catch (NumberFormatException nfe) {
						// Ignorar línea inválida
					}
					switch (words[0]) {
						case "EASY":
							records[0] = temp;
							if (this.level.name().equals("EASY"))
								level = temp;
							break;
						case "HARD":
							records[1] = temp;
							if (this.level.name().equals("HARD"))
								level = temp;
							break;
						case "INSANE":
							records[2] = temp;
							if (this.level.name().equals("INSANE"))
								level = temp;
							break;
						default:
							break;
					}
				}
			}
		} catch (java.io.FileNotFoundException fnfe) {
			// Si no existe record.txt en el primer inicio, no hay registros previos.
			return 0;
		} catch (IOException ioe) {
			throw new RecordException(Messages.RECORD_READ_ERROR);
		}
		return level;
	}

	public int getRecord() {
		return this.levelRecord;
	}

}
