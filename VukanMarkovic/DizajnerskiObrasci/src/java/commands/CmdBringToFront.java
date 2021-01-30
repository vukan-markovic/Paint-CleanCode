package commands;

import java.util.Collections;
import model.DrawingModel;
import shapes.Shape;

public class CmdBringToFront implements Command {
	private DrawingModel model;
	private int indexOfShape;

	public CmdBringToFront(DrawingModel model, Shape shape) {
		this.model = model;
		indexOfShape = model.getIndexOfShape(shape);
	}

	@Override
	public void execute() {
		swapShapes();
	}

	@Override
	public void unexecute() {
		swapShapes();
	}

	private void swapShapes() {
		int indexOfLastShape = model.getNumberOfShapes() - 1;
		Collections.swap(model.getShapes(), indexOfLastShape, indexOfShape);
	}
}