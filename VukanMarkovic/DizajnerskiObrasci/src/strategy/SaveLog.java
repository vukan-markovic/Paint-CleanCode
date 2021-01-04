package strategy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLog implements Save {
	private String commandsLog;

	@Override
	public void saveFile(String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(commandsLog);
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	public String getCommandsLog() {
		return commandsLog;
	}

	public void setCommandsLog(String commandsLog) {
		this.commandsLog = commandsLog;
	}
}