package controller;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import java.awt.Color;
import org.junit.rules.TemporaryFolder;
import commands.CmdAdd;
import frame.DrawingFrame;
import model.DrawingModel;
import shapes.Point;

public class FileControllerTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private Queue<String> commandsLog;
	private FileController fileController;

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsLog = new LinkedList<String>();
		fileController = new FileController(model, frame, commandsLog);
	}

	@Test
	public void testSaveDrawingAndLogNoShapes() {
		fileController.saveDrawingAndLog();
		assertNull(fileController.getFilePath());
		assertNull(fileController.getFileDrawing());
		assertNull(fileController.getFileLog());
		assertNull(fileController.getStrategy());
	}

	@Test
	public void testSaveDrawingAndLog() {
		Point point = new Point(1, 2, false, Color.BLACK);
		new CmdAdd(model, point).execute();
		frame.getCommandsListModel().addElement("Add - " + point.getClassName() + " " + point);
		fileController.saveDrawingAndLog();
		assertNotNull(fileController.getFilePath());
		assertNotNull(fileController.getFileDrawing());
		assertNotNull(fileController.getFileLog());
		assertNotNull(fileController.getStrategy());
	}

	@Test
	public void testLoadDrawing() {
		fileController.loadDrawing();
		assertFalse(model.getShapes().isEmpty());
	}

	@Test
	public void testLoadLog() {
		fileController.loadLog();
		assertTrue(fileController.getBtnNext().isEnabled());
		assertFalse(fileController.getCommandsLog().isEmpty());
	}
}