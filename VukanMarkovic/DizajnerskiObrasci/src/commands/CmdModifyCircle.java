package commands;

import shapes.Circle;

public class CmdModifyCircle implements Command {
	private Circle oldState;
	private Circle newState;
	private Circle originalState;

	public CmdModifyCircle(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setCenter(newState.getCenter());
		oldState.setRadius(newState.getRadius());
		oldState.setOuterColor(newState.getInnerColor());
		oldState.setInnerColor(newState.getInnerColor());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.setCenter(originalState.getCenter());
		oldState.setRadius(originalState.getRadius());
		oldState.setOuterColor(originalState.getInnerColor());
		oldState.setInnerColor(originalState.getInnerColor());
		oldState.setSelected(originalState.isSelected());
	}
}