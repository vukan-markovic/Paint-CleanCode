package logger;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import model.DrawingModel;
import java.awt.Color;
import stack.CommandsStack;

public class LineLogReaderTests {
	private DrawingModel model;
	private CommandsStack commandsStack;
	private LineLogReader logReader;

	@Before
	public void setUp() {
		model = new DrawingModel();
		commandsStack = new CommandsStack();
		logReader = new LineLogReader(model, commandsStack);
	}

	@Test
	public void testAddShapeFromLog() {
		String[] line = "Add - Line Start point: (x: 359 , y: 226 , Border color: -360334 ), End point: (x: 237 , y: 117 , Border color: -360334 ), Border color: -360334"
				.split(" ");

		logReader.addShapeFromLog(line);
		assertTrue(model.doesContainShape(logReader.getLine()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdAdd()));
	}

	@Test
	public void testModifyShapeFromLog() {
		String[] line = "Modify - Line from Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334 to  Start point: (x: 90 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
				.split(" ");

		Line lineShape = new Line(new Point(77, 86), new Point(79, 197), false, new Color(250, 128, 114));

		Point startPoint = new Point(Integer.parseInt(line[36]), Integer.parseInt(line[39]), false,
				(Integer.parseInt(line[43]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[43]))));

		Point endPoint = new Point(Integer.parseInt(line[48]), Integer.parseInt(line[51]), false,
				(Integer.parseInt(line[55]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[55]))));

		Line newState = new Line(startPoint, endPoint, true,
				(Integer.parseInt(line[59]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[59]))));

		Line originalState = lineShape.clone();
		logReader.modifyShapeFromLog(line, lineShape);
		assertFalse(model.getSelectedShapes().contains(originalState));
		assertTrue(model.getSelectedShapes().contains(newState));
		assertEquals(lineShape, newState);
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdModifyLine()));
	}

	@Test
	public void testSelectShapeFromLog() {
		String[] lineLogAdd = "Add - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
				.split(" ");
		
		logReader.addShapeFromLog(lineLogAdd);

		String[] line = "Select - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
				.split(" ");
		
		logReader.selectShapeFromLog(line);
		assertTrue(model.doesContainSelectedShape(logReader.getLine()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdSelect()));
	}

	@Test
	public void testDeselectShapeFromLog() {
		String[] lineLogAdd = "Add - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
				.split(" ");
		
		logReader.addShapeFromLog(lineLogAdd);
		
		String[] line = "Deselect - Line Start point: (x: 77 , y: 86 , Border color: -360334 ), End point: (x: 79 , y: 197 , Border color: -360334 ), Border color: -360334"
				.split(" ");

		logReader.deselectShapeFromLog(line);
		assertFalse(model.doesContainSelectedShape(logReader.getLine()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdDeselect()));
	}
}