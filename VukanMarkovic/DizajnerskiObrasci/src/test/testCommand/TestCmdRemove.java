package test.testCommand;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import command.CmdRemove;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class TestCmdRemove {
	private DrawingModel model;
	private Shape shape;
	private CmdRemove cmdRemove;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point();
		cmdRemove = new CmdRemove(model, shape);
	}

	@Test
	public void testExecuteShapeRemovedListEmpty() {
		cmdRemove.execute();
		assertFalse(model.getShapes().contains(shape));
	}

	@Test
	public void testExecuteShapeRemoved() {
		model.add(shape);
		cmdRemove.execute();
		assertFalse(model.getShapes().contains(shape));
	}

	@Test
	public void testUnexecuteShapeAddedExecuteNotCalled() {
		cmdRemove.unexecute();
		assertTrue(model.getShapes().contains(shape));
	}

	@Test
	public void testUnexecuteShapeAdded() {
		model.add(shape);
		cmdRemove.execute();
		cmdRemove.unexecute();
		assertTrue(model.getShapes().contains(shape));
	}
}