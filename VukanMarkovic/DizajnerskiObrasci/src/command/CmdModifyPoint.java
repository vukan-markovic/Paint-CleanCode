package command;

import geometry.Point;

public class CmdModifyPoint implements Command{
	
	private Point oldState;
	private Point newState;
	private Point original = new Point();
	
	

	public CmdModifyPoint(Point oldState, Point newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		
		original = oldState.clone();
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setBorder_Color(newState.getBorder_Color());
		oldState.setSelected(newState.isSelected());
		
	}

	@Override
	public void unexecute() {
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setBorder_Color(original.getBorder_Color());
		oldState.setSelected(original.isSelected());
		
	}

}
