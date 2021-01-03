package commands;

import shapes.Point;

public class CmdModifyPoint implements Command {
	private Point oldState;
	private Point newState;
	private Point originalState;

	public CmdModifyPoint(Point oldState, Point newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setXcoordinate(newState.getXcoordinate());
		oldState.setYcoordinate(newState.getYcoordinate());
		oldState.setOuterColor(newState.getOuterColor());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.setXcoordinate(originalState.getXcoordinate());
		oldState.setYcoordinate(originalState.getYcoordinate());
		oldState.setOuterColor(originalState.getOuterColor());
		oldState.setSelected(originalState.isSelected());
	}
}