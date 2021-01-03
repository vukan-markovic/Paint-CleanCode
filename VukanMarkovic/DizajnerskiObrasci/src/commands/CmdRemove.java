package commands;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdRemove implements Command {
	private DrawingModel model;
	private Shape shape;

	public CmdRemove(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.removeShape(shape);
	}

	@Override
	public void unexecute() {
		model.addShape(shape);
	}
}