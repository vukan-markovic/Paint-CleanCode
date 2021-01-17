package commands;

import java.util.Collections;

import model.DrawingModel;
import shapes.Shape;

public class CmdSendToBack implements Command {
	private DrawingModel model;
	private int indexOfShape;

	public CmdSendToBack(DrawingModel model, Shape shape) {
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
		Collections.swap(model.getShapes(), 0, indexOfShape);
	}
}