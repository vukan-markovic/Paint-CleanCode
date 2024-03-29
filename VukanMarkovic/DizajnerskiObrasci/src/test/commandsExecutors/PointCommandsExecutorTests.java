package commandsExecutors;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import shapes.*;
import java.awt.Color;
import commandsHandler.CommandsHandler;
import controller.OptionsController;
import dialogs.DialogPoint;
import frame.DrawingFrame;
import model.DrawingModel;

public class PointCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private DialogPoint dialogPoint;
	private Queue<String> commandsLog;
	private OptionsController optionsController;
	private PointCommandsExecutor commandsExecutor;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsHandler = new CommandsHandler();
		dialogPoint = new DialogPoint();
		dialogPoint.getXcoordinate().setText("1");
		dialogPoint.getYcoordinate().setText("2");
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsHandler, commandsLog);
		commandsExecutor = new PointCommandsExecutor(dialogPoint, optionsController);
	}

	@Test
	public void testAddShape() {
		commandsExecutor.addShape(1, 2);
		assertTrue(model.doesContainShape(commandsExecutor.getPoint()));
		assertTrue(commandsHandler.getExecutedCommands().contains(commandsExecutor.getCmdAdd()));
	}

	@Test
	public void testModifyShape() {
		Point oldState = new Point(1, 2, true, Color.BLACK);
		commandsExecutor.modifyShape(oldState);
		assertEquals(oldState, commandsExecutor.getPoint());
		assertTrue(commandsHandler.getExecutedCommands().contains(commandsExecutor.getCmdModifyPoint()));
	}
}