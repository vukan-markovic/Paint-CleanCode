package commands;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdBringToFront implements Command {
	private DrawingModel model;
	private Shape shape;
	private int indexOfShape;

	public CmdBringToFront(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		indexOfShape = model.getIndexOfShape(shape);
		model.removeShape(shape);
		model.addShape(shape);
	}

	@Override
	public void unexecute() {
		model.removeShape(shape);
		model.addShapeToIndex(indexOfShape, shape);
	}
}