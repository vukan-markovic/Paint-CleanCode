package logger;

import commandsExecutors.RemoveCommandExecutor;
import commandsHandler.CommandsHandler;
import controller.OptionsController;
import model.DrawingModel;
import shapes.Shape;

public class LogParser {
	private DrawingModel model;
	private OptionsController optionsController;
	private CommandsHandler commandsHandler;
	private CircleLogReader circleLogReader;
	private DonutLogReader donutLogReader;
	private HexagonLogReader hexagonLogReader;
	private LineLogReader lineLogReader;
	private PointLogReader pointLogReader;
	private RectangleLogReader rectangleLogReader;
	private RemoveCommandExecutor removeCommandExecutor;
	private String[] logLine;

	public LogParser(OptionsController optionsController) {
		this.model = optionsController.getModel();
		this.optionsController = optionsController;
		this.commandsHandler = optionsController.getCommandsHandler();
		circleLogReader = new CircleLogReader(model, commandsHandler);
		donutLogReader = new DonutLogReader(model, commandsHandler);
		hexagonLogReader = new HexagonLogReader(model, commandsHandler);
		lineLogReader = new LineLogReader(model, commandsHandler);
		pointLogReader = new PointLogReader(model, commandsHandler);
		rectangleLogReader = new RectangleLogReader(model, commandsHandler);
		removeCommandExecutor = new RemoveCommandExecutor(model, optionsController.getFrame(), commandsHandler);
	}

	public void parseLogCommand(String[] logLine) {
		this.logLine = logLine;

		if (logLine[0].equals("Add"))
			parseAddCommand();
		else if (logLine[0].equals("Select"))
			parseSelectCommand();
		else if (logLine[0].equals("Deselect"))
			parseDeselectCommand();
		else if (logLine[0].equals("Modify"))
			parseModifyCommand();
		else if (logLine[0].equals("Removed"))
			removeCommandExecutor.removeShapes();
		else if (logLine[0].equals("Undo"))
			optionsController.undoExecutedCommand();
		else if (logLine[0].equals("Redo"))
			optionsController.redoUnexecutedCommand();
		else if (logLine[0].equals("ToBack"))
			optionsController.moveShapeToBack();
		else if (logLine[0].equals("ToFront"))
			optionsController.moveShapeToFront();
		else if (logLine[0].equals("BringToFront"))
			optionsController.bringShapeToFront();
		else if (logLine[0].equals("BringToBack"))
			optionsController.bringShapeToBack();
	}

	private void parseAddCommand() {
		if (logLine[2].equals("Point"))
			pointLogReader.addShapeFromLog(logLine);
		else if (logLine[2].equals("Line"))
			lineLogReader.addShapeFromLog(logLine);
		else if (logLine[2].equals("Rectangle"))
			rectangleLogReader.addShapeFromLog(logLine);
		else if (logLine[2].equals("Circle"))
			circleLogReader.addShapeFromLog(logLine);
		else if (logLine[2].equals("Donut"))
			donutLogReader.addShapeFromLog(logLine);
		else if (logLine[2].equals("HexagonAdapter"))
			hexagonLogReader.addShapeFromLog(logLine);
	}

	private void parseSelectCommand() {
		if (logLine[2].equals("Point"))
			pointLogReader.selectShapeFromLog(logLine);
		else if (logLine[2].equals("Line"))
			lineLogReader.selectShapeFromLog(logLine);
		else if (logLine[2].equals("Rectangle"))
			rectangleLogReader.selectShapeFromLog(logLine);
		else if (logLine[2].equals("Circle"))
			circleLogReader.selectShapeFromLog(logLine);
		else if (logLine[2].equals("Donut"))
			donutLogReader.selectShapeFromLog(logLine);
		else if (logLine[2].equals("HexagonAdapter"))
			hexagonLogReader.selectShapeFromLog(logLine);
	}

	private void parseDeselectCommand() {
		if (logLine[2].equals("Point"))
			pointLogReader.deselectShapeFromLog(logLine);
		else if (logLine[2].equals("Line"))
			lineLogReader.deselectShapeFromLog(logLine);
		else if (logLine[2].equals("Rectangle"))
			rectangleLogReader.deselectShapeFromLog(logLine);
		else if (logLine[2].equals("Circle"))
			circleLogReader.deselectShapeFromLog(logLine);
		else if (logLine[2].equals("Donut"))
			donutLogReader.deselectShapeFromLog(logLine);
		else if (logLine[2].equals("HexagonAdapter"))
			hexagonLogReader.deselectShapeFromLog(logLine);
	}

	private void parseModifyCommand() {
		Shape selectedShape = model.getFirstSelectedShape();

		if (logLine[2].equals("Point"))
			pointLogReader.modifyShapeFromLog(logLine, selectedShape);
		else if (logLine[2].equals("Line"))
			lineLogReader.modifyShapeFromLog(logLine, selectedShape);
		else if (logLine[2].equals("Rectangle"))
			rectangleLogReader.modifyShapeFromLog(logLine, selectedShape);
		else if (logLine[2].equals("Circle"))
			circleLogReader.modifyShapeFromLog(logLine, selectedShape);
		else if (logLine[2].equals("Donut"))
			donutLogReader.modifyShapeFromLog(logLine, selectedShape);
		else if (logLine[2].equals("HexagonAdapter"))
			hexagonLogReader.modifyShapeFromLog(logLine, selectedShape);
	}
}