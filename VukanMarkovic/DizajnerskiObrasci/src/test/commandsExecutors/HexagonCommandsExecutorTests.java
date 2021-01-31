package commandsExecutors;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import shapes.*;
import java.awt.Color;
import commandsHandler.CommandsHandler;
import controller.OptionsController;
import dialogs.DialogHexagon;
import frame.DrawingFrame;
import hexagon.Hexagon;
import model.DrawingModel;

public class HexagonCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private DialogHexagon dialogHexagon;
	private Queue<String> commandsLog;
	private OptionsController optionsController;
	private HexagonCommandsExecutor commandsExecutor;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsHandler = new CommandsHandler();
		dialogHexagon = new DialogHexagon();
		dialogHexagon.getXcoordinate().setText("1");
		dialogHexagon.getYcoordinate().setText("2");
		dialogHexagon.getRadius().setText("3");
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsHandler, commandsLog);
		commandsExecutor = new HexagonCommandsExecutor(dialogHexagon, optionsController);
	}

	@Test
	public void testAddShape() {
		commandsExecutor.addShape();
		assertTrue(model.doesContainShape(commandsExecutor.getHexagonAdapter()));
		assertTrue(commandsHandler.getExecutedCommands().contains(commandsExecutor.getCmdAdd()));
	}

	@Test
	public void testModifyShape() {
		HexagonAdapter oldState = new HexagonAdapter(new Hexagon(1, 2, 3), true, Color.BLACK, Color.WHITE);
		commandsExecutor.modifyShape(oldState);
		assertEquals(oldState, commandsExecutor.getHexagonAdapter());
		assertTrue(commandsHandler.getExecutedCommands().contains(commandsExecutor.getCmdModifyHexagon()));
	}
}