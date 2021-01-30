package commandsExecutors;

import commands.*;
import shapes.*;
import java.awt.Color;
import controller.OptionsController;
import dialogs.DialogCircle;
import logger.LogWriter;
import model.DrawingModel;
import stack.CommandsStack;

public class CircleCommandsExecutor implements SurfaceShapeCommandsExecutor {
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsStack commandsStack;
	private DialogCircle dialogCircle;
	private OptionsController optionsController;
	private CmdAdd cmdAdd;
	private CmdModifyCircle cmdModifyCircle;
	private boolean isSelected;
	private Color outerColor;
	private Color innerColor;
	private Circle circle;

	public CircleCommandsExecutor(DialogCircle dialogCircle, OptionsController optionsController) {
		this.model = optionsController.getModel();
		logWriter = new LogWriter(optionsController.getFrame());
		this.commandsStack = optionsController.getCommandsStack();
		this.dialogCircle = dialogCircle;
		this.optionsController = optionsController;
	}

	@Override
	public void addShape() {
		isSelected = false;
		outerColor = optionsController.getBorderColor();
		innerColor = optionsController.getFillColor();
		createCircle();

		cmdAdd = new CmdAdd(model, circle);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
		logWriter.logAddCommand(circle);
	}

	@Override
	public void modifyShape(Shape selectedShape) {
		Circle oldState = (Circle) selectedShape;
		isSelected = true;
		outerColor = dialogCircle.getBorderColor();
		innerColor = dialogCircle.getFillColor();
		createCircle();

		logWriter.logModifyCommand(oldState, circle);
		cmdModifyCircle = new CmdModifyCircle(oldState, circle);
		cmdModifyCircle.execute();
		commandsStack.addCommand(cmdModifyCircle);
	}

	private void createCircle() {
		int xCoordinate = dialogCircle.getXcoordinateValue();
		int yCoordinate = dialogCircle.getYcoordinateValue();
		int radius = dialogCircle.getRadiusValue();
		Point center = new Point(xCoordinate, yCoordinate, isSelected, outerColor);
		circle = new Circle(center, radius, isSelected, outerColor, innerColor);
	}
	
	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public Circle getCircle() {
		return circle;
	}

	public CmdModifyCircle getCmdModifyCircle() {
		return cmdModifyCircle;
	}
}