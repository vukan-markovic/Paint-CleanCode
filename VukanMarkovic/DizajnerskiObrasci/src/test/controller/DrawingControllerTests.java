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
//	public TemporaryFolder tempFolder = new TemporaryFolder();
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
//		FileDrawing savePainting = new FileDrawing();
//		controller.setStrategy(files);
//		controller.setSavePainting(savePainting);
//		File file = tempFolder.newFile(frame.getFileName().getText() + ".bin");
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
//		File file = tempFolder.newFile(frame.getFileName().getText() + ".txt");
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
////		File file = tempFolder.newFile("log.txt");
////		fileChooser.setSelectedFile(file);
////		controller.setFileChooser(fileChooser);
////		BufferedReader buffer = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getAbsolutePath()));
////		controller.addLog();
////		assertEquals(controller.getLogCommands(), buffer.lines().collect(Collectors.joining("\n")));
//	}
//
//	@Test
//	public void testAddPainting() throws FileNotFoundException, IOException, ClassNotFoundException {
////		File file = tempFolder.newFile("paint.bin");
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