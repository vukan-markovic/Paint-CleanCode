package controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;
import commands.*;
import java.awt.Color;
import commandsHandler.CommandsHandler;
import frame.DrawingFrame;
import model.DrawingModel;
import shapes.Point;

public class OptionsControllerTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsHandler commandsHandler;
	private Queue<String> commandsLog;
	private OptionsController optionsController;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsHandler = spy(CommandsHandler.class);
		commandsLog = new LinkedList<String>();
		optionsController = new OptionsController(model, frame, commandsHandler, commandsLog);
	}

	@Test
	public void testUndoExecutedCommand() {
		commandsHandler.addExecutedCommand(new CmdAdd(model, new Point(1, 2)));
		optionsController.undoExecutedCommand();
		verify(commandsHandler).undoCommand();
	}

	@Test
	public void testUndoExecutedRemoveCommands() {
		commandsHandler.addExecutedCommand(new CmdAdd(model, new Point(1, 2)));
		commandsHandler.addExecutedCommand(new CmdAdd(model, new Point(1, 3)));
		commandsHandler.addExecutedCommand(new CmdRemove(model, new Point(1, 2)));
		commandsHandler.addExecutedCommand(new CmdRemove(model, new Point(1, 2)));
		optionsController.undoExecutedCommand();
		verify(commandsHandler).undoCommand();
	}

	@Test
	public void testRedoUnexecutedCommand() {
		commandsHandler.addExecutedCommand(new CmdAdd(model, new Point(1, 2)));
		optionsController.undoExecutedCommand();
		optionsController.redoUnexecutedCommand();
		verify(commandsHandler).redoCommand();
	}

	@Test
	public void testRedoUnexecutedRemoveCommands() {
		commandsHandler.addExecutedCommand(new CmdAdd(model, new Point(1, 2)));
		commandsHandler.addExecutedCommand(new CmdAdd(model, new Point(1, 3)));
		commandsHandler.addExecutedCommand(new CmdRemove(model, new Point(1, 2)));
		commandsHandler.addExecutedCommand(new CmdRemove(model, new Point(1, 2)));
		optionsController.undoExecutedCommand();
		optionsController.redoUnexecutedCommand();
		verify(commandsHandler).redoCommand();
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
	public void testSetBorderColorIfChoosen() {
		optionsController.setBorderColorIfChoosen();

		assertEquals(optionsController.getBorderColor(),
				optionsController.getFrame().getRightToolbar().getBtnBorderColor().getBackground());
	}

	@Test
	public void testSetFillColorIfChoosen() {
		optionsController.setFillColorIfChoosen();

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

		assertTrue(commandsHandler.getExecutedCommands()
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

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(optionsController.getPositionCommandsExecutor().getCmdToFront()));
	}

	@Test
	public void testBringShapeToBack() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		new CmdSelect(model, point).execute();
		optionsController.bringShapeToBack();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(optionsController.getPositionCommandsExecutor().getCmdBringToBack()));
	}

	@Test
	public void testBringShapeToFront() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		new CmdSelect(model, point).execute();
		optionsController.bringShapeToFront();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(optionsController.getPositionCommandsExecutor().getCmdBringToFront()));
	}
}