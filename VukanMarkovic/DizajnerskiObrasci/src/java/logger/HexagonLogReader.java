package logger;

import commands.*;
import commandsHandler.CommandsHandler;
import shapes.*;
import hexagon.Hexagon;
import model.DrawingModel;

public class HexagonLogReader extends CircleLogReader {
	private DrawingModel model;
	private CommandsHandler commandsHandler;
	private CmdAdd cmdAdd;
	private CmdModifyHexagon cmdModifyHexagon;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private HexagonAdapter hexagon;

	public HexagonLogReader(DrawingModel model, CommandsHandler commandsHandler) {
		this.model = model;
		this.commandsHandler = commandsHandler;
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
		commandsHandler.addExecutedCommand(cmdAdd);
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
		commandsHandler.addExecutedCommand(cmdModifyHexagon);
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
		commandsHandler.addExecutedCommand(cmdSelect);
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
		commandsHandler.addExecutedCommand(cmdDeselect);
	}

	@Override
	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdModifyHexagon getCmdModifyHexagon() {
		return cmdModifyHexagon;
	}

	@Override
	public CmdSelect getCmdSelect() {
		return cmdSelect;
	}

	@Override
	public CmdDeselect getCmdDeselect() {
		return cmdDeselect;
	}

	public HexagonAdapter getHexagon() {
		return hexagon;
	}
}