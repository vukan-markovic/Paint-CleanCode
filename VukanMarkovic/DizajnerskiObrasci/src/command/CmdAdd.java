package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdAdd implements Command {
	private DrawingModel model;
	private Shape shape;

	public CmdAdd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		shape.setSelected(false);
		model.add(shape);
	}

	@Override
	public void unexecute() {
		model.remove(shape);
	}
}