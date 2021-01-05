package strategy;

import java.io.*;
import java.util.List;

import shapes.Shape;

public class SavePainting implements Save {
	private List<Shape> shapes;

	@Override
	public void saveFile(String filePath) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			outputStream.writeObject(shapes);
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
}