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
	private Color innerColor;
	private Color outerColor;

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
		innerColor = Color.WHITE;
		outerColor = Color.BLACK;
	}

	public void undoCommand() {
		if (commandsStack.getExecutedCommands().isEmpty())
			return;

		commandsStack.undoCommand();
		logWriter.logUndoCommand();
		frame.getView().repaint();
		fireEventsForUndoAndRedoButtons();
		fireEventsForButtons();
	}

	public void redoCommand() {
		if (commandsStack.getUnexecutedCommands().isEmpty())
			return;

		commandsStack.redoCommand();
		logWriter.logRedoCommand();
		frame.getView().repaint();
		fireEventsForUndoAndRedoButtons();
		fireEventsForButtons();
	}

	public void executeCommandFromLog() {
		LogParser parser = new LogParser(model, frame, this, commandsStack);
		parser.parse(commandsLog.peek().split(" "));

		if (commandsLog.size() == 1)
			disableButton();

		fireEventsForButtons();
		fireEventsForUndoAndRedoButtons();
		frame.getView().repaint();
		frame.getCommandsListModel().addElement(commandsLog.poll());
	}

	private void disableButton() {
		RightToolbar toolbar = frame.getRightToolbar();
		JButton btnNext = toolbar.getBtnNext();
		btnNext.setEnabled(false);
	}

	public void chooseOuterColor() {
		outerColor = JColorChooser.showDialog(frame, "Choose a color!", outerColor);

		if (outerColor != null)
			setBtnOuterColor();
	}

	public void chooseInnerColor() {
		innerColor = JColorChooser.showDialog(frame, "Choose a color!", innerColor);

		if (innerColor != null)
			setBtnInnerColor();
	}

	private void setBtnInnerColor() {
		RightToolbar toolbar = frame.getRightToolbar();
		JButton btnInnerColor = toolbar.getBtnInnerColor();
		btnInnerColor.setBackground(innerColor);
	}

	private void setBtnOuterColor() {
		RightToolbar toolbar = frame.getRightToolbar();
		JButton btnOuterColor = toolbar.getBtnOuterColor();
		btnOuterColor.setBackground(outerColor);
	}

	public void moveShapeToBack() {
		if (model.getSelectedShapes().size() == 1) {
			positionCommandsExecutor.toBack();
			commandsStack.clearUnexecutedCommands();
		}
	}

	public void moveShapeToFront() {
		if (model.getSelectedShapes().size() == 1) {
			positionCommandsExecutor.toFront();
			commandsStack.clearUnexecutedCommands();
		}
	}

	public void bringShapeToBack() {
		if (model.getSelectedShapes().size() == 1) {
			positionCommandsExecutor.bringToBack();
			commandsStack.clearUnexecutedCommands();
		}
	}

	public void bringShapeToFront() {
		if (model.getSelectedShapes().size() == 1) {
			positionCommandsExecutor.bringToFront();
			commandsStack.clearUnexecutedCommands();
		}
	}

	public void fireEventsForUndoAndRedoButtons() {
		observer.setBtnUndoEnabled(commandsStack.getExecutedCommands().size() > 0);
		observer.setBtnRedoEnabled(commandsStack.getUnexecutedCommands().size() > 0);
	}

	public void fireEventsForButtons() {
		observer.setBtnDeleteEnabled(model.getSelectedShapes().size() > 0);
		observer.setBtnModifyEnabled(model.getSelectedShapes().size() == 1);
		observer.setBtnToBackEnabled(model.getSelectedShapes().size() == 1);
		observer.setBtnToFrontEnabled(model.getSelectedShapes().size() == 1);
		observer.setBtnSendToBackEnabled(model.getSelectedShapes().size() == 1);
		observer.setBtnBringToFrontEnabled(model.getSelectedShapes().size() == 1);
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public Color getOuterColor() {
		return outerColor;
	}
}