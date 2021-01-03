package commands;

import shapes.HexagonAdapter;

public class CmdModifyHexagon implements Command {
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter originalState;

	public CmdModifyHexagon(HexagonAdapter oldState, HexagonAdapter newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.setXcoordinate(newState.getXcoordinate());
		oldState.setYcoordinate(newState.getYcoordinate());
		oldState.setRadius(newState.getRadius());
		oldState.setOuterColor(newState.getInnerColor());
		oldState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		oldState.setXcoordinate(originalState.getXcoordinate());
		oldState.setYcoordinate(originalState.getYcoordinate());
		oldState.setRadius(originalState.getRadius());
		oldState.setOuterColor(originalState.getInnerColor());
		oldState.setInnerColor(originalState.getInnerColor());
	}
}