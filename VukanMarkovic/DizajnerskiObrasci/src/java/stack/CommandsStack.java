package stack;

import java.util.Stack;
import commands.Command;

public class CommandsStack {
	private Stack<Command> executedCommands;
	private Stack<Command> unexecutedCommands;

	public CommandsStack() {
		executedCommands = new Stack<Command>();
		unexecutedCommands = new Stack<Command>();
	}

	public void addCommand(Command command) {
		executedCommands.push(command);
	}

	public void undoCommand() {
		executedCommands.peek().unexecute();
		moveCommandAfterUndo();
	}

	private void moveCommandAfterUndo() {
		unexecutedCommands.push(executedCommands.peek());
		executedCommands.pop();
	}

	public void redoCommand() {
		unexecutedCommands.peek().execute();
		moveCommandAfterRedo();
	}

	private void moveCommandAfterRedo() {
		executedCommands.push(unexecutedCommands.peek());
		unexecutedCommands.pop();
	}

	public Stack<Command> getExecutedCommands() {
		return executedCommands;
	}

	public Stack<Command> getUnexecutedCommands() {
		return unexecutedCommands;
	}

	public void setExecutedCommands(Stack<Command> executedCommands) {
		this.executedCommands = executedCommands;
	}

	public void setUnexecutedCommands(Stack<Command> unexecutedCommands) {
		this.unexecutedCommands = unexecutedCommands;
	}

	public void clearUnexecutedCommands() {
		unexecutedCommands.clear();
	}
}