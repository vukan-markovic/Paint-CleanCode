package logger;

import commandsExecutors.RemoveCommandExecutor;
import controller.OptionsController;
import frame.DrawingFrame;
import model.DrawingModel;
import shapes.Shape;
import stack.CommandsStack;

public class LogParser {
	private CircleLogReader circleLogReader;
	private DonutLogReader donutLogReader;
	private HexagonLogReader hexagonLogReader;
	private LineLogReader lineLogReader;
	private PointLogReader pointLogReader;
	private RectangleLogReader rectangleLogReader;
	private DrawingModel model;
	private OptionsController optionsController;
	private CommandsStack commandsStack;
	private RemoveCommandExecutor removeCommandExecutor;

	public LogParser(DrawingModel model, DrawingFrame frame, OptionsController optionsController,
			CommandsStack commandsStack) {
		this.model = model;
		this.optionsController = optionsController;
		this.commandsStack = commandsStack;
		circleLogReader = new CircleLogReader(model, commandsStack);
		donutLogReader = new DonutLogReader(model, commandsStack);
		hexagonLogReader = new HexagonLogReader(model, commandsStack);
		lineLogReader = new LineLogReader(model, commandsStack);
		pointLogReader = new PointLogReader(model, commandsStack);
		rectangleLogReader = new RectangleLogReader(model, commandsStack);
		removeCommandExecutor = new RemoveCommandExecutor(model, frame, commandsStack);
	}

	public void parse(String[] logLine) {
		if (logLine[0].equals("Add")) {
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
		} else if (logLine[0].equals("Select")) {
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
		} else if (logLine[0].equals("Deselect")) {
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
		} else if (logLine[0].equals("Modify")) {
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
		} else if (logLine[0].equals("Deleted"))
			removeCommandExecutor.removeShapes();
		else if (logLine[0].equals("Undo"))
			commandsStack.undoCommand();
		else if (logLine[0].equals("Redo"))
			commandsStack.redoCommand();
		else if (logLine[0].equals("ToBack"))
			optionsController.moveShapeToBack();
		else if (logLine[0].equals("ToFront"))
			optionsController.moveShapeToFront();
		else if (logLine[0].equals("BringToFront"))
			optionsController.bringShapeToFront();
		else if (logLine[0].equals("SendToBack"))
			optionsController.bringShapeToBack();
	}
}