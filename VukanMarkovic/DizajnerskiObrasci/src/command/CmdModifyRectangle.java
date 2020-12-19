package command;

import geometry.Rectangle;

public class CmdModifyRectangle implements Command {
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original = new Rectangle();

	public CmdModifyRectangle(Rectangle oldState, Rectangle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		oldState.setUpperLeftPoint(newState.getUpperLeftPoint());
		oldState.setHeight(newState.getHeight());
		oldState.setWidth(newState.getWidth());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setFillColor(newState.getFillColor());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		oldState.setUpperLeftPoint(original.getUpperLeftPoint());
		oldState.setHeight(original.getHeight());
		oldState.setWidth(original.getWidth());
		oldState.setBorderColor(original.getBorderColor());
		oldState.setFillColor(original.getFillColor());
		oldState.setSelected(original.isSelected());
	}
}