package command;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdDeselect implements Command{
	
	Shape shape;
	DrawingModel model;
	
	
	

	public CmdDeselect(Shape shape, DrawingModel model) {
		super();
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		
		for(int i = 0; i < model.getShapes().size(); i++) {
			
			if(model.getShapes().get(i).equals(shape)) {
				
				model.getShapes().get(i).setSelected(false);
				model.getSelectedShapes().remove(shape);
				
			}
		}
		
	}

	@Override
	public void unexecute() {
		
		for(int i = 0; i < model.getShapes().size(); i++) {
			
			if(model.getShapes().get(i).equals(shape)) {
				
				model.getShapes().get(i).setSelected(true);
				model.getSelectedShapes().add(shape);
				
			}
		}
		
	}


}
