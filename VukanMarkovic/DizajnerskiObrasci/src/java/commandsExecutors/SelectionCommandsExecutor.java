package commandsExecutors;

import commands.*;
import frame.DrawingFrame;
import logger.LogWriter;
import model.DrawingModel;
import shapes.Shape;
import stack.CommandsStack;

public class SelectionCommandsExecutor {
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsStack commandsStack;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;

	public SelectionCommandsExecutor(DrawingModel model, DrawingFrame frame, CommandsStack commandsStack) {
		this.model = model;
		logWriter = new LogWriter(frame);
		this.commandsStack = commandsStack;
	}

	public void selectShape(Shape shape) {
		cmdSelect = new CmdSelect(model, shape);
		cmdSelect.execute();
		commandsStack.addCommand(cmdSelect);
		logWriter.logSelectCommand(shape);
	}

	public void deselectShape(Shape shape) {
		cmdDeselect = new CmdDeselect(model, shape);
		cmdDeselect.execute();
		commandsStack.addCommand(cmdDeselect);
		logWriter.logDeselectCommand(shape);
	}

	public CmdSelect getCmdSelect() {
		return cmdSelect;
	}

	public CmdDeselect getCmdDeselect() {
		return cmdDeselect;
	}
}