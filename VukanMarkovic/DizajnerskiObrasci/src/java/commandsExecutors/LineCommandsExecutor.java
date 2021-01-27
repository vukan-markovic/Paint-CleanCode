package commandsExecutors;

import commands.*;
import shapes.*;
import java.awt.Color;
import controller.OptionsController;
import java.awt.event.MouseEvent;
import dialogs.DialogLine;
import frame.DrawingFrame;
import logger.LogWriter;
import model.DrawingModel;
import stack.CommandsStack;

public class LineCommandsExecutor {
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsStack commandsStack;
	private DialogLine dialogLine;
	private OptionsController optionsController;
	private CmdAdd cmdAdd;
	private CmdModifyLine cmdModifyLine;
	private Color color;
	private Point startPoint;

	public LineCommandsExecutor(DrawingModel model, DrawingFrame drawingFrame, CommandsStack commandsStack,
			DialogLine dialogLine, OptionsController optionsController) {
		this.model = model;
		this.commandsStack = commandsStack;
		this.dialogLine = dialogLine;
		this.optionsController = optionsController;
		logWriter = new LogWriter(drawingFrame);
	}

	public void addLineOrCreateStartPoint(MouseEvent click) {
		color = optionsController.getOuterColor();

		if (startPoint == null)
			startPoint = new Point(click.getX(), click.getY(), false, color);
		else
			addLine(click);
	}

	private void addLine(MouseEvent click) {
		Point endPoint = new Point(click.getX(), click.getY(), false, color);
		Line line = new Line(startPoint, endPoint, false, color);
		cmdAdd = new CmdAdd(model, line);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
		logWriter.logAddCommand(line);
		startPoint = null;
	}

	public void modifyLine(Shape selectedShape) {
		Line oldState = (Line) selectedShape;
		int xCoordinateOfStartPoint = dialogLine.getXcoordinateValue();
		int yCoordinateOfStartPoint = dialogLine.getYcoordinateValue();
		int xCoordinateOfEndPoint = dialogLine.getXCoordinateOfEndPointValue();
		int yCoordinateOfEndPoint = dialogLine.getYCoordinateOfEndPointValue();
		color = dialogLine.getOuterColor();
		Point startPoint = new Point(xCoordinateOfStartPoint, yCoordinateOfStartPoint, true, color);
		Point endPoint = new Point(xCoordinateOfEndPoint, yCoordinateOfEndPoint, true, color);
		Line newState = new Line(startPoint, endPoint, false, color);

		logWriter.logModifyCommand(oldState, newState);
		cmdModifyLine = new CmdModifyLine(oldState, (Line) newState);
		cmdModifyLine.execute();
		commandsStack.addCommand(cmdModifyLine);
	}

	public CmdModifyLine getCmdModifyLine() {
		return cmdModifyLine;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setCmdModifyLine(CmdModifyLine cmdModifyLine) {
		this.cmdModifyLine = cmdModifyLine;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
}