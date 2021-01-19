package files;

import java.io.File;

interface FileStrategy {
	void saveFile(String filePath);
	void openFile(File file);
}