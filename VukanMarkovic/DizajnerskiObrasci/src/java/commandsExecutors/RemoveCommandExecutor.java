package commandsExecutors;

import commands.CmdRemove;
import frame.DrawingFrame;
import logger.LogWriter;
import model.DrawingModel;
import shapes.Shape;
import stack.CommandsStack;

public class RemoveCommandExecutor {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private LogWriter logWriter;
	private CmdRemove cmdRemove;

	public RemoveCommandExecutor(DrawingModel model, DrawingFrame frame, CommandsStack commandsStack) {
		this.model = model;
		this.frame = frame;
		this.commandsStack = commandsStack;
		this.logWriter = new LogWriter(frame);
	}

	public void removeShapes() {
		int numberOfSelectedShapes = model.getNumberOfSelectedShapes();

		for (int indexOfShape = 0; indexOfShape < numberOfSelectedShapes; indexOfShape++)
			removeShape(indexOfShape);

		model.clearSelectedShapes();
	}

	private void removeShape(int indexOfShape) {
		Shape shape = model.getSelectedShapeByIndex(indexOfShape);
		cmdRemove = new CmdRemove(model, shape);
		cmdRemove.execute();
		commandsStack.addCommand(cmdRemove);
		logWriter.logRemoveCommand();
		frame.getView().repaint();
	}

	public CmdRemove getCmdRemove() {
		return cmdRemove;
	}

	public void setCmdRemove(CmdRemove cmdRemove) {
		this.cmdRemove = cmdRemove;
	}
}