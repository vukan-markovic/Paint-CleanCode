package commandsExecutors;

import commands.*;
import shapes.*;
import java.awt.Color;
import stack.CommandsStack;
import dialogs.DialogRectangle;
import frame.DrawingFrame;
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

	public RectangleCommandsExecutor(DrawingModel model, DrawingFrame drawingFrame, CommandsStack commandsStack,
			DialogRectangle dialogRectangle, OptionsController optionsController) {
		this.model = model;
		logWriter = new LogWriter(drawingFrame);
		this.commandsStack = commandsStack;
		this.dialogRectangle = dialogRectangle;
		this.optionsController = optionsController;
	}

	@Override
	public void addShape() {
		isSelected = false;
		outerColor = optionsController.getOuterColor();
		innerColor = optionsController.getInnerColor();
		setRectangle();

		cmdAdd = new CmdAdd(model, rectangle);
		cmdAdd.execute();
		commandsStack.addCommand(cmdAdd);
		logWriter.logAddCommand(rectangle);
	}

	@Override
	public void modifyShape(Shape selectedShape) {
		Rectangle oldState = (Rectangle) selectedShape;
		isSelected = true;
		outerColor = dialogRectangle.getOuterColor();
		innerColor = dialogRectangle.getInnerColor();
		setRectangle();

		logWriter.logModifyCommand(oldState, rectangle);
		cmdModifyRectangle = new CmdModifyRectangle(oldState, rectangle);
		cmdModifyRectangle.execute();
		commandsStack.addCommand(cmdModifyRectangle);
	}

	private void setRectangle() {
		int xCoordinate = dialogRectangle.getXcoordinateValue();
		int yCoordinate = dialogRectangle.getYcoordinateValue();
		int height = dialogRectangle.getheightValue();
		int width = dialogRectangle.getwidthValue();
		Point upperLeftPoint = new Point(xCoordinate, yCoordinate, isSelected, outerColor);
		rectangle = new Rectangle(upperLeftPoint, height, width, false, outerColor, innerColor);
	}

	public CmdModifyRectangle getCmdModifyRectangle() {
		return cmdModifyRectangle;
	}

	public void setCmdModifyRectangle(CmdModifyRectangle cmdModifyRectangle) {
		this.cmdModifyRectangle = cmdModifyRectangle;
	}
}