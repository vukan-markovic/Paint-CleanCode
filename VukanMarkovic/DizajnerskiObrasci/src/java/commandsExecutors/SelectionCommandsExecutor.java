package commandsExecutors;

import commands.*;
import commandsHandler.CommandsHandler;
import frame.DrawingFrame;
import logger.LogWriter;
import model.DrawingModel;
import shapes.Shape;

public class SelectionCommandsExecutor {
	private DrawingModel model;
	private LogWriter logWriter;
	private CommandsHandler commandsHandler;
	private CmdSelect cmdSelect;
	private CmdDeselect cmdDeselect;

	public SelectionCommandsExecutor(DrawingModel model, DrawingFrame frame, CommandsHandler commandsHandler) {
		this.model = model;
		logWriter = new LogWriter(frame);
		this.commandsHandler = commandsHandler;
	}

	public void selectShape(Shape shape) {
		cmdSelect = new CmdSelect(model, shape);
		cmdSelect.execute();
		commandsHandler.addExecutedCommand(cmdSelect);
		logWriter.logSelectCommand(shape);
	}

	public void deselectShape(Shape shape) {
		cmdDeselect = new CmdDeselect(model, shape);
		cmdDeselect.execute();
		commandsHandler.addExecutedCommand(cmdDeselect);
		logWriter.logDeselectCommand(shape);
	}

	public CmdSelect getCmdSelect() {
		return cmdSelect;
	}

	public CmdDeselect getCmdDeselect() {
		return cmdDeselect;
	}
}