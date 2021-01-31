package logger;

import commands.*;
import commandsHandler.CommandsHandler;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;

public class LineLogReader implements LogReader {
	private DrawingModel model;
	private CommandsHandler commandsHandler;
	private String[] logLine;
	private CmdAdd cmdAdd;
	private CmdModifyLine cmdModifyLine;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private Point startPoint;
	private int xCoordinateOfStartPoint;
	private int yCoordinateOfStartPoint;
	private Color startPointColor;
	private Point endPoint;
	private int xCoordinateOfEndPoint;
	private int yCoordinateOfEndPoint;
	private Color endPointColor;
	private Color lineColor;
	private Line line;

	public LineLogReader(DrawingModel model, CommandsHandler commandsHandler) {
		this.model = model;
		this.commandsHandler = commandsHandler;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readStartPoint();
		readEndPoint();
		readLine();
		cmdAdd = new CmdAdd(model, line);
		cmdAdd.execute();
		commandsHandler.addExecutedCommand(cmdAdd);
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
		commandsHandler.addExecutedCommand(cmdModifyLine);
	}

	private void readModifiedStartPoint() {
		xCoordinateOfStartPoint = Integer.parseInt(logLine[36]);
		yCoordinateOfStartPoint = Integer.parseInt(logLine[39]);
		startPointColor = new Color(Integer.parseInt(logLine[43]));
		startPoint = new Point(xCoordinateOfStartPoint, yCoordinateOfStartPoint, true, startPointColor);
	}

	private void readModifiedEndPoint() {
		xCoordinateOfEndPoint = Integer.parseInt(logLine[48]);
		yCoordinateOfEndPoint = Integer.parseInt(logLine[51]);
		endPointColor = new Color(Integer.parseInt(logLine[55]));
		endPoint = new Point(xCoordinateOfEndPoint, yCoordinateOfEndPoint, true, endPointColor);
	}

	private void readModifiedLine() {
		lineColor = new Color(Integer.parseInt(logLine[59]));
		line = new Line(startPoint, endPoint, true, lineColor);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readStartPoint();
		readEndPoint();
		readLine();
		cmdSelect = new CmdSelect(model, line);
		cmdSelect.execute();
		commandsHandler.addExecutedCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readStartPoint();
		readEndPoint();
		readLine();
		cmdDeselect = new CmdDeselect(model, line);
		cmdDeselect.execute();
		commandsHandler.addExecutedCommand(cmdDeselect);
	}

	private void readStartPoint() {
		xCoordinateOfStartPoint = Integer.parseInt(logLine[6]);
		yCoordinateOfStartPoint = Integer.parseInt(logLine[9]);
		startPointColor = new Color(Integer.parseInt(logLine[13]));
		startPoint = new Point(xCoordinateOfStartPoint, yCoordinateOfStartPoint, false, startPointColor);
	}

	private void readEndPoint() {
		xCoordinateOfEndPoint = Integer.parseInt(logLine[18]);
		yCoordinateOfEndPoint = Integer.parseInt(logLine[21]);
		endPointColor = new Color(Integer.parseInt(logLine[25]));
		endPoint = new Point(xCoordinateOfEndPoint, yCoordinateOfEndPoint, false, endPointColor);
	}

	private void readLine() {
		lineColor = new Color(Integer.parseInt(logLine[29]));
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