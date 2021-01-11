package commands;

import shapes.HexagonAdapter;

public class CmdModifyHexagon implements Command {
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter originalState;

	public CmdModifyHexagon(HexagonAdapter oldState, HexagonAdapter newState) {
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

	private void setOldState(HexagonAdapter state) {
		oldState.setXcoordinate(state.getXcoordinate());
		oldState.setYcoordinate(state.getYcoordinate());
		oldState.setRadius(state.getRadius());
		oldState.setOuterColor(state.getInnerColor());
		oldState.setInnerColor(state.getInnerColor());
	}
}