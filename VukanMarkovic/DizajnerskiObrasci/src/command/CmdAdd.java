package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdAdd implements Command{
	
	private DrawingModel model;
	private Shape shape;
	
	

	public CmdAdd(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		shape.setSelected(false);
		System.out.println(shape.isSelected());
		model.add(shape);
		
	}

	@Override
	public void unexecute() {
		model.remove(shape);
		
	}


	
	

	





}
