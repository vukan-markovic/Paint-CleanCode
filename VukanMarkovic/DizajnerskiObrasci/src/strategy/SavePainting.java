package strategy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import geometry.Shape;

public class SavePainting implements Save {
	private List<Shape> shapes;

	@Override
	public void saveFile(String filePath) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
			objectOutputStream.writeObject(shapes);
			objectOutputStream.close();
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