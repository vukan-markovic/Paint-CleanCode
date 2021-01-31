package commandsExecutors;

import commands.*;
import commandsHandler.CommandsHandler;
import shapes.*;
import controller.OptionsController;
import java.awt.Color;
import dialogs.DialogHexagon;
import hexagon.Hexagon;
import logger.LogWriter;
import model.DrawingModel;

public class HexagonCommandsExecutor implements SurfaceShapeCommandsExecutor {
	private DialogHexagon dialogHexagon;
	private OptionsController optionsController;
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsHandler commandsHandler;
	private CmdAdd cmdAdd;
	private CmdModifyHexagon cmdModifyHexagon;
	private boolean isSelected;
	private Color outerColor;
	private Color innerColor;
	private HexagonAdapter hexagonAdapter;

	public HexagonCommandsExecutor(DialogHexagon dialogHexagon, OptionsController optionsController) {
		this.dialogHexagon = dialogHexagon;
		this.optionsController = optionsController;
		model = optionsController.getModel();
		logWriter = new LogWriter(optionsController.getFrame());
		commandsHandler = optionsController.getCommandsHandler();
	}

	@Override
	public void addShape() {
		isSelected = false;
		outerColor = optionsController.getBorderColor();
		innerColor = optionsController.getFillColor();
		createHexagon();

		cmdAdd = new CmdAdd(model, hexagonAdapter);
		cmdAdd.execute();
		commandsHandler.addExecutedCommand(cmdAdd);
		logWriter.logAddCommand(hexagonAdapter);
	}

	@Override
	public void modifyShape(Shape selectedShape) {
		HexagonAdapter oldState = (HexagonAdapter) selectedShape;
		isSelected = true;
		outerColor = dialogHexagon.getBorderColor();
		innerColor = dialogHexagon.getFillColor();
		createHexagon();

		logWriter.logModifyCommand(oldState, hexagonAdapter);
		cmdModifyHexagon = new CmdModifyHexagon(oldState, hexagonAdapter);
		cmdModifyHexagon.execute();
		commandsHandler.addExecutedCommand(cmdModifyHexagon);
	}

	private void createHexagon() {
		int xCoordinate = dialogHexagon.getXcoordinateValue();
		int yCoordinate = dialogHexagon.getYcoordinateValue();
		int radius = dialogHexagon.getRadiusValue();
		Hexagon hexagon = new Hexagon(xCoordinate, yCoordinate, radius);
		hexagonAdapter = new HexagonAdapter(hexagon, isSelected, outerColor, innerColor);
	}

	public CmdAdd getCmdAdd() {
		return cmdAdd;
	}

	public CmdModifyHexagon getCmdModifyHexagon() {
		return cmdModifyHexagon;
	}

	public HexagonAdapter getHexagonAdapter() {
		return hexagonAdapter;
	}
}