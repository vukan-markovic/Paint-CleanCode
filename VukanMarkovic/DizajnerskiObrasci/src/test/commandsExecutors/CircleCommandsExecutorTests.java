package commandsExecutors;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import shapes.*;
import java.awt.Color;
import commandsHandler.CommandsHandler;
import controller.OptionsController;
import dialogs.DialogCircle;
import frame.DrawingFrame;
import model.DrawingModel;

public class CircleCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private DialogCircle dialogCircle;
	private Queue<String> commandsLog;
	private OptionsController optionsController;
	private CircleCommandsExecutor commandsExecutor;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsHandler = new CommandsHandler();
		dialogCircle = new DialogCircle();
		dialogCircle.getXcoordinate().setText("1");
		dialogCircle.getYcoordinate().setText("2");
		dialogCircle.getRadius().setText("3");
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsHandler, commandsLog);
		commandsExecutor = new CircleCommandsExecutor(dialogCircle, optionsController);
	}

	@Test
	public void testAddShape() {
		commandsExecutor.addShape();
		assertTrue(model.doesContainShape(commandsExecutor.getCircle()));
		assertTrue(commandsHandler.getExecutedCommands().contains(commandsExecutor.getCmdAdd()));
	}

	@Test
	public void testModifyShape() {
		Circle oldState = new Circle(new Point(1, 2, true, Color.BLACK), 3, true, Color.BLACK, Color.WHITE);
		commandsExecutor.modifyShape(oldState);
		assertEquals(oldState, commandsExecutor.getCircle());
		assertTrue(commandsHandler.getExecutedCommands().contains(commandsExecutor.getCmdModifyCircle()));
	}
}