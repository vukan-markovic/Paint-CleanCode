package commandsHandler;

import java.util.Stack;

import commands.CmdRemove;
import commands.Command;

public class CommandsHandler {
	private Stack<Command> executedCommands;
	private Stack<Command> unexecutedCommands;

	public CommandsHandler() {
		executedCommands = new Stack<Command>();
		unexecutedCommands = new Stack<Command>();
	}

	public void addExecutedCommand(Command command) {
		executedCommands.push(command);
	}

	public void undoCommand() {
		Command command = executedCommands.peek();

		if (command instanceof CmdRemove)
			unexecuteConsecutiveRemoveCommands(command);
		else {
			executedCommands.peek().unexecute();
			moveCommandAfterUndo();
		}
	}

	private void unexecuteConsecutiveRemoveCommands(Command command) {
		while (command instanceof CmdRemove) {
			executedCommands.peek().unexecute();
			moveCommandAfterUndo();
			command = executedCommands.peek();
		}
	}

	private void moveCommandAfterUndo() {
		unexecutedCommands.push(executedCommands.pop());
	}

	public void redoCommand() {
		Command command = unexecutedCommands.peek();

		if (command instanceof CmdRemove)
			executeConsecutiveRemoveCommands(command);
		else {
			unexecutedCommands.peek().execute();
			moveCommandAfterRedo();
		}
	}

	private void executeConsecutiveRemoveCommands(Command command) {
		while (command instanceof CmdRemove) {
			unexecutedCommands.peek().execute();
			moveCommandAfterRedo();

			if (!unexecutedCommands.isEmpty())
				command = unexecutedCommands.peek();
			else
				command = null;
		}
	}

	private void moveCommandAfterRedo() {
		executedCommands.push(unexecutedCommands.pop());
	}

	public void clearUnexecutedCommands() {
		unexecutedCommands.clear();
	}

	public Stack<Command> getExecutedCommands() {
		return executedCommands;
	}

	public Stack<Command> getUnexecutedCommands() {
		return unexecutedCommands;
	}
}