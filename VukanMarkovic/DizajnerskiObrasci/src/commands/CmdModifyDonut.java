package commands;

import shapes.Donut;

public class CmdModifyDonut implements Command {
	private Donut oldState;
	private Donut newState;
	private Donut originalState;

	public CmdModifyDonut(Donut oldState, Donut newState) {
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

	private void setOldState(Donut state) {
		oldState.setCenter(state.getCenter());
		oldState.setRadius(state.getRadius());
		oldState.setInnerRadius(state.getInnerRadius());
		oldState.setOuterColor(state.getInnerColor());
		oldState.setInnerColor(state.getInnerColor());
		oldState.setSelected(state.isSelected());
	}
}