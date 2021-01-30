package logger;

import commands.*;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;
import stack.CommandsStack;

public class LineLogReader implements LogReader {
	private DrawingModel model;
	private CommandsStack commandsStack;
	private CmdAdd cmdAdd;
	private CmdModifyLine cmdModifyLine;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private String[] logLine;
	private Point startPoint;
	private int xCoordinateOfStartPoint;
	private int yCoordinateOfStartPoint;
	private int startPointColorNumber;
	private Color startPointColor;
	private Point endPoint;
	private int xCoordinateOfEndPoint;
	private int yCoordinateOfEndPoint;
	private int endPointColorNumber;
	private Color endPointColor;
	private Line line;
	private int lineColorNumber;
	private Color lineColor;

	public LineLogReader(DrawingModel model, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readStartPoint();
		readEndPoint();
		readLine();
		cmdAdd = new CmdAdd(model, line);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
	}

	@Override
	public void modifyShapeFromLog(String[] logLine, Shape selectedShape) {
		this.logLine = logLine;
		Line oldState = (Line) selectedShape;
		readModifiedStartPoint();
		readModifiedEndPoint();
		readModifiedLine();
		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(line);
		cmdModifyLine = new CmdModifyLine(oldState, line);
		cmdModifyLine.execute();
		commandsStack.addCommand(cmdModifyLine);
	}

	private void readModifiedStartPoint() {
		xCoordinateOfStartPoint = Integer.parseInt(logLine[36]);
		yCoordinateOfStartPoint = Integer.parseInt(logLine[39]);
		startPointColorNumber = Integer.parseInt(logLine[43]);

		if (startPointColorNumber == 0)
			startPointColor = new Color(0, 0, 0, 0);
		else
			startPointColor = new Color(startPointColorNumber);

		startPoint = new Point(xCoordinateOfStartPoint, yCoordinateOfStartPoint, false, startPointColor);
	}

	private void readModifiedEndPoint() {
		xCoordinateOfEndPoint = Integer.parseInt(logLine[48]);
		yCoordinateOfEndPoint = Integer.parseInt(logLine[51]);

		endPointColorNumber = Integer.parseInt(logLine[55]);

		if (endPointColorNumber == 0)
			endPointColor = new Color(0, 0, 0, 0);
		else
			endPointColor = new Color(endPointColorNumber);

		endPoint = new Point(xCoordinateOfEndPoint, yCoordinateOfEndPoint, false, endPointColor);
	}

	private void readModifiedLine() {
		lineColorNumber = Integer.parseInt(logLine[59]);

		if (lineColorNumber == 0)
			lineColor = new Color(0, 0, 0, 0);
		else
			lineColor = new Color(lineColorNumber);

		line = new Line(startPoint, endPoint, false, lineColor);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readStartPoint();
		readEndPoint();
		readLine();
		cmdSelect = new CmdSelect(model, line);
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readStartPoint();
		readEndPoint();
		readLine();
		cmdDeselect = new CmdDeselect(model, line);
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
	}

	private void readStartPoint() {
		xCoordinateOfStartPoint = Integer.parseInt(logLine[6]);
		yCoordinateOfStartPoint = Integer.parseInt(logLine[9]);
		startPointColorNumber = Integer.parseInt(logLine[13]);

		if (startPointColorNumber == 0)
			startPointColor = new Color(0, 0, 0, 0);
		else
			startPointColor = new Color(startPointColorNumber);

		startPoint = new Point(xCoordinateOfStartPoint, yCoordinateOfStartPoint, false, startPointColor);
	}

	private void readEndPoint() {
		xCoordinateOfEndPoint = Integer.parseInt(logLine[18]);
		yCoordinateOfEndPoint = Integer.parseInt(logLine[21]);

		endPointColorNumber = Integer.parseInt(logLine[25]);

		if (endPointColorNumber == 0)
			endPointColor = new Color(0, 0, 0, 0);
		else
			endPointColor = new Color(endPointColorNumber);

		endPoint = new Point(xCoordinateOfEndPoint, yCoordinateOfEndPoint, false, endPointColor);
	}

	private void readLine() {
		lineColorNumber = Integer.parseInt(logLine[29]);

		if (lineColorNumber == 0)
			lineColor = new Color(0, 0, 0, 0);
		else
			lineColor = new Color(lineColorNumber);

		line = new Line(startPoint, endPoint, false, lineColor);
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdModifyLine getCmdModifyLine() {
		return cmdModifyLine;
	}

	public CmdSelect getCmdSelect() {
		return cmdSelect;
	}

	public CmdDeselect getCmdDeselect() {
		return cmdDeselect;
	}

	public Line getLine() {
		return line;
	}
}