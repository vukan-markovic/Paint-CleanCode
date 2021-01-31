package logger;

import static org.junit.Assert.*;
import org.junit.*;

import commandsHandler.CommandsHandler;
import shapes.*;
import model.DrawingModel;
import java.awt.Color;

public class CircleLogReaderTests {
	private DrawingModel model;
	private CommandsHandler commandsHandler;
	private CircleLogReader logReader;

	@Before
	public void setUp() {
		model = new DrawingModel();
		commandsHandler = new CommandsHandler();
		logReader = new CircleLogReader(model, commandsHandler);
	}

	@Test
	public void testAddShapeFromLog() {
		String[] line = "Add - Circle Center: (x: 551 , y: 162 , Border color: -360334 ), radius: 12 , Border color: -360334 , Fill color: -5171"
				.split(" ");
		logReader.addShapeFromLog(line);
		assertTrue(model.doesContainShape(logReader.getCircle()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdAdd()));
	}

	@Test
	public void testModifyShapeFromLog() {
		String[] line = "Modify - Circle from Center: (x: 521 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171 to  Center: (x: 550 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		Circle circle = new Circle(new Point(521, 256), 21, false, new Color(250, 128, 114), new Color(255, 235, 205));

		Point point = new Point(Integer.parseInt(line[29]), Integer.parseInt(line[32]), false,
				new Color(Integer.parseInt(line[36])));

		Circle newState = new Circle(point, Integer.parseInt(line[39]), true, new Color(Integer.parseInt(line[43])),
				new Color(Integer.parseInt(line[47])));

		Circle originalState = circle.clone();
		logReader.modifyShapeFromLog(line, circle);
		assertFalse(model.getSelectedShapes().contains(originalState));
		assertTrue(model.getSelectedShapes().contains(newState));
		assertEquals(circle, newState);
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdModifyCircle()));
	}

	@Test
	public void testSelectShapeFromLog() {
		String[] lineLogAdd = "Add - Circle Center: (x: 521 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");
		logReader.addShapeFromLog(lineLogAdd);

		String[] line = "Select - Circle Center: (x: 521 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.selectShapeFromLog(line);
		assertTrue(model.doesContainSelectedShape(logReader.getCircle()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdSelect()));
	}

	@Test
	public void testDeselectShapeFromLog() {
		String[] lineLogAdd = "Add - Circle Center: (x: 521 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");
		logReader.addShapeFromLog(lineLogAdd);

		String[] line = "Deselect - Circle Center: (x: 521 , y: 256 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171"
				.split(" ");

		logReader.deselectShapeFromLog(line);
		assertFalse(model.doesContainSelectedShape(logReader.getCircle()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdDeselect()));
	}
}