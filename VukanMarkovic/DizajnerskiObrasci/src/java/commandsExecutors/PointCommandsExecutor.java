package commandsExecutors;

import commands.*;
import shapes.*;
import controller.OptionsController;
import dialogs.DialogPoint;
import java.awt.Color;
import logger.LogWriter;
import model.DrawingModel;
import stack.CommandsStack;

public class PointCommandsExecutor implements ShapeCommandsExecutor {
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsStack commandsStack;
	private DialogPoint dialogPoint;
	private OptionsController optionsController;
	private CmdAdd cmdAdd;
	private CmdModifyPoint cmdModifyPoint;
	private Point point;

	public PointCommandsExecutor(DialogPoint dialogPoint, OptionsController optionsController) {
		this.model = optionsController.getModel();
		logWriter = new LogWriter(optionsController.getFrame());
		this.commandsStack = optionsController.getCommandsStack();
		this.dialogPoint = dialogPoint;
		this.optionsController = optionsController;
	}

	@Override
	public void addShape(int xCoordinate, int yCoordinate) {
		Color color = optionsController.getBorderColor();
		point = new Point(xCoordinate, yCoordinate, false, color);
		cmdAdd = new CmdAdd(model, point);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
		logWriter.logAddCommand(point);
	}

	@Override
	public void modifyShape(Shape selectedShape) {
		Point oldState = (Point) selectedShape;
		int xCoordinate = dialogPoint.getXcoordinateValue();
		int yCoordinate = dialogPoint.getYcoordinateValue();
		Color color = dialogPoint.getBorderColor();
		point = new Point(xCoordinate, yCoordinate, true, color);
		logWriter.logModifyCommand(oldState, point);
		cmdModifyPoint = new CmdModifyPoint(oldState, point);
		cmdModifyPoint.execute();
		commandsStack.addCommand(cmdModifyPoint);
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public Point getPoint() {
		return point;
	}

	public CmdModifyPoint getCmdModifyPoint() {
		return cmdModifyPoint;
	}
}