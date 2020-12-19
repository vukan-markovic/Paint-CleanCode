package command;

import geometry.Donut;

public class CmdModifyDonut implements Command {
	Donut oldState;
	Donut newState;
	Donut original;

	public CmdModifyDonut(Donut oldState, Donut newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		oldState.setCenter(newState.getCenter());
		oldState.setRadius(newState.getRadius());
		oldState.setInnerRadius(newState.getInnerRadius());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setFillColor(newState.getFillColor());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.setCenter(original.getCenter());
		oldState.setRadius(original.getRadius());
		oldState.setInnerRadius(original.getInnerRadius());
		oldState.setBorderColor(original.getBorderColor());
		oldState.setFillColor(original.getFillColor());
		oldState.setSelected(original.isSelected());
	}
}