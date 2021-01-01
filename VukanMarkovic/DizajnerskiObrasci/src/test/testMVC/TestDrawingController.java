package test.testMVC;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import command.CmdAdd;
import command.CmdBringToFront;
import command.CmdDeselect;
import command.CmdModifyCircle;
import command.CmdModifyDonut;
import command.CmdModifyHexagon;
import command.CmdModifyLine;
import command.CmdModifyPoint;
import command.CmdModifyRectangle;
import command.CmdRemove;
import command.CmdSelect;
import command.CmdSendToBack;
import command.CmdToBack;
import command.CmdToFront;
import command.Command;
import dialogs.DialogCircle;
import dialogs.DialogDonut;
import dialogs.DialogHexagon;
import dialogs.DialogLine;
import dialogs.DialogPoint;
import dialogs.DialogRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import hexagon.Hexagon;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import strategy.SaveLog;
import strategy.SavePainting;
import strategy.StrategyManager;

public class TestDrawingController {
	private DrawingController drawingController;
	private DrawingModel drawingModel;
	private DrawingFrame drawingFrame;
	private Command command;
	private DrawingController drawingControllerMock;
	private MouseEvent click;
	private DialogRectangle dialogRectangle;
	private DialogCircle dialogCircle;
	private DialogDonut dialogDonut;
	private DialogHexagon dialogHexagon;
	private DialogLine dialogLine;
	private DialogPoint dialogPoint;
	private JFileChooser jFileChooser;
	private StrategyManager strategyManager;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() {
		drawingModel = new DrawingModel();
		drawingFrame = new DrawingFrame();
		drawingController = new DrawingController();
		drawingController.setController(drawingModel, drawingFrame);
		drawingFrame.getView().setModel(drawingModel);
		drawingFrame.setController(drawingController);
		command = mock(Command.class);
		drawingControllerMock = spy(DrawingController.class);
		drawingControllerMock.setController(drawingModel, drawingFrame);
		click = mock(MouseEvent.class);
		dialogRectangle = new DialogRectangle();
		dialogCircle = new DialogCircle();
		dialogDonut = new DialogDonut();
		dialogHexagon = new DialogHexagon();
		dialogLine = new DialogLine();
		dialogPoint = new DialogPoint();
		jFileChooser = new JFileChooser();
		strategyManager = mock(StrategyManager.class);
	}

	@Test
	public void testExecuteCommand() {
		drawingController.executeCommand(command);

		verify(command).execute();
		assertTrue(drawingController.getCommandsNormal().contains(command));
	}

	@Test
	public void testLogCommand() {
		String command = "Command";
		drawingController.logCommand(command);

		assertTrue(drawingFrame.getlModel().contains(command));
	}

