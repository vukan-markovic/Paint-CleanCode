package test.commandsTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import commands.CmdRemove;
import mvc.DrawingModel;
import shapes.Point;
import shapes.Shape;

public class CmdRemoveTests {
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
		model.addShape(shape);
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
		model.addShape(shape);
		cmdRemove.execute();
		cmdRemove.unexecute();
		assertTrue(model.getShapes().contains(shape));
	}
}