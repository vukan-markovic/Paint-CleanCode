package controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.Color;
import java.util.*;
import org.junit.*;
import commands.*;
import frame.DrawingFrame;
import model.DrawingModel;
import shapes.Point;
import stack.CommandsStack;

public class OptionsControllerTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private Queue<String> commandsLog;
	private OptionsController optionsController;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsStack = spy(CommandsStack.class);
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsStack, commandsLog);
	}

	@Test
	public void testUndoCommand() {
		commandsStack.addCommand(new CmdAdd(model, new Point(1, 2)));
		optionsController.undoCommand();
		verify(commandsStack).undoCommand();
	}

	@Test
	public void testRedoCommand() {
		commandsStack.addCommand(new CmdAdd(model, new Point(1, 2)));
		optionsController.undoCommand();
		optionsController.redoCommand();
		verify(commandsStack).redoCommand();
	}

	@Test
	public void executeCommandFromLogOneShape() {
		Point point = new Point(1, 2, false, Color.BLACK);
		optionsController.getCommandsLog().add("Add - " + point.getClassName() + " " + point);
		optionsController.executeCommandFromLog();
		assertTrue(model.doesContainShape(point));
		assertFalse(frame.getRightToolbar().getBtnNextCommand().isEnabled());
	}

	@Test
	public void executeCommandFromLogMoreShapes() {
		Point firstPoint = new Point(1, 2, false, Color.BLACK);
		Point secondPoint = new Point(1, 2, false, Color.BLACK);
		optionsController.getCommandsLog().add("Add - " + firstPoint.getClassName() + " " + firstPoint);
		optionsController.getCommandsLog().add("Add - " + secondPoint.getClassName() + " " + secondPoint);
		optionsController.executeCommandFromLog();
		assertTrue(model.doesContainShape(secondPoint));
		assertFalse(frame.getRightToolbar().getBtnNextCommand().isEnabled());
	}

	@Test
	public void testSetBorderColor() {
		optionsController.setBorderColor();

		assertEquals(optionsController.getBorderColor(),
				optionsController.getFrame().getRightToolbar().getBtnBorderColor().getBackground());
	}

	@Test
	public void testSetFillColor() {
		optionsController.setFillColor();

		assertEquals(optionsController.getFillColor(),
				optionsController.getFrame().getRightToolbar().getBtnFillColor().getBackground());
	}

	@Test
	public void testMoveShapeToBack() {
		Point firstPoint = new Point(1, 2, false, Color.BLACK);
		Point secondPoint = new Point(3, 4, false, Color.BLACK);
		new CmdAdd(model, firstPoint).execute();
		new CmdAdd(model, secondPoint).execute();
		new CmdSelect(model, secondPoint).execute();
		optionsController.moveShapeToBack();

		assertTrue(commandsStack.getExecutedCommands()
				.contains(optionsController.getPositionCommandsExecutor().getCmdToBack()));
	}

	@Test
	public void testMoveShapeToFront() {
		Point firstPoint = new Point(1, 2, false, Color.BLACK);
		Point secondPoint = new Point(3, 4, false, Color.BLACK);
		new CmdAdd(model, firstPoint).execute();
		new CmdSelect(model, firstPoint).execute();
		new CmdAdd(model, secondPoint).execute();
		optionsController.moveShapeToFront();

		assertTrue(commandsStack.getExecutedCommands()
				.contains(optionsController.getPositionCommandsExecutor().getCmdToFront()));
	}

	@Test
	public void testBringShapeToBack() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		new CmdSelect(model, point).execute();
		optionsController.bringShapeToBack();

		assertTrue(commandsStack.getExecutedCommands()
				.contains(optionsController.getPositionCommandsExecutor().getCmdSendToBack()));
	}

	@Test
	public void testBringShapeToFront() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		new CmdSelect(model, point).execute();
		optionsController.bringShapeToFront();

		assertTrue(commandsStack.getExecutedCommands()
				.contains(optionsController.getPositionCommandsExecutor().getCmdBringToFront()));
	}
}