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
		model.getShapes().remove(shape);
		model.getShapes().add(0, shape);
	}

	@Override
	public void unexecute() {
		model.getShapes().remove(0);
		model.getShapes().add(indexOfShape, shape);
	}
}