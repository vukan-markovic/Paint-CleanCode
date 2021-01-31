package commandsExecutors;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import shapes.*;
import java.awt.Color;
import commandsHandler.CommandsHandler;
import controller.OptionsController;
import dialogs.DialogDonut;
import frame.DrawingFrame;
import model.DrawingModel;

public class DonutCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private DialogDonut dialogDonut;
	private Queue<String> commandsLog;
	private OptionsController optionsController;
	private DonutCommandsExecutor commandsExecutor;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsHandler = new CommandsHandler();
		dialogDonut = new DialogDonut();
		dialogDonut.getXcoordinate().setText("1");
		dialogDonut.getYcoordinate().setText("2");
		dialogDonut.getRadius().setText("3");
		dialogDonut.getInnerRadius().setText("2");
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsHandler, commandsLog);
		commandsExecutor = new DonutCommandsExecutor(dialogDonut, optionsController);
	}

	@Test
	public void testAddShape() {
		commandsExecutor.addShape();
		assertTrue(model.doesContainShape(commandsExecutor.getDonut()));
		assertTrue(commandsHandler.getExecutedCommands().contains(commandsExecutor.getCmdAdd()));
	}

	@Test
	public void testModifyShape() {
		Donut oldState = new Donut(new Point(1, 2, true, Color.BLACK), 3, 2, true, Color.BLACK, Color.WHITE);
		commandsExecutor.modifyShape(oldState);
		assertEquals(oldState, commandsExecutor.getDonut());
		assertTrue(commandsHandler.getExecutedCommands().contains(commandsExecutor.getCmdModifyDonut()));
	}
}