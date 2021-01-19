package files;

import java.io.*;
import model.DrawingModel;

public class FilePainting implements FileStrategy {
	private DrawingModel model;
	
	public FilePainting(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void saveFile(String filePath) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			outputStream.writeObject(model.getShapes());
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Override
	public void openFile(File file) {
		
	}

	public DrawingModel getModel() {
		return model;
	}
}