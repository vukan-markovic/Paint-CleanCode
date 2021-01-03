package commands;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdDeselect implements Command {
	private Shape shape;
	private DrawingModel model;

	public CmdDeselect(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		for (int indexOfShape = 0; indexOfShape < model.getShapes().size(); indexOfShape++) {
			if (model.getShapes().get(indexOfShape).equals(shape)) {
				model.getShapes().get(indexOfShape).setSelected(false);
				model.getSelectedShapes().remove(shape);
			}
		}
	}

	@Override
	public void unexecute() {
		for (int indexOfShape = 0; indexOfShape < model.getShapes().size(); indexOfShape++) {
			if (model.getShapes().get(indexOfShape).equals(shape)) {
				model.getShapes().get(indexOfShape).setSelected(true);
				model.getSelectedShapes().add(shape);
			}
		}
	}
}