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
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setFillColor(newState.getFillColor());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.setCenter(original.getCenter());
		oldState.setRadius(original.getRadius());
		oldState.setBorderColor(original.getBorderColor());
		oldState.setFillColor(original.getFillColor());
		oldState.setSelected(original.isSelected());
	}
}