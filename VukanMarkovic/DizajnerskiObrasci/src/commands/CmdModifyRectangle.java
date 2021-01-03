package commands;

import shapes.Rectangle;

public class CmdModifyRectangle implements Command {
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle originalState;

	public CmdModifyRectangle(Rectangle oldState, Rectangle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setUpperLeftPoint(newState.getUpperLeftPoint());
		oldState.setHeight(newState.getHeight());
		oldState.setWidth(newState.getWidth());
		oldState.setOuterColor(newState.getInnerColor());
		oldState.setInnerColor(newState.getInnerColor());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.setUpperLeftPoint(originalState.getUpperLeftPoint());
		oldState.setHeight(originalState.getHeight());
		oldState.setWidth(originalState.getWidth());
		oldState.setOuterColor(originalState.getInnerColor());
		oldState.setInnerColor(originalState.getInnerColor());
		oldState.setSelected(originalState.isSelected());
	}
}