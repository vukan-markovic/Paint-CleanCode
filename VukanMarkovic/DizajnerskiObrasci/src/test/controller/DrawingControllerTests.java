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
		frame.setController(drawingController);
		click = mock(MouseEvent.class);
	}

	@Test
	public void testSelectShapeShapeIsNotSelected() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		drawingController.selectOrDeselectShapes(click);
		assertTrue(point.isSelected());
	}

	@Test
	public void testSelectShapeShapeIsSelected() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		new CmdSelect(model, point).execute();
		drawingController.selectOrDeselectShapes(click);
		assertFalse(point.isSelected());
	}

	@Test
	public void testSelectShapeDoesNotContainClick() {
		Point firstPoint = new Point(1123, 2213, false, Color.BLACK);
		new CmdAdd(model, firstPoint).execute();
		new CmdSelect(model, firstPoint).execute();
		Point secondPoint = new Point(1233, 2322, false, Color.BLACK);
		new CmdAdd(model, secondPoint).execute();
		new CmdSelect(model, secondPoint).execute();
		drawingController.selectOrDeselectShapes(click);
		assertFalse(firstPoint.isSelected());
		assertFalse(secondPoint.isSelected());
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
	public void testDrawRectangle() {
		drawingController.getDialogRectangle().getheight().setText("1");
		drawingController.getDialogRectangle().getwidth().setText("2");
		drawingController.drawRectangle(click);
		assertTrue(model.doesContainShape(drawingController.getRectangleCommandsExecutor().getRectangle()));
	}

	@Test
	public void testDrawCircle() {
		drawingController.getDialogCircle().getRadius().setText("3");
		drawingController.drawCircle(click);
		assertTrue(model.doesContainShape(drawingController.getCircleCommandsExecutor().getCircle()));
	}

	@Test
	public void testDrawDonut() {
		drawingController.getDialogDonut().getRadius().setText("3");
		drawingController.getDialogDonut().getInnerRadius().setText("2");
		drawingController.drawDonut(click);
		assertTrue(model.doesContainShape(drawingController.getDonutCommandsExecutor().getDonut()));
	}

	@Test
	public void testDrawHexagon() {
		drawingController.getDialogHexagon().getRadius().setText("3");
		drawingController.drawHexagon(click);
		assertTrue(model.doesContainShape(drawingController.getHexagonCommandsExecutor().getHexagonAdapter()));
	}

	@Test
	public void testModifyPoint() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		new CmdSelect(model, point).execute();
		drawingController.modifyShape();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(drawingController.getPointCommandsExecutor().getCmdModifyPoint()));
	}

	@Test
	public void testModifyLine() {
		Line line = new Line(new Point(1, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK), false,
				Color.BLACK);
		new CmdAdd(model, line).execute();
		new CmdSelect(model, line).execute();
		drawingController.modifyShape();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(drawingController.getLineCommandsExecutor().getCmdModifyLine()));
	}

	@Test
	public void testModifyRectangle() {
		Rectangle rectangle = new Rectangle(new Point(1, 2, false, Color.BLACK), 3, 4, false, Color.BLACK, Color.WHITE);
		new CmdAdd(model, rectangle).execute();
		new CmdSelect(model, rectangle).execute();
		drawingController.modifyShape();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(drawingController.getRectangleCommandsExecutor().getCmdModifyRectangle()));
	}

	@Test
	public void testModifyDonut() {
		Donut donut = new Donut(new Point(1, 2, false, Color.BLACK), 3, 2, false, Color.BLACK, Color.WHITE);
		new CmdAdd(model, donut).execute();
		new CmdSelect(model, donut).execute();
		drawingController.modifyShape();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(drawingController.getDonutCommandsExecutor().getCmdModifyDonut()));
	}

	@Test
	public void testModifyCircle() {
		Circle circle = new Circle(new Point(1, 2, false, Color.BLACK), 3, false, Color.BLACK, Color.WHITE);
		new CmdAdd(model, circle).execute();
		new CmdSelect(model, circle).execute();
		drawingController.modifyShape();

		assertTrue(commandsHandler.getExecutedCommands()
				.contains(drawingController.getCircleCommandsExecutor().getCmdModifyCircle()));
	}

	@Test
	public void testModifyHexagon() {
		HexagonAdapter hexagon = new HexagonAdapter(new Hexagon(1, 2, 3), false, Color.BLACK, Color.WHITE);
		new CmdAdd(model, hexagon).execute();
		new CmdSelect(model, hexagon).execute();
		drawingController.modifyShape();

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