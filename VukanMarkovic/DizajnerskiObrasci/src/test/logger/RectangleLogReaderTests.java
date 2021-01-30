package logger;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import model.DrawingModel;
import java.awt.Color;
import stack.CommandsStack;

public class RectangleLogReaderTests {
	private DrawingModel model;
	private CommandsStack commandsStack;
	private RectangleLogReader logReader;

	@Before
	public void setUp() {
		model = new DrawingModel();
		commandsStack = new CommandsStack();
		logReader = new RectangleLogReader(model, commandsStack);
	}

	@Test
	public void testAddShapeFromLog() {
		String[] line = "Add - Rectangle Upper left point: (x: 419 , y: 148 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.addShapeFromLog(line);
		assertTrue(model.doesContainShape(logReader.getRectangle()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdAdd()));
	}

	@Test
	public void testModifyShapeFromLog() {
		String[] line = "Modify - Rectangle from Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171 to  Upper left point: (x: 150 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		Rectangle rectangle = new Rectangle(new Point(142, 60), 12, 21, false, new Color(250, 128, 114),
				new Color(255, 235, 205));

		Point point = new Point(Integer.parseInt(line[36]), Integer.parseInt(line[39]), false,
				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))));

		Rectangle newState = new Rectangle(point, Integer.parseInt(line[46]), Integer.parseInt(line[49]), true,
				(Integer.parseInt(line[53]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[53]))),
				(Integer.parseInt(line[57]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[57]))));

		Rectangle originalState = rectangle.clone();
		logReader.modifyShapeFromLog(line, rectangle);
		assertFalse(model.getSelectedShapes().contains(originalState));
		assertTrue(model.getSelectedShapes().contains(newState));
		assertEquals(rectangle, newState);
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdModifyRectangle()));
	}

	@Test
	public void testSelectShapeFromLog() {
		String[] logLine = "Add - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.addShapeFromLog(logLine);

		String[] line = "Select - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.selectShapeFromLog(logLine);
		assertTrue(model.doesContainSelectedShape(logReader.getRectangle()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdSelect()));
	}

	@Test
	public void testDeselectShapeFromLog() {
		String[] logLine = "Add - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.addShapeFromLog(logLine);

		String[] line = "Deselect - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.deselectShapeFromLog(logLine);
		assertFalse(logReader.getRectangle().isSelected());
		assertFalse(model.doesContainSelectedShape(logReader.getRectangle()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdDeselect()));
	}
}