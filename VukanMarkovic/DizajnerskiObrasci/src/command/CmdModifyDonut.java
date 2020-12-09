package command;

import geometry.Donut;

public class CmdModifyDonut implements Command{

	Donut oldState;
	Donut newState;
	Donut original = new Donut();
	
	
	
	public CmdModifyDonut(Donut oldState, Donut newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		oldState.setCenter(newState.getCenter());
		try {
			oldState.setRadius(newState.getRadius());
			oldState.setInnerRadius(newState.getInnerRadius());
		}catch (Exception e ) {
			e.printStackTrace();
		}
		
		oldState.setBorder_Color(newState.getBorder_Color());
		oldState.setFill_Color(newState.getFill_Color());
		oldState.setSelected(newState.isSelected());
		
	}

	@Override
	public void unexecute() {
		oldState.setCenter(original.getCenter());
		try {
			oldState.setRadius(original.getRadius());
			oldState.setInnerRadius(original.getInnerRadius());
		}catch (Exception e ) {
			e.printStackTrace();
		}
		
		oldState.setBorder_Color(original.getBorder_Color());
		oldState.setFill_Color(original.getFill_Color());
		oldState.setSelected(original.isSelected());
		
	}

	
}
