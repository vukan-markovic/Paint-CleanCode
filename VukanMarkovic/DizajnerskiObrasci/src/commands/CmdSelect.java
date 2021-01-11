package commands;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdSelect implements Command {
	private DrawingModel model;
	private Shape shape;

	public CmdSelect(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		shape.setSelected(true);
		model.addSelectedShape(shape);
	}

	@Override
	public void unexecute() {
		shape.setSelected(false);
		model.removeSelectedShape(shape);
	}
}