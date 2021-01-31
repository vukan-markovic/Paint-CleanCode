package commands;

import java.util.*;
import model.DrawingModel;
import shapes.Shape;

public class CmdToFront implements Command {
	private List<Shape> shapes;
	private int indexOfShape;
	private int indexOfNextShape;
	private int indexOfLastShape;

	public CmdToFront(DrawingModel model, Shape shape) {
		shapes = model.getShapes();
		indexOfShape = shapes.indexOf(shape);
		indexOfNextShape = indexOfShape + 1;
		indexOfLastShape = shapes.size() - 1;
	}

	@Override
	public void execute() {
		if (isNotLastShape())
			swapShapes();
	}

	@Override
	public void unexecute() {
		if (isNotLastShape())
			swapShapes();
	}

	private boolean isNotLastShape() {
		return indexOfShape != indexOfLastShape;
	}

	private void swapShapes() {
		Collections.swap(shapes, indexOfNextShape, indexOfShape);
	}
}