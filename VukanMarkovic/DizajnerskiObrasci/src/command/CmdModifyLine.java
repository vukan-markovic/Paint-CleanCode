package command;

import geometry.Line;

public class CmdModifyLine implements Command {
	private Line oldState;
	private Line newState;
	private Line original;

	public CmdModifyLine(Line oldState, Line newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		oldState.setStartPoint(newState.getStartPoint());
		oldState.setEndPoint(newState.getEndPoint());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.setStartPoint(original.getStartPoint());
		oldState.setEndPoint(original.getEndPoint());
		oldState.setBorderColor(original.getBorderColor());
		oldState.setSelected(original.isSelected());
	}
}