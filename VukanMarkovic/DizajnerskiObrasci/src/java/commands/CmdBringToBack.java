package commands;

import model.DrawingModel;
import shapes.Shape;

public class CmdBringToBack implements Command {
	private DrawingModel model;
	private Shape shape;
	private int indexOfShape;

	public CmdBringToBack(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		indexOfShape = model.getIndexOfShape(shape);
		model.removeShapeAtIndex(indexOfShape);
		model.addShapeToIndex(0, shape);
	}

	@Override
	public void unexecute() {
		model.removeShapeAtIndex(0);
		model.addShapeToIndex(indexOfShape, shape);
	}
}