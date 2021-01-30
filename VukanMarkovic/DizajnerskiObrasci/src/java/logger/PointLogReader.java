package logger;

import commands.*;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;
import stack.CommandsStack;

public class PointLogReader implements LogReader {
	private DrawingModel model;
	private CommandsStack commandsStack;
	private CmdAdd cmdAdd;
	private CmdModifyPoint cmdModifyPoint;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private int xCoordinate;
	private int yCoordinate;
	private int pointColorNumber;
	private Color pointColor;
	private String[] logLine;
	private Point point;

	public PointLogReader(DrawingModel model, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readPoint();
		cmdAdd = new CmdAdd(model, point);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
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
		commandsStack.addCommand(cmdModifyPoint);
	}

	private void readModifiedPoint() {
		xCoordinate = Integer.parseInt(logLine[17]);
		yCoordinate = Integer.parseInt(logLine[20]);
		pointColorNumber = Integer.parseInt(logLine[24]);

		if (pointColorNumber == 0)
			pointColor = new Color(0, 0, 0, 0);
		else
			pointColor = new Color(pointColorNumber);

		point = new Point(xCoordinate, yCoordinate, true, pointColor);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readPoint();
		cmdSelect = new CmdSelect(model, point);
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readPoint();
		cmdDeselect = new CmdDeselect(model, point);
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
	}

	private void readPoint() {
		xCoordinate = Integer.parseInt(logLine[4]);
		yCoordinate = Integer.parseInt(logLine[7]);
		pointColorNumber = Integer.parseInt(logLine[11]);

		if (pointColorNumber == 0)
			pointColor = new Color(0, 0, 0, 0);
		else
			pointColor = new Color(pointColorNumber);

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