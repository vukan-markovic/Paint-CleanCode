package logger;

import commands.*;
import commandsHandler.CommandsHandler;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;

public class CircleLogReader implements LogReader {
	private DrawingModel model;
	private CommandsHandler commandsHandler;
	private String[] logLine;
	private CmdAdd cmdAdd;
	private CmdModifyCircle cmdModifyCircle;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private int xCoordinate;
	private int yCoordinate;
	private Color centerColor;
	private Point center;
	private int radius;
	private Color borderColor;
	private Color fillColor;
	private Circle circle;

	public CircleLogReader() {
	}

	public CircleLogReader(DrawingModel model, CommandsHandler commandsHandler) {
		this.model = model;
		this.commandsHandler = commandsHandler;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readCenter();
		readShape();
		circle = new Circle(center, radius, false, borderColor, fillColor);
		cmdAdd = new CmdAdd(model, circle);
		cmdAdd.execute();
		commandsHandler.addExecutedCommand(cmdAdd);
	}

	@Override
	public void modifyShapeFromLog(String[] logLine, Shape selectedShape) {
		this.logLine = logLine;
		Circle oldState = (Circle) selectedShape;
		readModifiedCenter();
		readModifiedCircle();
		circle = new Circle(center, radius, true, borderColor, fillColor);
		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(circle);
		cmdModifyCircle = new CmdModifyCircle(oldState, circle);
		cmdModifyCircle.execute();
		commandsHandler.addExecutedCommand(cmdModifyCircle);
	}

	protected void readModifiedCenter() {
		xCoordinate = Integer.parseInt(logLine[29]);
		yCoordinate = Integer.parseInt(logLine[32]);
		centerColor = new Color(Integer.parseInt(logLine[36]));
		center = new Point(xCoordinate, yCoordinate, true, centerColor);
	}

	protected void readModifiedCircle() {
		radius = Integer.parseInt(logLine[39]);
		borderColor = new Color(Integer.parseInt(logLine[43]));
		fillColor = new Color(Integer.parseInt(logLine[47]));
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readCenter();
		readShape();
		circle = new Circle(center, radius, false, borderColor, fillColor);
		cmdSelect = new CmdSelect(model, circle);
		cmdSelect.execute();
		commandsHandler.addExecutedCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readCenter();
		readShape();
		circle = new Circle(center, radius, true, borderColor, fillColor);
		cmdDeselect = new CmdDeselect(model, circle);
		cmdDeselect.execute();
		commandsHandler.addExecutedCommand(cmdDeselect);
	}

	protected void readCenter() {
		xCoordinate = Integer.parseInt(logLine[5]);
		yCoordinate = Integer.parseInt(logLine[8]);
		centerColor = new Color(Integer.parseInt(logLine[12]));
		center = new Point(xCoordinate, yCoordinate, false, centerColor);
	}

	protected void readShape() {
		radius = Integer.parseInt(logLine[15]);
		borderColor = new Color(Integer.parseInt(logLine[19]));
		fillColor = new Color(Integer.parseInt(logLine[23]));
	}

	public String[] getLogLine() {
		return logLine;
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdModifyCircle getCmdModifyCircle() {
		return cmdModifyCircle;
	}

	public CmdSelect getCmdSelect() {
		return cmdSelect;
	}

	public CmdDeselect getCmdDeselect() {
		return cmdDeselect;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public Point getCenter() {
		return center;
	}

	public int getRadius() {
		return radius;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setLogLine(String[] logLine) {
		this.logLine = logLine;
	}
}