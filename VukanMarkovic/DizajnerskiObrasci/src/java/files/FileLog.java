package files;

import java.io.*;
import java.util.Queue;
import javax.swing.DefaultListModel;

public class FileLog implements FileStrategy {
	private DefaultListModel<String> executedLogCommands;
	private Queue<String> logCommandsFromFile;

	public FileLog(DefaultListModel<String> log, Queue<String> commandsLog) {
		this.executedLogCommands = log;
		this.logCommandsFromFile = commandsLog;
	}

	@Override
	public void saveFile(String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (int i = 0; i < executedLogCommands.getSize(); i++) {
				writer.write(executedLogCommands.get(i));
				writer.newLine();
			}
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Override
	public void openFile(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String logLine = "";

			while ((logLine = reader.readLine()) != null)
				logCommandsFromFile.add(logLine);
		} catch (IOException exception) {
			logCommandsFromFile.clear();
			System.out.println(exception.getMessage());
		}
	}

	public DefaultListModel<String> getLog() {
		return executedLogCommands;
	}
}