package commandsExecutors;

import commands.*;
import frame.DrawingFrame;
import logger.LogWriter;
import model.DrawingModel;
import shapes.Shape;
import stack.CommandsStack;

public class PositionCommandsExecutor {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private LogWriter logWriter;
	private CmdToBack cmdToBack;
	private CmdToFront cmdToFront;
	private CmdBringToBack cmdBringToBack;
	private CmdBringToFront cmdBringToFront;

	public PositionCommandsExecutor(DrawingModel model, DrawingFrame frame, CommandsStack commandsStack) {
		this.model = model;
		this.frame = frame;
		this.commandsStack = commandsStack;
		logWriter = new LogWriter(frame);
	}

	public void toBack() {
		Shape shape = model.getFirstSelectedShape();
		cmdToBack = new CmdToBack(model, shape);
		cmdToBack.execute();
		commandsStack.addCommand(cmdToBack);
		logWriter.logToBackCommand(shape);
	}

	public void toFront() {
		Shape shape = model.getFirstSelectedShape();
		cmdToFront = new CmdToFront(model, shape);
		cmdToFront.execute();
		commandsStack.addCommand(cmdToFront);
		logWriter.logToFrontCommand(shape);
		frame.getView().repaint();
	}

	public void bringToBack() {
		Shape shape = model.getFirstSelectedShape();
		cmdBringToBack = new CmdBringToBack(model, shape);
		cmdBringToBack.execute();
		commandsStack.addCommand(cmdBringToBack);
		logWriter.logBringToBackCommand(shape);
		frame.getView().repaint();
	}

	public void bringToFront() {
		Shape shape = model.getFirstSelectedShape();
		cmdBringToFront = new CmdBringToFront(model, shape);
		cmdBringToFront.execute();
		commandsStack.addCommand(cmdBringToFront);
		logWriter.logBringToFrontCommand(shape);
		frame.getView().repaint();
	}

	public CmdToBack getCmdToBack() {
		return cmdToBack;
	}

	public CmdToFront getCmdToFront() {
		return cmdToFront;
	}

	public CmdBringToFront getCmdBringToFront() {
		return cmdBringToFront;
	}

	public CmdBringToBack getCmdSendToBack() {
		return cmdBringToBack;
	}

	public void setCmdToBack(CmdToBack cmdToBack) {
		this.cmdToBack = cmdToBack;
	}

	public void setCmdToFront(CmdToFront cmdToFront) {
		this.cmdToFront = cmdToFront;
	}

	public void setCmdBringToFront(CmdBringToFront cmdBringToFront) {
		this.cmdBringToFront = cmdBringToFront;
	}

	public void setCmdSendToBack(CmdBringToBack cmdBringToBack) {
		this.cmdBringToBack = cmdBringToBack;
	}
}