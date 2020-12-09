package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdRemove implements Command {
	
	private DrawingModel model;
	private Shape shape;
	
	

	public CmdRemove(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.remove(shape);
		
	}

	@Override
	public void unexecute() {
		model.add(shape);
		
	}





}
