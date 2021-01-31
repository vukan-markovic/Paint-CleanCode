package files;

public class FileManager {
	private FileStrategy strategy;

	public FileManager(FileStrategy strategy) {
		this.strategy = strategy;
	}

	public void save(String filePath) {
		strategy.saveFile(filePath);
	}

	public void open(String filePath) {
		strategy.openFile(filePath);
	}
}