package logger;

import commands.*;
import commandsHandler.CommandsHandler;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;

public class PointLogReader implements LogReader {
	private DrawingModel model;
	private CommandsHandler commandsHandler;
	private String[] logLine;
	private CmdAdd cmdAdd;
	private CmdModifyPoint cmdModifyPoint;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private int xCoordinate;
	private int yCoordinate;
	private Color pointColor;
	private Point point;

	public PointLogReader(DrawingModel model, CommandsHandler commandsHandler) {
		this.model = model;
		this.commandsHandler = commandsHandler;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readPoint();
		cmdAdd = new CmdAdd(model, point);
		cmdAdd.execute();
		commandsHandler.addExecutedCommand(cmdAdd);
	}

	@Override
	public void modifyShapeFromLog(String[] logLine, Shape selectedShape) {
		this.logLine = logLine;
		Point oldState = (Point) selectedShape;
		readModifiedPoint();
		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(point);
		cmdModifyPoint = new CmdModifyPoint(oldState, point);
		cmdModifyPoint.execute();
		commandsHandler.addExecutedCommand(cmdModifyPoint);
	}

	private void readModifiedPoint() {
		xCoordinate = Integer.parseInt(logLine[17]);
		yCoordinate = Integer.parseInt(logLine[20]);
		pointColor = new Color(Integer.parseInt(logLine[24]));
		point = new Point(xCoordinate, yCoordinate, true, pointColor);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readPoint();
		cmdSelect = new CmdSelect(model, point);
		cmdSelect.execute();
		commandsHandler.addExecutedCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readPoint();
		cmdDeselect = new CmdDeselect(model, point);
		cmdDeselect.execute();
		commandsHandler.addExecutedCommand(cmdDeselect);
	}

	private void readPoint() {
		xCoordinate = Integer.parseInt(logLine[4]);
		yCoordinate = Integer.parseInt(logLine[7]);
		pointColor = new Color(Integer.parseInt(logLine[11]));
		point = new Point(xCoordinate, yCoordinate, false, pointColor);
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdModifyPoint getCmdModifyPoint() {
		return cmdModifyPoint;
	}

	public CmdSelect getCmdSelect() {
		return cmdSelect;
	}

	public CmdDeselect getCmdDeselect() {
		return cmdDeselect;
	}

	public Point getPoint() {
		return point;
	}
}