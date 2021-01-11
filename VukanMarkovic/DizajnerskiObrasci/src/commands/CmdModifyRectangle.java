package commands;

import shapes.Rectangle;

public class CmdModifyRectangle implements Command {
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle originalState;

	public CmdModifyRectangle(Rectangle oldState, Rectangle newState) {
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

	private void setOldState(Rectangle state) {
		oldState.setUpperLeftPoint(state.getUpperLeftPoint());
		oldState.setHeight(state.getHeight());
		oldState.setWidth(state.getWidth());
		oldState.setOuterColor(state.getInnerColor());
		oldState.setInnerColor(state.getInnerColor());
		oldState.setSelected(state.isSelected());
	}
}