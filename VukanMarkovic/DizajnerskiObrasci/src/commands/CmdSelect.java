package commands;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdSelect implements Command {
	private Shape shape;
	private DrawingModel model;

	public CmdSelect(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		Shape shapeToSelect = model.getShapes().stream().filter(modelShape -> modelShape.equals(shape)).findFirst()
				.get();

		shapeToSelect.setSelected(true);
		model.addSelectedShape(shapeToSelect);
	}

	@Override
	public void unexecute() {
		Shape shapeToSelect = model.getShapes().stream().filter(modelShape -> modelShape.equals(shape)).findFirst()
				.get();

		shapeToSelect.setSelected(false);
		model.removeSelectedShape(shapeToSelect);
	}
}