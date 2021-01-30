package commandsExecutors;

import commands.*;
import shapes.*;
import java.awt.Color;
import controller.OptionsController;
import dialogs.DialogLine;
import logger.LogWriter;
import model.DrawingModel;
import stack.CommandsStack;

public class LineCommandsExecutor implements ShapeCommandsExecutor {
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsStack commandsStack;
	private DialogLine dialogLine;
	private OptionsController optionsController;
	private CmdAdd cmdAdd;
	private CmdModifyLine cmdModifyLine;
	private Color color;
	private Point startPoint;
	private Line line;

	public LineCommandsExecutor(DialogLine dialogLine, OptionsController optionsController) {
		this.model = optionsController.getModel();
		this.commandsStack = optionsController.getCommandsStack();
		this.dialogLine = dialogLine;
		this.optionsController = optionsController;
		logWriter = new LogWriter(optionsController.getFrame());
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
		commandsStack.addCommand(cmdAdd);
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
		line = new Line(startPoint, endPoint, false, color);

		logWriter.logModifyCommand(oldState, line);
		cmdModifyLine = new CmdModifyLine(oldState, (Line) line);
		cmdModifyLine.execute();
		commandsStack.addCommand(cmdModifyLine);
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public Line getLine() {
		return line;
	}

	public CmdModifyLine getCmdModifyLine() {
		return cmdModifyLine;
	}
}