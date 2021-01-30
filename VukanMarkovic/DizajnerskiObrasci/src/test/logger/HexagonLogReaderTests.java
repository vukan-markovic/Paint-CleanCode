package logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.*;

import hexagon.Hexagon;
import model.DrawingModel;
import shapes.HexagonAdapter;
import shapes.Point;
import stack.CommandsStack;

public class HexagonLogReaderTests {
	private DrawingModel model;
	private CommandsStack commandsStack;
	private HexagonLogReader logReader;

	@Before
	public void setUp() {
		model = new DrawingModel();
		commandsStack = new CommandsStack();
		logReader = new HexagonLogReader(model, commandsStack);
	}

	@Test
	public void testAddShapeFromLog() {
		String[] line = "Add - HexagonAdapter Center: (x: 267 , y: 302 , Border color: -360334 ), radius: 123 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.addShapeFromLog(line);
		assertTrue(model.doesContainShape(logReader.getHexagon()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdAdd()));
	}

	@Test
	public void testModifyShapeFromLog() {
		String[] line = "Modify - HexagonAdapter from Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171 to  Center: (x: 700 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		HexagonAdapter hexagon = new HexagonAdapter(new Hexagon(609, 141, 21), false, new Color(250, 128, 114),
				new Color(255, 235, 205));

		Point point = new Point(Integer.parseInt(line[29]), Integer.parseInt(line[32]), false,
				(Integer.parseInt(line[36]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[36]))));

		HexagonAdapter newState = new HexagonAdapter(
				new Hexagon(point.getXcoordinate(), point.getYcoordinate(), Integer.parseInt(line[39])), true,
				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))),
				(Integer.parseInt(line[47]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[47]))));

		HexagonAdapter originalState = hexagon.clone();
		logReader.modifyShapeFromLog(line, hexagon);
		assertFalse(model.getSelectedShapes().contains(originalState));
		assertTrue(model.getSelectedShapes().contains(newState));
		assertEquals(hexagon, newState);
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdModifyHexagon()));
	}

	@Test
	public void testSelectShapeFromLog() {
		String[] line = "Select - HexagonAdapter Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.selectShapeFromLog(line);
		assertTrue(logReader.getHexagon().isSelected());
		assertTrue(model.doesContainSelectedShape(logReader.getHexagon()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdSelect()));
	}

	@Test
	public void testDeselectShapeFromLog() {
		String[] line = "Deselect - HexagonAdapter Center: (x: 609 , y: 141 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.deselectShapeFromLog(line);
		assertFalse(logReader.getHexagon().isSelected());
		assertFalse(model.doesContainSelectedShape(logReader.getHexagon()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdDeselect()));
	}
}