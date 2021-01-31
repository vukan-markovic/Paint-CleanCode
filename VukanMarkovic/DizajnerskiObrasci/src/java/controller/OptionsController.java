package controller;

import observer.*;
import logger.*;
import java.awt.Color;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import commandsExecutors.PositionCommandsExecutor;
import commandsHandler.CommandsHandler;
import frame.DrawingFrame;
import model.DrawingModel;
import toolbars.RightToolbar;

public class OptionsController {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private Queue<String> commandsLog;
	private PositionCommandsExecutor positionCommandsExecutor;
	private LogWriter logWriter;
	private DrawingObserver observer;
	private PropertyManager manager;
	private Color borderColor;
	private Color fillColor;
	private LogParser parser;

	public OptionsController(DrawingModel model, DrawingFrame frame, CommandsHandler commandsHandler,
			Queue<String> commandsLog) {
		this.model = model;
		this.frame = frame;
		this.commandsHandler = commandsHandler;
		this.commandsLog = commandsLog;
		positionCommandsExecutor = new PositionCommandsExecutor(model, frame, commandsHandler);
		logWriter = new LogWriter(frame);
		observer = new DrawingObserver();
		manager = new PropertyManager(frame);
		observer.addPropertyChangeListener(manager);
		borderColor = Color.BLACK;
		fillColor = Color.WHITE;
		parser = new LogParser(this);
	}

	public void undoCommand() {
		commandsHandler.undoCommand();
		logWriter.logUndoCommand();
		frame.getView().repaint();
		fireEventsForOptionsButtons();
		fireEventsForUndoAndRedoButtons();
	}

	public void redoCommand() {
		commandsHandler.redoCommand();
		logWriter.logRedoCommand();
		frame.getView().repaint();
		fireEventsForOptionsButtons();
		fireEventsForUndoAndRedoButtons();
	}

	public void executeCommandFromLog() {
		parser.parse(commandsLog.peek().split(" "));

		if (commandsLog.size() == 1)
			disableButton();

		fireEventsForOptionsButtons();
		fireEventsForUndoAndRedoButtons();
		frame.getView().repaint();
		frame.getCommandsListModel().addElement(commandsLog.poll());
	}

	public void fireEventsForOptionsButtons() {
		int numberOfSelectedShapes = model.getNumberOfSelectedShapes();
		boolean isNumberOfSelectedShapesOne = numberOfSelectedShapes == 1;
		observer.setBtnSelectEnabled(model.getNumberOfShapes() > 0);
		observer.setBtnDeleteEnabled(numberOfSelectedShapes > 0);
		observer.setBtnModifyEnabled(isNumberOfSelectedShapesOne);
		observer.setBtnToBackEnabled(isNumberOfSelectedShapesOne);
		observer.setBtnToFrontEnabled(isNumberOfSelectedShapesOne);
		observer.setBtnBringToBackEnabled(isNumberOfSelectedShapesOne);
		observer.setBtnBringToFrontEnabled(isNumberOfSelectedShapesOne);
	}

	public void fireEventsForUndoAndRedoButtons() {
		int numberOfExecutedCommands = commandsHandler.getExecutedCommands().size();
		boolean isNumberOfExecutedCommandsGreaterThanZero = numberOfExecutedCommands > 0;
		observer.setBtnUndoEnabled(isNumberOfExecutedCommandsGreaterThanZero);

		int numberOfUnexecutedCommands = commandsHandler.getUnexecutedCommands().size();
		boolean isNumberOfUnexecutedCommandsGreaterThanZero = numberOfUnexecutedCommands > 0;
		observer.setBtnRedoEnabled(isNumberOfUnexecutedCommandsGreaterThanZero);
	}

	private void disableButton() {
		RightToolbar toolbar = frame.getRightToolbar();
		JButton btnNext = toolbar.getBtnNextCommand();
		btnNext.setEnabled(false);
	}

	public void setBorderColor() {
		borderColor = JColorChooser.showDialog(frame, "Choose a color!", borderColor);

		if (borderColor != null)
			setBtnBorderColor();
	}

	private void setBtnBorderColor() {
		RightToolbar toolbar = frame.getRightToolbar();
		JButton btnBorderColor = toolbar.getBtnBorderColor();
		btnBorderColor.setBackground(borderColor);
	}

	public void setFillColor() {
		fillColor = JColorChooser.showDialog(frame, "Choose a color!", fillColor);

		if (fillColor != null)
			setBtnFillColor();
	}

	private void setBtnFillColor() {
		RightToolbar toolbar = frame.getRightToolbar();
		JButton btnInnerColor = toolbar.getBtnFillColor();
		btnInnerColor.setBackground(fillColor);
	}

	public void moveShapeToBack() {
		positionCommandsExecutor.toBack();
		commandsHandler.clearUnexecutedCommands();
	}

	public void moveShapeToFront() {
		positionCommandsExecutor.toFront();
		commandsHandler.clearUnexecutedCommands();
	}

	public void bringShapeToBack() {
		positionCommandsExecutor.bringToBack();
		commandsHandler.clearUnexecutedCommands();
	}

	public void bringShapeToFront() {
		positionCommandsExecutor.bringToFront();
		commandsHandler.clearUnexecutedCommands();
	}

	public DrawingModel getModel() {
		return model;
	}

	public DrawingFrame getFrame() {
		return frame;
	}

	public CommandsHandler getCommandsHandler() {
		return commandsHandler;
	}

	public Queue<String> getCommandsLog() {
		return commandsLog;
	}

	public PositionCommandsExecutor getPositionCommandsExecutor() {
		return positionCommandsExecutor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Color getFillColor() {
		return fillColor;
	}
}