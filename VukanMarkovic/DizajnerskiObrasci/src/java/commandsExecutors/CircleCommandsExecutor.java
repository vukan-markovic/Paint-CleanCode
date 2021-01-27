package commandsExecutors;

import commands.*;
import shapes.*;
import java.awt.Color;
import controller.OptionsController;
import dialogs.DialogCircle;
import frame.DrawingFrame;
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

	public CircleCommandsExecutor(DrawingModel model, DrawingFrame drawingFrame, CommandsStack commandsStack,
			DialogCircle dialogCircle, OptionsController optionsController) {
		this.model = model;
		logWriter = new LogWriter(drawingFrame);
		this.commandsStack = commandsStack;
		this.dialogCircle = dialogCircle;
		this.optionsController = optionsController;
	}

	@Override
	public void addShape() {
		isSelected = false;
		outerColor = optionsController.getOuterColor();
		innerColor = optionsController.getInnerColor();
		setCircle();

		cmdAdd = new CmdAdd(model, circle);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
		logWriter.logAddCommand(circle);
	}

	@Override
	public void modifyShape(Shape selectedShape) {
		Circle oldState = (Circle) selectedShape;
		isSelected = true;
		outerColor = dialogCircle.getOuterColor();
		innerColor = dialogCircle.getInnerColor();
		setCircle();

		logWriter.logModifyCommand(oldState, circle);
		cmdModifyCircle = new CmdModifyCircle(oldState, circle);
		cmdModifyCircle.execute();
		commandsStack.addCommand(cmdModifyCircle);
	}

	private void setCircle() {
		int xCoordinate = dialogCircle.getXcoordinateValue();
		int yCoordinate = dialogCircle.getYcoordinateValue();
		int radius = dialogCircle.getRadiusValue();
		Point center = new Point(xCoordinate, yCoordinate, isSelected, outerColor);
		circle = new Circle(center, radius, isSelected, outerColor, innerColor);
	}

	public CmdModifyCircle getCmdModifyCircle() {
		return cmdModifyCircle;
	}

	public void setCmdModifyCircle(CmdModifyCircle cmdModifyCircle) {
		this.cmdModifyCircle = cmdModifyCircle;
	}
}