	@Test
	public void testBtnSelectClicked() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		drawingControllerMock.btnSelectClicked(click);
//		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));

//		verify(drawingControllerMock).executeCommand();
//		verify(drawingControllerMock).logCommand(
//				"Deselect - " + drawingModel.get(0).getClass().getSimpleName() + " " + drawingModel.get(0).toString());
//		verify(drawingControllerMock).fireEvents();
	}

	@Test
	public void testBtnPointClicked() {
		drawingControllerMock.btnPointClicked(click);

		verify(drawingControllerMock, Mockito.times(1)).addPoint(click);
		verify(drawingControllerMock, Mockito.times(1)).clearStack();
	}

	@Test
	public void testAddPoint() {
		Point point = new Point(click.getX(), click.getY(), false, drawingControllerMock.getOuterColor());
		drawingControllerMock.addPoint(click);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
		verify(drawingControllerMock).logCommand("Add - " + point.getClass().getSimpleName() + " " + point);
	}

	@Test
	public void testClearStack() {
		Stack<Command> commands = new Stack<Command>();
		commands.add(new CmdAdd(drawingModel, new Point()));
		drawingController.setCommandsReverse(commands);
		drawingController.clearStack();

		assertTrue(drawingController.getCommandsReverse().isEmpty());
	}

	@Test
	public void testAddLine() {
		Point endPoint = new Point(click.getX(), click.getY(), false, drawingControllerMock.getOuterColor());
		Line line = new Line(drawingControllerMock.getStartPoint(), endPoint, false,
				drawingControllerMock.getOuterColor());
		drawingControllerMock.addLine(click);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
		verify(drawingControllerMock).logCommand("Add - " + line.getClass().getSimpleName() + " " + line);
	}

	@Test
	public void testBtnLineClickedStartPointNull() {
		drawingControllerMock.btnLineClicked(click);

		assertEquals(new Point(click.getX(), click.getY(), false, Color.BLACK), drawingControllerMock.getStartPoint());
	}

	@Test
	public void testBtnLineClickedStartPointNotNull() {
		drawingControllerMock.setStartPoint(new Point());
		drawingControllerMock.btnLineClicked(click);

		verify(drawingControllerMock, Mockito.times(1)).addLine(click);
		verify(drawingControllerMock, Mockito.times(1)).clearStack();
	}

	@Test
	public void testAddRectangle() {
		DialogRectangle dialogRectangle = new DialogRectangle();
		dialogRectangle.setWidth(new JTextField("1"));
		dialogRectangle.setHeight(new JTextField("1"));
		Point point = new Point();

		Rectangle rectangle = new Rectangle(point, Integer.parseInt(dialogRectangle.getheight().getText()),
				Integer.parseInt(dialogRectangle.getwidth().getText()), false, drawingControllerMock.getOuterColor(),
				drawingControllerMock.getInnerColor());

		drawingControllerMock.addRectangle(dialogRectangle, point);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock, Mockito.times(1)).executeCommand(cmdAdd);
		verify(drawingControllerMock, Mockito.times(1)).clearStack();
		verify(drawingControllerMock, Mockito.times(1))
				.logCommand("Add - " + rectangle.getClass().getSimpleName() + " " + rectangle);
	}

	@Test
	public void testCreateRectangleDialog() {
		Point point = new Point(1, 2);
		drawingController.setDialogRectangle(dialogRectangle);
		drawingController.createRectangleDialog(point);

		assertFalse(dialogRectangle.getBtnSetBorderColor().isVisible());
		assertFalse(dialogRectangle.getBtnSetFillColor().isVisible());
		assertEquals("1", dialogRectangle.getXcoordinate().getText());
		assertEquals("2", dialogRectangle.getYcoordinate().getText());
		assertFalse(dialogRectangle.getXcoordinate().isEditable());
		assertFalse(dialogRectangle.getYcoordinate().isEditable());
		assertFalse(dialogRectangle.isVisible());
	}

	@Test
	public void testBtnRectangleClicked() {
		drawingControllerMock.setDialogRectangle(dialogRectangle);
		drawingControllerMock.btnRectangleClicked(click);
		Point point = drawingControllerMock.getPoint();
		DialogRectangle dialogRectangle = drawingControllerMock.getDialogRectangle();

		verify(drawingControllerMock).createRectangleDialog(point);
		verify(drawingControllerMock).addRectangle(dialogRectangle, point);
	}

	@Test
	public void testCreateCircleDialog() {
		Point point = new Point(1, 2);
		drawingController.setDialogCircle(dialogCircle);
		drawingController.createCircleDialog(point);

		assertFalse(dialogCircle.getBtnSetOuterColor().isVisible());
		assertFalse(dialogCircle.getBtnSetInnerColor().isVisible());
		assertEquals("1", dialogCircle.getXcoordinate().getText());
		assertEquals("2", dialogCircle.getYcoordinate().getText());
		assertFalse(dialogCircle.getXcoordinate().isEditable());
		assertFalse(dialogCircle.getYcoordinate().isEditable());
		assertFalse(dialogCircle.isVisible());
	}

	@Test
	public void testAddCircle() {
		DialogCircle dialogCircle = new DialogCircle();
		dialogCircle.setRadius(new JTextField("1"));
		Point point = new Point();

		Circle circle = new Circle(point, Integer.parseInt(dialogCircle.getRadius().getText()), false,
				drawingControllerMock.getOuterColor(), drawingControllerMock.getInnerColor());

		drawingControllerMock.addCircle(dialogCircle, point);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock, Mockito.times(1)).executeCommand(cmdAdd);
		verify(drawingControllerMock, Mockito.times(1)).clearStack();
		verify(drawingControllerMock, Mockito.times(1))
				.logCommand("Add - " + circle.getClass().getSimpleName() + " " + circle);
	}

	@Test
	public void testBtnCircleClicked() {
		drawingControllerMock.setDialogCircle(dialogCircle);
		drawingControllerMock.btnCircleClicked(click);
		Point point = drawingControllerMock.getPoint();
		DialogCircle dialogCircle = drawingControllerMock.getDialogCircle();

		verify(drawingControllerMock).createCircleDialog(point);
		verify(drawingControllerMock).addCircle(dialogCircle, point);
	}

	@Test
	public void testCreateDonutDialog() {
		Point point = new Point(1, 2);
		drawingController.setDialogDonut(dialogDonut);
		drawingController.createDonutDialog(point);

		assertFalse(dialogDonut.getBtnSetOuterColor().isVisible());
		assertFalse(dialogDonut.getBtnSetInnerColor().isVisible());
		assertEquals("1", dialogDonut.getXcoordinate().getText());
		assertEquals("2", dialogDonut.getYcoordinate().getText());
		assertFalse(dialogDonut.getXcoordinate().isEditable());
		assertFalse(dialogDonut.getYcoordinate().isEditable());
		assertFalse(dialogDonut.isVisible());
	}

	@Test
	public void testAddDonut() {
		DialogDonut dialogDonut = new DialogDonut();
		dialogDonut.setRadius(new JTextField("1"));
		dialogDonut.setSmallRadius(new JTextField("1"));
		Point point = new Point();

		Donut donut = new Donut(point, Integer.parseInt(dialogDonut.getRadius().getText()),
				Integer.parseInt(dialogDonut.getSmallRadius().getText()), false, drawingControllerMock.getOuterColor(),
				drawingControllerMock.getInnerColor());

		drawingControllerMock.addDonut(dialogDonut, point);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock, Mockito.times(1)).executeCommand(cmdAdd);
		verify(drawingControllerMock, Mockito.times(1)).clearStack();
		verify(drawingControllerMock, Mockito.times(1))
				.logCommand("Add - " + donut.getClass().getSimpleName() + " " + donut);
	}

	@Test
	public void testBtnDonutClicked() {
		drawingControllerMock.setDialogDonut(dialogDonut);
		drawingControllerMock.btnDonutClicked(click);
		Point point = drawingControllerMock.getPoint();
		DialogDonut dialogDonut = drawingControllerMock.getDialogDonut();

		verify(drawingControllerMock).createDonutDialog(point);
		verify(drawingControllerMock).addDonut(dialogDonut, point);
	}

	@Test
	public void testCreateHexagonDialog() {
		Point point = new Point(1, 2);
		drawingController.setDialogHexagon(dialogHexagon);
		drawingController.createHexagonDialog(point);

		assertFalse(dialogHexagon.getBtnSetOuterColor().isVisible());
		assertFalse(dialogHexagon.getBtnSetInnerColor().isVisible());
		assertEquals("1", dialogHexagon.getXcoordinate().getText());
		assertEquals("2", dialogHexagon.getYcoordinate().getText());
		assertFalse(dialogHexagon.getXcoordinate().isEditable());
		assertFalse(dialogHexagon.getYcoordinate().isEditable());
		assertFalse(dialogHexagon.isVisible());
	}

	@Test
	public void testAddHexagon() {
		DialogHexagon dialogHexagon = new DialogHexagon();
		dialogHexagon.setRadius(new JTextField("1"));
		Point point = new Point();

		HexagonAdapter hexagon = new HexagonAdapter(
				new Hexagon(point.getX(), point.getY(), Integer.parseInt(dialogHexagon.getRadius().getText())),
				drawingControllerMock.getOuterColor(), drawingControllerMock.getInnerColor());

		drawingControllerMock.addHexagon(dialogHexagon, point);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock, Mockito.times(1)).executeCommand(cmdAdd);
		verify(drawingControllerMock, Mockito.times(1)).clearStack();
		verify(drawingControllerMock, Mockito.times(1))
				.logCommand("Add - " + hexagon.getClass().getSimpleName() + " " + hexagon);
	}

	@Test
	public void testBtnHexagonClicked() {
		drawingControllerMock.setDialogHexagon(dialogHexagon);
		drawingControllerMock.btnHexagonClicked(click);
		Point point = drawingControllerMock.getPoint();
		DialogHexagon dialogHexagon = drawingControllerMock.getDialogHexagon();

		verify(drawingControllerMock).createHexagonDialog(point);
		verify(drawingControllerMock).addHexagon(dialogHexagon, point);
	}

	@Test
	public void testCreatePointDialog() {
		Point point = new Point(1, 2);
		drawingController.setDialogPoint(dialogPoint);
		drawingController.createPointDialog(point);

		assertEquals("1", dialogPoint.getXcoordinate().getText());
		assertEquals("2", dialogPoint.getYcoordinate().getText());
		assertEquals(Color.BLACK, dialogPoint.getOuterColor());
		assertEquals(Color.BLACK, dialogPoint.getBtnSetOuterColor().getBackground());
		assertFalse(dialogLine.isVisible());
	}

	@Test
	public void testModifyPoint() {
		Point oldState = new Point(1, 2);
		dialogPoint.getXcoordinate().setText("1");
		dialogPoint.getYcoordinate().setText("2");

		Point newState = new Point(Integer.parseInt(dialogPoint.getXcoordinate().getText()),
				Integer.parseInt(dialogPoint.getYcoordinate().getText()), oldState.isSelected(),
				dialogPoint.getOuterColor());

		Point originalState = oldState.clone();

		drawingControllerMock.modifyPoint(oldState, dialogPoint);
		CmdModifyPoint cmdModifyPoint = drawingControllerMock.getCmdModifyPoint();

		verify(drawingControllerMock).logCommand(
				"Modify - " + newState.getClass().getSimpleName() + " from " + originalState + " to " + " " + newState);
		verify(drawingControllerMock).executeCommand(cmdModifyPoint);
		verify(drawingControllerMock).clearStack();
	}

	@Test
	public void testCreateLineDialog() {
		Line line = new Line(new Point(1, 2), new Point(3, 4));
		drawingController.setDialogLine(dialogLine);
		drawingController.createLineDialog(line);

		assertEquals("1", dialogLine.getX1coordinate().getText());
		assertEquals("3", dialogLine.getX2coordinate().getText());
		assertEquals("2", dialogLine.getY1coordinate().getText());
		assertEquals("4", dialogLine.getY2coordinate().getText());
		assertEquals(Color.BLACK, dialogLine.getOuterColor());
		assertEquals(Color.BLACK, dialogLine.getBtnSetOuterColor().getBackground());
		assertFalse(dialogLine.isVisible());
	}

	@Test
	public void testModifyLine() {
		Line oldState = new Line(new Point(1, 2), new Point(3, 4));
		dialogLine.getX1coordinate().setText("1");
		dialogLine.getY1coordinate().setText("2");
		dialogLine.getX2coordinate().setText("3");
		dialogLine.getY2coordinate().setText("4");

		Line newState = new Line(
				new Point(Integer.parseInt(dialogLine.getX1coordinate().getText()),
						Integer.parseInt(dialogLine.getY1coordinate().getText()), oldState.isSelected(),
						dialogLine.getOuterColor()),
				new Point(Integer.parseInt(dialogLine.getX2coordinate().getText()),
						Integer.parseInt(dialogLine.getY2coordinate().getText()), oldState.isSelected(),
						dialogLine.getOuterColor()),
				oldState.isSelected(), dialogLine.getOuterColor());

		Line originalState = oldState.clone();

		drawingControllerMock.modifyLine(oldState, dialogLine);
		CmdModifyLine cmdModifyLine = drawingControllerMock.getCmdModifyLine();

		verify(drawingControllerMock).logCommand(
				"Modify - " + newState.getClass().getSimpleName() + " from " + originalState + " to " + " " + newState);
		verify(drawingControllerMock).executeCommand(cmdModifyLine);
		verify(drawingControllerMock).clearStack();
	}

	@Test
	public void testCreateRectangleModifyDialog() {
		Rectangle rectangle = new Rectangle(new Point(1, 2), 3, 4);
		drawingController.setDialogRectangle(dialogRectangle);
		drawingController.createRectangleModifyDialog(rectangle);

		assertEquals("1", dialogRectangle.getXcoordinate().getText());
		assertEquals("2", dialogRectangle.getYcoordinate().getText());
		assertEquals("3", dialogRectangle.getheight().getText());
		assertEquals("4", dialogRectangle.getwidth().getText());
		assertEquals(Color.BLACK, dialogRectangle.getOuterColor());
		assertEquals(new Color(0, 0, 0, 0), dialogRectangle.getInnerColor());
		assertEquals(Color.BLACK, dialogRectangle.getBtnSetBorderColor().getBackground());
		assertEquals(new Color(0, 0, 0, 0), dialogRectangle.getBtnSetFillColor().getBackground());
		assertFalse(dialogRectangle.isVisible());
	}

	@Test
	public void testModifyRectangle() {
		Rectangle oldState = new Rectangle(new Point(1, 2), 3, 4);
		dialogRectangle.getXcoordinate().setText("1");
		dialogRectangle.getYcoordinate().setText("2");
		dialogRectangle.getheight().setText("3");
		dialogRectangle.getwidth().setText("4");

		Rectangle newState = new Rectangle(
				new Point(Integer.parseInt(dialogRectangle.getXcoordinate().getText()),
						Integer.parseInt(dialogRectangle.getYcoordinate().getText()), oldState.isSelected(),
						dialogRectangle.getOuterColor()),
				Integer.parseInt(dialogRectangle.getheight().getText()),
				Integer.parseInt(dialogRectangle.getwidth().getText()), oldState.isSelected(),
				dialogRectangle.getOuterColor(), dialogRectangle.getInnerColor());

		drawingControllerMock.modifyRectangle(oldState, dialogRectangle);
		CmdModifyRectangle cmdModifyRectangle = drawingControllerMock.getCmdModifyRectangle();

		verify(drawingControllerMock).logCommand(
				"Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
		verify(drawingControllerMock).executeCommand(cmdModifyRectangle);
		verify(drawingControllerMock).clearStack();
	}

	@Test
	public void testCreateDonutModifyDialog() {
		Donut donut = new Donut(new Point(1, 2), 4, 3, true, Color.BLACK, Color.BLACK);
		drawingController.setDialogDonut(dialogDonut);
		drawingController.createDonutModifyDialog(donut);

		assertEquals("1", dialogDonut.getXcoordinate().getText());
		assertEquals("2", dialogDonut.getYcoordinate().getText());
		assertEquals("4", dialogDonut.getRadius().getText());
		assertEquals("3", dialogDonut.getSmallRadius().getText());
		assertEquals(Color.BLACK, dialogDonut.getOuterColor());
		assertEquals(Color.BLACK, dialogDonut.getInnerColor());
		assertEquals(Color.BLACK, dialogDonut.getBtnSetOuterColor().getBackground());
		assertEquals(Color.BLACK, dialogDonut.getBtnSetInnerColor().getBackground());
		assertFalse(dialogDonut.isVisible());
	}

	@Test
	public void testModifyDonut() {
		Donut oldState = new Donut(new Point(1, 2), 3, 4, true, Color.BLACK, Color.BLACK);
		dialogDonut.getXcoordinate().setText("1");
		dialogDonut.getYcoordinate().setText("2");
		dialogDonut.setRadius(new JTextField("4"));
		dialogDonut.setSmallRadius(new JTextField("3"));

		Donut newState = new Donut(
				new Point(Integer.parseInt(dialogDonut.getXcoordinate().getText()),
						Integer.parseInt(dialogDonut.getYcoordinate().getText()), oldState.isSelected(),
						dialogDonut.getOuterColor()),
				Integer.parseInt(dialogDonut.getRadius().getText()),
				Integer.parseInt(dialogDonut.getSmallRadius().getText()), oldState.isSelected(),
				dialogDonut.getOuterColor(), dialogDonut.getInnerColor());

		Donut originalState = oldState.clone();

		drawingControllerMock.modifyDonut(oldState, dialogDonut);
		CmdModifyDonut cmdModifyDonut = drawingControllerMock.getCmdModifyDonut();

		verify(drawingControllerMock).logCommand(
				"Modify - " + newState.getClass().getSimpleName() + " from " + originalState + " to " + " " + newState);
		verify(drawingControllerMock).executeCommand(cmdModifyDonut);
		verify(drawingControllerMock).clearStack();
	}

	@Test
	public void testCreateCircletModifyDialog() {
		Circle circle = new Circle(new Point(1, 2), 3);
		drawingController.setDialogCircle(dialogCircle);
		drawingController.createCircleModifyDialog(circle);

		assertEquals("1", dialogCircle.getXcoordinate().getText());
		assertEquals("2", dialogCircle.getYcoordinate().getText());
		assertEquals("3", dialogCircle.getRadius().getText());
		assertEquals(Color.BLACK, dialogCircle.getOuterColor());
		assertEquals(new Color(0, 0, 0, 0), dialogCircle.getInnerColor());
		assertEquals(Color.BLACK, dialogCircle.getBtnSetOuterColor().getBackground());
		assertEquals(new Color(0, 0, 0, 0), dialogCircle.getBtnSetInnerColor().getBackground());
		assertFalse(dialogCircle.isVisible());
	}

	@Test
	public void testModifyCircle() {
		Circle oldState = new Circle(new Point(1, 2), 3);
		dialogCircle.getXcoordinate().setText("1");
		dialogCircle.getYcoordinate().setText("2");
		dialogCircle.setRadius(new JTextField("3"));

		Circle newState = new Circle(
				new Point(Integer.parseInt(dialogCircle.getXcoordinate().getText()),
						Integer.parseInt(dialogCircle.getYcoordinate().getText()), oldState.isSelected(),
						dialogCircle.getOuterColor()),
				Integer.parseInt(dialogCircle.getRadius().getText()), oldState.isSelected(),
				dialogCircle.getOuterColor(), dialogCircle.getInnerColor());

		drawingControllerMock.modifyCircle(oldState, dialogCircle);
		CmdModifyCircle cmdModifyCircle = drawingControllerMock.getCmdModifyCircle();

		verify(drawingControllerMock).logCommand(
				"Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
		verify(drawingControllerMock).executeCommand(cmdModifyCircle);
		verify(drawingControllerMock).clearStack();
	}

	@Test
	public void testCreateHexagonModifyDialog() {
		HexagonAdapter hexagonAdapter = new HexagonAdapter(1, 2, 3, Color.BLACK, Color.BLACK, true);
		drawingController.setDialogHexagon(dialogHexagon);
		drawingController.createHexagonModifyDialog(hexagonAdapter);

		assertEquals("1", dialogHexagon.getXcoordinate().getText());
		assertEquals("2", dialogHexagon.getYcoordinate().getText());
		assertEquals("3", dialogHexagon.getRadius().getText());
		assertEquals(Color.BLACK, dialogHexagon.getOuterColor());
		assertEquals(Color.BLACK, dialogHexagon.getInnerColor());
		assertEquals(Color.BLACK, dialogHexagon.getBtnSetOuterColor().getBackground());
		assertEquals(Color.BLACK, dialogHexagon.getBtnSetInnerColor().getBackground());
		assertFalse(dialogHexagon.isVisible());
	}

	@Test
	public void testModifyHexagon() {
		HexagonAdapter oldState = new HexagonAdapter(1, 2, 3, Color.BLACK, Color.BLACK, true);
		dialogHexagon.getXcoordinate().setText("1");
		dialogHexagon.getYcoordinate().setText("2");
		dialogHexagon.setRadius(new JTextField("4"));

		HexagonAdapter newState = new HexagonAdapter(Integer.parseInt(dialogHexagon.getXcoordinate().getText()),
				Integer.parseInt(dialogHexagon.getYcoordinate().getText()),
				Integer.parseInt(dialogHexagon.getRadius().getText()), dialogHexagon.getOuterColor(),
				dialogHexagon.getInnerColor(), oldState.isSelected());

		HexagonAdapter originalState = oldState.clone();

		drawingControllerMock.modifyHexagon(oldState, dialogHexagon);
		CmdModifyHexagon cmdModifyHexagon = drawingControllerMock.getCmdModifyHexagon();

		verify(drawingControllerMock).logCommand(
				"Modify - " + newState.getClass().getSimpleName() + " from " + originalState + " to " + " " + newState);
		verify(drawingControllerMock).executeCommand(cmdModifyHexagon);
		verify(drawingControllerMock).clearStack();
	}

	@Test
	public void testModifySizeNotOne() {
		Point point = new Point(1, 2, true, Color.BLACK);
		Line line = new Line(new Point(1, 2), new Point(3, 4));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, line));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(1), drawingModel));
		drawingControllerMock.modify();
		DialogPoint dialogPoint = drawingControllerMock.getDialogPoint();

		verify(drawingControllerMock, never()).createPointDialog(point);
		verify(drawingControllerMock, never()).modifyPoint(point, dialogPoint);
	}

	@Test
	public void testModifyPointInstance() {
		Point point = new Point(1, 2, true, Color.BLACK);
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.modify();
		DialogPoint dialogPoint = drawingControllerMock.getDialogPoint();

		verify(drawingControllerMock).createPointDialog(point);
		verify(drawingControllerMock).modifyPoint(point, dialogPoint);
	}

	@Test
	public void testModifyLineInstance() {
		Line line = new Line(new Point(1, 2), new Point(3, 4));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, line));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.modify();
		DialogLine dialogLine = drawingControllerMock.getDialogLine();

		verify(drawingControllerMock).createLineDialog(line);
		verify(drawingControllerMock).modifyLine(line, dialogLine);
	}

	@Test
	public void testModifyRectangleInstance() {
		Rectangle rectangle = new Rectangle(new Point(1, 2), 3, 4);
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, rectangle));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.modify();
		DialogRectangle dialogRectangle = drawingControllerMock.getDialogRectangle();

		verify(drawingControllerMock).createRectangleModifyDialog(rectangle);
		verify(drawingControllerMock).modifyRectangle(rectangle, dialogRectangle);
	}

	@Test
	public void testModifyDonutInstance() {
		Point center = new Point(1, 2);
		Donut donut = new Donut(center, 3, 4, true, Color.BLACK, Color.BLACK);
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, donut));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.modify();
		DialogDonut dialogDonut = drawingControllerMock.getDialogDonut();

		verify(drawingControllerMock).createDonutModifyDialog(donut);
		verify(drawingControllerMock).modifyDonut(donut, dialogDonut);
	}

	@Test
	public void testModifyCircleInstance() {
		Point center = new Point(1, 2);
		Circle circle = new Circle(center, 3);
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, circle));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.modify();
		DialogCircle dialogCircle = drawingControllerMock.getDialogCircle();

		verify(drawingControllerMock).createCircleModifyDialog(circle);
		verify(drawingControllerMock).modifyCircle(circle, dialogCircle);
	}

	@Test
	public void testModifyHexagonInstance() {
		Point center = new Point(1, 2);
		HexagonAdapter hexagon = new HexagonAdapter(center.getX(), center.getY(), 3, Color.BLACK, Color.BLACK, true);
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, hexagon));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.modify();
		DialogHexagon dialogHexagon = drawingControllerMock.getDialogHexagon();

		verify(drawingControllerMock).createHexagonModifyDialog(hexagon);
		verify(drawingControllerMock).modifyHexagon(hexagon, dialogHexagon);
	}

	@Test
	public void testDeleteNoSelectedShapes() {
		drawingControllerMock.delete();

		verify(drawingControllerMock, never()).deleteForLogs();
		verify(drawingControllerMock, never()).logCommand("Deleted");
		verify(drawingControllerMock, never()).fireEvents();
	}

	@Test
	public void testDelete() {
		HexagonAdapter hexagon = new HexagonAdapter(1, 2, 3, Color.BLACK, Color.BLACK, true);
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, hexagon));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.delete();

		verify(drawingControllerMock).deleteForLogs();
		verify(drawingControllerMock).logCommand("Deleted");
		verify(drawingControllerMock).fireEvents();
	}

	@Test
	public void testDeleteLogs() {
		Point point = new Point(1, 2, true, Color.BLACK);
		Line line = new Line(new Point(1, 2), new Point(3, 4));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, line));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(1), drawingModel));
		drawingControllerMock.deleteForLogs();
		CmdRemove cmdRemove = drawingControllerMock.getCmdRemove();

		verify(drawingControllerMock).executeCommand(cmdRemove);
		verify(drawingControllerMock, Mockito.times(2)).clearStack();
	}

	@Test
	public void testUndoNoCommands() {
		drawingControllerMock.undo();

		verify(drawingControllerMock, never()).undoForLog();
		verify(drawingControllerMock, never()).logCommand("Undo");
		verify(drawingControllerMock, never()).fireUndoRedo();
		verify(drawingControllerMock, never()).fireEvents();
	}

	@Test
	public void testUndo() {
		Line line = new Line(new Point(1, 2), new Point(3, 4));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, line));
		drawingControllerMock.undo();

		verify(drawingControllerMock).undoForLog();
		verify(drawingControllerMock).logCommand("Undo");
		verify(drawingControllerMock).fireUndoRedo();
		verify(drawingControllerMock).fireEvents();
	}

	@Test
	public void testUndoForLog() {
		CmdAdd cmdAdd = new CmdAdd(drawingModel, new Line(new Point(1, 2), new Point(3, 4)));
		drawingController.executeCommand(cmdAdd);
		drawingController.undoForLog();

		assertTrue(drawingController.getCommandsReverse().contains(cmdAdd));
		assertFalse(drawingController.getCommandsNormal().contains(cmdAdd));
	}

	@Test
	public void testRedoNoCommands() {
		drawingControllerMock.redo();

		verify(drawingControllerMock, never()).redoForLog();
		verify(drawingControllerMock, never()).logCommand("Redo");
		verify(drawingControllerMock, never()).fireUndoRedo();
		verify(drawingControllerMock, never()).fireEvents();
	}

	@Test
	public void testRedo() {
		drawingController.executeCommand(new CmdAdd(drawingModel, new Line(new Point(1, 2), new Point(3, 4))));
		drawingControllerMock.redo();

		verify(drawingControllerMock).redoForLog();
		verify(drawingControllerMock).logCommand("Redo");
		verify(drawingControllerMock).fireUndoRedo();
		verify(drawingControllerMock).fireEvents();
	}

	@Test(expected = EmptyStackException.class)
	public void testRedoForLogEmptyStackExceptionExpected() {
		drawingControllerMock.redoForLog();
	}

	@Test(expected = EmptyStackException.class)
	public void testRedoForLogUndoNotCalledExceptionExpected() {
		Line line = new Line(new Point(1, 2), new Point(3, 4));
		drawingController.executeCommand(new CmdAdd(drawingModel, line));
		drawingControllerMock.redoForLog();
	}

	@Test
	public void testRedoForLog() {
		CmdAdd cmdAdd = new CmdAdd(drawingModel, new Line(new Point(1, 2), new Point(3, 4)));
		drawingController.executeCommand(cmdAdd);
		drawingController.undoForLog();
		drawingController.redoForLog();
		assertFalse(drawingController.getCommandsReverse().contains(cmdAdd));
		assertTrue(drawingController.getCommandsNormal().contains(cmdAdd));
	}

	@Test
	public void testOuterColorNull() {
		drawingController.outerColor();
		assertNotEquals(drawingFrame.getBtnOuterCol().getBackground(), drawingController.getOuterColor());
		assertNull(drawingController.getOuterColor());
	}

	@Test
	public void testOuterColor() {
		drawingController.outerColor();
		assertEquals(drawingFrame.getBtnOuterCol().getBackground(), drawingController.getOuterColor());
	}

	@Test
	public void testInnerColorNull() {
		drawingController.innerColor();
		assertNotEquals(drawingFrame.getBtnInnerCol().getBackground(), drawingController.getInnerColor());
		assertNull(drawingController.getInnerColor());
	}

	@Test
	public void testInnerColor() {
		drawingController.innerColor();
		assertEquals(drawingFrame.getBtnInnerCol().getBackground(), drawingController.getInnerColor());
	}

	@Test
	public void testToBackMultipleSelectedShapes() {
		Point point = new Point(1, 2, true, Color.BLACK);
		Line line = new Line(new Point(1, 2), new Point(3, 4));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, line));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(1), drawingModel));
		drawingControllerMock.toBack();
		CmdToBack cmdToBack = drawingControllerMock.getCmdToBack();

		verify(drawingControllerMock, never()).executeCommand(cmdToBack);
		verify(drawingControllerMock, never()).clearStack();
		verify(drawingControllerMock, never())
				.logCommand("ToBack - " + point.getClass().getSimpleName() + " " + point.toString());
		verify(drawingControllerMock, never())
				.logCommand("ToBack - " + line.getClass().getSimpleName() + " " + line.toString());
	}

	@Test
	public void testToBack() {
		Point point = new Point(1, 2, true, Color.BLACK);
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.toBack();
		CmdToBack cmdToBack = drawingControllerMock.getCmdToBack();

		verify(drawingControllerMock).executeCommand(cmdToBack);
		verify(drawingControllerMock).clearStack();
		verify(drawingControllerMock)
				.logCommand("ToBack - " + point.getClass().getSimpleName() + " " + point.toString());
	}

	@Test
	public void testToFrontMultipleSelectedShapes() {
		Point point = new Point(1, 2, true, Color.BLACK);
		Line line = new Line(new Point(1, 2), new Point(3, 4));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, line));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(1), drawingModel));
		drawingControllerMock.toFront();
		CmdToFront cmdToFront = drawingControllerMock.getCmdToFront();

		verify(drawingControllerMock, never()).executeCommand(cmdToFront);
		verify(drawingControllerMock, never()).clearStack();
		verify(drawingControllerMock, never())
				.logCommand("ToFront - " + point.getClass().getSimpleName() + " " + point.toString());
		verify(drawingControllerMock, never())
				.logCommand("ToFront - " + line.getClass().getSimpleName() + " " + line.toString());
	}

	@Test
	public void testToFront() {
		Point point = new Point(1, 2, true, Color.BLACK);
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.toFront();
		CmdToFront cmdToFront = drawingControllerMock.getCmdToFront();

		verify(drawingControllerMock).executeCommand(cmdToFront);
		verify(drawingControllerMock).clearStack();
		verify(drawingControllerMock)
				.logCommand("ToFront - " + point.getClass().getSimpleName() + " " + point.toString());
	}

	@Test
	public void testSendToBackMultipleSelectedShapes() {
		Point point = new Point(1, 2, true, Color.BLACK);
		Line line = new Line(new Point(1, 2), new Point(3, 4));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, line));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(1), drawingModel));
		drawingControllerMock.sendToBack();
		CmdSendToBack cmdSendToBack = drawingControllerMock.getCmdSendToBack();

		verify(drawingControllerMock, never()).executeCommand(cmdSendToBack);
		verify(drawingControllerMock, never()).clearStack();
		verify(drawingControllerMock, never())
				.logCommand("SendToBack - " + point.getClass().getSimpleName() + " " + point.toString());
		verify(drawingControllerMock, never())
				.logCommand("SendToBack - " + line.getClass().getSimpleName() + " " + line.toString());
	}

	@Test
	public void testSendToBack() {
		Point point = new Point(1, 2, true, Color.BLACK);
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.sendToBack();
		CmdSendToBack cmdSendToBack = drawingControllerMock.getCmdSendToBack();

		verify(drawingControllerMock).executeCommand(cmdSendToBack);
		verify(drawingControllerMock).clearStack();
		verify(drawingControllerMock)
				.logCommand("SendToBack - " + point.getClass().getSimpleName() + " " + point.toString());
	}

	@Test
	public void testBringToFrontMultipleSelectedShapes() {
		Point point = new Point(1, 2, true, Color.BLACK);
		Line line = new Line(new Point(1, 2), new Point(3, 4));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, line));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(1), drawingModel));
		drawingControllerMock.bringToFront();
		CmdBringToFront cmdBringToFront = drawingControllerMock.getCmdBringToFront();

		verify(drawingControllerMock, never()).executeCommand(cmdBringToFront);
		verify(drawingControllerMock, never()).clearStack();
		verify(drawingControllerMock, never())
				.logCommand("BringToFront - " + point.getClass().getSimpleName() + " " + point.toString());
		verify(drawingControllerMock, never())
				.logCommand("BringToFront - " + line.getClass().getSimpleName() + " " + line.toString());
	}

	@Test
	public void testBringToFront() {
		Point point = new Point(1, 2, true, Color.BLACK);
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingControllerMock.bringToFront();
		CmdBringToFront cmdBringToFront = drawingControllerMock.getCmdBringToFront();

		verify(drawingControllerMock).executeCommand(cmdBringToFront);
		verify(drawingControllerMock).clearStack();
		verify(drawingControllerMock)
				.logCommand("BringToFront - " + point.getClass().getSimpleName() + " " + point.toString());
	}

	@Test
	public void testFireEventsNoSelectedShapes() {
		drawingController.fireEvents();
		assertFalse(drawingFrame.getBtnDelete().isEnabled());
		assertFalse(drawingFrame.getBtnModify().isEnabled());
		assertFalse(drawingFrame.getBtnToBack().isEnabled());
		assertFalse(drawingFrame.getBtnToFront().isEnabled());
		assertFalse(drawingFrame.getBtnSendToBack().isEnabled());
		assertFalse(drawingFrame.getBtnBringToFront().isEnabled());
	}

	@Test
	public void testFireEvents() {
		Point point = new Point(1, 2, true, Color.BLACK);
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(drawingModel.get(0), drawingModel));
		drawingController.fireEvents();
		assertTrue(drawingFrame.getBtnDelete().isEnabled());
		assertTrue(drawingFrame.getBtnModify().isEnabled());
		assertTrue(drawingFrame.getBtnToBack().isEnabled());
		assertTrue(drawingFrame.getBtnToFront().isEnabled());
		assertTrue(drawingFrame.getBtnSendToBack().isEnabled());
		assertTrue(drawingFrame.getBtnBringToFront().isEnabled());
	}

	@Test
	public void testSaveDrawing() throws IOException {
		SavePainting savePainting = new SavePainting();
		drawingController.setStrategy(strategyManager);
		drawingController.setPainting(savePainting);
		File file = folder.newFile(drawingFrame.getFileName().getText() + ".bin");
		jFileChooser.setSelectedFile(file);
		drawingController.setFileChooser(jFileChooser);
		drawingController.saveDrawing();

		assertEquals(drawingModel.getShapes(), savePainting.getShapes());
		verify(strategyManager).setStrategy(savePainting);
		verify(strategyManager).save(jFileChooser.getSelectedFile().getAbsolutePath() + "\\" + file.getName());
	}

	@Test
	public void testSaveLog() throws IOException {
		SaveLog saveLog = new SaveLog();
		drawingController.setStrategy(strategyManager);
		drawingController.setSaveLog(saveLog);
		File file = folder.newFile(drawingFrame.getFileName().getText() + ".txt");
		jFileChooser.setSelectedFile(file);
		drawingController.setFileChooser(jFileChooser);
		drawingController.saveLog();

		assertEquals(drawingController.getLog(), saveLog.getLog());
		verify(strategyManager).setStrategy(saveLog);
		verify(strategyManager).save(jFileChooser.getSelectedFile().getAbsolutePath() + "\\" + file.getName());
	}

	@Test
	public void testShowFileChooser() {
		drawingController.setFileChooser(jFileChooser);
		int result = drawingController.showFileChooser("Title", JFileChooser.DIRECTORIES_ONLY);

		assertEquals("Title", jFileChooser.getDialogTitle());
		assertEquals(JFileChooser.DIRECTORIES_ONLY, jFileChooser.getFileSelectionMode());
		assertEquals(JFileChooser.APPROVE_OPTION, result);
	}

	@Test
	public void testSaveNoShapes() {
		drawingControllerMock.save();
		verify(drawingControllerMock, never()).saveLog();
		verify(drawingControllerMock, never()).saveDrawing();
	}

	@Test
	public void testSave() {
		Point point = new Point(1, 2, true, Color.BLACK);
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.save();
		verify(drawingControllerMock).saveLog();
		verify(drawingControllerMock).saveDrawing();
	}

	@Test
	public void testLoadLog() {
		drawingControllerMock.loadLog();
		jFileChooser = drawingControllerMock.getFileChooser();

		assertTrue(drawingFrame.getBtnNext().isEnabled());
		assertTrue(drawingControllerMock.getLogCommands().isEmpty());
		verify(drawingControllerMock).addLog();
	}

	@Test
	public void testAddLog() throws IOException {
		File file = folder.newFile("log.txt");
		jFileChooser.setSelectedFile(file);
		drawingController.setFileChooser(jFileChooser);
		BufferedReader buffer = new BufferedReader(new FileReader(jFileChooser.getSelectedFile().getAbsolutePath()));
		drawingController.addLog();
		assertEquals(drawingController.getLogCommands(), buffer.lines().collect(Collectors.joining("\n")));
	}

	@Test
	public void testAddPainting() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = folder.newFile("paint.bin");
		jFileChooser.setSelectedFile(file);
		drawingController.setFileChooser(jFileChooser);
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(drawingController.getFileChooser().getSelectedFile().getAbsoluteFile()));
		drawingController.addPainting();
		assertEquals(ois.readObject(), drawingModel.getShapes());
	}

	@Test
	public void testLoadPainting() {
		drawingControllerMock.loadPainting();
		verify(drawingControllerMock).addPainting();
	}

	@Test(expected = NumberFormatException.class)
	public void testAddPointFromLogWrongLogExceptionExpected() {
		String[] line = "Add - Point (x: 86A , y: 137 , Border color: -360334 )".split(" ");
		drawingControllerMock.addPointFromLog(line);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
	}

	@Test
	public void testAddPointFromLog() {
		String[] line = "Add - Point (x: 86 , y: 137 , Border color: -360334 )".split(" ");
		drawingControllerMock.addPointFromLog(line);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
	}

	@Test(expected = NumberFormatException.class)
	public void testAddLineFromLogWrongLogExceptionExpected() {
		String[] line = "Add - Line Start point: (x: 359 , y: 226 , Border color: -360334 ), End point: (x: 237 , y: 1C17 , Border color: -360334 ), Border color: -360334"
				.split(" ");

		drawingControllerMock.addLineFromLog(line);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
	}

	@Test
	public void testAddLineFromLog() {
		String[] line = "Add - Line Start point: (x: 359 , y: 226 , Border color: -360334 ), End point: (x: 237 , y: 117 , Border color: -360334 ), Border color: -360334"
				.split(" ");

		drawingControllerMock.addLineFromLog(line);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
	}

	@Test(expected = NumberFormatException.class)
	public void testAddRectangleFromLogWrongLogExceptionExpected() {
		String[] line = "Add - Rectangle Upper left point: (x: 419 , y: 148 , Border color: -360334 ), height: 12sad , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.addRectangleFromLog(line);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
	}

	@Test
	public void testAddRectangleFromLog() {
		String[] line = "Add - Rectangle Upper left point: (x: 419 , y: 148 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.addRectangleFromLog(line);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
	}

	@Test(expected = NumberFormatException.class)
	public void testAddCircleFromLogWrongLogExceptionExpected() {
		String[] line = "Add - Circle Center: (x: 551 , y: 162 , Border color: -3sadd60334 ), radius: 12 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.addCircleFromLog(line);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
	}

	@Test
	public void testAddCircleFromLog() {
		String[] line = "Add - Circle Center: (x: 551 , y: 162 , Border color: -360334 ), radius: 12 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.addCircleFromLog(line);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
	}

	@Test(expected = NumberFormatException.class)
	public void testAddDonutFromLogWrongLogExceptionExpected() {
		String[] line = "Add - Donut Center: (x: 214 , y: 173 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171 , inner radius: 12 , Border color: -36s0334 , Fill Color: -5171"
				.split(" ");

		drawingControllerMock.addDonutFromLog(line);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
	}

	@Test
	public void testAddDonutFromLog() {
		String[] line = "Add - Donut Center: (x: 214 , y: 173 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171 , inner radius: 12 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		drawingControllerMock.addDonutFromLog(line);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
	}

	@Test(expected = NumberFormatException.class)
	public void testAddHexagonFromLogWrongLogExceptionExpected() {
		String[] line = "Add - HexagonAdapter Center: (x: 267 , y: 302 , Border color: -360qw334 ), radius: 123 , Border color: -36033d4 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.addHexagonFromLog(line);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
	}

	@Test
	public void testAddHexagonFromLog() {
		String[] line = "Add - HexagonAdapter Center: (x: 267 , y: 302 , Border color: -360334 ), radius: 123 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.addHexagonFromLog(line);
		CmdAdd cmdAdd = drawingControllerMock.getCmdAdd();

		verify(drawingControllerMock).executeCommand(cmdAdd);
	}

	@Test(expected = NumberFormatException.class)
	public void testSelectPointWrongLogExceptionExpected() {
		String[] line = "Select - Point (x: 45we , y: 78 , Border color: -360334 )".split(" ");
		drawingControllerMock.selectPoint(line);
		CmdSelect cmdSelect = drawingControllerMock.getCmdSelect();

		verify(drawingControllerMock).executeCommand(cmdSelect);
	}

	@Test
	public void testSelectPoint() {
		String[] line = "Select - Point (x: 45 , y: 78 , Border color: -360334 )".split(" ");
		drawingControllerMock.selectPoint(line);
		CmdSelect cmdSelect = drawingControllerMock.getCmdSelect();

		verify(drawingControllerMock).executeCommand(cmdSelect);
	}

	@Test(expected = NumberFormatException.class)
	public void testSelectLineWrongLogExceptionExpected() {
		String[] line = "Select - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334we"
				.split(" ");

		drawingControllerMock.selectLine(line);
		CmdSelect cmdSelect = drawingControllerMock.getCmdSelect();

		verify(drawingControllerMock).executeCommand(cmdSelect);
	}

	@Test
	public void testSelectLine() {
		String[] line = "Select - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
				.split(" ");

		drawingControllerMock.selectLine(line);
		CmdSelect cmdSelect = drawingControllerMock.getCmdSelect();

		verify(drawingControllerMock).executeCommand(cmdSelect);
	}

	@Test(expected = NumberFormatException.class)
	public void testSelectRectangleWrongLogExceptionExpected() {
		String[] line = "Select - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171we"
				.split(" ");

		drawingControllerMock.selectRectangle(line);
		CmdSelect cmdSelect = drawingControllerMock.getCmdSelect();

		verify(drawingControllerMock).executeCommand(cmdSelect);
	}

	@Test
	public void testSelectRectangle() {
		String[] line = "Select - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.selectRectangle(line);
		CmdSelect cmdSelect = drawingControllerMock.getCmdSelect();

		verify(drawingControllerMock).executeCommand(cmdSelect);
	}

	@Test(expected = NumberFormatException.class)
	public void testSelectCircleWrongLogExceptionExpected() {
		String[] line = "Select - Circle Center: (x: 521 , y: 256 , Border color: -3603sad34 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.selectCircle(line);
		CmdSelect cmdSelect = drawingControllerMock.getCmdSelect();

		verify(drawingControllerMock).executeCommand(cmdSelect);
	}

	@Test
	public void testSelectCircle() {
		String[] line = "Select - Circle Center: (x: 521 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.selectCircle(line);
		CmdSelect cmdSelect = drawingControllerMock.getCmdSelect();

		verify(drawingControllerMock).executeCommand(cmdSelect);
	}

	@Test(expected = NumberFormatException.class)
	public void testSelectDonutWrongLogExceptionExpected() {
		String[] line = "Select - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: we-360334 , Fill color: -5171 , inner radius: 2d1 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		drawingControllerMock.selectDonut(line);
		CmdSelect cmdSelect = drawingControllerMock.getCmdSelect();

		verify(drawingControllerMock).executeCommand(cmdSelect);
	}

	@Test
	public void testSelectDonut() {
		String[] line = "Select - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		drawingControllerMock.selectDonut(line);
		CmdSelect cmdSelect = drawingControllerMock.getCmdSelect();

		verify(drawingControllerMock).executeCommand(cmdSelect);
	}

	@Test(expected = NumberFormatException.class)
	public void testSelectHexagonWrongLogExceptionExpected() {
		String[] line = "Select - HexagonAdapter Center: (x: 609 , y: 141 , Border color: -360334 ), radiusds: 2w1 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.selectHexagon(line);
		CmdSelect cmdSelect = drawingControllerMock.getCmdSelect();

		verify(drawingControllerMock).executeCommand(cmdSelect);
	}

	@Test
	public void testSelectHexagon() {
		String[] line = "Select - HexagonAdapter Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.selectHexagon(line);
		CmdSelect cmdSelect = drawingControllerMock.getCmdSelect();

		verify(drawingControllerMock).executeCommand(cmdSelect);
	}

	@Test
	public void testDeselectPoint() {
		String[] line = "Deselect - Point (x: 45 , y: 78 , Border color: -360334 )".split(" ");
		drawingControllerMock.deselectPoint(line);
		CmdDeselect cmdDeselect = drawingControllerMock.getCmdDeselect();

		verify(drawingControllerMock).executeCommand(cmdDeselect);
	}

	@Test
	public void testDeselectLine() {
		String[] line = "Deselect - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
				.split(" ");

		drawingControllerMock.deselectLine(line);
		CmdDeselect cmdDeselect = drawingControllerMock.getCmdDeselect();

		verify(drawingControllerMock).executeCommand(cmdDeselect);
	}

	@Test
	public void testDeselectRectangle() {
		String[] line = "Deselect - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.deselectRectangle(line);
		CmdDeselect cmdDeselect = drawingControllerMock.getCmdDeselect();

		verify(drawingControllerMock).executeCommand(cmdDeselect);
	}

	@Test
	public void testDeselectCircle() {
		String[] line = "Deselect - Circle Center: (x: 521 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.deselectCircle(line);
		CmdDeselect cmdDeselect = drawingControllerMock.getCmdDeselect();

		verify(drawingControllerMock).executeCommand(cmdDeselect);
	}

	@Test
	public void testDeselectDonut() {
		String[] line = "Deselect - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		drawingControllerMock.deselectDonut(line);
		CmdDeselect cmdDeselect = drawingControllerMock.getCmdDeselect();

		verify(drawingControllerMock).executeCommand(cmdDeselect);
	}

	@Test
	public void testDeselectHexagon() {
		String[] line = "Deselect - HexagonAdapter Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		drawingControllerMock.deselectHexagon(line);
		CmdDeselect cmdDeselect = drawingControllerMock.getCmdDeselect();

		verify(drawingControllerMock).executeCommand(cmdDeselect);
	}

	@Test
	public void testModifyPointFromLog() {
		String[] line = "Modify - Point from (x: 45 , y: 78 , Border color: -360334 ) to  (x: 46 , y: 78 , Border color: -360334 )"
				.split(" ");
		Point point = new Point(45, 78, false, new Color(250, 128, 114));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, point));
		drawingControllerMock.executeCommand(new CmdSelect(point, drawingModel));

		Point newState = new Point(Integer.parseInt(line[17]), Integer.parseInt(line[20]), true,
				(Integer.parseInt(line[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[24]))));

		Point originalState = point.clone();

		drawingControllerMock.modifyPointFromLog(line, point);
		CmdModifyPoint cmdModifyPoint = drawingControllerMock.getCmdModifyPoint();

		assertFalse(drawingModel.getSelectedShapes().contains(originalState));
		assertTrue(drawingModel.getSelectedShapes().contains(newState));
		verify(drawingControllerMock).executeCommand(cmdModifyPoint);
	}

	@Test
	public void testModifyLineFromLog() {
		String[] line = "Modify - Line from Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334 to  Start point: (x: 90 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
				.split(" ");

		Line lineShape = new Line(new Point(77, 86), new Point(79, 197), false, new Color(250, 128, 114));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, lineShape));
		drawingControllerMock.executeCommand(new CmdSelect(lineShape, drawingModel));

		Point startPoint = new Point(Integer.parseInt(line[36]), Integer.parseInt(line[39]), false,
				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))));

		Point endPoint = new Point(Integer.parseInt(line[48]), Integer.parseInt(line[51]), false,
				(Integer.parseInt(line[55]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[55]))));

		Line newState = new Line(startPoint, endPoint, true,
				(Integer.parseInt(line[59]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[59]))));

		Line originalState = lineShape.clone();

		drawingControllerMock.modifyLineFromLog(line, lineShape);
		CmdModifyLine cmdModifyLine = drawingControllerMock.getCmdModifyLine();

		assertFalse(drawingModel.getSelectedShapes().contains(originalState));
		assertTrue(drawingModel.getSelectedShapes().contains(newState));
		verify(drawingControllerMock).executeCommand(cmdModifyLine);
	}

	@Test
	public void testModifyRectangleFromLog() {
		String[] line = "Modify - Rectangle from Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171 to  Upper left point: (x: 150 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		Rectangle rectangle = new Rectangle(new Point(142, 60), 12, 21, false, new Color(250, 128, 114),
				new Color(255, 235, 205));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, rectangle));
		drawingControllerMock.executeCommand(new CmdSelect(rectangle, drawingModel));

		Point point = new Point(Integer.parseInt(line[36]), Integer.parseInt(line[39]), false,
				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))));

		Rectangle newState = new Rectangle(point, Integer.parseInt(line[46]), Integer.parseInt(line[49]), true,
				(Integer.parseInt(line[53]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[53]))),
				(Integer.parseInt(line[57]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[57]))));

		Rectangle originalState = rectangle.clone();

		drawingControllerMock.modifyRectangleFromLog(line, rectangle);
		CmdModifyRectangle cmdModifyRectangle = drawingControllerMock.getCmdModifyRectangle();

		assertFalse(drawingModel.getSelectedShapes().contains(originalState));
		assertTrue(drawingModel.getSelectedShapes().contains(newState));
		verify(drawingControllerMock).executeCommand(cmdModifyRectangle);
	}

	@Test
	public void testModifyCircleFromLog() {
		String[] line = "Modify - Circle from Center: (x: 521 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171 to  Center: (x: 550 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		Circle circle = new Circle(new Point(521, 256), 21, false, new Color(250, 128, 114), new Color(255, 235, 205));
		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, circle));
		drawingControllerMock.executeCommand(new CmdSelect(circle, drawingModel));

		Point point = new Point(Integer.parseInt(line[29]), Integer.parseInt(line[32]), false,
				(Integer.parseInt(line[36]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[36]))));

		Circle newState = new Circle(point, Integer.parseInt(line[39]), true,
				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))),
				(Integer.parseInt(line[47]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[47]))));

		Circle originalState = circle.clone();

		drawingControllerMock.modifyCircleFromLog(line, circle);
		CmdModifyCircle cmdModifyCircle = drawingControllerMock.getCmdModifyCircle();

		assertFalse(drawingModel.getSelectedShapes().contains(originalState));
		assertTrue(drawingModel.getSelectedShapes().contains(newState));
		verify(drawingControllerMock).executeCommand(cmdModifyCircle);
	}

	@Test
	public void testModifyDonutFromLog() {
		String[] line = "Modify - Donut from Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171 to  Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 23 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		Donut donut = new Donut(new Point(330, 156), 121, 21, false, new Color(250, 128, 114),
				new Color(255, 235, 205));

		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, donut));
		drawingControllerMock.executeCommand(new CmdSelect(donut, drawingModel));

		Point point = new Point(Integer.parseInt(line[41]), Integer.parseInt(line[44]), false,
				(Integer.parseInt(line[48]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[48]))));

		Donut newState = new Donut(point, Integer.parseInt(line[51]), Integer.parseInt(line[63]), true,
				(Integer.parseInt(line[67]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[67]))),
				(Integer.parseInt(line[71]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[71]))));

		Donut originalState = donut.clone();

		drawingControllerMock.modifyDonutFromLog(line, donut);
		CmdModifyDonut cmdModifyDonut = drawingControllerMock.getCmdModifyDonut();

		assertFalse(drawingModel.getSelectedShapes().contains(originalState));
		assertTrue(drawingModel.getSelectedShapes().contains(newState));
		verify(drawingControllerMock).executeCommand(cmdModifyDonut);
	}

	@Test
	public void testModifyHexagonFromLog() {
		String[] line = "Modify - HexagonAdapter from Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171 to  Center: (x: 700 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		HexagonAdapter hexagon = new HexagonAdapter(609, 141, 21, new Color(250, 128, 114), new Color(255, 235, 205),
				false);

		drawingControllerMock.executeCommand(new CmdAdd(drawingModel, hexagon));
		drawingControllerMock.executeCommand(new CmdSelect(hexagon, drawingModel));

		Point point = new Point(Integer.parseInt(line[29]), Integer.parseInt(line[32]), false,
				(Integer.parseInt(line[36]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[36]))));

		HexagonAdapter newState = new HexagonAdapter(point.getX(), point.getY(), Integer.parseInt(line[39]),
				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))),
				(Integer.parseInt(line[47]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[47]))),
				true);

		HexagonAdapter originalState = hexagon.clone();

		drawingControllerMock.modifyHexagonFromLog(line, hexagon);
		CmdModifyHexagon cmdModifyHexagon = drawingControllerMock.getCmdModifyHexagon();

		assertFalse(drawingModel.getSelectedShapes().contains(originalState));
		assertTrue(drawingModel.getSelectedShapes().contains(newState));
		verify(drawingControllerMock).executeCommand(cmdModifyHexagon);
	}
}