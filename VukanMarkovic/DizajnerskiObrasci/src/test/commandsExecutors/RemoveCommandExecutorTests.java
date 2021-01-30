package commandsExecutors;

import static org.junit.Assert.*;
import org.junit.*;

import commands.CmdAdd;
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
	private CmdAdd cmdAdd;
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
		cmdAdd = new CmdAdd(model, firstShape);
		cmdAdd.execute();
		cmdSelect = new CmdSelect(model, firstShape);
		cmdSelect.execute();
		Point secondShape = new Point(3, 4);
		cmdAdd = new CmdAdd(model, secondShape);
		cmdAdd.execute();
		cmdSelect = new CmdSelect(model, secondShape);
		cmdSelect.execute();
		commandsExecutor.removeShapes();
		assertFalse(model.doesContainShape(firstShape));
		assertFalse(model.doesContainShape(secondShape));
		assertTrue(model.getSelectedShapes().isEmpty());
	}
}