package command;

import geometry.HexagonAdapter;

public class CmdModifyHexagon implements Command {
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original;

	public CmdModifyHexagon(HexagonAdapter oldState, HexagonAdapter newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setR(newState.getR());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setFillColor(newState.getFillColor());
	}

	@Override
	public void unexecute() {
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setR(original.getR());
		oldState.setBorderColor(original.getBorderColor());
		oldState.setFillColor(original.getFillColor());
	}
}