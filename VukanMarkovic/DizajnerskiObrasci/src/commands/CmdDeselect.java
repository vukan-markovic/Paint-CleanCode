package commands;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdDeselect implements Command {
	private DrawingModel model;
	private Shape shape;

	public CmdDeselect(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		Shape shapeToSelect = model.getShapes().stream().filter(modelShape -> modelShape.equals(shape)).findFirst()
				.get();

		shapeToSelect.setSelected(false);
		model.removeSelectedShape(shapeToSelect);
	}

	@Override
	public void unexecute() {
		Shape shapeToSelect = model.getShapes().stream().filter(modelShape -> modelShape.equals(shape)).findFirst()
				.get();

		shapeToSelect.setSelected(true);
		model.addSelectedShape(shapeToSelect);
	}
}