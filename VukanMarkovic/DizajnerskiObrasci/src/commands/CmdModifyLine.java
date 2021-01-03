package commands;

import shapes.Line;

public class CmdModifyLine implements Command {
	private Line oldState;
	private Line newState;
	private Line originalState;

	public CmdModifyLine(Line oldState, Line newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setStartPoint(newState.getStartPoint());
		oldState.setEndPoint(newState.getEndPoint());
		oldState.setOuterColor(newState.getOuterColor());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.setStartPoint(originalState.getStartPoint());
		oldState.setEndPoint(originalState.getEndPoint());
		oldState.setOuterColor(originalState.getOuterColor());
		oldState.setSelected(originalState.isSelected());
	}
}