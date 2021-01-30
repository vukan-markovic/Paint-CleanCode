package commands;

import model.DrawingModel;
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
		model.removeShapeAtIndex(indexOfShape);
		model.addShapeToIndex(model.getNumberOfShapes(), shape);
	}

	@Override
	public void unexecute() {
		model.removeShapeAtIndex(model.getNumberOfShapes() - 1);
		model.addShapeToIndex(indexOfShape, shape);
	}
}