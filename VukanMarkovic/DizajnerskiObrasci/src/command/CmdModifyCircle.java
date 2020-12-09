package command;

import geometry.Circle;
import geometry.Shape;

public class CmdModifyCircle implements Command{

	private Circle oldState;
	private Circle newState;
	private Circle original = new Circle();

	
	public CmdModifyCircle(Circle oldState, Circle newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		oldState.setCenter(newState.getCenter());
		try {
			oldState.setRadius(newState.getRadius());
		}catch (Exception e) {
			e.printStackTrace();
		}
		oldState.setBorder_Color(newState.getBorder_Color());
		oldState.setFill_Color(newState.getFill_Color());
		oldState.setSelected(newState.isSelected());

	}

	@Override
	public void unexecute() {
		oldState.setCenter(original.getCenter());
		try {
			oldState.setRadius(original.getRadius());
		}catch (Exception e) {
			e.printStackTrace();
		}
		oldState.setBorder_Color(original.getBorder_Color());
		oldState.setFill_Color(original.getFill_Color());
		oldState.setSelected(original.isSelected());
	}


}
