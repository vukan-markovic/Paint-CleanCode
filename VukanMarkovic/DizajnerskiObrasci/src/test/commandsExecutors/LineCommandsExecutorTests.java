package commandsExecutors;

import static org.junit.Assert.*;
import java.awt.Color;
import java.util.*;
import org.junit.*;
import shapes.*;
import controller.OptionsController;
import dialogs.DialogLine;
import frame.DrawingFrame;
import model.DrawingModel;
import stack.CommandsStack;

public class LineCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private DialogLine dialogLine;
	private Queue<String> commandsLog;
	private OptionsController optionsController;
	private LineCommandsExecutor commandsExecutor;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsStack = new CommandsStack();
		dialogLine = new DialogLine();
		dialogLine.getXcoordinate().setText("1");
		dialogLine.getYcoordinate().setText("2");
		dialogLine.getXCoordinateOfEndPoint().setText("3");
		dialogLine.getYCoordinateOfEndPoint().setText("4");
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsStack, commandsLog);
		commandsExecutor = new LineCommandsExecutor(dialogLine, optionsController);
	}

	@Test
	public void testAddShapeStartPointIsNull() {
		commandsExecutor.addShape(1, 2);
		assertFalse(model.doesContainShape(commandsExecutor.getLine()));
		assertFalse(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdAdd()));
	}

	@Test
	public void testAddShape() {
		commandsExecutor.addShape(1, 2);
		commandsExecutor.addShape(1, 2);
		assertTrue(model.doesContainShape(commandsExecutor.getLine()));
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdAdd()));
	}

	@Test
	public void testModifyShape() {
		Line oldState = new Line(new Point(1, 2, true, Color.BLACK), new Point(3, 4, true, Color.BLACK), true,
				Color.BLACK);
		
		commandsExecutor.modifyShape(oldState);
		assertEquals(oldState, commandsExecutor.getLine());
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdModifyLine()));
	}
}