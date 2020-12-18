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
		oldState.setBorder_Color(newState.getBorder_Color());
		oldState.setFill_Color(newState.getFill_Color());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.setCenter(original.getCenter());
		oldState.setRadius(original.getRadius());
		oldState.setInnerRadius(original.getInnerRadius());
		oldState.setBorder_Color(original.getBorder_Color());
		oldState.setFill_Color(original.getFill_Color());
		oldState.setSelected(original.isSelected());
	}
}