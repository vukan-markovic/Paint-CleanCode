package logger;

import commands.*;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;
import stack.CommandsStack;

public class RectangleLogReader implements LogReader {
	private DrawingModel model;
	private CommandsStack commandsStack;
	private CmdAdd cmdAdd;
	private CmdModifyRectangle cmdModifyRectangle;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private int xCoordinate;
	private int yCoordinate;
	private String[] logLine;
	private Point upperLeftPoint;
	private int width;
	private int height;
	private int borderColorNumber;
	private Color borderColor;
	private int fillColorNumber;
	private Color fillColor;
	private Rectangle rectangle;
	private int upperLeftPointColorNumber;
	private Color upperLeftPointColor;

	public RectangleLogReader(DrawingModel model, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readCenter();
		readRectangle();
		cmdAdd = new CmdAdd(model, rectangle);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
	}

	@Override
	public void modifyShapeFromLog(String[] logLine, Shape selectedShape) {
		this.logLine = logLine;
		Rectangle oldState = (Rectangle) selectedShape;
		readModifiedCenter();
		readModifiedRectangle();
		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(rectangle);
		cmdModifyRectangle = new CmdModifyRectangle(oldState, rectangle);
		cmdModifyRectangle.execute();
		commandsStack.addCommand(cmdModifyRectangle);
	}

	private void readModifiedCenter() {
		xCoordinate = Integer.parseInt(logLine[36]);
		yCoordinate = Integer.parseInt(logLine[39]);
		upperLeftPointColorNumber = Integer.parseInt(logLine[43]);

		if (upperLeftPointColorNumber == 0)
			upperLeftPointColor = new Color(0, 0, 0, 0);
		else
			upperLeftPointColor = new Color(upperLeftPointColorNumber);

		upperLeftPoint = new Point(xCoordinate, yCoordinate, false, upperLeftPointColor);
	}

	private void readModifiedRectangle() {
		height = Integer.parseInt(logLine[46]);
		width = Integer.parseInt(logLine[49]);

		borderColorNumber = Integer.parseInt(logLine[53]);

		if (borderColorNumber == 0)
			borderColor = new Color(0, 0, 0, 0);
		else
			borderColor = new Color(borderColorNumber);

		fillColorNumber = Integer.parseInt(logLine[57]);

		if (fillColorNumber == 0)
			fillColor = new Color(0, 0, 0, 0);
		else
			fillColor = new Color(fillColorNumber);

		rectangle = new Rectangle(upperLeftPoint, height, width, false, borderColor, fillColor);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readCenter();
		readRectangle();
		cmdSelect = new CmdSelect(model, rectangle);
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readCenter();
		readRectangle();
		cmdDeselect = new CmdDeselect(model, rectangle);
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
	}

	private void readCenter() {
		xCoordinate = Integer.parseInt(logLine[7]);
		yCoordinate = Integer.parseInt(logLine[10]);
		upperLeftPointColorNumber = Integer.parseInt(logLine[14]);

		if (upperLeftPointColorNumber == 0)
			upperLeftPointColor = new Color(0, 0, 0, 0);
		else
			upperLeftPointColor = new Color(upperLeftPointColorNumber);

		upperLeftPoint = new Point(xCoordinate, yCoordinate, false, upperLeftPointColor);
	}

	private void readRectangle() {
		height = Integer.parseInt(logLine[17]);
		width = Integer.parseInt(logLine[20]);

		borderColorNumber = Integer.parseInt(logLine[24]);

		if (borderColorNumber == 0)
			borderColor = new Color(0, 0, 0, 0);
		else
			borderColor = new Color(borderColorNumber);

		fillColorNumber = Integer.parseInt(logLine[28]);

		if (fillColorNumber == 0)
			fillColor = new Color(0, 0, 0, 0);
		else
			fillColor = new Color(fillColorNumber);

		rectangle = new Rectangle(upperLeftPoint, height, width, false, borderColor, fillColor);
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdModifyRectangle getCmdModifyRectangle() {
		return cmdModifyRectangle;
	}

	public CmdSelect getCmdSelect() {
		return cmdSelect;
	}

	public CmdDeselect getCmdDeselect() {
		return cmdDeselect;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
}