package logger;

import commands.*;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;
import stack.CommandsStack;

public class CircleLogReader implements LogReader {
	private CmdAdd cmdAdd;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private DrawingModel model;
	private CmdModifyCircle cmdModifyCircle;
	private CommandsStack commandsStack;
	private int xCoordinate;
	private int yCoordinate;
	private String[] logLine;
	private Point center;
	private int radius;
	private int borderColorNumber;
	private Color borderColor;
	private int fillColorNumber;
	private Color fillColor;
	private Circle circle;
	private int centerColorNumber;
	private Color centerColor;

	public CircleLogReader() {
	}

	public CircleLogReader(DrawingModel model, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readCenter();
		readShape();
		circle = new Circle(center, radius, false, borderColor, fillColor);
		cmdAdd = new CmdAdd(model, circle);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
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
		commandsStack.addCommand(cmdModifyCircle);
	}

	protected void readModifiedCenter() {
		xCoordinate = Integer.parseInt(logLine[29]);
		yCoordinate = Integer.parseInt(logLine[32]);
		centerColorNumber = Integer.parseInt(logLine[36]);

		if (centerColorNumber == 0)
			centerColor = new Color(0, 0, 0, 0);
		else
			centerColor = new Color(centerColorNumber);

		center = new Point(xCoordinate, yCoordinate, true, centerColor);
	}

	protected void readModifiedCircle() {
		radius = Integer.parseInt(logLine[39]);
		borderColorNumber = Integer.parseInt(logLine[43]);

		if (borderColorNumber == 0)
			borderColor = new Color(0, 0, 0, 0);
		else
			borderColor = new Color(borderColorNumber);

		fillColorNumber = Integer.parseInt(logLine[47]);

		if (fillColorNumber == 0)
			fillColor = new Color(0, 0, 0, 0);
		else
			fillColor = new Color(fillColorNumber);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readCenter();
		readShape();
		circle = new Circle(center, radius, false, borderColor, fillColor);
		cmdSelect = new CmdSelect(model, circle);
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readCenter();
		readShape();
		circle = new Circle(center, radius, true, borderColor, fillColor);
		cmdDeselect = new CmdDeselect(model, circle);
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
	}

	protected void readCenter() {
		xCoordinate = Integer.parseInt(logLine[5]);
		yCoordinate = Integer.parseInt(logLine[8]);
		centerColorNumber = Integer.parseInt(logLine[12]);

		if (centerColorNumber == 0)
			centerColor = new Color(0, 0, 0, 0);
		else
			centerColor = new Color(centerColorNumber);

		center = new Point(xCoordinate, yCoordinate, false, centerColor);
	}

	protected void readShape() {
		radius = Integer.parseInt(logLine[15]);
		borderColorNumber = Integer.parseInt(logLine[19]);

		if (borderColorNumber == 0)
			borderColor = new Color(0, 0, 0, 0);
		else
			borderColor = new Color(borderColorNumber);

		fillColorNumber = Integer.parseInt(logLine[23]);

		if (fillColorNumber == 0)
			fillColor = new Color(0, 0, 0, 0);
		else
			fillColor = new Color(fillColorNumber);
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
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

	public CmdModifyCircle getCmdModifyCircle() {
		return cmdModifyCircle;
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public Circle getCircle() {
		return circle;
	}

	public CmdSelect getCmdSelect() {
		return cmdSelect;
	}

	public CmdDeselect getCmdDeselect() {
		return cmdDeselect;
	}

	public String[] getLogLine() {
		return logLine;
	}

	public Point getCenter() {
		return center;
	}

	public void setLogLine(String[] logLine) {
		this.logLine = logLine;
	}
}