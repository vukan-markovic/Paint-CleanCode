package mvc;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;

public class DrawingModel {
	private List<Shape> shapes = new ArrayList<Shape>();
	private List<Shape> selectedShapes = new ArrayList<Shape>();

	public void selectShape(Shape shape) {
		for (int index = 0; index < shapes.size(); index++) {
			if (shapes.get(index).equals(shape)) {
				shapes.get(index).setSelected(true);
				selectedShapes.add(shape);
			}
		}
	}

	public Shape getOneSelectedShape() {
		for (Shape shape : shapes) {
			if (shape != null && shape.isSelected())
				return shape;
		}

		return null;
	}

	public int indexOfShape(Shape shape) {
		return shapes.indexOf(shape);
	}

	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void setSelectedShapes(List<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void add(Shape shape) {
		shapes.add(shape);
	}

	public void remove(Shape shape) {
		shapes.remove(shape);
	}

	public Shape get(int index) {
		return shapes.get(index);
	}
}