package command;

import geometry.HexagonAdapter;

public class CmdModifyHexagon implements Command{
	
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original = new HexagonAdapter();
	
	

	public CmdModifyHexagon(HexagonAdapter oldState, HexagonAdapter newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		
		original = oldState.clone();
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setR(newState.getR());
		oldState.setBorderColor(newState.getBorder_Color());
		oldState.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {

		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setR(original.getR());
		oldState.setBorderColor(original.getBorder_Color());
		oldState.setInnerColor(original.getInnerColor());
		
	}

}
