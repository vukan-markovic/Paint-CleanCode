package commandsExecutors;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.*;
import commands.*;
import shapes.*;
import frame.DrawingFrame;
import model.DrawingModel;
import stack.CommandsStack;

public class PositionCommandsExecutorTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private CommandsStack commandsStack;
	private PositionCommandsExecutor commandsExecutor;
	private Point point;
	private Line line;
	private int indexOfShape;
	private CmdSelect cmdSelect;
	private CmdAdd cmdAdd;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		commandsStack = new CommandsStack();
		commandsExecutor = new PositionCommandsExecutor(model, frame, commandsStack);
		point = new Point(1, 2, false, Color.BLACK);
		line = new Line(new Point(1, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK), false, Color.BLACK);
	}

	@Test
	public void testToBack() {
		cmdAdd = new CmdAdd(model, point);
		cmdAdd.execute();
		cmdAdd = new CmdAdd(model, line);
		cmdAdd.execute();
		cmdSelect = new CmdSelect(model, line);
		cmdSelect.execute();
		indexOfShape = model.getIndexOfShape(line);
		commandsExecutor.toBack();
		assertEquals(indexOfShape - 1, model.getIndexOfShape(line));
		assertEquals(indexOfShape, model.getIndexOfShape(point));
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdToBack()));
	}

	@Test
	public void testToFront() {
		cmdAdd = new CmdAdd(model, point);
		cmdAdd.execute();
		cmdAdd = new CmdAdd(model, line);
		cmdAdd.execute();
		cmdSelect = new CmdSelect(model, point);
		cmdSelect.execute();
		indexOfShape = model.getIndexOfShape(point);
		commandsExecutor.toFront();
		assertEquals(indexOfShape + 1, model.getIndexOfShape(point));
		assertEquals(indexOfShape, model.getIndexOfShape(line));
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdToFront()));
	}

	@Test
	public void testBringToBack() {
		cmdAdd = new CmdAdd(model, line);
		cmdAdd.execute();
		cmdSelect = new CmdSelect(model, line);
		cmdSelect.execute();
		commandsExecutor.bringToBack();
		assertEquals(0, model.getIndexOfShape(line));
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdSendToBack()));
	}

	@Test
	public void testBringToFront() {
		cmdAdd = new CmdAdd(model, line);
		cmdAdd.execute();
		cmdSelect = new CmdSelect(model, line);
		cmdSelect.execute();
		commandsExecutor.bringToFront();
		assertEquals(model.getNumberOfShapes() - 1, model.getIndexOfShape(line));
		assertTrue(commandsStack.getExecutedCommands().contains(commandsExecutor.getCmdBringToFront()));
	}
}