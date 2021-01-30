package logger;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import model.DrawingModel;
import java.awt.Color;
import stack.CommandsStack;

public class PointLogReaderTests {
	private DrawingModel model;
	private CommandsStack commandsStack;
	private PointLogReader logReader;

	@Before
	public void setUp() {
		model = new DrawingModel();
		commandsStack = new CommandsStack();
		logReader = new PointLogReader(model, commandsStack);
	}

	@Test
	public void testAddShapeFromLog() {
		String[] line = "Add - Point (x: 86 , y: 137 , Border color: -360334 )".split(" ");
		logReader.addShapeFromLog(line);
		assertTrue(model.doesContainShape(logReader.getPoint()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdAdd()));
	}

	@Test
	public void testModifyShapeFromLog() {
		String[] line = "Modify - Point from (x: 45 , y: 78 , Border color: -360334 ) to  (x: 46 , y: 78 , Border color: -360334 )"
				.split(" ");

		Point point = new Point(45, 78, false, new Color(250, 128, 114));

		Point newState = new Point(Integer.parseInt(line[17]), Integer.parseInt(line[20]), true,
				(Integer.parseInt(line[24]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[24]))));

		Point originalState = point.clone();
		logReader.modifyShapeFromLog(line, point);
		assertFalse(model.getSelectedShapes().contains(originalState));
		assertTrue(model.getSelectedShapes().contains(newState));
		assertEquals(point, newState);
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdModifyPoint()));
	}

	@Test
	public void testSelectShapeFromLog() {
		String[] lineLogAdd = "Add - Point (x: 86 , y: 137 , Border color: -360334 )".split(" ");
		logReader.addShapeFromLog(lineLogAdd);
		String[] line = "Select - Point (x: 86 , y: 137 , Border color: -360334 )".split(" ");
		logReader.selectShapeFromLog(line);
		assertTrue(model.doesContainSelectedShape(logReader.getPoint()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdSelect()));
	}

	@Test
	public void testDeselectShapeFromLog() {
		String[] lineLogAdd = "Add - Point (x: 86 , y: 137 , Border color: -360334 )".split(" ");
		logReader.addShapeFromLog(lineLogAdd);
		String[] line = "Add - Point (x: 86 , y: 137 , Border color: -360334 )".split(" ");
		logReader.deselectShapeFromLog(line);
		assertFalse(model.doesContainSelectedShape(logReader.getPoint()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdDeselect()));
	}
}