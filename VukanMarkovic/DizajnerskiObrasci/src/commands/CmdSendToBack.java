package commands;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdSendToBack implements Command {
	private DrawingModel model;
	private Shape shape;
	private int indexOfShape;

	public CmdSendToBack(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		indexOfShape = model.getIndexOfShape(shape);
		model.removeShape(shape);
		model.addShapeToIndex(0, shape);
	}

	@Override
	public void unexecute() {
		model.removeShapeAtIndex(0);
		model.addShapeToIndex(indexOfShape, shape);
	}
}