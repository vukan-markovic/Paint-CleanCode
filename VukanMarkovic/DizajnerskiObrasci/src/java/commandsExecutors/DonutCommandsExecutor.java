package commandsExecutors;

import commands.*;
import shapes.*;
import controller.OptionsController;
import java.awt.Color;
import dialogs.DialogDonut;
import frame.DrawingFrame;
import logger.LogWriter;
import model.DrawingModel;
import stack.CommandsStack;

public class DonutCommandsExecutor implements SurfaceShapeCommandsExecutor {
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsStack commandsStack;
	private DialogDonut dialogDonut;
	private OptionsController optionsController;
	private CmdAdd cmdAdd;
	private CmdModifyDonut cmdModifyDonut;
	private boolean isSelected;
	private Color outerColor;
	private Color innerColor;
	private Donut donut;

	public DonutCommandsExecutor(DrawingModel model, DrawingFrame drawingFrame, CommandsStack commandsStack,
			DialogDonut dialogDonut, OptionsController optionsController) {
		this.model = model;
		logWriter = new LogWriter(drawingFrame);
		this.commandsStack = commandsStack;
		this.dialogDonut = dialogDonut;
		this.optionsController = optionsController;
	}

	@Override
	public void addShape() {
		isSelected = false;
		outerColor = optionsController.getOuterColor();
		innerColor = optionsController.getInnerColor();
		setDonut();

		cmdAdd = new CmdAdd(model, donut);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
		logWriter.logAddCommand(donut);
	}

	@Override
	public void modifyShape(Shape selectedShape) {
		Donut oldState = (Donut) selectedShape;
		isSelected = true;
		outerColor = dialogDonut.getOuterColor();
		innerColor = dialogDonut.getInnerColor();
		setDonut();

		logWriter.logModifyCommand(oldState, donut);
		cmdModifyDonut = new CmdModifyDonut(oldState, donut);
		cmdModifyDonut.execute();
		commandsStack.addCommand(cmdModifyDonut);
	}

	private void setDonut() {
		int xCoordinate = dialogDonut.getXcoordinateValue();
		int yCoordinate = dialogDonut.getYcoordinateValue();
		int radius = dialogDonut.getRadiusValue();
		int innerRadius = dialogDonut.getInnerRadiusValue();
		Point center = new Point(xCoordinate, yCoordinate, isSelected, outerColor);
		donut = new Donut(center, radius, innerRadius, isSelected, outerColor, innerColor);
	}

	public CmdModifyDonut getCmdModifyDonut() {
		return cmdModifyDonut;
	}

	public void setCmdModifyDonut(CmdModifyDonut cmdModifyDonut) {
		this.cmdModifyDonut = cmdModifyDonut;
	}
}