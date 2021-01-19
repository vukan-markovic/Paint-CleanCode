package files;

import java.io.*;
import javax.swing.DefaultListModel;

public class FileLog implements FileStrategy {
	private DefaultListModel<String> log;

	public FileLog(DefaultListModel<String> log) {
		this.log = log;
	}

	@Override
	public void saveFile(String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (int i = 0; i < log.getSize(); i++) {
				writer.write(log.get(i));
				writer.newLine();
			}
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Override
	public void openFile(File file) {

	}

	public DefaultListModel<String> getLog() {
		return log;
	}
}