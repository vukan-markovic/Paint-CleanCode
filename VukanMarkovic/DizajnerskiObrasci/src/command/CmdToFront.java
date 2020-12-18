package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdToFront implements Command {
	private DrawingModel model;
	private Shape shape;
	private int index;

	public CmdToFront(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		index = model.indexOfShape(shape);
		
		if (index != model.getShapes().size() - 1)
			Collections.swap(model.getShapes(), index + 1, index);
	}

	@Override
	public void unexecute() {
		if (index != model.getShapes().size() - 1)
			Collections.swap(model.getShapes(), index, index + 1);
	}
}