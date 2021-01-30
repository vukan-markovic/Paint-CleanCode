package controller;

import observer.*;
import logger.*;
import java.awt.Color;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import commandsExecutors.PositionCommandsExecutor;
import frame.DrawingFrame;
import model.DrawingModel;
import stack.CommandsStack;
import toolbars.RightToolbar;

public class OptionsController {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private Queue<String> commandsLog;
	private PositionCommandsExecutor positionCommandsExecutor;
	private LogWriter logWriter;
	private DrawingObserver observer;
	private PropertyManager manager;
	private Color fillColor;
	private Color borderColor;

	public OptionsController(DrawingModel model, DrawingFrame frame, CommandsStack commandsStack,
			Queue<String> commandsLog) {
		this.model = model;
		this.frame = frame;
		this.commandsStack = commandsStack;
		this.commandsLog = commandsLog;
		positionCommandsExecutor = new PositionCommandsExecutor(model, frame, commandsStack);
		logWriter = new LogWriter(frame);
		observer = new DrawingObserver();
		manager = new PropertyManager(frame);
		observer.addPropertyChangeListener(manager);
		fillColor = Color.WHITE;
		borderColor = Color.BLACK;
	}

	public void undoCommand() {
		commandsStack.undoCommand();
		logWriter.logUndoCommand();
		frame.getView().repaint();
		fireEventsForOptionsButtons();
		fireEventsForUndoAndRedoButtons();
	}

	public void redoCommand() {
		commandsStack.redoCommand();
		logWriter.logRedoCommand();
		frame.getView().repaint();
		fireEventsForOptionsButtons();
		fireEventsForUndoAndRedoButtons();
	}

	public void executeCommandFromLog() {
		LogParser parser = new LogParser(this);
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
		observer.setBtnDeleteEnabled(numberOfSelectedShapes > 0);
		observer.setBtnModifyEnabled(isNumberOfSelectedShapesOne);
		observer.setBtnToBackEnabled(isNumberOfSelectedShapesOne);
		observer.setBtnToFrontEnabled(isNumberOfSelectedShapesOne);
		observer.setBtnSendToBackEnabled(isNumberOfSelectedShapesOne);
		observer.setBtnBringToFrontEnabled(isNumberOfSelectedShapesOne);
	}

	public void fireEventsForUndoAndRedoButtons() {
		int numberOfExecutedCommands = commandsStack.getExecutedCommands().size();
		boolean isNumberOfExecutedCommandsGreaterThanZero = numberOfExecutedCommands > 0;
		observer.setBtnUndoEnabled(isNumberOfExecutedCommandsGreaterThanZero);
		observer.setBtnRedoEnabled(isNumberOfExecutedCommandsGreaterThanZero);
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
		JButton btnOuterColor = toolbar.getBtnOuterColor();
		btnOuterColor.setBackground(borderColor);
	}

	public void setFillColor() {
		fillColor = JColorChooser.showDialog(frame, "Choose a color!", fillColor);

		if (fillColor != null)
			setBtnFillColor();
	}

	private void setBtnFillColor() {
		RightToolbar toolbar = frame.getRightToolbar();
		JButton btnInnerColor = toolbar.getBtnInnerColor();
		btnInnerColor.setBackground(fillColor);
	}

	public void moveShapeToBack() {
		positionCommandsExecutor.toBack();
		commandsStack.clearUnexecutedCommands();
	}

	public void moveShapeToFront() {
		positionCommandsExecutor.toFront();
		commandsStack.clearUnexecutedCommands();
	}

	public void bringShapeToBack() {
		positionCommandsExecutor.bringToBack();
		commandsStack.clearUnexecutedCommands();
	}

	public void bringShapeToFront() {
		positionCommandsExecutor.bringToFront();
		commandsStack.clearUnexecutedCommands();
	}

	public DrawingModel getModel() {
		return model;
	}

	public DrawingFrame getFrame() {
		return frame;
	}

	public CommandsStack getCommandsStack() {
		return commandsStack;
	}

	public LogWriter getLogWriter() {
		return logWriter;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}
}