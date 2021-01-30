package commandsExecutors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import commands.CmdSelect;
import frame.DrawingFrame;
import model.DrawingModel;
import shapes.Point;
import stack.CommandsStack;

public class RemoveCommandExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private RemoveCommandExecutor commandsExecutor;
	private CmdSelect cmdSelect;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsStack = new CommandsStack();
		commandsExecutor = new RemoveCommandExecutor(model, frame, commandsStack);
	}

	@Test
	public void testRemoveShapes() {
		Point firstShape = new Point(1, 2);
		cmdSelect = new CmdSelect(model, firstShape);
		cmdSelect.execute();
		Point secondShape = new Point(3, 4);
		cmdSelect = new CmdSelect(model, secondShape);
		cmdSelect.execute();
		commandsExecutor.removeShapes();
		assertFalse(model.doesContainShape(firstShape));
		assertFalse(model.doesContainShape(secondShape));
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdRemove()));
		assertTrue(model.getSelectedShapes().isEmpty());
	}
}