package commands;

import java.util.*;
import mvc.DrawingModel;
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
		if (indexOfShape != 0)
			Collections.swap(shapes, indexOfPreviousShape, indexOfShape);
	}

	@Override
	public void unexecute() {
		if (indexOfShape != 0)
			Collections.swap(shapes, indexOfShape, indexOfPreviousShape);
	}
}