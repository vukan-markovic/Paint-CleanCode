package commandsExecutors;

import static org.junit.Assert.*;
import org.junit.*;
import commandsHandler.CommandsHandler;
import java.awt.Color;
import frame.DrawingFrame;
import model.DrawingModel;
import shapes.Point;

public class SelectionCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private SelectionCommandsExecutor commandExecutor;
	private Point point;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsHandler = new CommandsHandler();
		commandExecutor = new SelectionCommandsExecutor(model, frame, commandsHandler);
		point = new Point(1, 2, false, Color.BLACK);
	}

	@Test
	public void testSelectShape() {
		model.addShape(point);
		commandExecutor.selectShape(point);
		assertTrue(model.doesContainSelectedShape(point));
		assertTrue(commandsHandler.getExecutedCommands().contains(commandExecutor.getCmdSelect()));
	}

	@Test
	public void testDeselectShape() {
		commandExecutor.deselectShape(point);
		assertFalse(point.isSelected());
		assertFalse(model.doesContainSelectedShape(point));
		assertTrue(commandsHandler.getExecutedCommands().contains(commandExecutor.getCmdDeselect()));
	}
}