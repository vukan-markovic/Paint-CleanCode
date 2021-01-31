package logger;

import commands.*;
import commandsHandler.CommandsHandler;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;

public class RectangleLogReader implements LogReader {
	private DrawingModel model;
	private CommandsHandler commandsHandler;
	private String[] logLine;
	private CmdAdd cmdAdd;
	private CmdModifyRectangle cmdModifyRectangle;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private int xCoordinate;
	private int yCoordinate;
	private Point upperLeftPoint;
	private Color upperLeftPointColor;
	private int width;
	private int height;
	private Color borderColor;
	private Color fillColor;
	private Rectangle rectangle;

	public RectangleLogReader(DrawingModel model, CommandsHandler commandsHandler) {
		this.model = model;
		this.commandsHandler = commandsHandler;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readCenter();
		readRectangle();
		cmdAdd = new CmdAdd(model, rectangle);
		cmdAdd.execute();
		commandsHandler.addExecutedCommand(cmdAdd);
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
		commandsHandler.addExecutedCommand(cmdModifyRectangle);
	}

	private void readModifiedCenter() {
		xCoordinate = Integer.parseInt(logLine[36]);
		yCoordinate = Integer.parseInt(logLine[39]);
		upperLeftPointColor = new Color(Integer.parseInt(logLine[43]));
		upperLeftPoint = new Point(xCoordinate, yCoordinate, true, upperLeftPointColor);
	}

	private void readModifiedRectangle() {
		height = Integer.parseInt(logLine[46]);
		width = Integer.parseInt(logLine[49]);
		borderColor = new Color(Integer.parseInt(logLine[53]));
		fillColor = new Color(Integer.parseInt(logLine[57]));
		rectangle = new Rectangle(upperLeftPoint, height, width, true, borderColor, fillColor);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readCenter();
		readRectangle();
		cmdSelect = new CmdSelect(model, rectangle);
		cmdSelect.execute();
		commandsHandler.addExecutedCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		this.logLine = logLine;
		readCenter();
		readRectangle();
		cmdDeselect = new CmdDeselect(model, rectangle);
		cmdDeselect.execute();
		commandsHandler.addExecutedCommand(cmdDeselect);
	}

	private void readCenter() {
		xCoordinate = Integer.parseInt(logLine[7]);
		yCoordinate = Integer.parseInt(logLine[10]);
		upperLeftPointColor = new Color(Integer.parseInt(logLine[14]));
		upperLeftPoint = new Point(xCoordinate, yCoordinate, false, upperLeftPointColor);
	}

	private void readRectangle() {
		height = Integer.parseInt(logLine[17]);
		width = Integer.parseInt(logLine[20]);
		borderColor = new Color(Integer.parseInt(logLine[24]));
		fillColor = new Color(Integer.parseInt(logLine[28]));
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