package logger;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import commandsHandler.CommandsHandler;
import model.DrawingModel;
import java.awt.Color;

public class DonutLogReaderTests {
	private DrawingModel model;
	private CommandsHandler commandsHandler;
	private DonutLogReader logReader;

	@Before
	public void setUp() {
		model = new DrawingModel();
		commandsHandler = new CommandsHandler();
		logReader = new DonutLogReader(model, commandsHandler);
	}

	@Test
	public void testAddShapeFromLog() {
		addShapeFromLog();
		assertTrue(model.doesContainShape(logReader.getDonut()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdAdd()));
	}

	@Test
	public void testModifyShapeFromLog() {
		String[] logLine = "Modify - Donut from Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171 to  Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 23 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		Donut donut = new Donut(new Point(330, 156), 121, 21, false, new Color(250, 128, 114),
				new Color(255, 235, 205));

		Point point = new Point(Integer.parseInt(logLine[41]), Integer.parseInt(logLine[44]), false,
				new Color(Integer.parseInt(logLine[48])));

		Donut newState = new Donut(point, Integer.parseInt(logLine[51]), Integer.parseInt(logLine[63]), true,
				new Color(Integer.parseInt(logLine[67])), new Color(Integer.parseInt(logLine[71])));

		Donut originalState = donut.clone();
		logReader.modifyShapeFromLog(logLine, donut);
		assertFalse(model.getSelectedShapes().contains(originalState));
		assertTrue(model.getSelectedShapes().contains(newState));
		assertEquals(donut, newState);
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdModifyDonut()));
	}

	@Test
	public void testSelectShapeFromLog() {
		addShapeFromLog();

		String[] logLine = "Select - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		logReader.selectShapeFromLog(logLine);
		assertTrue(model.doesContainSelectedShape(logReader.getDonut()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdSelect()));
	}

	@Test
	public void testDeselectShapeFromLog() {
		addShapeFromLog();

		String[] lineLog = "Deselect - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		logReader.deselectShapeFromLog(lineLog);
		assertFalse(model.doesContainSelectedShape(logReader.getDonut()));
		assertTrue(commandsHandler.getExecutedCommands().contains(logReader.getCmdDeselect()));
	}

	private void addShapeFromLog() {
		String[] lineLog = "Add - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		logReader.addShapeFromLog(lineLog);
	}
}