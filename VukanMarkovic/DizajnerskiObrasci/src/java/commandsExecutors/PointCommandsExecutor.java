package commandsExecutors;

import commands.*;
import commandsHandler.CommandsHandler;
import shapes.*;
import controller.OptionsController;
import dialogs.DialogPoint;
import java.awt.Color;
import logger.LogWriter;
import model.DrawingModel;

public class PointCommandsExecutor implements ShapeCommandsExecutor {
	private DialogPoint dialogPoint;
	private OptionsController optionsController;
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsHandler commandsHandler;
	private CmdAdd cmdAdd;
	private CmdModifyPoint cmdModifyPoint;
	private Point point;

	public PointCommandsExecutor(DialogPoint dialogPoint, OptionsController optionsController) {
		this.dialogPoint = dialogPoint;
		this.optionsController = optionsController;
		model = optionsController.getModel();
		logWriter = new LogWriter(optionsController.getFrame());
		commandsHandler = optionsController.getCommandsHandler();
	}

	@Override
	public void addShape(int xCoordinate, int yCoordinate) {
		Color color = optionsController.getBorderColor();
		point = new Point(xCoordinate, yCoordinate, false, color);

		cmdAdd = new CmdAdd(model, point);
		cmdAdd.execute();
		commandsHandler.addExecutedCommand(cmdAdd);
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
		commandsHandler.addExecutedCommand(cmdModifyPoint);
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdModifyPoint getCmdModifyPoint() {
		return cmdModifyPoint;
	}

	public Point getPoint() {
		return point;
	}
}