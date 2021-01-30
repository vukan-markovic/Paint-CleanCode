package commandsExecutors;

import commands.*;
import shapes.*;
import java.awt.Color;
import stack.CommandsStack;
import dialogs.DialogRectangle;
import logger.LogWriter;
import model.DrawingModel;
import controller.OptionsController;

public class RectangleCommandsExecutor implements SurfaceShapeCommandsExecutor {
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsStack commandsStack;
	private DialogRectangle dialogRectangle;
	private OptionsController optionsController;
	private CmdAdd cmdAdd;
	private CmdModifyRectangle cmdModifyRectangle;
	private boolean isSelected;
	private Color outerColor;
	private Color innerColor;
	private Rectangle rectangle;

	public RectangleCommandsExecutor(DialogRectangle dialogRectangle, OptionsController optionsController) {
		this.model = optionsController.getModel();
		logWriter = new LogWriter(optionsController.getFrame());
		this.commandsStack = optionsController.getCommandsStack();
		this.dialogRectangle = dialogRectangle;
		this.optionsController = optionsController;
	}

	@Override
	public void addShape() {
		isSelected = false;
		outerColor = optionsController.getBorderColor();
		innerColor = optionsController.getFillColor();
		createRectangle();

		cmdAdd = new CmdAdd(model, rectangle);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
		logWriter.logAddCommand(rectangle);
	}

	@Override
	public void modifyShape(Shape selectedShape) {
		Rectangle oldState = (Rectangle) selectedShape;
		isSelected = true;
		outerColor = dialogRectangle.getBorderColor();
		innerColor = dialogRectangle.getFillColor();
		createRectangle();

		logWriter.logModifyCommand(oldState, rectangle);
		cmdModifyRectangle = new CmdModifyRectangle(oldState, rectangle);
		cmdModifyRectangle.execute();
		commandsStack.addCommand(cmdModifyRectangle);
	}

	private void createRectangle() {
		int xCoordinate = dialogRectangle.getXcoordinateValue();
		int yCoordinate = dialogRectangle.getYcoordinateValue();
		int height = dialogRectangle.getheightValue();
		int width = dialogRectangle.getwidthValue();
		Point upperLeftPoint = new Point(xCoordinate, yCoordinate, isSelected, outerColor);
		rectangle = new Rectangle(upperLeftPoint, height, width, isSelected, outerColor, innerColor);
	}
	
	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdModifyRectangle getCmdModifyRectangle() {
		return cmdModifyRectangle;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
}