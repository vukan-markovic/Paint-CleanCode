package logger;

import commands.*;
import commandsHandler.CommandsHandler;
import shapes.*;
import java.awt.Color;
import model.DrawingModel;

public class DonutLogReader extends CircleLogReader {
	private DrawingModel model;
	private CommandsHandler commandsHandler;
	private CmdAdd cmdAdd;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;
	private CmdModifyDonut cmdModifyDonut;
	private int xCoordinate;
	private int yCoordinate;
	private Color centerColor;
	private Point center;
	private int radius;
	private int innerRadius;
	private Color borderColor;
	private Color fillColor;
	private Donut donut;

	public DonutLogReader(DrawingModel model, CommandsHandler commandsHandler) {
		this.model = model;
		this.commandsHandler = commandsHandler;
	}

	@Override
	public void addShapeFromLog(String[] logLine) {
		setLogLine(logLine);
		readCenter();
		readDonut();
		cmdAdd = new CmdAdd(model, donut);
		cmdAdd.execute();
		commandsHandler.addExecutedCommand(cmdAdd);
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
		commandsHandler.addExecutedCommand(cmdModifyDonut);
	}

	@Override
	protected void readModifiedCenter() {
		xCoordinate = Integer.parseInt(getLogLine()[41]);
		yCoordinate = Integer.parseInt(getLogLine()[44]);
		centerColor = new Color(Integer.parseInt(getLogLine()[48]));
		center = new Point(xCoordinate, yCoordinate, true, centerColor);
	}

	private void readModifiedDonut() {
		radius = Integer.parseInt(getLogLine()[51]);
		innerRadius = Integer.parseInt(getLogLine()[63]);
		borderColor = new Color(Integer.parseInt(getLogLine()[67]));
		fillColor = new Color(Integer.parseInt(getLogLine()[71]));
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
		commandsHandler.addExecutedCommand(cmdSelect);
	}

	@Override
	public void deselectShapeFromLog(String[] logLine) {
		setLogLine(logLine);
		readCenter();
		readShape();
		donut = new Donut(getCenter(), radius, innerRadius, true, borderColor, fillColor);
		cmdDeselect = new CmdDeselect(model, donut);
		cmdDeselect.execute();
		commandsHandler.addExecutedCommand(cmdDeselect);
	}

	private void readDonut() {
		radius = Integer.parseInt(getLogLine()[15]);
		innerRadius = Integer.parseInt(getLogLine()[27]);
		borderColor = new Color(Integer.parseInt(getLogLine()[31]));
		fillColor = new Color(Integer.parseInt(getLogLine()[35]));
		donut = new Donut(getCenter(), radius, innerRadius, false, borderColor, fillColor);
	}

	@Override
	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdModifyDonut getCmdModifyDonut() {
		return cmdModifyDonut;
	}

	@Override
	public CmdSelect getCmdSelect() {
		return cmdSelect;
	}

	@Override
	public CmdDeselect getCmdDeselect() {
		return cmdDeselect;
	}

	public Donut getDonut() {
		return donut;
	}
}