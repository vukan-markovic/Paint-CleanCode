package command;

import geometry.Rectangle;

public class CmdModifyRectangle implements Command {
	
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original = new Rectangle();
	
	
	public CmdModifyRectangle(Rectangle oldState,Rectangle newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}
	
	
	@Override
	public void execute() {
		
		original = oldState.clone();
		oldState.setUpperLeftPoint(newState.getUpperLeftPoint());
		oldState.setHeight(newState.getHeight());
		oldState.setWidth(newState.getWidth());
		oldState.setBorder_Color(newState.getBorder_Color());
		oldState.setFill_Color(newState.getFill_Color());
		oldState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		
		oldState.setUpperLeftPoint(original.getUpperLeftPoint());
		oldState.setHeight(original.getHeight());
		oldState.setWidth(original.getWidth());
		oldState.setBorder_Color(original.getBorder_Color());
		oldState.setFill_Color(original.getFill_Color());
		oldState.setSelected(original.isSelected());
	}

}
