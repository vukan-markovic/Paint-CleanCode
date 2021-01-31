package commandsExecutors;

import commands.*;
import commandsHandler.CommandsHandler;
import shapes.*;
import java.awt.Color;
import controller.OptionsController;
import dialogs.DialogLine;
import logger.LogWriter;
import model.DrawingModel;

public class LineCommandsExecutor implements ShapeCommandsExecutor {
	private DialogLine dialogLine;
	private OptionsController optionsController;
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsHandler commandsHandler;
	private CmdAdd cmdAdd;
	private CmdModifyLine cmdModifyLine;
	private Color color;
	private Point startPoint;
	private Line line;

	public LineCommandsExecutor(DialogLine dialogLine, OptionsController optionsController) {
		this.dialogLine = dialogLine;
		this.optionsController = optionsController;
		model = optionsController.getModel();
		logWriter = new LogWriter(optionsController.getFrame());
		commandsHandler = optionsController.getCommandsHandler();
	}

	@Override
	public void addShape(int xCoordinate, int yCoordinate) {
		color = optionsController.getBorderColor();

		if (startPoint == null)
			startPoint = new Point(xCoordinate, yCoordinate, false, color);
		else
			addLine(xCoordinate, yCoordinate);
	}

	private void addLine(int xCoordinate, int yCoordinate) {
		Point endPoint = new Point(xCoordinate, yCoordinate, false, color);
		line = new Line(startPoint, endPoint, false, color);

		cmdAdd = new CmdAdd(model, line);
		cmdAdd.execute();
		commandsHandler.addExecutedCommand(cmdAdd);
		logWriter.logAddCommand(line);
		startPoint = null;
	}

	@Override
	public void modifyShape(Shape selectedShape) {
		Line oldState = (Line) selectedShape;
		int xCoordinateOfStartPoint = dialogLine.getXcoordinateValue();
		int yCoordinateOfStartPoint = dialogLine.getYcoordinateValue();
		int xCoordinateOfEndPoint = dialogLine.getXCoordinateOfEndPointValue();
		int yCoordinateOfEndPoint = dialogLine.getYCoordinateOfEndPointValue();
		color = dialogLine.getBorderColor();
		Point startPoint = new Point(xCoordinateOfStartPoint, yCoordinateOfStartPoint, true, color);
		Point endPoint = new Point(xCoordinateOfEndPoint, yCoordinateOfEndPoint, true, color);
		line = new Line(startPoint, endPoint, true, color);

		logWriter.logModifyCommand(oldState, line);
		cmdModifyLine = new CmdModifyLine(oldState, line);
		cmdModifyLine.execute();
		commandsHandler.addExecutedCommand(cmdModifyLine);
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdModifyLine getCmdModifyLine() {
		return cmdModifyLine;
	}

	public Line getLine() {
		return line;
	}
}