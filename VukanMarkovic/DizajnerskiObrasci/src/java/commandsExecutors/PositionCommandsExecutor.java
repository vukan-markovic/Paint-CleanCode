package commandsExecutors;

import commands.*;
import commandsHandler.CommandsHandler;
import frame.DrawingFrame;
import logger.LogWriter;
import model.DrawingModel;
import shapes.Shape;

public class PositionCommandsExecutor {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private LogWriter logWriter;
	private CmdToBack cmdToBack;
	private CmdToFront cmdToFront;
	private CmdBringToBack cmdBringToBack;
	private CmdBringToFront cmdBringToFront;
	private Shape shape;

	public PositionCommandsExecutor(DrawingModel model, DrawingFrame frame, CommandsHandler commandsHandler) {
		this.model = model;
		this.frame = frame;
		this.commandsHandler = commandsHandler;
		logWriter = new LogWriter(frame);
	}

	public void toBack() {
		shape = model.getFirstSelectedShape();
		cmdToBack = new CmdToBack(model, shape);
		cmdToBack.execute();
		commandsHandler.addExecutedCommand(cmdToBack);
		logWriter.logToBackCommand(shape);
		frame.getView().repaint();
	}

	public void toFront() {
		shape = model.getFirstSelectedShape();
		cmdToFront = new CmdToFront(model, shape);
		cmdToFront.execute();
		commandsHandler.addExecutedCommand(cmdToFront);
		logWriter.logToFrontCommand(shape);
		frame.getView().repaint();
	}

	public void bringToBack() {
		shape = model.getFirstSelectedShape();
		cmdBringToBack = new CmdBringToBack(model, shape);
		cmdBringToBack.execute();
		commandsHandler.addExecutedCommand(cmdBringToBack);
		logWriter.logBringToBackCommand(shape);
		frame.getView().repaint();
	}

	public void bringToFront() {
		shape = model.getFirstSelectedShape();
		cmdBringToFront = new CmdBringToFront(model, shape);
		cmdBringToFront.execute();
		commandsHandler.addExecutedCommand(cmdBringToFront);
		logWriter.logBringToFrontCommand(shape);
		frame.getView().repaint();
	}

	public CmdToBack getCmdToBack() {
		return cmdToBack;
	}

	public CmdToFront getCmdToFront() {
		return cmdToFront;
	}

	public CmdBringToBack getCmdBringToBack() {
		return cmdBringToBack;
	}

	public CmdBringToFront getCmdBringToFront() {
		return cmdBringToFront;
	}
}