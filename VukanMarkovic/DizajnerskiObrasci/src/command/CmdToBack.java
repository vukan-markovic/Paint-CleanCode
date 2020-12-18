package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdToBack implements Command {
	private DrawingModel model;
	private Shape shape;
	private int index;

	public CmdToBack(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		index = model.indexOfShape(shape);

		if (index != 0)
			Collections.swap(model.getShapes(), index - 1, index);
	}

	@Override
	public void unexecute() {
		if (index != 0)
			Collections.swap(model.getShapes(), index, index - 1);
	}
}