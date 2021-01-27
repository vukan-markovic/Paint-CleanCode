package commandsExecutors;

import commands.*;
import frame.DrawingFrame;
import logger.LogWriter;
import model.DrawingModel;
import shapes.Shape;
import stack.CommandsStack;

public class SelectCommandExecutor {
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsStack commandsStack;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;

	public SelectCommandExecutor(DrawingModel model, DrawingFrame drawingFrame, CommandsStack commandsStack) {
		this.model = model;
		this.commandsStack = commandsStack;
		logWriter = new LogWriter(drawingFrame);
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

	public void setCmdSelect(CmdSelect cmdSelect) {
		this.cmdSelect = cmdSelect;
	}

	public void setCmdDeselect(CmdDeselect cmdDeselect) {
		this.cmdDeselect = cmdDeselect;
	}
}