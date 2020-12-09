package command;

import geometry.Line;

public class CmdModifyLine implements Command{
	
	private Line oldState;
	private Line newState;
	private Line original = new Line();
	
	

	public CmdModifyLine(Line oldState, Line newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		
		original = oldState.clone();
		oldState.setStartPoint(newState.getStartPoint());
		oldState.setEndPoint(newState.getEndPoint());
		oldState.setBorder_Color(newState.getBorder_Color());
		oldState.setSelected(newState.isSelected());
		
	}

	@Override
	public void unexecute() {

		oldState.setStartPoint(original.getStartPoint());
		oldState.setEndPoint(original.getEndPoint());
		oldState.setBorder_Color(original.getBorder_Color());
		oldState.setSelected(original.isSelected());
		
	}

}
