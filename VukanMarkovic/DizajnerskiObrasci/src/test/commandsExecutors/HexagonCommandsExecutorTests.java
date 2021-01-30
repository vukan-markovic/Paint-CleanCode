package commandsExecutors;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Color;
import java.util.*;
import org.junit.*;
import controller.OptionsController;
import dialogs.DialogHexagon;
import frame.DrawingFrame;
import hexagon.Hexagon;
import model.DrawingModel;
import shapes.HexagonAdapter;
import stack.CommandsStack;

public class HexagonCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private DialogHexagon dialogHexagon;
	private Queue<String> commandsLog;
	private OptionsController optionsController;
	private HexagonCommandsExecutor commandsExecutor;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsStack = new CommandsStack();
		dialogHexagon = new DialogHexagon();
		dialogHexagon.getXcoordinate().setText("1");
		dialogHexagon.getYcoordinate().setText("2");
		dialogHexagon.getRadius().setText("3");
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsStack, commandsLog);
		commandsExecutor = new HexagonCommandsExecutor(dialogHexagon, optionsController);
	}

	@Test
	public void testAddShape() {
		commandsExecutor.addShape();
		assertTrue(model.doesContainShape(commandsExecutor.getHexagonAdapter()));
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdAdd()));
	}

	@Test
	public void testModifyShape() {
		HexagonAdapter oldState = new HexagonAdapter(new Hexagon(1, 2, 3), true, Color.BLACK, Color.WHITE);
		commandsExecutor.modifyShape(oldState);
		assertEquals(oldState, commandsExecutor.getHexagonAdapter());
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdModifyHexagon()));
	}
}