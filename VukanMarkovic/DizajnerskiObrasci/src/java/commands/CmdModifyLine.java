package commands;

import shapes.Line;

public class CmdModifyLine implements Command {
	private Line oldState;
	private Line newState;
	private Line originalState;

	public CmdModifyLine(Line oldState, Line newState) {
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

	private void setOldState(Line state) {
		oldState.setStartPoint(state.getStartPoint());
		oldState.setEndPoint(state.getEndPoint());
		oldState.setBorderColor(state.getBorderColor());
		oldState.setSelected(state.isSelected());
	}
}