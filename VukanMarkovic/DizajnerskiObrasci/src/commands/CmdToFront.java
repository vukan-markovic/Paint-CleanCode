package commands;

import java.util.Collections;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdToFront implements Command {
	private DrawingModel model;
	private Shape shape;
	private int indexOfShape;

	public CmdToFront(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		indexOfShape = model.getIndexOfShape(shape);

		if (indexOfShape != model.getShapes().size() - 1)
			Collections.swap(model.getShapes(), indexOfShape + 1, indexOfShape);
	}

	@Override
	public void unexecute() {
		if (indexOfShape != model.getShapes().size() - 1)
			Collections.swap(model.getShapes(), indexOfShape, indexOfShape + 1);
	}
}