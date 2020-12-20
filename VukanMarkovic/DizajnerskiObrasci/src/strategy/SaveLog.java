package strategy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLog implements Save {
	private String log;

	@Override
	public void saveFile(String filePath) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			writer.write(log);
			writer.close();
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
}