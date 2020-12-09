package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdToFront implements Command{
	

	private DrawingModel model;
	private Shape s;
	private int index;
	
	

	public CmdToFront(DrawingModel model, Shape s) {
		super();
		this.model = model;
		this.s = s;
	}

	
	@Override
	public void execute() {
		
		index = model.indexOfShape(s);
		
		if(index != model.getShapes().size() - 1) {
			
			Collections.swap(model.getShapes(), index + 1, index);
			
		}
		
	}

	@Override
	public void unexecute() {
		
		if(index != model.getShapes().size() - 1) {
			
			Collections.swap(model.getShapes(), index, index + 1);
			
		}
		
	}

}
