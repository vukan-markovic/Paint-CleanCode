package commands;

import shapes.Point;

public class CmdModifyPoint implements Command {
	private Point oldState;
	private Point newState;
	private Point originalState;

	public CmdModifyPoint(Point oldState, Point newState) {
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

	private void setOldState(Point state) {
		oldState.setXcoordinate(state.getXcoordinate());
		oldState.setYcoordinate(state.getYcoordinate());
		oldState.setOuterColor(state.getOuterColor());
		oldState.setSelected(state.isSelected());
	}
}