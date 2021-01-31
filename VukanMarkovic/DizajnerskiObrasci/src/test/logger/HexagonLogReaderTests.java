package logger;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import commandsHandler.CommandsHandler;
import hexagon.Hexagon;
import model.DrawingModel;
import java.awt.Color;

public class HexagonLogReaderTests {
	private DrawingModel model;
	private CommandsHandler commandsHandler;
	private HexagonLogReader logReader;

	@Before
	public void setUp() {
		model = new DrawingModel();
		commandsHandler = new CommandsHandler();
		logReader = new HexagonLogReader(model, commandsHandler);
	}

	@Test
	public void testAddShapeFromLog() {
		addShapeFromLog();
		assertTrue(model.doesContainShape(logReader.getHexagon()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdAdd()));
	}

	@Test
	public void testModifyShapeFromLog() {
		String[] logLine = "Modify - HexagonAdapter from Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171 to  Center: (x: 700 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		HexagonAdapter hexagon = new HexagonAdapter(new Hexagon(609, 141, 21), false, new Color(250, 128, 114),
				new Color(255, 235, 205));

		Point point = new Point(Integer.parseInt(logLine[29]), Integer.parseInt(logLine[32]), false,
				new Color(Integer.parseInt(logLine[36])));

		HexagonAdapter newState = new HexagonAdapter(
				new Hexagon(point.getXcoordinate(), point.getYcoordinate(), Integer.parseInt(logLine[39])), true,
				new Color(Integer.parseInt(logLine[43])), new Color(Integer.parseInt(logLine[47])));

		HexagonAdapter originalState = hexagon.clone();
		logReader.modifyShapeFromLog(logLine, hexagon);
		assertFalse(model.getSelectedShapes().contains(originalState));
		assertTrue(model.getSelectedShapes().contains(newState));
		assertEquals(hexagon, newState);
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdModifyHexagon()));
	}

	@Test
	public void testSelectShapeFromLog() {
		addShapeFromLog();

		String[] lineLog = "Select - HexagonAdapter Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.selectShapeFromLog(lineLog);
		assertTrue(model.doesContainSelectedShape(logReader.getHexagon()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdSelect()));
	}

	@Test
	public void testDeselectShapeFromLog() {
		addShapeFromLog();

		String[] lineLog = "Deselect - HexagonAdapter Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.deselectShapeFromLog(lineLog);
		assertFalse(model.doesContainSelectedShape(logReader.getHexagon()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdDeselect()));
	}

	private void addShapeFromLog() {
		String[] lineLog = "Add - HexagonAdapter Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.addShapeFromLog(lineLog);
	}
}