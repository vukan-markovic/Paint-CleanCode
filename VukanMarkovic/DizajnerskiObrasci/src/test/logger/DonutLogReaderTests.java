package logger;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import model.DrawingModel;
import java.awt.Color;
import stack.CommandsStack;

public class DonutLogReaderTests {
	private DrawingModel model;
	private CommandsStack commandsStack;
	private DonutLogReader logReader;

	@Before
	public void setUp() {
		model = new DrawingModel();
		commandsStack = new CommandsStack();
		logReader = new DonutLogReader(model, commandsStack);
	}

	@Test
	public void testAddShapeFromLog() {
		String[] line = "Add - Donut Center: (x: 214 , y: 173 , Border color: -360334 ), radius: 21 , Border color: -360334 , Fill color: -5171 , inner radius: 12 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		logReader.addShapeFromLog(line);
		assertTrue(model.doesContainShape(logReader.getDonut()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdAdd()));
	}

	@Test
	public void testModifyShapeFromLog() {
		String[] line = "Modify - Donut from Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171 to  Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 23 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		Donut donut = new Donut(new Point(330, 156), 121, 21, false, new Color(250, 128, 114),
				new Color(255, 235, 205));

		Point point = new Point(Integer.parseInt(line[41]), Integer.parseInt(line[44]), false,
				(Integer.parseInt(line[48]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[48]))));

		Donut newState = new Donut(point, Integer.parseInt(line[51]), Integer.parseInt(line[63]), true,
				(Integer.parseInt(line[67]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[67]))),
				(Integer.parseInt(line[71]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[71]))));

		Donut originalState = donut.clone();
		logReader.modifyShapeFromLog(line, donut);
		assertFalse(model.getSelectedShapes().contains(originalState));
		assertTrue(model.getSelectedShapes().contains(newState));
		assertEquals(donut, newState);
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdModifyDonut()));
	}

	@Test
	public void testSelectShapeFromLog() {
		String[] lineLogAdd = "Add - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		logReader.addShapeFromLog(lineLogAdd);

		String[] line = "Select - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		logReader.selectShapeFromLog(line);
		assertTrue(model.doesContainSelectedShape(logReader.getDonut()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdSelect()));
	}

	@Test
	public void testDeselectShapeFromLog() {
		String[] lineLogAdd = "Add - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		logReader.addShapeFromLog(lineLogAdd);

		String[] line = "Deselect - Donut Center: (x: 330 , y: 156 , Border color: -360334 ), radius: 121 , Border color: -360334 , Fill color: -5171 , inner radius: 21 , Border color: -360334 , Fill Color: -5171"
				.split(" ");

		logReader.deselectShapeFromLog(line);
		assertFalse(model.doesContainSelectedShape(logReader.getDonut()));
		assertTrue(commandsStack.getExecutedCommands().contains(logReader.getCmdDeselect()));
	}
}