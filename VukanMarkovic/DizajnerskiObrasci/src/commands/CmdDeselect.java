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