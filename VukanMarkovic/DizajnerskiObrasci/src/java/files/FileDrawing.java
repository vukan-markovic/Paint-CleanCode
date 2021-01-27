package files;

import java.io.*;
import java.util.ArrayList;

import frame.DrawingFrame;
import model.DrawingModel;
import shapes.Shape;

public class FileDrawing implements FileStrategy {
	private DrawingModel model;
	private DrawingFrame frame;

	public FileDrawing(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	@Override
	public void saveFile(String filePath) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			outputStream.writeObject(model.getShapes());
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void openFile(String filePath) {
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
			model.setShapes((ArrayList<Shape>) inputStream.readObject());
			frame.getView().repaint();
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		} catch (ClassNotFoundException exception) {
			model.getShapes().clear();
			System.out.println(exception.getMessage());
		}
	}

	public DrawingModel getModel() {
		return model;
	}
}