package commandsExecutors;

import commands.*;
import shapes.*;
import controller.OptionsController;
import java.awt.Color;
import dialogs.DialogDonut;
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

	public DonutCommandsExecutor(DialogDonut dialogDonut, OptionsController optionsController) {
		this.model = optionsController.getModel();
		logWriter = new LogWriter(optionsController.getFrame());
		this.commandsStack = optionsController.getCommandsStack();
		this.dialogDonut = dialogDonut;
		this.optionsController = optionsController;
	}

	@Override
	public void addShape() {
		isSelected = false;
		outerColor = optionsController.getBorderColor();
		innerColor = optionsController.getFillColor();
		createDonut();

		cmdAdd = new CmdAdd(model, donut);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
		logWriter.logAddCommand(donut);
	}

	@Override
	public void modifyShape(Shape selectedShape) {
		Donut oldState = (Donut) selectedShape;
		isSelected = true;
		outerColor = dialogDonut.getBorderColor();
		innerColor = dialogDonut.getFillColor();
		createDonut();

		logWriter.logModifyCommand(oldState, donut);
		cmdModifyDonut = new CmdModifyDonut(oldState, donut);
		cmdModifyDonut.execute();
		commandsStack.addCommand(cmdModifyDonut);
	}

	private void createDonut() {
		int xCoordinate = dialogDonut.getXcoordinateValue();
		int yCoordinate = dialogDonut.getYcoordinateValue();
		int radius = dialogDonut.getRadiusValue();
		int innerRadius = dialogDonut.getInnerRadiusValue();
		Point center = new Point(xCoordinate, yCoordinate, isSelected, outerColor);
		donut = new Donut(center, radius, innerRadius, isSelected, outerColor, innerColor);
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public Donut getDonut() {
		return donut;
	}

	public CmdModifyDonut getCmdModifyDonut() {
		return cmdModifyDonut;
	}
}