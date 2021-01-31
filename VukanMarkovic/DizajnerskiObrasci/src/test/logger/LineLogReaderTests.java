package logger;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import commandsHandler.CommandsHandler;
import model.DrawingModel;
import java.awt.Color;

public class LineLogReaderTests {
	private DrawingModel model;
	private CommandsHandler commandsHandler;
	private LineLogReader logReader;

	@Before
	public void setUp() {
		model = new DrawingModel();
		commandsHandler = new CommandsHandler();
		logReader = new LineLogReader(model, commandsHandler);
	}

	@Test
	public void testAddShapeFromLog() {
		addShapeFromLog();
		assertTrue(model.doesContainShape(logReader.getLine()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdAdd()));
	}

	@Test
	public void testModifyShapeFromLog() {
		String[] logLine = "Modify - Line from Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334 to  Start point: (x: 90 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
				.split(" ");

		Line lineShape = new Line(new Point(77, 86), new Point(79, 197), false, new Color(250, 128, 114));

		Point startPoint = new Point(Integer.parseInt(logLine[36]), Integer.parseInt(logLine[39]), false,
				new Color(Integer.parseInt(logLine[43])));

		Point endPoint = new Point(Integer.parseInt(logLine[48]), Integer.parseInt(logLine[51]), false,
				new Color(Integer.parseInt(logLine[55])));

		Line newState = new Line(startPoint, endPoint, true, new Color(Integer.parseInt(logLine[59])));

		Line originalState = lineShape.clone();
		logReader.modifyShapeFromLog(logLine, lineShape);
		assertFalse(model.getSelectedShapes().contains(originalState));
		assertTrue(model.getSelectedShapes().contains(newState));
		assertEquals(lineShape, newState);
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdModifyLine()));
	}

	@Test
	public void testSelectShapeFromLog() {
		addShapeFromLog();

		String[] logLine = "Select - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
				.split(" ");

		logReader.selectShapeFromLog(logLine);
		assertTrue(model.doesContainSelectedShape(logReader.getLine()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdSelect()));
	}

	@Test
	public void testDeselectShapeFromLog() {
		addShapeFromLog();

		String[] logLine = "Deselect - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
				.split(" ");

		logReader.deselectShapeFromLog(logLine);
		assertFalse(model.doesContainSelectedShape(logReader.getLine()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdDeselect()));
	}

	private void addShapeFromLog() {
		String[] logLine = "Add - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
				.split(" ");

		logReader.addShapeFromLog(logLine);
	}
}