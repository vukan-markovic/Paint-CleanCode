package mvc;

import java.util.*;
import shapes.Shape;

public class DrawingModel {
	private List<Shape> shapes;
	private List<Shape> selectedShapes;

	public DrawingModel() {
		shapes = new ArrayList<Shape>();
		selectedShapes = new ArrayList<Shape>();
	}

	public void selectShape(Shape shape) {
		for (int indexOfShape = 0; indexOfShape < shapes.size(); indexOfShape++) {
			if (shapes.get(indexOfShape).equals(shape)) {
				shapes.get(indexOfShape).setSelected(true);
				selectedShapes.add(shape);
			}
		}
	}

	public void addShape(Shape shape) {
		shapes.add(shape);
	}
	
	public void addSelectedShape(Shape shape) {
		selectedShapes.add(shape);
	}
	
	public void removeSelectedShape(Shape shape) {
		selectedShapes.remove(shape);
	}
	
	public void addShapeToIndex(int index, Shape shape) {
		shapes.add(index, shape);
	}
	
	public void removeShapeAtIndex(int index) {
		shapes.remove(index);
	}

	public void removeShape(Shape shape) {
		shapes.remove(shape);
	}

	public int getNumberOfShapes() {
		return shapes.size();
	}
	
	public int getIndexOfShape(Shape shape) {
		return shapes.indexOf(shape);
	}

	public Shape getShapeByIndex(int indexOfShape) {
		return shapes.get(indexOfShape);
	}

	public Shape getFirstSelectedShape() {
		return shapes.stream().filter(shape -> shape.isSelected()).findFirst().get();
	}
	
	public List<Shape> getShapes() {
		return shapes;
	}

	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
}