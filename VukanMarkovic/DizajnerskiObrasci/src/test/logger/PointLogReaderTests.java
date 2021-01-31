package logger;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import commandsHandler.CommandsHandler;
import model.DrawingModel;
import java.awt.Color;

public class PointLogReaderTests {
	private DrawingModel model;
	private CommandsHandler commandsHandler;
	private PointLogReader logReader;

	@Before
	public void setUp() {
		model = new DrawingModel();
		commandsHandler = new CommandsHandler();
		logReader = new PointLogReader(model, commandsHandler);
	}

	@Test
	public void testAddShapeFromLog() {
		addShapeFromLog();
		assertTrue(model.doesContainShape(logReader.getPoint()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdAdd()));
	}

	@Test
	public void testModifyShapeFromLog() {
		String[] logLine = "Modify - Point from (x: 45 , y: 78 , Border color: -360334 ) to  (x: 46 , y: 78 , Border color: -360334 )"
				.split(" ");

		Point point = new Point(45, 78, false, new Color(250, 128, 114));

		Point newState = new Point(Integer.parseInt(logLine[17]), Integer.parseInt(logLine[20]), true,
				new Color(Integer.parseInt(logLine[24])));

		Point originalState = point.clone();
		logReader.modifyShapeFromLog(logLine, point);
		assertFalse(model.getSelectedShapes().contains(originalState));
		assertTrue(model.getSelectedShapes().contains(newState));
		assertEquals(point, newState);
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdModifyPoint()));
	}

	@Test
	public void testSelectShapeFromLog() {
		addShapeFromLog();
		String[] logLine = "Select - Point (x: 86 , y: 137 , Border color: -360334 )".split(" ");
		logReader.selectShapeFromLog(logLine);
		assertTrue(model.doesContainSelectedShape(logReader.getPoint()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdSelect()));
	}

	@Test
	public void testDeselectShapeFromLog() {
		addShapeFromLog();
		String[] logLine = "Add - Point (x: 86 , y: 137 , Border color: -360334 )".split(" ");
		logReader.deselectShapeFromLog(logLine);
		assertFalse(model.doesContainSelectedShape(logReader.getPoint()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdDeselect()));
	}

	private void addShapeFromLog() {
		String[] logLine = "Add - Point (x: 86 , y: 137 , Border color: -360334 )".split(" ");
		logReader.addShapeFromLog(logLine);
	}
}