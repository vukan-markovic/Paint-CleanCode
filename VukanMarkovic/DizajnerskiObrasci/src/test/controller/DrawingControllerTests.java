package controller;
//package view;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//import java.io.*;
//import java.util.*;
//import javax.swing.*;
//import org.junit.*;
//import commands.*;
//import dialogs.*;
//import controller.*;
//import shapes.*;
//import files.*;
//import java.awt.AWTException;
//import java.awt.Color;
//import java.awt.Robot;
//import java.awt.event.MouseEvent;
//import hexagon.Hexagon;
//import org.junit.rules.TemporaryFolder;
//import org.mockito.Mockito;
//
//public class DrawingControllerTests {
//	private DrawingController controller;
//	private DrawingModel model;
//	private DrawingFrame frame;
//	private Command command;
//	private DrawingController controllerMock;
//	private MouseEvent click;
//	private DialogRectangle dialogRectangle;
//	private DialogCircle dialogCircle;
//	private DialogDonut dialogDonut;
//	private DialogHexagon dialogHexagon;
//	private DialogLine dialogLine;
//	private DialogPoint dialogPoint;
//	private JFileChooser fileChooser;
//	private FileManager files;
//	private Robot robot;
//
//	@Rule
//	public TemporaryFolder folder = new TemporaryFolder();
//
//	@Before
//	public void setUp() throws AWTException {
//		model = new DrawingModel();
//		frame = new DrawingFrame();
//		controller = new DrawingController();
//		controller.setController(model, frame);
//		frame.getView().setModel(model);
//		frame.setController(controller);
//		command = mock(Command.class);
//		controllerMock = spy(DrawingController.class);
//		controllerMock.setController(model, frame);
//		click = mock(MouseEvent.class);
//		dialogRectangle = new DialogRectangle();
//		dialogCircle = new DialogCircle();
//		dialogDonut = new DialogDonut();
//		dialogHexagon = new DialogHexagon();
//		dialogLine = new DialogLine();
//		dialogPoint = new DialogPoint();
//		fileChooser = new JFileChooser();
//		files = mock(FileManager.class);
//		robot = new Robot();
//	}
//
//	@Test
//	public void testExecuteCommand() {
//		controller.executeCommand(command);
//		verify(command).execute();
//		assertTrue(controller.getExecutedCommands().contains(command));
//	}
//
//	@Test
//	public void testLogCommand() {
//		String command = "Command";
//		controller.logCommand(command);
//		assertTrue(frame.getCommandsListModel().contains(command));
//	}
//
//	@Test
//	public void testBtnSelectClicked() {
////		Point point = new Point(1, 2, true, Color.BLACK);
////		controllerMock.btnSelectClicked(click);
////		controllerMock.executeCommand(new CmdAdd(model, point));
//
////		verify(controllerMock).executeCommand();
////		verify(controllerMock).logCommand(
////				"Deselect - " + model.get(0).getClass().getSimpleName() + " " + model.get(0).toString());
////		verify(controllerMock).fireEvents();
//	}
//
//	@Test
//	public void testBtnPointClicked() {
//		controllerMock.btnPointClicked(click);
//		verify(controllerMock, Mockito.times(1)).addPoint(click);
//		verify(controllerMock, Mockito.times(1)).clearUnexecutedCommands();
//	}
//
//	@Test
//	public void testAddPoint() {
//		Point point = new Point(click.getX(), click.getY(), false, controllerMock.getOuterColor());
//		controllerMock.addPoint(click);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//		verify(controllerMock).logCommand("Add - " + point.getClass().getSimpleName() + " " + point);
//	}
//
//	@Test
//	public void testClearStack() {
//		Stack<Command> commands = new Stack<Command>();
//		commands.add(new CmdAdd(model, new Point(1, 2)));
//		controller.setUnexecutedCommands(commands);
//		controller.clearUnexecutedCommands();
//		assertTrue(controller.getUnexecutedCommands().isEmpty());
//	}
//
//	@Test
//	public void testAddLine() {
//		Point endPoint = new Point(click.getX(), click.getY(), false, controllerMock.getOuterColor());
//
//		Line line = new Line(controllerMock.getStartPoint(), endPoint, false, controllerMock.getOuterColor());
//
//		controllerMock.addLine(click);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//		verify(controllerMock).logCommand("Add - " + line.getClass().getSimpleName() + " " + line);
//	}
//
//	@Test
//	public void testBtnLineClickedStartPointNull() {
//		controllerMock.btnLineClicked(click);
//		assertEquals(new Point(click.getX(), click.getY(), false, Color.BLACK), controllerMock.getStartPoint());
//	}
//
//	@Test
//	public void testBtnLineClickedStartPointNotNull() {
//		controllerMock.setStartPoint(new Point(1, 2));
//		controllerMock.btnLineClicked(click);
//		verify(controllerMock, Mockito.times(1)).addLine(click);
//		verify(controllerMock, Mockito.times(1)).clearUnexecutedCommands();
//	}
//
//	@Test
//	public void testAddRectangle() {
//		DialogRectangle dialogRectangle = new DialogRectangle();
//		dialogRectangle.getwidth().setText("1");
//		dialogRectangle.getheight().setText("1");
//		Point point = new Point(1, 2);
//
//		Rectangle rectangle = new Rectangle(point, Integer.parseInt(dialogRectangle.getheight().getText()),
//				Integer.parseInt(dialogRectangle.getwidth().getText()), false, controllerMock.getOuterColor(),
//				controllerMock.getInnerColor());
//
//		controllerMock.addRectangle(dialogRectangle, point);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock, Mockito.times(1)).executeCommand(cmdAdd);
//		verify(controllerMock, Mockito.times(1)).clearUnexecutedCommands();
//		verify(controllerMock, Mockito.times(1))
//				.logCommand("Add - " + rectangle.getClass().getSimpleName() + " " + rectangle);
//	}
//
//	@Test
//	public void testCreateRectangleDialog() {
//		Point point = new Point(1, 2);
//		controller.setDialogRectangle(dialogRectangle);
//		controller.createRectangleDialog(point);
//		assertFalse(dialogRectangle.getBtnOuterColor().isVisible());
//		assertFalse(dialogRectangle.getBtnInnerColor().isVisible());
//		assertEquals("1", dialogRectangle.getXcoordinate().getText());
//		assertEquals("2", dialogRectangle.getYcoordinate().getText());
//		assertFalse(dialogRectangle.getXcoordinate().isEditable());
//		assertFalse(dialogRectangle.getYcoordinate().isEditable());
//		assertFalse(dialogRectangle.isVisible());
//	}
//
//	@Test
//	public void testBtnRectangleClicked() {
//		controllerMock.setDialogRectangle(dialogRectangle);
//		controllerMock.btnRectangleClicked(click);
//		Point point = controllerMock.getPoint();
//		DialogRectangle dialogRectangle = controllerMock.getDialogRectangle();
//		verify(controllerMock).createRectangleDialog(point);
//		verify(controllerMock).addRectangle(dialogRectangle, point);
//	}
//
//	@Test
//	public void testCreateCircleDialog() {
//		Point point = new Point(1, 2);
//		controller.setDialogCircle(dialogCircle);
//		controller.createCircleDialog(point);
//		assertFalse(dialogCircle.getBtnOuterColor().isVisible());
//		assertFalse(dialogCircle.getBtnInnerColor().isVisible());
//		assertEquals("1", dialogCircle.getXcoordinate().getText());
//		assertEquals("2", dialogCircle.getYcoordinate().getText());
//		assertFalse(dialogCircle.getXcoordinate().isEditable());
//		assertFalse(dialogCircle.getYcoordinate().isEditable());
//		assertFalse(dialogCircle.isVisible());
//	}
//
//	@Test
//	public void testAddCircle() {
//		DialogCircle dialogCircle = new DialogCircle();
//		dialogCircle.getRadius().setText("1");
//		Point point = new Point(1, 2);
//
//		Circle circle = new Circle(point, Integer.parseInt(dialogCircle.getRadius().getText()), false,
//				controllerMock.getOuterColor(), controllerMock.getInnerColor());
//
//		controllerMock.addCircle(dialogCircle, point);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock, Mockito.times(1)).executeCommand(cmdAdd);
//		verify(controllerMock, Mockito.times(1)).clearUnexecutedCommands();
//		verify(controllerMock, Mockito.times(1))
//				.logCommand("Add - " + circle.getClass().getSimpleName() + " " + circle);
//	}
//
//	@Test
//	public void testBtnCircleClicked() {
//		controllerMock.setDialogCircle(dialogCircle);
//		controllerMock.btnCircleClicked(click);
//		Point point = controllerMock.getPoint();
//		DialogCircle dialogCircle = controllerMock.getDialogCircle();
//		verify(controllerMock).createCircleDialog(point);
//		verify(controllerMock).addCircle(dialogCircle, point);
//	}
//
//	@Test
//	public void testCreateDonutDialog() {
//		Point point = new Point(1, 2);
//		controller.setDialogDonut(dialogDonut);
//		controller.createDonutDialog(point);
//		assertFalse(dialogDonut.getBtnOuterColor().isVisible());
//		assertFalse(dialogDonut.getBtnInnerColor().isVisible());
//		assertEquals("1", dialogDonut.getXcoordinate().getText());
//		assertEquals("2", dialogDonut.getYcoordinate().getText());
//		assertFalse(dialogDonut.getXcoordinate().isEditable());
//		assertFalse(dialogDonut.getYcoordinate().isEditable());
//		assertFalse(dialogDonut.isVisible());
//	}
//
//	@Test
//	public void testAddDonut() {
//		DialogDonut dialogDonut = new DialogDonut();
//		dialogDonut.getRadius().setText("1");
//		dialogDonut.getInnerRadius().setText("1");
//		Point point = new Point(1, 2);
//
//		Donut donut = new Donut(point, Integer.parseInt(dialogDonut.getRadius().getText()),
//				Integer.parseInt(dialogDonut.getInnerRadius().getText()), false, controllerMock.getOuterColor(),
//				controllerMock.getInnerColor());
//
//		controllerMock.addDonut(dialogDonut, point);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock, Mockito.times(1)).executeCommand(cmdAdd);
//		verify(controllerMock, Mockito.times(1)).clearUnexecutedCommands();
//		verify(controllerMock, Mockito.times(1)).logCommand("Add - " + donut.getClass().getSimpleName() + " " + donut);
//	}
//
//	@Test
//	public void testBtnDonutClicked() {
//		controllerMock.setDialogDonut(dialogDonut);
//		controllerMock.btnDonutClicked(click);
//		Point point = controllerMock.getPoint();
//		DialogDonut dialogDonut = controllerMock.getDialogDonut();
//		verify(controllerMock).createDonutDialog(point);
//		verify(controllerMock).addDonut(dialogDonut, point);
//	}
//
//	@Test
//	public void testCreateHexagonDialog() {
//		Point point = new Point(1, 2);
//		controller.setDialogHexagon(dialogHexagon);
//		controller.createHexagonDialog(point);
//		assertFalse(dialogHexagon.getBtnOuterColor().isVisible());
//		assertFalse(dialogHexagon.getBtnInnerColor().isVisible());
//		assertEquals("1", dialogHexagon.getXcoordinate().getText());
//		assertEquals("2", dialogHexagon.getYcoordinate().getText());
//		assertFalse(dialogHexagon.getXcoordinate().isEditable());
//		assertFalse(dialogHexagon.getYcoordinate().isEditable());
//		assertFalse(dialogHexagon.isVisible());
//	}
//
//	@Test
//	public void testAddHexagon() {
//		DialogHexagon dialogHexagon = new DialogHexagon();
//		dialogHexagon.getRadius().setText("1");
//		Point point = new Point(1, 2);
//
//		HexagonAdapter hexagon = new HexagonAdapter(
//				new Hexagon(point.getXcoordinate(), point.getYcoordinate(),
//						Integer.parseInt(dialogHexagon.getRadius().getText())),
//				controllerMock.getOuterColor(), controllerMock.getInnerColor());
//
//		controllerMock.addHexagon(dialogHexagon, point);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock, Mockito.times(1)).executeCommand(cmdAdd);
//		verify(controllerMock, Mockito.times(1)).clearUnexecutedCommands();
//		verify(controllerMock, Mockito.times(1))
//				.logCommand("Add - " + hexagon.getClass().getSimpleName() + " " + hexagon);
//	}
//
//	@Test
//	public void testBtnHexagonClicked() {
//		controllerMock.setDialogHexagon(dialogHexagon);
//		controllerMock.btnHexagonClicked(click);
//		Point point = controllerMock.getPoint();
//		DialogHexagon dialogHexagon = controllerMock.getDialogHexagon();
//		verify(controllerMock).createHexagonDialog(point);
//		verify(controllerMock).addHexagon(dialogHexagon, point);
//	}
//
//	@Test
//	public void testCreatePointDialog() {
//		Point point = new Point(1, 2);
//		controller.setDialogPoint(dialogPoint);
//		controller.createPointDialog(point);
//		assertEquals("1", dialogPoint.getXcoordinate().getText());
//		assertEquals("2", dialogPoint.getYcoordinate().getText());
//		assertEquals(Color.BLACK, dialogPoint.getOuterColor());
//		assertEquals(Color.BLACK, dialogPoint.getBtnOuterColor().getBackground());
//		assertFalse(dialogLine.isVisible());
//	}
//
//	@Test
//	public void testModifyPoint() {
//		Point oldState = new Point(1, 2);
//		dialogPoint.getXcoordinate().setText("1");
//		dialogPoint.getYcoordinate().setText("2");
//
//		Point newState = new Point(Integer.parseInt(dialogPoint.getXcoordinate().getText()),
//				Integer.parseInt(dialogPoint.getYcoordinate().getText()), oldState.isSelected(),
//				dialogPoint.getOuterColor());
//
//		Point originalState = oldState.clone();
//
//		controllerMock.modifyPoint(oldState, dialogPoint);
//		CmdModifyPoint cmdModifyPoint = controllerMock.getCmdModifyPoint();
//
//		verify(controllerMock).logCommand(
//				"Modify - " + newState.getClass().getSimpleName() + " from " + originalState + " to " + " " + newState);
//
//		verify(controllerMock).executeCommand(cmdModifyPoint);
//		verify(controllerMock).clearUnexecutedCommands();
//	}
//
//	@Test
//	public void testCreateLineDialog() {
//		Line line = new Line(new Point(1, 2), new Point(3, 4));
//		controller.setDialogLine(dialogLine);
//		controller.createLineDialog(line);
//		assertEquals("1", dialogLine.getXcoordinate().getText());
//		assertEquals("3", dialogLine.getXCoordinateOfEndPoint().getText());
//		assertEquals("2", dialogLine.getYcoordinate().getText());
//		assertEquals("4", dialogLine.getYCoordinateOfEndPoint().getText());
//		assertEquals(Color.BLACK, dialogLine.getOuterColor());
//		assertEquals(Color.BLACK, dialogLine.getBtnOuterColor().getBackground());
//		assertFalse(dialogLine.isVisible());
//	}
//
//	@Test
//	public void testModifyLine() {
//		Line oldState = new Line(new Point(1, 2), new Point(3, 4));
//		dialogLine.getXcoordinate().setText("1");
//		dialogLine.getYcoordinate().setText("2");
//		dialogLine.getXCoordinateOfEndPoint().setText("3");
//		dialogLine.getYCoordinateOfEndPoint().setText("4");
//
//		Line newState = new Line(
//				new Point(Integer.parseInt(dialogLine.getXcoordinate().getText()),
//						Integer.parseInt(dialogLine.getYcoordinate().getText()), oldState.isSelected(),
//						dialogLine.getOuterColor()),
//				new Point(Integer.parseInt(dialogLine.getXCoordinateOfEndPoint().getText()),
//						Integer.parseInt(dialogLine.getYCoordinateOfEndPoint().getText()), oldState.isSelected(),
//						dialogLine.getOuterColor()),
//				oldState.isSelected(), dialogLine.getOuterColor());
//
//		Line originalState = oldState.clone();
//		controllerMock.modifyLine(oldState, dialogLine);
//		CmdModifyLine cmdModifyLine = controllerMock.getCmdModifyLine();
//
//		verify(controllerMock).logCommand(
//				"Modify - " + newState.getClass().getSimpleName() + " from " + originalState + " to " + " " + newState);
//
//		verify(controllerMock).executeCommand(cmdModifyLine);
//		verify(controllerMock).clearUnexecutedCommands();
//	}
//
//	@Test
//	public void testCreateRectangleModifyDialog() {
//		Rectangle rectangle = new Rectangle(new Point(1, 2), 3, 4);
//		controller.setDialogRectangle(dialogRectangle);
//		controller.createRectangleModifyDialog(rectangle);
//		assertEquals("1", dialogRectangle.getXcoordinate().getText());
//		assertEquals("2", dialogRectangle.getYcoordinate().getText());
//		assertEquals("3", dialogRectangle.getheight().getText());
//		assertEquals("4", dialogRectangle.getwidth().getText());
//		assertEquals(Color.BLACK, dialogRectangle.getOuterColor());
//		assertEquals(new Color(0, 0, 0, 0), dialogRectangle.getInnerColor());
//		assertEquals(Color.BLACK, dialogRectangle.getBtnOuterColor().getBackground());
//		assertEquals(new Color(0, 0, 0, 0), dialogRectangle.getBtnInnerColor().getBackground());
//		assertFalse(dialogRectangle.isVisible());
//	}
//
//	@Test
//	public void testModifyRectangle() {
//		Rectangle oldState = new Rectangle(new Point(1, 2), 3, 4);
//		dialogRectangle.getXcoordinate().setText("1");
//		dialogRectangle.getYcoordinate().setText("2");
//		dialogRectangle.getheight().setText("3");
//		dialogRectangle.getwidth().setText("4");
//
//		Rectangle newState = new Rectangle(
//				new Point(Integer.parseInt(dialogRectangle.getXcoordinate().getText()),
//						Integer.parseInt(dialogRectangle.getYcoordinate().getText()),
//						oldState.isSelected(), dialogRectangle.getOuterColor()),
//				Integer.parseInt(dialogRectangle.getheight().getText()),
//				Integer.parseInt(dialogRectangle.getwidth().getText()), oldState.isSelected(),
//				dialogRectangle.getOuterColor(), dialogRectangle.getInnerColor());
//
//		controllerMock.modifyRectangle(oldState, dialogRectangle);
//		CmdModifyRectangle cmdModifyRectangle = controllerMock.getCmdModifyRectangle();
//
//		verify(controllerMock).logCommand(
//				"Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
//
//		verify(controllerMock).executeCommand(cmdModifyRectangle);
//		verify(controllerMock).clearUnexecutedCommands();
//	}
//
//	@Test
//	public void testCreateDonutModifyDialog() {
//		Donut donut = new Donut(new Point(1, 2), 4, 3, true, Color.BLACK, Color.BLACK);
//		controller.setDialogDonut(dialogDonut);
//		controller.createDonutModifyDialog(donut);
//		assertEquals("1", dialogDonut.getXcoordinate().getText());
//		assertEquals("2", dialogDonut.getYcoordinate().getText());
//		assertEquals("4", dialogDonut.getRadius().getText());
//		assertEquals("3", dialogDonut.getInnerRadius().getText());
//		assertEquals(Color.BLACK, dialogDonut.getOuterColor());
//		assertEquals(Color.BLACK, dialogDonut.getInnerColor());
//		assertEquals(Color.BLACK, dialogDonut.getBtnOuterColor().getBackground());
//		assertEquals(Color.BLACK, dialogDonut.getBtnInnerColor().getBackground());
//		assertFalse(dialogDonut.isVisible());
//	}
//
//	@Test
//	public void testModifyDonut() {
//		Donut oldState = new Donut(new Point(1, 2), 3, 4, true, Color.BLACK, Color.BLACK);
//		dialogDonut.getXcoordinate().setText("1");
//		dialogDonut.getYcoordinate().setText("2");
//		dialogDonut.getRadius().setText("4");
//		dialogDonut.getInnerRadius().setText("3");
//
//		Donut newState = new Donut(
//				new Point(Integer.parseInt(dialogDonut.getXcoordinate().getText()),
//						Integer.parseInt(dialogDonut.getYcoordinate().getText()), oldState.isSelected(),
//						dialogDonut.getOuterColor()),
//				Integer.parseInt(dialogDonut.getRadius().getText()),
//				Integer.parseInt(dialogDonut.getInnerRadius().getText()), oldState.isSelected(),
//				dialogDonut.getOuterColor(), dialogDonut.getInnerColor());
//
//		Donut originalState = oldState.clone();
//		controllerMock.modifyDonut(oldState, dialogDonut);
//		CmdModifyDonut cmdModifyDonut = controllerMock.getCmdModifyDonut();
//
//		verify(controllerMock).logCommand(
//				"Modify - " + newState.getClass().getSimpleName() + " from " + originalState + " to " + " " + newState);
//
//		verify(controllerMock).executeCommand(cmdModifyDonut);
//		verify(controllerMock).clearUnexecutedCommands();
//	}
//
//	@Test
//	public void testCreateCircletModifyDialog() {
//		Circle circle = new Circle(new Point(1, 2), 3);
//		controller.setDialogCircle(dialogCircle);
//		controller.createCircleModifyDialog(circle);
//		assertEquals("1", dialogCircle.getXcoordinate().getText());
//		assertEquals("2", dialogCircle.getYcoordinate().getText());
//		assertEquals("3", dialogCircle.getRadius().getText());
//		assertEquals(Color.BLACK, dialogCircle.getOuterColor());
//		assertEquals(new Color(0, 0, 0, 0), dialogCircle.getInnerColor());
//		assertEquals(Color.BLACK, dialogCircle.getBtnOuterColor().getBackground());
//		assertEquals(new Color(0, 0, 0, 0), dialogCircle.getBtnInnerColor().getBackground());
//		assertFalse(dialogCircle.isVisible());
//	}
//
//	@Test
//	public void testModifyCircle() {
//		Circle oldState = new Circle(new Point(1, 2), 3);
//		dialogCircle.getXcoordinate().setText("1");
//		dialogCircle.getYcoordinate().setText("2");
//		dialogCircle.getRadius().setText("3");
//
//		Circle newState = new Circle(
//				new Point(Integer.parseInt(dialogCircle.getXcoordinate().getText()),
//						Integer.parseInt(dialogCircle.getYcoordinate().getText()), oldState.isSelected(),
//						dialogCircle.getOuterColor()),
//				Integer.parseInt(dialogCircle.getRadius().getText()), oldState.isSelected(),
//				dialogCircle.getOuterColor(), dialogCircle.getInnerColor());
//
//		controllerMock.modifyCircle(oldState, dialogCircle);
//		CmdModifyCircle cmdModifyCircle = controllerMock.getCmdModifyCircle();
//
//		verify(controllerMock).logCommand(
//				"Modify - " + newState.getClass().getSimpleName() + " from " + oldState + " to " + " " + newState);
//
//		verify(controllerMock).executeCommand(cmdModifyCircle);
//		verify(controllerMock).clearUnexecutedCommands();
//	}
//
//	@Test
//	public void testCreateHexagonModifyDialog() {
//		HexagonAdapter hexagonAdapter = new HexagonAdapter(1, 2, 3, Color.BLACK, Color.BLACK, true);
//		controller.setDialogHexagon(dialogHexagon);
//		controller.createHexagonModifyDialog(hexagonAdapter);
//		assertEquals("1", dialogHexagon.getXcoordinate().getText());
//		assertEquals("2", dialogHexagon.getYcoordinate().getText());
//		assertEquals("3", dialogHexagon.getRadius().getText());
//		assertEquals(Color.BLACK, dialogHexagon.getOuterColor());
//		assertEquals(Color.BLACK, dialogHexagon.getInnerColor());
//		assertEquals(Color.BLACK, dialogHexagon.getBtnOuterColor().getBackground());
//		assertEquals(Color.BLACK, dialogHexagon.getBtnInnerColor().getBackground());
//		assertFalse(dialogHexagon.isVisible());
//	}
//
//	@Test
//	public void testModifyHexagon() {
//		HexagonAdapter oldState = new HexagonAdapter(1, 2, 3, Color.BLACK, Color.BLACK, true);
//		dialogHexagon.getXcoordinate().setText("1");
//		dialogHexagon.getYcoordinate().setText("2");
//		dialogHexagon.getRadius().setText("4");
//
//		HexagonAdapter newState = new HexagonAdapter(Integer.parseInt(dialogHexagon.getXcoordinate().getText()),
//				Integer.parseInt(dialogHexagon.getYcoordinate().getText()),
//				Integer.parseInt(dialogHexagon.getRadius().getText()), dialogHexagon.getOuterColor(),
//				dialogHexagon.getInnerColor(), oldState.isSelected());
//
//		HexagonAdapter originalState = oldState.clone();
//		controllerMock.modifyHexagon(oldState, dialogHexagon);
//		CmdModifyHexagon cmdModifyHexagon = controllerMock.getCmdModifyHexagon();
//
//		verify(controllerMock).logCommand(
//				"Modify - " + newState.getClass().getSimpleName() + " from " + originalState + " to " + " " + newState);
//
//		verify(controllerMock).executeCommand(cmdModifyHexagon);
//		verify(controllerMock).clearUnexecutedCommands();
//	}
//
//	@Test
//	public void testModifySizeNotOne() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		Line line = new Line(new Point(1, 2), new Point(3, 4));
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.executeCommand(new CmdAdd(model, line));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(1)));
//		controllerMock.btnModifyClicked();
//		DialogPoint dialogPoint = controllerMock.getDialogPoint();
//		verify(controllerMock, never()).createPointDialog(point);
//		verify(controllerMock, never()).modifyPoint(point, dialogPoint);
//	}
//
//	@Test
//	public void testModifyPointInstance() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.btnModifyClicked();
//		DialogPoint dialogPoint = controllerMock.getDialogPoint();
//		verify(controllerMock).createPointDialog(point);
//		verify(controllerMock).modifyPoint(point, dialogPoint);
//	}
//
//	@Test
//	public void testModifyLineInstance() {
//		Line line = new Line(new Point(1, 2), new Point(3, 4));
//		controllerMock.executeCommand(new CmdAdd(model, line));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.btnModifyClicked();
//		DialogLine dialogLine = controllerMock.getDialogLine();
//		verify(controllerMock).createLineDialog(line);
//		verify(controllerMock).modifyLine(line, dialogLine);
//	}
//
//	@Test
//	public void testModifyRectangleInstance() {
//		Rectangle rectangle = new Rectangle(new Point(1, 2), 3, 4);
//		controllerMock.executeCommand(new CmdAdd(model, rectangle));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.btnModifyClicked();
//		DialogRectangle dialogRectangle = controllerMock.getDialogRectangle();
//		verify(controllerMock).createRectangleModifyDialog(rectangle);
//		verify(controllerMock).modifyRectangle(rectangle, dialogRectangle);
//	}
//
//	@Test
//	public void testModifyDonutInstance() {
//		Point center = new Point(1, 2);
//		Donut donut = new Donut(center, 3, 4, true, Color.BLACK, Color.BLACK);
//		controllerMock.executeCommand(new CmdAdd(model, donut));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.btnModifyClicked();
//		DialogDonut dialogDonut = controllerMock.getDialogDonut();
//		verify(controllerMock).createDonutModifyDialog(donut);
//		verify(controllerMock).modifyDonut(donut, dialogDonut);
//	}
//
//	@Test
//	public void testModifyCircleInstance() {
//		Point center = new Point(1, 2);
//		Circle circle = new Circle(center, 3);
//		controllerMock.executeCommand(new CmdAdd(model, circle));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.btnModifyClicked();
//		DialogCircle dialogCircle = controllerMock.getDialogCircle();
//		verify(controllerMock).createCircleModifyDialog(circle);
//		verify(controllerMock).modifyCircle(circle, dialogCircle);
//	}
//
//	@Test
//	public void testModifyHexagonInstance() {
//		Point center = new Point(1, 2);
//		HexagonAdapter hexagon = new HexagonAdapter(center.getXcoordinate(), center.getYcoordinate(), 3, Color.BLACK,
//				Color.BLACK, true);
//		controllerMock.executeCommand(new CmdAdd(model, hexagon));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.btnModifyClicked();
//		DialogHexagon dialogHexagon = controllerMock.getDialogHexagon();
//		verify(controllerMock).createHexagonModifyDialog(hexagon);
//		verify(controllerMock).modifyHexagon(hexagon, dialogHexagon);
//	}
//
//	@Test
//	public void testDeleteNoSelectedShapes() {
//		controllerMock.btnRemoveClicked();
//		verify(controllerMock, never()).removeShapes();
//		verify(controllerMock, never()).logCommand("Deleted");
//		verify(controllerMock, never()).fireEvents();
//	}
//
//	@Test
//	public void testDelete() {
//		HexagonAdapter hexagon = new HexagonAdapter(1, 2, 3, Color.BLACK, Color.BLACK, true);
//		controllerMock.executeCommand(new CmdAdd(model, hexagon));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.btnRemoveClicked();
//		verify(controllerMock).removeShapes();
//		verify(controllerMock).logCommand("Deleted");
//		verify(controllerMock).fireEvents();
//	}
//
//	@Test
//	public void testDeleteLogs() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		Line line = new Line(new Point(1, 2), new Point(3, 4));
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.executeCommand(new CmdAdd(model, line));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(1)));
//		controllerMock.removeShapes();
//		CmdRemove cmdRemove = controllerMock.getCmdRemove();
//		verify(controllerMock).executeCommand(cmdRemove);
//		verify(controllerMock, Mockito.times(2)).clearUnexecutedCommands();
//	}
//
//	@Test
//	public void testUndoNoCommands() {
//		controllerMock.btnUndoClicked();
//		verify(controllerMock, never()).undoCommand();
//		verify(controllerMock, never()).logCommand("Undo");
//		verify(controllerMock, never()).fireEventsForUndoAndRedoButtons();
//		verify(controllerMock, never()).fireEvents();
//	}
//
//	@Test
//	public void testUndo() {
//		Line line = new Line(new Point(1, 2), new Point(3, 4));
//		controllerMock.executeCommand(new CmdAdd(model, line));
//		controllerMock.btnUndoClicked();
//		verify(controllerMock).undoCommand();
//		verify(controllerMock).logCommand("Undo");
//		verify(controllerMock).fireEventsForUndoAndRedoButtons();
//		verify(controllerMock).fireEvents();
//	}
//
//	@Test
//	public void testUndoForLog() {
//		CmdAdd cmdAdd = new CmdAdd(model, new Line(new Point(1, 2), new Point(3, 4)));
//		controller.executeCommand(cmdAdd);
//		controller.undoCommand();
//		assertTrue(controller.getUnexecutedCommands().contains(cmdAdd));
//		assertFalse(controller.getExecutedCommands().contains(cmdAdd));
//	}
//
//	@Test
//	public void testRedoNoCommands() {
//		controllerMock.btnRedoClicked();
//		verify(controllerMock, never()).redoCommand();
//		verify(controllerMock, never()).logCommand("Redo");
//		verify(controllerMock, never()).fireEventsForUndoAndRedoButtons();
//		verify(controllerMock, never()).fireEvents();
//	}
//
//	@Test
//	public void testRedo() {
//		controller.executeCommand(new CmdAdd(model, new Line(new Point(1, 2), new Point(3, 4))));
//		controllerMock.btnRedoClicked();
//		verify(controllerMock).redoCommand();
//		verify(controllerMock).logCommand("Redo");
//		verify(controllerMock).fireEventsForUndoAndRedoButtons();
//		verify(controllerMock).fireEvents();
//	}
//
//	@Test(expected = EmptyStackException.class)
//	public void testRedoForLogEmptyStackExceptionExpected() {
//		controllerMock.redoCommand();
//	}
//
//	@Test(expected = EmptyStackException.class)
//	public void testRedoForLogUndoNotCalledExceptionExpected() {
//		Line line = new Line(new Point(1, 2), new Point(3, 4));
//		controller.executeCommand(new CmdAdd(model, line));
//		controllerMock.redoCommand();
//	}
//
//	@Test
//	public void testRedoForLog() {
//		CmdAdd cmdAdd = new CmdAdd(model, new Line(new Point(1, 2), new Point(3, 4)));
//		controller.executeCommand(cmdAdd);
//		controller.undoCommand();
//		controller.redoCommand();
//		assertFalse(controller.getUnexecutedCommands().contains(cmdAdd));
//		assertTrue(controller.getExecutedCommands().contains(cmdAdd));
//	}
//
//	@Test
//	public void testOuterColorNull() {
//		controller.chooseOuterColor();
//		assertNotEquals(frame.getBtnOuterColor().getBackground(), controller.getOuterColor());
//		assertNull(controller.getOuterColor());
//	}
//
//	@Test
//	public void testOuterColor() {
//		controller.chooseOuterColor();
//		assertEquals(frame.getBtnOuterColor().getBackground(), controller.getOuterColor());
//	}
//
//	@Test
//	public void testInnerColorNull() {
//		controller.chooseInnerColor();
//		assertNotEquals(frame.getBtnInnerColor().getBackground(), controller.getInnerColor());
//		assertNull(controller.getInnerColor());
//	}
//
//	@Test
//	public void testInnerColor() {
//		controller.chooseInnerColor();
//		assertEquals(frame.getBtnInnerColor().getBackground(), controller.getInnerColor());
//	}
//
//	@Test
//	public void testToBackMultipleSelectedShapes() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		Line line = new Line(new Point(1, 2), new Point(3, 4));
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.executeCommand(new CmdAdd(model, line));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(1)));
//		controllerMock.btnToBackClicked();
//		CmdToBack cmdToBack = controllerMock.getCmdToBack();
//
//		verify(controllerMock, never()).executeCommand(cmdToBack);
//		verify(controllerMock, never()).clearUnexecutedCommands();
//
//		verify(controllerMock, never())
//				.logCommand("ToBack - " + point.getClass().getSimpleName() + " " + point.toString());
//
//		verify(controllerMock, never())
//				.logCommand("ToBack - " + line.getClass().getSimpleName() + " " + line.toString());
//	}
//
//	@Test
//	public void testToBack() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.btnToBackClicked();
//		CmdToBack cmdToBack = controllerMock.getCmdToBack();
//
//		verify(controllerMock).executeCommand(cmdToBack);
//		verify(controllerMock).clearUnexecutedCommands();
//		verify(controllerMock).logCommand("ToBack - " + point.getClass().getSimpleName() + " " + point.toString());
//	}
//
//	@Test
//	public void testToFrontMultipleSelectedShapes() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		Line line = new Line(new Point(1, 2), new Point(3, 4));
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.executeCommand(new CmdAdd(model, line));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(1)));
//		controllerMock.btnToFrontClicked();
//		CmdToFront cmdToFront = controllerMock.getCmdToFront();
//
//		verify(controllerMock, never()).executeCommand(cmdToFront);
//		verify(controllerMock, never()).clearUnexecutedCommands();
//		verify(controllerMock, never())
//				.logCommand("ToFront - " + point.getClass().getSimpleName() + " " + point.toString());
//
//		verify(controllerMock, never())
//				.logCommand("ToFront - " + line.getClass().getSimpleName() + " " + line.toString());
//	}
//
//	@Test
//	public void testToFront() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.btnToFrontClicked();
//		CmdToFront cmdToFront = controllerMock.getCmdToFront();
//		verify(controllerMock).executeCommand(cmdToFront);
//		verify(controllerMock).clearUnexecutedCommands();
//
//		verify(controllerMock).logCommand("ToFront - " + point.getClass().getSimpleName() + " " + point.toString());
//	}
//
//	@Test
//	public void testSendToBackMultipleSelectedShapes() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		Line line = new Line(new Point(1, 2), new Point(3, 4));
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.executeCommand(new CmdAdd(model, line));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(1)));
//		controllerMock.btnSendToBackClicked();
//		CmdBringToBack cmdSendToBack = controllerMock.getCmdSendToBack();
//		verify(controllerMock, never()).executeCommand(cmdSendToBack);
//		verify(controllerMock, never()).clearUnexecutedCommands();
//
//		verify(controllerMock, never())
//				.logCommand("SendToBack - " + point.getClass().getSimpleName() + " " + point.toString());
//
//		verify(controllerMock, never())
//				.logCommand("SendToBack - " + line.getClass().getSimpleName() + " " + line.toString());
//	}
//
//	@Test
//	public void testSendToBack() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.btnSendToBackClicked();
//		CmdBringToBack cmdSendToBack = controllerMock.getCmdSendToBack();
//		verify(controllerMock).executeCommand(cmdSendToBack);
//		verify(controllerMock).clearUnexecutedCommands();
//
//		verify(controllerMock).logCommand("SendToBack - " + point.getClass().getSimpleName() + " " + point.toString());
//	}
//
//	@Test
//	public void testBringToFrontMultipleSelectedShapes() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		Line line = new Line(new Point(1, 2), new Point(3, 4));
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.executeCommand(new CmdAdd(model, line));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(1)));
//		controllerMock.btnBringToFrontClicked();
//		CmdBringToFront cmdBringToFront = controllerMock.getCmdBringToFront();
//		verify(controllerMock, never()).executeCommand(cmdBringToFront);
//		verify(controllerMock, never()).clearUnexecutedCommands();
//		verify(controllerMock, never())
//				.logCommand("BringToFront - " + point.getClass().getSimpleName() + " " + point.toString());
//		verify(controllerMock, never())
//				.logCommand("BringToFront - " + line.getClass().getSimpleName() + " " + line.toString());
//	}
//
//	@Test
//	public void testBringToFront() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controllerMock.btnBringToFrontClicked();
//		CmdBringToFront cmdBringToFront = controllerMock.getCmdBringToFront();
//		verify(controllerMock).executeCommand(cmdBringToFront);
//		verify(controllerMock).clearUnexecutedCommands();
//		verify(controllerMock)
//				.logCommand("BringToFront - " + point.getClass().getSimpleName() + " " + point.toString());
//	}
//
//	@Test
//	public void testFireEventsNoSelectedShapes() {
//		controller.fireEvents();
//		assertFalse(frame.getBtnDelete().isEnabled());
//		assertFalse(frame.getBtnModify().isEnabled());
//		assertFalse(frame.getBtnToBack().isEnabled());
//		assertFalse(frame.getBtnToFront().isEnabled());
//		assertFalse(frame.getBtnSendToBack().isEnabled());
//		assertFalse(frame.getBtnBringToFront().isEnabled());
//	}
//
//	@Test
//	public void testFireEvents() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, model.getShapeByIndex(0)));
//		controller.fireEvents();
//		assertTrue(frame.getBtnDelete().isEnabled());
//		assertTrue(frame.getBtnModify().isEnabled());
//		assertTrue(frame.getBtnToBack().isEnabled());
//		assertTrue(frame.getBtnToFront().isEnabled());
//		assertTrue(frame.getBtnSendToBack().isEnabled());
//		assertTrue(frame.getBtnBringToFront().isEnabled());
//	}
//
//	@Test
//	public void testSaveDrawing() throws IOException {
//		FilePainting savePainting = new FilePainting();
//		controller.setStrategy(files);
//		controller.setSavePainting(savePainting);
//		File file = folder.newFile(frame.getFileName().getText() + ".bin");
//		fileChooser.setSelectedFile(file);
//		controller.setFileChooser(fileChooser);
//		controller.saveDrawing();
//		assertEquals(model.getShapes(), savePainting.getShapes());
//		verify(files).setStrategy(savePainting);
//		verify(files).save(fileChooser.getSelectedFile().getAbsolutePath() + "\\" + file.getName());
//	}
//
//	@Test
//	public void testSaveLog() throws IOException {
//		FileLog saveLog = new FileLog();
//		controller.setStrategy(files);
//		controller.setSaveLog(saveLog);
//		File file = folder.newFile(frame.getFileName().getText() + ".txt");
//		fileChooser.setSelectedFile(file);
//		controller.setFileChooser(fileChooser);
//		controller.saveLog();
//		assertEquals(controller.getLog(), saveLog.getCommandsLog());
//		verify(files).setStrategy(saveLog);
//		verify(files).save(fileChooser.getSelectedFile().getAbsolutePath() + "\\" + file.getName());
//	}
//
//	@Test
//	public void testShowFileChooser() {
//		controller.setFileChooser(fileChooser);
//		int result = controller.showFileChooser("Title", JFileChooser.DIRECTORIES_ONLY);
//		assertEquals("Title", fileChooser.getDialogTitle());
//		assertEquals(JFileChooser.DIRECTORIES_ONLY, fileChooser.getFileSelectionMode());
//		assertEquals(JFileChooser.APPROVE_OPTION, result);
//	}
//
//	@Test
//	public void testSaveNoShapes() {
//		controllerMock.save();
//		verify(controllerMock, never()).saveLog();
//		verify(controllerMock, never()).saveDrawing();
//	}
//
//	@Test
//	public void testSave() {
//		Point point = new Point(1, 2, true, Color.BLACK);
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.save();
//		verify(controllerMock).saveLog();
//		verify(controllerMock).saveDrawing();
//	}
//
//	@Test
//	public void testLoadLog() {
//		controllerMock.loadLog();
//		fileChooser = controllerMock.getFileChooser();
//		assertTrue(frame.getBtnNext().isEnabled());
//		assertTrue(controllerMock.getCommandsLog().isEmpty());
//		verify(controllerMock).addLog();
//	}
//
//	@Test
//	public void testAddLog() throws IOException {
////		File file = folder.newFile("log.txt");
////		fileChooser.setSelectedFile(file);
////		controller.setFileChooser(fileChooser);
////		BufferedReader buffer = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getAbsolutePath()));
////		controller.addLog();
////		assertEquals(controller.getLogCommands(), buffer.lines().collect(Collectors.joining("\n")));
//	}
//
//	@Test
//	public void testAddPainting() throws FileNotFoundException, IOException, ClassNotFoundException {
////		File file = folder.newFile("paint.bin");
////		fileChooser.setSelectedFile(file);
////		controller.setFileChooser(fileChooser);
////		ObjectInputStream ois = new ObjectInputStream(
////				new FileInputStream(controller.getFileChooser().getSelectedFile().getAbsoluteFile()));
////		controller.addPainting();
////		assertEquals(ois.readObject(), model.getShapes());
//	}
//
//	@Test
//	public void testLoadPainting() {
//		controllerMock.loadPainting();
//		verify(controllerMock).addPainting();
//	}
//
//	@Test(expected = NumberFormatException.class)
//	public void testAddPointFromLogWrongLogExceptionExpected() {
//		String[] line = "Add - Point (x: 86A , y: 137 , Border color: -360334 )".split(" ");
//		controllerMock.addPointFromLog(line);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//	}
//
//	@Test
//	public void testAddPointFromLog() {
//		String[] line = "Add - Point (x: 86 , y: 137 , Border color: -360334 )".split(" ");
//		controllerMock.addPointFromLog(line);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//	}
//
//	@Test(expected = NumberFormatException.class)
//	public void testAddLineFromLogWrongLogExceptionExpected() {
//		String[] line = "Add - Line Start point: (x: 359 , y: 226 , Border color: -360334 ), End point: (x: 237 , y: 1C17 , Border color: -360334 ), Border color: -360334"
//				.split(" ");
//
//		controllerMock.addLineFromLog(line);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//	}
//
//	@Test
//	public void testAddLineFromLog() {
//		String[] line = "Add - Line Start point: (x: 359 , y: 226 , Border color: -360334 ), End point: (x: 237 , y: 117 , Border color: -360334 ), Border color: -360334"
//				.split(" ");
//
//		controllerMock.addLineFromLog(line);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//	}
//
//	@Test(expected = NumberFormatException.class)
//	public void testAddRectangleFromLogWrongLogExceptionExpected() {
//		String[] line = "Add - Rectangle Upper left point: (x: 419 , y: 148 , Border color: -360334 ), height: 12sad , width: 21 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.addRectangleFromLog(line);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//	}
//
//	@Test
//	public void testAddRectangleFromLog() {
//		String[] line = "Add - Rectangle Upper left point: (x: 419 , y: 148 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.addRectangleFromLog(line);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//	}
//
//	@Test(expected = NumberFormatException.class)
//	public void testAddCircleFromLogWrongLogExceptionExpected() {
//		String[] line = "Add - Circle Center: (x: 551 , y: 162 , Border color: -3sadd60334 ), radius: 12 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.addCircleFromLog(line);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//	}
//
//	@Test
//	public void testAddCircleFromLog() {
//		String[] line = "Add - Circle Center: (x: 551 , y: 162 , Border color: -360334 ), radius: 12 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.addCircleFromLog(line);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//	}
//
//	@Test(expected = NumberFormatException.class)
//	public void testAddDonutFromLogWrongLogExceptionExpected() {
//		String[] line = "Add - Donut Center: (x: 214 , y: 173 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171 , inner radius: 12 , Border color: -36s0334 , Fill Color: -5171"
//				.split(" ");
//
//		controllerMock.addDonutFromLog(line);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//	}
//
//	@Test
//	public void testAddDonutFromLog() {
//		String[] line = "Add - Donut Center: (x: 214 , y: 173 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171 , inner radius: 12 , Border color: -360334 , Fill Color: -5171"
//				.split(" ");
//
//		controllerMock.addDonutFromLog(line);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//	}
//
//	@Test(expected = NumberFormatException.class)
//	public void testAddHexagonFromLogWrongLogExceptionExpected() {
//		String[] line = "Add - HexagonAdapter Center: (x: 267 , y: 302 , Border color: -360qw334 ), radius: 123 , Border color: -36033d4 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.addHexagonFromLog(line);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//	}
//
//	@Test
//	public void testAddHexagonFromLog() {
//		String[] line = "Add - HexagonAdapter Center: (x: 267 , y: 302 , Border color: -360334 ), radius: 123 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.addHexagonFromLog(line);
//		CmdAdd cmdAdd = controllerMock.getCmdAdd();
//		verify(controllerMock).executeCommand(cmdAdd);
//	}
//
//	@Test(expected = NumberFormatException.class)
//	public void testSelectPointWrongLogExceptionExpected() {
//		String[] line = "Select - Point (x: 45we , y: 78 , Border color: -360334 )".split(" ");
//		controllerMock.selectPointFromLog(line);
//		CmdSelect cmdSelect = controllerMock.getCmdSelect();
//		verify(controllerMock).executeCommand(cmdSelect);
//	}
//
//	@Test
//	public void testSelectPoint() {
//		String[] line = "Select - Point (x: 45 , y: 78 , Border color: -360334 )".split(" ");
//		controllerMock.selectPointFromLog(line);
//		CmdSelect cmdSelect = controllerMock.getCmdSelect();
//		verify(controllerMock).executeCommand(cmdSelect);
//	}
//
//	@Test(expected = NumberFormatException.class)
//	public void testSelectLineWrongLogExceptionExpected() {
//		String[] line = "Select - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334we"
//				.split(" ");
//
//		controllerMock.selectLineFromLog(line);
//		CmdSelect cmdSelect = controllerMock.getCmdSelect();
//		verify(controllerMock).executeCommand(cmdSelect);
//	}
//
//	@Test
//	public void testSelectLine() {
//		String[] line = "Select - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
//				.split(" ");
//
//		controllerMock.selectLineFromLog(line);
//		CmdSelect cmdSelect = controllerMock.getCmdSelect();
//		verify(controllerMock).executeCommand(cmdSelect);
//	}
//
//	@Test(expected = NumberFormatException.class)
//	public void testSelectRectangleWrongLogExceptionExpected() {
//		String[] line = "Select - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171we"
//				.split(" ");
//
//		controllerMock.selectRectangleFromLog(line);
//		CmdSelect cmdSelect = controllerMock.getCmdSelect();
//		verify(controllerMock).executeCommand(cmdSelect);
//	}
//
//	@Test
//	public void testSelectRectangle() {
//		String[] line = "Select - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.selectRectangleFromLog(line);
//		CmdSelect cmdSelect = controllerMock.getCmdSelect();
//		verify(controllerMock).executeCommand(cmdSelect);
//	}
//
//	@Test(expected = NumberFormatException.class)
//	public void testSelectCircleWrongLogExceptionExpected() {
//		String[] line = "Select - Circle Center: (x: 521 , y: 256 , Border color: -3603sad34 ), radius: 21 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.selectCircleFromLog(line);
//		CmdSelect cmdSelect = controllerMock.getCmdSelect();
//		verify(controllerMock).executeCommand(cmdSelect);
//	}
//
//	@Test
//	public void testSelectCircle() {
//		String[] line = "Select - Circle Center: (x: 521 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.selectCircleFromLog(line);
//		CmdSelect cmdSelect = controllerMock.getCmdSelect();
//		verify(controllerMock).executeCommand(cmdSelect);
//	}
//
//	@Test(expected = NumberFormatException.class)
//	public void testSelectDonutWrongLogExceptionExpected() {
//		String[] line = "Select - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: we-360334 , Fill color: -5171 , inner radius: 2d1 , Border color: -360334 , Fill Color: -5171"
//				.split(" ");
//
//		controllerMock.selectDonutFromLog(line);
//		CmdSelect cmdSelect = controllerMock.getCmdSelect();
//		verify(controllerMock).executeCommand(cmdSelect);
//	}
//
//	@Test
//	public void testSelectDonut() {
//		String[] line = "Select - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171"
//				.split(" ");
//
//		controllerMock.selectDonutFromLog(line);
//		CmdSelect cmdSelect = controllerMock.getCmdSelect();
//		verify(controllerMock).executeCommand(cmdSelect);
//	}
//
//	@Test(expected = NumberFormatException.class)
//	public void testSelectHexagonWrongLogExceptionExpected() {
//		String[] line = "Select - HexagonAdapter Center: (x: 609 , y: 141 , Border color: -360334 ), radiusds: 2w1 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.selectHexagonFromLog(line);
//		CmdSelect cmdSelect = controllerMock.getCmdSelect();
//		verify(controllerMock).executeCommand(cmdSelect);
//	}
//
//	@Test
//	public void testSelectHexagon() {
//		String[] line = "Select - HexagonAdapter Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.selectHexagonFromLog(line);
//		CmdSelect cmdSelect = controllerMock.getCmdSelect();
//		verify(controllerMock).executeCommand(cmdSelect);
//	}
//
//	@Test
//	public void testDeselectPoint() {
//		String[] line = "Deselect - Point (x: 45 , y: 78 , Border color: -360334 )".split(" ");
//		controllerMock.deselectPointFromLog(line);
//		CmdDeselect cmdDeselect = controllerMock.getCmdDeselect();
//		verify(controllerMock).executeCommand(cmdDeselect);
//	}
//
//	@Test
//	public void testDeselectLine() {
//		String[] line = "Deselect - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
//				.split(" ");
//
//		controllerMock.deselectLineFromLog(line);
//		CmdDeselect cmdDeselect = controllerMock.getCmdDeselect();
//		verify(controllerMock).executeCommand(cmdDeselect);
//	}
//
//	@Test
//	public void testDeselectRectangle() {
//		String[] line = "Deselect - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.deselectRectangleFromLog(line);
//		CmdDeselect cmdDeselect = controllerMock.getCmdDeselect();
//		verify(controllerMock).executeCommand(cmdDeselect);
//	}
//
//	@Test
//	public void testDeselectCircle() {
//		String[] line = "Deselect - Circle Center: (x: 521 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.deselectCircleFromLog(line);
//		CmdDeselect cmdDeselect = controllerMock.getCmdDeselect();
//		verify(controllerMock).executeCommand(cmdDeselect);
//	}
//
//	@Test
//	public void testDeselectDonut() {
//		String[] line = "Deselect - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171"
//				.split(" ");
//
//		controllerMock.deselectDonutFromLog(line);
//		CmdDeselect cmdDeselect = controllerMock.getCmdDeselect();
//		verify(controllerMock).executeCommand(cmdDeselect);
//	}
//
//	@Test
//	public void testDeselectHexagon() {
//		String[] line = "Deselect - HexagonAdapter Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		controllerMock.deselectHexagonFromLog(line);
//		CmdDeselect cmdDeselect = controllerMock.getCmdDeselect();
//		verify(controllerMock).executeCommand(cmdDeselect);
//	}
//
//	@Test
//	public void testModifyPointFromLog() {
//		String[] line = "Modify - Point from (x: 45 , y: 78 , Border color: -360334 ) to  (x: 46 , y: 78 , Border color: -360334 )"
//				.split(" ");
//
//		Point point = new Point(45, 78, false, new Color(250, 128, 114));
//		controllerMock.executeCommand(new CmdAdd(model, point));
//		controllerMock.executeCommand(new CmdSelect(model, point));
//
//		Point newState = new Point(Integer.parseInt(line[17]), Integer.parseInt(line[20]), true,
//				(Integer.parseInt(line[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[24]))));
//
//		Point originalState = point.clone();
//
//		controllerMock.modifyPointFromLog(line, point);
//		CmdModifyPoint cmdModifyPoint = controllerMock.getCmdModifyPoint();
//		assertFalse(model.getSelectedShapes().contains(originalState));
//		assertTrue(model.getSelectedShapes().contains(newState));
//		verify(controllerMock).executeCommand(cmdModifyPoint);
//	}
//
//	@Test
//	public void testModifyLineFromLog() {
//		String[] line = "Modify - Line from Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334 to  Start point: (x: 90 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
//				.split(" ");
//
//		Line lineShape = new Line(new Point(77, 86), new Point(79, 197), false, new Color(250, 128, 114));
//		controllerMock.executeCommand(new CmdAdd(model, lineShape));
//		controllerMock.executeCommand(new CmdSelect(model, lineShape));
//
//		Point startPoint = new Point(Integer.parseInt(line[36]), Integer.parseInt(line[39]), false,
//				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))));
//
//		Point endPoint = new Point(Integer.parseInt(line[48]), Integer.parseInt(line[51]), false,
//				(Integer.parseInt(line[55]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[55]))));
//
//		Line newState = new Line(startPoint, endPoint, true,
//				(Integer.parseInt(line[59]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[59]))));
//
//		Line originalState = lineShape.clone();
//
//		controllerMock.modifyLineFromLog(line, lineShape);
//		CmdModifyLine cmdModifyLine = controllerMock.getCmdModifyLine();
//		assertFalse(model.getSelectedShapes().contains(originalState));
//		assertTrue(model.getSelectedShapes().contains(newState));
//		verify(controllerMock).executeCommand(cmdModifyLine);
//	}
//
//	@Test
//	public void testModifyRectangleFromLog() {
//		String[] line = "Modify - Rectangle from Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171 to  Upper left point: (x: 150 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		Rectangle rectangle = new Rectangle(new Point(142, 60), 12, 21, false, new Color(250, 128, 114),
//				new Color(255, 235, 205));
//		controllerMock.executeCommand(new CmdAdd(model, rectangle));
//		controllerMock.executeCommand(new CmdSelect(model, rectangle));
//
//		Point point = new Point(Integer.parseInt(line[36]), Integer.parseInt(line[39]), false,
//				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))));
//
//		Rectangle newState = new Rectangle(point, Integer.parseInt(line[46]), Integer.parseInt(line[49]), true,
//				(Integer.parseInt(line[53]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[53]))),
//				(Integer.parseInt(line[57]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[57]))));
//
//		Rectangle originalState = rectangle.clone();
//		controllerMock.modifyRectangleFromLog(line, rectangle);
//		CmdModifyRectangle cmdModifyRectangle = controllerMock.getCmdModifyRectangle();
//		assertFalse(model.getSelectedShapes().contains(originalState));
//		assertTrue(model.getSelectedShapes().contains(newState));
//		verify(controllerMock).executeCommand(cmdModifyRectangle);
//	}
//
//	@Test
//	public void testModifyCircleFromLog() {
//		String[] line = "Modify - Circle from Center: (x: 521 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171 to  Center: (x: 550 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		Circle circle = new Circle(new Point(521, 256), 21, false, new Color(250, 128, 114), new Color(255, 235, 205));
//		controllerMock.executeCommand(new CmdAdd(model, circle));
//		controllerMock.executeCommand(new CmdSelect(model, circle));
//
//		Point point = new Point(Integer.parseInt(line[29]), Integer.parseInt(line[32]), false,
//				(Integer.parseInt(line[36]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[36]))));
//
//		Circle newState = new Circle(point, Integer.parseInt(line[39]), true,
//				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))),
//				(Integer.parseInt(line[47]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[47]))));
//
//		Circle originalState = circle.clone();
//
//		controllerMock.modifyCircleFromLog(line, circle);
//		CmdModifyCircle cmdModifyCircle = controllerMock.getCmdModifyCircle();
//		assertFalse(model.getSelectedShapes().contains(originalState));
//		assertTrue(model.getSelectedShapes().contains(newState));
//		verify(controllerMock).executeCommand(cmdModifyCircle);
//	}
//
//	@Test
//	public void testModifyDonutFromLog() {
//		String[] line = "Modify - Donut from Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171 to  Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 23 , Border color: -360334 , Fill Color: -5171"
//				.split(" ");
//
//		Donut donut = new Donut(new Point(330, 156), 121, 21, false, new Color(250, 128, 114),
//				new Color(255, 235, 205));
//
//		controllerMock.executeCommand(new CmdAdd(model, donut));
//		controllerMock.executeCommand(new CmdSelect(model, donut));
//
//		Point point = new Point(Integer.parseInt(line[41]), Integer.parseInt(line[44]), false,
//				(Integer.parseInt(line[48]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[48]))));
//
//		Donut newState = new Donut(point, Integer.parseInt(line[51]), Integer.parseInt(line[63]), true,
//				(Integer.parseInt(line[67]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[67]))),
//				(Integer.parseInt(line[71]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[71]))));
//
//		Donut originalState = donut.clone();
//		controllerMock.modifyDonutFromLog(line, donut);
//		CmdModifyDonut cmdModifyDonut = controllerMock.getCmdModifyDonut();
//		assertFalse(model.getSelectedShapes().contains(originalState));
//		assertTrue(model.getSelectedShapes().contains(newState));
//		verify(controllerMock).executeCommand(cmdModifyDonut);
//	}
//
//	@Test
//	public void testModifyHexagonFromLog() {
//		String[] line = "Modify - HexagonAdapter from Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171 to  Center: (x: 700 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
//				.split(" ");
//
//		HexagonAdapter hexagon = new HexagonAdapter(609, 141, 21, new Color(250, 128, 114), new Color(255, 235, 205),
//				false);
//
//		controllerMock.executeCommand(new CmdAdd(model, hexagon));
//		controllerMock.executeCommand(new CmdSelect(model, hexagon));
//
//		Point point = new Point(Integer.parseInt(line[29]), Integer.parseInt(line[32]), false,
//				(Integer.parseInt(line[36]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[36]))));
//
//		HexagonAdapter newState = new HexagonAdapter(point.getXcoordinate(), point.getYcoordinate(),
//				Integer.parseInt(line[39]),
//				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))),
//				(Integer.parseInt(line[47]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[47]))),
//				true);
//
//		HexagonAdapter originalState = hexagon.clone();
//		controllerMock.modifyHexagonFromLog(line, hexagon);
//		CmdModifyHexagon cmdModifyHexagon = controllerMock.getCmdModifyHexagon();
//		assertFalse(model.getSelectedShapes().contains(originalState));
//		assertTrue(model.getSelectedShapes().contains(newState));
//		verify(controllerMock).executeCommand(cmdModifyHexagon);
//	}
//}