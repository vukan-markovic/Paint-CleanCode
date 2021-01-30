package commandsExecutors;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Color;
import java.util.*;
import org.junit.*;
import controller.OptionsController;
import dialogs.DialogDonut;
import frame.DrawingFrame;
import model.DrawingModel;
import shapes.*;
import stack.CommandsStack;

public class DonutCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private DialogDonut dialogDonut;
	private Queue<String> commandsLog;
	private OptionsController optionsController;
	private DonutCommandsExecutor commandsExecutor;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsStack = new CommandsStack();
		dialogDonut = new DialogDonut();
		dialogDonut.getXcoordinate().setText("1");
		dialogDonut.getYcoordinate().setText("2");
		dialogDonut.getRadius().setText("3");
		dialogDonut.getInnerRadius().setText("2");
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsStack, commandsLog);
		commandsExecutor = new DonutCommandsExecutor(dialogDonut, optionsController);
	}

	@Test
	public void testAddShape() {
		commandsExecutor.addShape();
		assertTrue(model.doesContainShape(commandsExecutor.getDonut()));
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdAdd()));
	}

	@Test
	public void testModifyShape() {
		Donut oldState = new Donut(new Point(1, 2, true, Color.BLACK), 3, 2, true, Color.BLACK, Color.WHITE);
		commandsExecutor.modifyShape(oldState);
		assertEquals(oldState, commandsExecutor.getDonut());
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdModifyDonut()));
	}
}