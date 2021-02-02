package logger;

import javax.swing.DefaultListModel;
import frame.DrawingFrame;
import shapes.Shape;

public class LogWriter {
	private DefaultListModel<String> commandsListModel;

	public LogWriter(DrawingFrame frame) {
		this.commandsListModel = frame.getCommandsListModel();
	}

	public void logAddCommand(Shape shape) {
		commandsListModel.addElement("Add - " + shape.getClassName() + " " + shape);
	}

	public void logModifyCommand(Shape oldState, Shape newState) {
		commandsListModel
				.addElement("Modify - " + newState.getClassName() + " from " + oldState + " to " + " " + newState);
	}

	public void logRemoveCommand() {
		commandsListModel.addElement("Removed");
	}

	public void logToFrontCommand(Shape shape) {
		commandsListModel.addElement("ToFront - " + shape.getClassName() + " " + shape.toString());
	}

	public void logToBackCommand(Shape shape) {
		commandsListModel.addElement("ToBack - " + shape.getClassName() + " " + shape.toString());
	}

	public void logBringToFrontCommand(Shape shape) {
		commandsListModel.addElement("BringToFront - " + shape.getClassName() + " " + shape.toString());
	}

	public void logBringToBackCommand(Shape shape) {
		commandsListModel.addElement("BringToBack - " + shape.getClassName() + " " + shape.toString());
	}

	public void logSelectCommand(Shape shape) {
		commandsListModel.addElement("Select - " + shape.getClassName() + " " + shape.toString());
	}

	public void logDeselectCommand(Shape shape) {
		commandsListModel.addElement("Deselect - " + shape.getClassName() + " " + shape.toString());
	}

	public void logUndoCommand() {
		commandsListModel.addElement("Undo");
	}

	public void logRedoCommand() {
		commandsListModel.addElement("Redo");
	}
}