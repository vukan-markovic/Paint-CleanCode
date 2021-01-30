package commandsExecutors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import frame.DrawingFrame;
import model.DrawingModel;
import shapes.Point;
import stack.CommandsStack;

public class SelectionCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private SelectionCommandsExecutor commandExecutor;
	Point point;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsStack = new CommandsStack();
		commandExecutor = new SelectionCommandsExecutor(model, frame, commandsStack);
		point = new Point(1, 2, false, Color.BLACK);
	}

	@Test
	public void testSelectShape() {
		commandExecutor.selectShape(point);
		assertTrue(point.isSelected());
		assertTrue(model.doesContainSelectedShape(point));
		assertTrue(commandsStack.getExecutedCommands().contains(commandExecutor.getCmdSelect()));
	}

	@Test
	public void testDeselectShape() {
		commandExecutor.deselectShape(point);
		assertFalse(point.isSelected());
		assertFalse(model.doesContainSelectedShape(point));
		assertTrue(commandsStack.getExecutedCommands().contains(commandExecutor.getCmdDeselect()));
	}
}