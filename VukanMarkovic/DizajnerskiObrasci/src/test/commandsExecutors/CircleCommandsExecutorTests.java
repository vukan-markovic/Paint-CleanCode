package commandsExecutors;

import static org.junit.Assert.*;
import java.awt.Color;
import java.util.*;
import org.junit.*;
import shapes.*;
import controller.OptionsController;
import dialogs.DialogCircle;
import frame.DrawingFrame;
import model.DrawingModel;
import stack.CommandsStack;

public class CircleCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private DialogCircle dialogCircle;
	private Queue<String> commandsLog;
	private OptionsController optionsController;
	private CircleCommandsExecutor commandsExecutor;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsStack = new CommandsStack();
		dialogCircle = new DialogCircle();
		dialogCircle.getXcoordinate().setText("1");
		dialogCircle.getYcoordinate().setText("2");
		dialogCircle.getRadius().setText("3");
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsStack, commandsLog);
		commandsExecutor = new CircleCommandsExecutor(dialogCircle, optionsController);
	}

	@Test
	public void testAddShape() {
		commandsExecutor.addShape();
		assertTrue(model.doesContainShape(commandsExecutor.getCircle()));
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdAdd()));
	}

	@Test
	public void testModifyShape() {
		Circle oldState = new Circle(new Point(1, 2, true, Color.BLACK), 3, true, Color.BLACK, Color.WHITE);
		commandsExecutor.modifyShape(oldState);
		assertEquals(oldState, commandsExecutor.getCircle());
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdModifyCircle()));
	}
}