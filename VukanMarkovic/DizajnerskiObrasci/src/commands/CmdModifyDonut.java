package commands;

import shapes.Donut;

public class CmdModifyDonut implements Command {
	private Donut oldState;
	private Donut newState;
	private Donut originalState;

	public CmdModifyDonut(Donut oldState, Donut newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setCenter(newState.getCenter());
		oldState.setRadius(newState.getRadius());
		oldState.setInnerRadius(newState.getInnerRadius());
		oldState.setOuterColor(newState.getInnerColor());
		oldState.setInnerColor(newState.getInnerColor());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.setCenter(originalState.getCenter());
		oldState.setRadius(originalState.getRadius());
		oldState.setInnerRadius(originalState.getInnerRadius());
		oldState.setOuterColor(originalState.getInnerColor());
		oldState.setInnerColor(originalState.getInnerColor());
		oldState.setSelected(originalState.isSelected());
	}
}