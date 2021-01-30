package commands;

import java.util.*;
import model.DrawingModel;
import shapes.Shape;

public class CmdToBack implements Command {
	private List<Shape> shapes;
	private int indexOfShape;
	private int indexOfPreviousShape;

	public CmdToBack(DrawingModel model, Shape shape) {
		shapes = model.getShapes();
		indexOfShape = shapes.indexOf(shape);
		indexOfPreviousShape = indexOfShape - 1;
	}

	@Override
	public void execute() {
		if (isNotFirstShape())
			swapShapes();
	}

	@Override
	public void unexecute() {
		if (isNotFirstShape())
			swapShapes();
	}

	private boolean isNotFirstShape() {
		return indexOfShape != 0;
	}
	
	private void swapShapes() {
		Collections.swap(shapes, indexOfPreviousShape, indexOfShape);
	}
}