package controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;
import commands.*;
import shapes.*;
import stack.CommandsStack;
import frame.DrawingFrame;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.event.MouseEvent;
import hexagon.Hexagon;
import model.DrawingModel;
import org.junit.rules.TemporaryFolder;

public class DrawingControllerTests {
	private OptionsController optionsController;
	private DrawingController controller;
	private DrawingModel model;
	private DrawingFrame frame;
	private MouseEvent click;
	private Queue<String> commandsLog;
	private CommandsStack commandsStack;

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Before
	public void setUp() throws AWTException {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsLog = new LinkedList<String>();
		commandsStack = new CommandsStack();
		optionsController = new OptionsController(model, frame, commandsStack, commandsLog);
		controller = new DrawingController(optionsController);
		frame.getView().setModel(model);
		frame.setController(controller);
		click = mock(MouseEvent.class);
	}

	@Test
	public void testSelectShapeShapeIsNotSelected() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		controller.selectOrDeselectShapes(click);
		assertTrue(point.isSelected());
	}

	@Test
	public void testSelectShapeShapeIsSelected() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		new CmdSelect(model, point).execute();
		controller.selectOrDeselectShapes(click);
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
		controller.selectOrDeselectShapes(click);
		assertFalse(firstPoint.isSelected());
		assertFalse(secondPoint.isSelected());
	}

	@Test
	public void testDrawPoint() {
		controller.drawPoint(click);
		assertTrue(model.doesContainShape(controller.getPointCommandsExecutor().getPoint()));
	}

	@Test
	public void testDrawLineStartPointIsNull() {
		controller.drawLine(click);
		assertFalse(model.doesContainShape(controller.getLineCommandsExecutor().getLine()));
	}

	@Test
	public void testDrawLine() {
		controller.drawLine(click);
		controller.drawLine(click);
		assertTrue(model.doesContainShape(controller.getLineCommandsExecutor().getLine()));
	}

	@Test
	public void testDrawRectangle() {
		controller.getDialogRectangle().getheight().setText("1");
		controller.getDialogRectangle().getwidth().setText("2");
		controller.drawRectangle(click);
		assertTrue(model.doesContainShape(controller.getRectangleCommandsExecutor().getRectangle()));
	}

	@Test
	public void testDrawCircle() {
		controller.getDialogCircle().getRadius().setText("3");
		controller.drawCircle(click);
		assertTrue(model.doesContainShape(controller.getCircleCommandsExecutor().getCircle()));
	}

	@Test
	public void testDrawDonut() {
		controller.getDialogDonut().getRadius().setText("3");
		controller.getDialogDonut().getInnerRadius().setText("2");
		controller.drawDonut(click);
		assertTrue(model.doesContainShape(controller.getDonutCommandsExecutor().getDonut()));
	}

	@Test
	public void testDrawHexagon() {
		controller.getDialogHexagon().getRadius().setText("3");
		controller.drawHexagon(click);
		assertTrue(model.doesContainShape(controller.getHexagonCommandsExecutor().getHexagonAdapter()));
	}

	@Test
	public void testModifyPoint() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		new CmdSelect(model, point).execute();
		controller.modifyShape();

		assertTrue(commandsStack.getExecutedCommands()
				.contains(controller.getPointCommandsExecutor().getCmdModifyPoint()));
	}

	@Test
	public void testModifyLine() {
		Line line = new Line(new Point(1, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK), false,
				Color.BLACK);
		new CmdAdd(model, line).execute();
		new CmdSelect(model, line).execute();
		controller.modifyShape();

		assertTrue(
				commandsStack.getExecutedCommands().contains(controller.getLineCommandsExecutor().getCmdModifyLine()));
	}

	@Test
	public void testModifyRectangle() {
		Rectangle rectangle = new Rectangle(new Point(1, 2, false, Color.BLACK), 3, 4, false, Color.BLACK, Color.WHITE);
		new CmdAdd(model, rectangle).execute();
		new CmdSelect(model, rectangle).execute();
		controller.modifyShape();

		assertTrue(commandsStack.getExecutedCommands()
				.contains(controller.getRectangleCommandsExecutor().getCmdModifyRectangle()));
	}

	@Test
	public void testModifyDonut() {
		Donut donut = new Donut(new Point(1, 2, false, Color.BLACK), 3, 2, false, Color.BLACK, Color.WHITE);
		new CmdAdd(model, donut).execute();
		new CmdSelect(model, donut).execute();
		controller.modifyShape();

		assertTrue(commandsStack.getExecutedCommands()
				.contains(controller.getDonutCommandsExecutor().getCmdModifyDonut()));
	}

	@Test
	public void testModifyCircle() {
		Circle circle = new Circle(new Point(1, 2, false, Color.BLACK), 3, false, Color.BLACK, Color.WHITE);
		new CmdAdd(model, circle).execute();
		new CmdSelect(model, circle).execute();
		controller.modifyShape();

		assertTrue(commandsStack.getExecutedCommands()
				.contains(controller.getCircleCommandsExecutor().getCmdModifyCircle()));
	}

	@Test
	public void testModifyHexagon() {
		HexagonAdapter hexagon = new HexagonAdapter(new Hexagon(1, 2, 3), false, Color.BLACK, Color.WHITE);
		new CmdAdd(model, hexagon).execute();
		new CmdSelect(model, hexagon).execute();
		controller.modifyShape();

		assertTrue(commandsStack.getExecutedCommands()
				.contains(controller.getHexagonCommandsExecutor().getCmdModifyHexagon()));
	}

	@Test
	public void testRemoveShapesIfUserConfirm() {
		Point firstPoint = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, firstPoint).execute();
		new CmdSelect(model, firstPoint).execute();
		Point secondPoint = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, secondPoint).execute();
		new CmdSelect(model, secondPoint).execute();
		controller.removeShapesIfUserConfirm();
		assertFalse(model.doesContainShape(firstPoint));
		assertFalse(model.doesContainShape(secondPoint));
	}
}