package commandsExecutors;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import java.awt.Color;
import shapes.*;
import commandsHandler.CommandsHandler;
import controller.OptionsController;
import dialogs.DialogRectangle;
import frame.DrawingFrame;
import model.DrawingModel;

public class RectangleCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private DialogRectangle dialogRectangle;
	private Queue<String> commandsLog;
	private OptionsController optionsController;
	private RectangleCommandsExecutor commandsExecutor;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsHandler = new CommandsHandler();
		dialogRectangle = new DialogRectangle();
		dialogRectangle.getXcoordinate().setText("1");
		dialogRectangle.getYcoordinate().setText("2");
		dialogRectangle.getwidth().setText("3");
		dialogRectangle.getheight().setText("4");
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsHandler, commandsLog);
		commandsExecutor = new RectangleCommandsExecutor(dialogRectangle, optionsController);
	}

	@Test
	public void testAddShape() {
		commandsExecutor.addShape();
		assertTrue(model.doesContainShape(commandsExecutor.getRectangle()));
		assertTrue(commandsHandler.getExecutedCommands().contains(commandsExecutor.getCmdAdd()));
	}

	@Test
	public void testModifyShape() {
		Rectangle oldState = new Rectangle(new Point(1, 2, true, Color.BLACK), 3, 4, true, Color.BLACK, Color.WHITE);
		commandsExecutor.modifyShape(oldState);
		assertEquals(oldState, commandsExecutor.getRectangle());
		assertTrue(commandsHandler.getExecutedCommands().contains(commandsExecutor.getCmdModifyRectangle()));
	}
}