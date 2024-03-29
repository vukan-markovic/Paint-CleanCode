package controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;
import commands.*;
import shapes.*;
import commandsHandler.CommandsHandler;
import frame.DrawingFrame;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.event.MouseEvent;
import hexagon.Hexagon;
import model.DrawingModel;
import org.junit.rules.TemporaryFolder;

public class DrawingControllerTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private Queue<String> commandsLog;
	private CommandsHandler commandsHandler;
	private OptionsController optionsController;
	private DrawingController drawingController;
	private MouseEvent click;

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Before
	public void setUp() throws AWTException {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsLog = new LinkedList<String>();
		commandsHandler = new CommandsHandler();
		optionsController = new OptionsController(model, frame, commandsHandler, commandsLog);
		drawingController = new DrawingController(optionsController);
		frame.getView().setModel(model);
		frame.setDrawingController(drawingController);
		click = mock(MouseEvent.class);
	}

	@Test
	public void testSelectOrDeselectShapeShapeIsNotSelected() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		drawingController.selectOrDeselectShape(click);
		assertTrue(point.isSelected());
	}

	@Test
	public void testSelectOrDeselectShapeShapeIsSelected() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		new CmdSelect(model, point).execute();
		drawingController.selectOrDeselectShape(click);
		assertFalse(point.isSelected());
	}

	@Test
	public void testDrawPoint() {
		drawingController.drawPoint(click);
		assertTrue(model.doesContainShape(drawingController.getPointCommandsExecutor().getPoint()));
	}

	@Test
	public void testDrawLineStartPointIsNull() {
		drawingController.drawLine(click);
		assertFalse(model.doesContainShape(drawingController.getLineCommandsExecutor().getLine()));
	}

	@Test
	public void testDrawLine() {
		drawingController.drawLine(click);
		drawingController.drawLine(click);
		assertTrue(model.doesContainShape(drawingController.getLineCommandsExecutor().getLine()));
	}

	@Test
	public void testDrawRectangleIfAccepted() {
		drawingController.getDialogRectangle().getheight().setText("1");
		drawingController.getDialogRectangle().getwidth().setText("2");
		drawingController.drawRectangleIfAccepted(click);
		assertTrue(model.doesContainShape(drawingController.getRectangleCommandsExecutor().getRectangle()));
	}

	@Test
	public void testDrawCircleIfAccepted() {
		drawingController.getDialogCircle().getRadius().setText("3");
		drawingController.drawCircleIfAccepted(click);
		assertTrue(model.doesContainShape(drawingController.getCircleCommandsExecutor().getCircle()));
	}

	@Test
	public void testDrawDonutIfAccepted() {
		drawingController.getDialogDonut().getRadius().setText("3");
		drawingController.getDialogDonut().getInnerRadius().setText("2");
		drawingController.drawDonutIfAccepted(click);
		assertTrue(model.doesContainShape(drawingController.getDonutCommandsExecutor().getDonut()));
	}

	@Test
	public void testDrawHexagonIfAccepted() {
		drawingController.getDialogHexagon().getRadius().setText("3");
		drawingController.drawHexagonIfAccepted(click);
		assertTrue(model.doesContainShape(drawingController.getHexagonCommandsExecutor().getHexagonAdapter()));
	}

	@Test
	public void testModifyPointIfAccepted() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		new CmdSelect(model, point).execute();
		drawingController.modifyShapeIfAccepted();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(drawingController.getPointCommandsExecutor().getCmdModifyPoint()));
	}

	@Test
	public void testModifyLineIfAccepted() {
		Line line = new Line(new Point(1, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK), false,
				Color.BLACK);
		new CmdAdd(model, line).execute();
		new CmdSelect(model, line).execute();
		drawingController.modifyShapeIfAccepted();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(drawingController.getLineCommandsExecutor().getCmdModifyLine()));
	}

	@Test
	public void testModifyRectangleIfAccepted() {
		Rectangle rectangle = new Rectangle(new Point(1, 2, false, Color.BLACK), 3, 4, false, Color.BLACK, Color.WHITE);
		new CmdAdd(model, rectangle).execute();
		new CmdSelect(model, rectangle).execute();
		drawingController.modifyShapeIfAccepted();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(drawingController.getRectangleCommandsExecutor().getCmdModifyRectangle()));
	}

	@Test
	public void testModifyDonutIfAccepted() {
		Donut donut = new Donut(new Point(1, 2, false, Color.BLACK), 3, 2, false, Color.BLACK, Color.WHITE);
		new CmdAdd(model, donut).execute();
		new CmdSelect(model, donut).execute();
		drawingController.modifyShapeIfAccepted();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(drawingController.getDonutCommandsExecutor().getCmdModifyDonut()));
	}

	@Test
	public void testModifyCircleIfAccepted() {
		Circle circle = new Circle(new Point(1, 2, false, Color.BLACK), 3, false, Color.BLACK, Color.WHITE);
		new CmdAdd(model, circle).execute();
		new CmdSelect(model, circle).execute();
		drawingController.modifyShapeIfAccepted();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(drawingController.getCircleCommandsExecutor().getCmdModifyCircle()));
	}

	@Test
	public void testModifyHexagonIfAccepted() {
		HexagonAdapter hexagon = new HexagonAdapter(new Hexagon(1, 2, 3), false, Color.BLACK, Color.WHITE);
		new CmdAdd(model, hexagon).execute();
		new CmdSelect(model, hexagon).execute();
		drawingController.modifyShapeIfAccepted();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(drawingController.getHexagonCommandsExecutor().getCmdModifyHexagon()));
	}

	@Test
	public void testRemoveShapesIfUserConfirm() {
		Point firstPoint = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, firstPoint).execute();
		new CmdSelect(model, firstPoint).execute();
		Point secondPoint = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, secondPoint).execute();
		new CmdSelect(model, secondPoint).execute();
		drawingController.removeShapesIfUserConfirm();
		assertFalse(model.doesContainShape(firstPoint));
		assertFalse(model.doesContainShape(secondPoint));
	}
}