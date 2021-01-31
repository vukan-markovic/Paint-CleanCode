package logger;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import commandsHandler.CommandsHandler;
import model.DrawingModel;
import java.awt.Color;

public class RectangleLogReaderTests {
	private DrawingModel model;
	private CommandsHandler commandsHandler;
	private RectangleLogReader logReader;

	@Before
	public void setUp() {
		model = new DrawingModel();
		commandsHandler = new CommandsHandler();
		logReader = new RectangleLogReader(model, commandsHandler);
	}

	@Test
	public void testAddShapeFromLog() {
		addShapeFromLog();
		assertTrue(model.doesContainShape(logReader.getRectangle()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdAdd()));
	}

	@Test
	public void testModifyShapeFromLog() {
		String[] logLine = "Modify - Rectangle from Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171 to  Upper left point: (x: 150 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		Rectangle rectangle = new Rectangle(new Point(142, 60), 12, 21, false, new Color(250, 128, 114),
				new Color(255, 235, 205));

		Point point = new Point(Integer.parseInt(logLine[36]), Integer.parseInt(logLine[39]), false,
				new Color(Integer.parseInt(logLine[43])));

		Rectangle newState = new Rectangle(point, Integer.parseInt(logLine[46]), Integer.parseInt(logLine[49]), true,
				new Color(Integer.parseInt(logLine[53])), new Color(Integer.parseInt(logLine[57])));

		Rectangle originalState = rectangle.clone();
		logReader.modifyShapeFromLog(logLine, rectangle);
		assertFalse(model.getSelectedShapes().contains(originalState));
		assertTrue(model.getSelectedShapes().contains(newState));
		assertEquals(rectangle, newState);
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdModifyRectangle()));
	}

	@Test
	public void testSelectShapeFromLog() {
		addShapeFromLog();

		String[] logLine = "Select - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.selectShapeFromLog(logLine);
		assertTrue(model.doesContainSelectedShape(logReader.getRectangle()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdSelect()));
	}

	@Test
	public void testDeselectShapeFromLog() {
		addShapeFromLog();

		String[] logLine = "Deselect - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.deselectShapeFromLog(logLine);
		assertFalse(logReader.getRectangle().isSelected());
		assertFalse(model.doesContainSelectedShape(logReader.getRectangle()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdDeselect()));
	}

	private void addShapeFromLog() {
		String[] logLine = "Add - Rectangle Upper left point: (x: 142 , y: 60 , Border color: -360334 ), height: 12 , width: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.addShapeFromLog(logLine);
	}
}