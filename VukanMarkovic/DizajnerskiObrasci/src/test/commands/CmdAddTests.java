package commands;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import model.DrawingModel;

public class CmdAddTests {
	private DrawingModel model;
	private Point point;
	private CmdAdd cmdAdd;

	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(1, 2);
		cmdAdd = new CmdAdd(model, point);
	}

	@Test
	public void testExecute() {
		cmdAdd.execute();
		assertTrue(model.doesContainShape(point));
	}

	@Test
	public void testUnexecuteExecuteNotCalled() {
		cmdAdd.unexecute();
		assertFalse(model.doesContainShape(point));
	}

	@Test
	public void testUnexecuteExecuteCalled() {
		cmdAdd.execute();
		cmdAdd.unexecute();
		assertFalse(model.doesContainShape(point));
	}
}