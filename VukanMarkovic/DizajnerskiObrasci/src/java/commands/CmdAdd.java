package commands;

import model.DrawingModel;
import shapes.Shape;

public class CmdAdd implements Command {
	private DrawingModel model;
	private Shape shape;

	public CmdAdd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.addShape(shape);
	}

	@Override
	public void unexecute() {
		model.removeShape(shape);
	}
}