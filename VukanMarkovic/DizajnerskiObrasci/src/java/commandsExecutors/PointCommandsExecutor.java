package commandsExecutors;

import commands.*;
import shapes.*;
import controller.OptionsController;
import dialogs.DialogPoint;
import java.awt.Color;
import java.awt.event.MouseEvent;
import frame.DrawingFrame;
import logger.LogWriter;
import model.DrawingModel;
import stack.CommandsStack;

public class PointCommandsExecutor {
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsStack commandsStack;
	private DialogPoint dialogPoint;
	private OptionsController optionsController;
	private CmdAdd cmdAdd;
	private CmdModifyPoint cmdModifyPoint;

	public PointCommandsExecutor(DrawingModel model, DrawingFrame drawingFrame, CommandsStack commandsStack,
			DialogPoint dialogPoint, OptionsController optionsController) {
		this.model = model;
		logWriter = new LogWriter(drawingFrame);
		this.commandsStack = commandsStack;
		this.dialogPoint = dialogPoint;
		this.optionsController = optionsController;
	}

	public void addPoint(MouseEvent click) {
		Color color = optionsController.getOuterColor();
		Point point = new Point(click.getX(), click.getY(), false, color);
		cmdAdd = new CmdAdd(model, point);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
		logWriter.logAddCommand(point);
	}

	public void modifyPoint(Shape selectedShape) {
		Point oldState = (Point) selectedShape;
		int xCoordinate = dialogPoint.getXcoordinateValue();
		int yCoordinate = dialogPoint.getYcoordinateValue();
		Color color = dialogPoint.getOuterColor();
		Point newState = new Point(xCoordinate, yCoordinate, true, color);
		logWriter.logModifyCommand(oldState, newState);
		cmdModifyPoint = new CmdModifyPoint(oldState, (Point) newState);
		cmdModifyPoint.execute();
		commandsStack.addCommand(cmdModifyPoint);
	}

	public CmdModifyPoint getCmdModifyPoint() {
		return cmdModifyPoint;
	}

	public void setCmdModifyPoint(CmdModifyPoint cmdModifyPoint) {
		this.cmdModifyPoint = cmdModifyPoint;
	}
}