package commandsExecutors;

import static org.junit.Assert.*;
import java.awt.Color;
import java.util.*;
import org.junit.*;
import shapes.*;
import controller.OptionsController;
import dialogs.DialogRectangle;
import frame.DrawingFrame;
import model.DrawingModel;
import stack.CommandsStack;

public class RectangleCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private DialogRectangle dialogRectangle;
	private Queue<String> commandsLog;
	private OptionsController optionsController;
	private RectangleCommandsExecutor commandsExecutor;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsStack = new CommandsStack();
		dialogRectangle = new DialogRectangle();
		dialogRectangle.getXcoordinate().setText("1");
		dialogRectangle.getYcoordinate().setText("2");
		dialogRectangle.getwidth().setText("3");
		dialogRectangle.getheight().setText("4");
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsStack, commandsLog);
		commandsExecutor = new RectangleCommandsExecutor(dialogRectangle, optionsController);
	}

	@Test
	public void testAddShape() {
		commandsExecutor.addShape();
		assertTrue(model.doesContainShape(commandsExecutor.getRectangle()));
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdAdd()));
	}

	@Test
	public void testModifyShape() {
		Rectangle oldState = new Rectangle(new Point(1, 2, true, Color.BLACK), 3, 4, true, Color.BLACK, Color.WHITE);
		commandsExecutor.modifyShape(oldState);
		assertEquals(oldState, commandsExecutor.getRectangle());
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdModifyRectangle()));
	}
}