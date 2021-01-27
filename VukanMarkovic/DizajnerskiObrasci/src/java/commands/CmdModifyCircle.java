package commands;

import shapes.Circle;

public class CmdModifyCircle implements Command {
	private Circle oldState;
	private Circle newState;
	private Circle originalState;

	public CmdModifyCircle(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
		originalState = oldState.clone();
	}

	@Override
	public void execute() {
		setOldState(newState);
	}

	@Override
	public void unexecute() {
		setOldState(originalState);
	}

	private void setOldState(Circle state) {
		oldState.setCenter(state.getCenter());
		oldState.setRadius(state.getRadius());
		oldState.setOuterColor(state.getOuterColor());
		oldState.setInnerColor(state.getInnerColor());
		oldState.setSelected(state.isSelected());
	}
}