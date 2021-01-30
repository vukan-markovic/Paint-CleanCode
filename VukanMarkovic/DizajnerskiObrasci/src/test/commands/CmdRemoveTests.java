package commands;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import model.DrawingModel;

public class CmdRemoveTests {
	private DrawingModel model;
	private Shape shape;
	private CmdRemove cmdRemove;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point(1, 2);
		cmdRemove = new CmdRemove(model, shape);
	}

	@Test
	public void testExecuteListEmpty() {
		cmdRemove.execute();
		assertFalse(model.getShapes().contains(shape));
	}

	@Test
	public void testExecute() {
		model.addShape(shape);
		cmdRemove.execute();
		assertFalse(model.getShapes().contains(shape));
	}

	@Test
	public void testUnexecuteExecuteNotCalled() {
		cmdRemove.unexecute();
		assertTrue(model.getShapes().contains(shape));
	}

	@Test
	public void testUnexecuteExecuteCalled() {
		model.addShape(shape);
		cmdRemove.execute();
		cmdRemove.unexecute();
		assertTrue(model.getShapes().contains(shape));
	}
}