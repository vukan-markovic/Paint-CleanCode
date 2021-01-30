package commands;

import model.DrawingModel;
import shapes.Shape;

public class CmdDeselect implements Command {
	private DrawingModel model;
	private Shape shapeToDeselect;

	public CmdDeselect(DrawingModel model, Shape shapeToDeselect) {
		this.model = model;
		this.shapeToDeselect = shapeToDeselect;
	}

	@Override
	public void execute() {
		for (int indexOfShape = 0; indexOfShape < model.getNumberOfShapes(); indexOfShape++) {
			Shape shape = model.getShapeByIndex(indexOfShape);

			if (shape.equals(shapeToDeselect)) {
				shape.setSelected(false);
				model.removeSelectedShape(shapeToDeselect);
			}
		}
	}

	@Override
	public void unexecute() {
		for (int indexOfShape = 0; indexOfShape < model.getNumberOfShapes(); indexOfShape++) {
			Shape shape = model.getShapeByIndex(indexOfShape);

			if (shape.equals(shapeToDeselect)) {
				shape.setSelected(true);
				model.addSelectedShape(shapeToDeselect);
			}
		}
	}
}