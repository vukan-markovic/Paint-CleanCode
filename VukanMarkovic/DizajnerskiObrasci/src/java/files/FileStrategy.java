package files;

interface FileStrategy {
	void saveFile(String filePath);
	void openFile(String filePath);
}