package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdSendToBack implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int index;
	
	

	public CmdSendToBack(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		
		index = model.indexOfShape(shape);
		model.getShapes().remove(shape);
		model.getShapes().add(0, shape);
		
	}

	@Override
	public void unexecute() {
		
		model.getShapes().remove(0);
		model.getShapes().add(index, shape);
		
	}

}
