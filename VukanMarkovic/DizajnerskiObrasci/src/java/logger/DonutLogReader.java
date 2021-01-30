package logger;

import commands.*;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;
import stack.CommandsStack;

public class DonutLogReader extends CircleLogReader {
	private CmdAdd cmdAdd;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private DrawingModel model;
	private CmdModifyDonut cmdModifyDonut;
	private CommandsStack commandsStack;
	private int radius;
	private int innerRadius;
	private int borderColorNumber;
	private Color borderColor;
	private int fillColorNumber;
	private Color fillColor;
	private Donut donut;
	private int xCoordinate;
	private int yCoordinate;
	private int centerColorNumber;
	private Color centerColor;
	private Point center;

	public DonutLogReader(DrawingModel model, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		setLogLine(logLine);
		readCenter();
		readDonut();
		cmdAdd = new CmdAdd(model, donut);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
	}

	@Override
	public void modifyShapeFromLog(String[] logLine, Shape selectedShape) {
		setLogLine(logLine);
		Donut oldState = (Donut) selectedShape;
		readModifiedCenter();
		readModifiedDonut();
		model.getSelectedShapes().remove(oldState);
		model.getSelectedShapes().add(donut);
		cmdModifyDonut = new CmdModifyDonut(oldState, donut);
		cmdModifyDonut.execute();
		commandsStack.addCommand(cmdModifyDonut);
	}

	@Override
	protected void readModifiedCenter() {
		xCoordinate = Integer.parseInt(getLogLine()[41]);
		yCoordinate = Integer.parseInt(getLogLine()[44]);
		centerColorNumber = Integer.parseInt(getLogLine()[48]);

		if (centerColorNumber == 0)
			centerColor = new Color(0, 0, 0, 0);
		else
			centerColor = new Color(centerColorNumber);

		center = new Point(xCoordinate, yCoordinate, true, centerColor);
	}

	private void readModifiedDonut() {
		radius = Integer.parseInt(getLogLine()[51]);
		innerRadius = Integer.parseInt(getLogLine()[63]);
		borderColorNumber = Integer.parseInt(getLogLine()[67]);

		if (borderColorNumber == 0)
			borderColor = new Color(0, 0, 0, 0);
		else
			borderColor = new Color(borderColorNumber);

		fillColorNumber = Integer.parseInt(getLogLine()[71]);

		if (fillColorNumber == 0)
			fillColor = new Color(0, 0, 0, 0);
		else
			fillColor = new Color(fillColorNumber);

		donut = new Donut(center, radius, innerRadius, true, borderColor, fillColor);
	}

	@Override
	public void selectShapeFromLog(String[] logLine) {
		setLogLine(logLine);
		readCenter();
		readShape();
		donut = new Donut(getCenter(), radius, innerRadius, false, borderColor, fillColor);
		cmdSelect = new CmdSelect(model, donut);
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		setLogLine(logLine);
		readCenter();
		readShape();
		donut = new Donut(getCenter(), radius, innerRadius, true, borderColor, fillColor);
		cmdDeselect = new CmdDeselect(model, donut);
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
	}

	private void readDonut() {
		radius = Integer.parseInt(getLogLine()[15]);
		innerRadius = Integer.parseInt(getLogLine()[27]);
		borderColorNumber = Integer.parseInt(getLogLine()[31]);

		if (borderColorNumber == 0)
			borderColor = new Color(0, 0, 0, 0);
		else
			borderColor = new Color(borderColorNumber);

		fillColorNumber = Integer.parseInt(getLogLine()[35]);

		if (fillColorNumber == 0)
			fillColor = new Color(0, 0, 0, 0);
		else
			fillColor = new Color(fillColorNumber);

		donut = new Donut(getCenter(), radius, innerRadius, false, borderColor, fillColor);
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdSelect getCmdSelect() {
		return cmdSelect;
	}

	public CmdDeselect getCmdDeselect() {
		return cmdDeselect;
	}

	public CmdModifyDonut getCmdModifyDonut() {
		return cmdModifyDonut;
	}

	public Donut getDonut() {
		return donut;
	}
}