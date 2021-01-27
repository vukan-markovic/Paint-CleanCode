package commands;

import java.util.Collections;
import model.DrawingModel;
import shapes.Shape;

public class CmdBringToFront implements Command {
	private DrawingModel model;
	private int indexOfShape;
	private int indexOfLastShape;

	public CmdBringToFront(DrawingModel model, Shape shape) {
		this.model = model;
		indexOfShape = model.getIndexOfShape(shape);
		indexOfLastShape = model.getNumberOfShapes() - 1;
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
		Collections.swap(model.getShapes(), indexOfLastShape, indexOfShape);
	}
}