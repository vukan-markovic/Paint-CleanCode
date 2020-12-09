package mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import geometry.Shape;

public class DrawingModel {
	
	private List<Shape> shapes = new ArrayList<Shape>(); 
	private List<Shape> selectedShapes = new ArrayList<Shape>();
	private Shape oneSelectedShape;
	private PropertyChangeSupport propertyChangeSupport;


	
	public void selectShape(Shape shape) {
		
		for(int i = 0; i < shapes.size(); i++) {
			
			if(shapes.get(i).equals(shape)) {
				shapes.get(i).setSelected(true);
				selectedShapes.add(shape);
			}
			
		}
		
	}
	
	public void deselectShape(Shape shape) {
		
		for(int i = 0; i < shapes.size(); i++) {
			
			if(shapes.get(i).equals(shape)) {
				shapes.get(i).setSelected(false);
				selectedShapes.remove(shape);
			}
			
		}
		
	}

	public Shape getOneSelectedShape() {
		
		for (Shape shape : shapes) {
			
			if (shape != null && shape.isSelected()) {
			
				return shape;
			}
		}
		
		return oneSelectedShape;
	}
	
	public int indexOfShape(Shape s) {
		
		int size = shapes.size() - 1;
		for(int i = 0; i <= size; i++) {
			if(shapes.get(i).equals(s)) {
				return i;
			}
		}
		return -1;
	}

	public void setOneSelectedShape(Shape oneSelectedShape) {
		this.oneSelectedShape = oneSelectedShape;
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

	public Shape get(int i) {
		return shapes.get(i);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.removePropertyChangeListener(pcl);
	}
	
	


}
