package commands;

import model.DrawingModel;
import shapes.Shape;

public class CmdSelect implements Command {
	private DrawingModel model;
	private Shape shapeToSelect;

	public CmdSelect(DrawingModel model, Shape shapeToSelect) {
		this.model = model;
		this.shapeToSelect = shapeToSelect;
	}

	@Override
	public void execute() {
		for (int indexOfShape = 0; indexOfShape < model.getNumberOfShapes(); indexOfShape++) {
			Shape shape = model.getShapeByIndex(indexOfShape);

			if (shape.equals(shapeToSelect)) {
				shape.setSelected(true);
				model.addSelectedShape(shapeToSelect);
			}
		}
	}

	@Override
	public void unexecute() {
		for (int indexOfShape = 0; indexOfShape < model.getNumberOfShapes(); indexOfShape++) {
			Shape shape = model.getShapeByIndex(indexOfShape);

			if (shape.equals(shapeToSelect)) {
				shape.setSelected(false);
				model.removeSelectedShape(shapeToSelect);
			}
		}
	}
}