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
		model.getShapes().remove(shape);
		model.getShapes().add(shape);
	}

	@Override
	public void unexecute() {
		model.getShapes().remove(shape);
		model.getShapes().add(indexOfShape, shape);
	}
}