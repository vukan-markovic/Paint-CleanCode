package logger;

import commands.*;
import shapes.*;
import hexagon.Hexagon;
import model.DrawingModel;
import stack.CommandsStack;

public class HexagonLogReader extends CircleLogReader {
	private DrawingModel model;
	private CommandsStack commandsStack;
	private CmdAdd cmdAdd;
	private CmdModifyHexagon cmdModifyHexagon;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private HexagonAdapter hexagon;

	public HexagonLogReader(DrawingModel model, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		setLogLine(logLine);
		readCenter();
		readShape();

		hexagon = new HexagonAdapter(new Hexagon(getxCoordinate(), getyCoordinate(), getRadius()), false,
				getBorderColor(), getFillColor());

		cmdAdd = new CmdAdd(model, hexagon);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
	}

	@Override
	public void modifyShapeFromLog(String[] logLine, Shape selectedShape) {
		setLogLine(logLine);
		HexagonAdapter oldState = (HexagonAdapter) selectedShape;
		readModifiedCenter();
		readModifiedCircle();

		hexagon = new HexagonAdapter(new Hexagon(getxCoordinate(), getyCoordinate(), getRadius()), true,
				getBorderColor(), getFillColor());

		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(hexagon);
		cmdModifyHexagon = new CmdModifyHexagon(oldState, hexagon);
		cmdModifyHexagon.execute();
		commandsStack.addCommand(cmdModifyHexagon);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		setLogLine(logLine);
		readCenter();
		readShape();
		
		hexagon = new HexagonAdapter(new Hexagon(getxCoordinate(), getyCoordinate(), getRadius()), false,
				getBorderColor(), getFillColor());

		cmdSelect = new CmdSelect(model, hexagon);
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		setLogLine(logLine);
		readCenter();
		readShape();
		
		hexagon = new HexagonAdapter(new Hexagon(getxCoordinate(), getyCoordinate(), getRadius()), false,
				getBorderColor(), getFillColor());

		cmdDeselect = new CmdDeselect(model, hexagon);
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdModifyHexagon getCmdModifyHexagon() {
		return cmdModifyHexagon;
	}

	public CmdSelect getCmdSelect() {
		return cmdSelect;
	}

	public CmdDeselect getCmdDeselect() {
		return cmdDeselect;
	}

	public HexagonAdapter getHexagon() {
		return hexagon;
	}
}