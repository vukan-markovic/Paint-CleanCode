package commandsExecutors;

import commands.CmdRemove;
import commandsHandler.CommandsHandler;
import frame.DrawingFrame;
import logger.LogWriter;
import model.DrawingModel;
import shapes.Shape;

public class RemoveCommandExecutor {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private LogWriter logWriter;
	private CmdRemove cmdRemove;

	public RemoveCommandExecutor(DrawingModel model, DrawingFrame frame, CommandsHandler commandsHandler) {
		this.model = model;
		this.frame = frame;
		logWriter = new LogWriter(frame);
		this.commandsHandler = commandsHandler;
	}

	public void removeShapes() {
		int numberOfSelectedShapes = model.getNumberOfSelectedShapes();

		for (int indexOfShape = 0; indexOfShape < numberOfSelectedShapes; indexOfShape++)
			removeShape(indexOfShape);

		model.clearSelectedShapes();
		frame.getView().repaint();
	}

	private void removeShape(int indexOfShape) {
		Shape shape = model.getSelectedShapeByIndex(indexOfShape);
		cmdRemove = new CmdRemove(model, shape);
		cmdRemove.execute();
		commandsHandler.addExecutedCommand(cmdRemove);
		logWriter.logRemoveCommand();
	}
}