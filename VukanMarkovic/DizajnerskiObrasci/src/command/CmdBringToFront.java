package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdBringToFront implements Command{
	
	private DrawingModel model;
	private Shape shape;
	private int index;
	
	

	public CmdBringToFront(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		
		index = model.indexOfShape(shape);
		model.getShapes().remove(shape);
		model.getShapes().add(shape);
		
	}

	@Override
	public void unexecute() {
		
		model.getShapes().remove(shape);
		model.getShapes().add(index, shape);
		
	}


}
