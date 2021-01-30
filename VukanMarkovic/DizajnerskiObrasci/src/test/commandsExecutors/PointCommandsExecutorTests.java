package commandsExecutors;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.Before;
import org.junit.Test;
import controller.OptionsController;
import dialogs.DialogPoint;
import frame.DrawingFrame;
import model.DrawingModel;
import shapes.Point;
import stack.CommandsStack;

public class PointCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private DialogPoint dialogPoint;
	private Queue<String> commandsLog;
	private OptionsController optionsController;
	private PointCommandsExecutor commandsExecutor;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsStack = new CommandsStack();
		dialogPoint = new DialogPoint();
		dialogPoint.getXcoordinate().setText("1");
		dialogPoint.getYcoordinate().setText("2");
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsStack, commandsLog);
		commandsExecutor = new PointCommandsExecutor(dialogPoint, optionsController);
	}

	@Test
	public void testAddShape() {
		commandsExecutor.addShape(1, 2);
		assertTrue(model.doesContainShape(commandsExecutor.getPoint()));
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdAdd()));
	}

	@Test
	public void testModifyShape() {
		Point oldState = new Point(1, 2, true, Color.BLACK);
		commandsExecutor.modifyShape(oldState);
		assertEquals(oldState, commandsExecutor.getPoint());
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdModifyPoint()));
	}
}