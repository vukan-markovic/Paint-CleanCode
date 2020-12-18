package command;

import geometry.Circle;

public class CmdModifyCircle implements Command {
	private Circle oldState;
	private Circle newState;
	private Circle original;

	public CmdModifyCircle(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		oldState.setCenter(newState.getCenter());
		oldState.setRadius(newState.getRadius());
		oldState.setBorder_Color(newState.getBorder_Color());
		oldState.setFill_Color(newState.getFill_Color());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.setCenter(original.getCenter());
		oldState.setRadius(original.getRadius());
		oldState.setBorder_Color(original.getBorder_Color());
		oldState.setFill_Color(original.getFill_Color());
		oldState.setSelected(original.isSelected());
	}
}