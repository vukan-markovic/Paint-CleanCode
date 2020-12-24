package test.testCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import command.CmdBringToFront;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class TestCmdBringToFront {
	private CmdBringToFront cmdBringToFront;
	private DrawingModel model;
	private Shape shape;
	private int index;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point();
		model.add(shape);
		index = model.indexOfShape(shape);
		cmdBringToFront = new CmdBringToFront(model, shape);
	}

	@Test
	public void testExecuteShapeAddedToModelShapes() {
		cmdBringToFront.execute();
		assertTrue(model.getShapes().contains(shape));
	}

	@Test
	public void testUnexecuteShapeAddedToModelShapesAtIndexExecuteNotCalled() {
		cmdBringToFront.unexecute();
		assertEquals(index, model.indexOfShape(shape));
	}

	@Test
	public void testUnexecuteShapeAddedToModelShapesAtIndex() {
		cmdBringToFront.execute();
		cmdBringToFront.unexecute();
		assertEquals(index, model.indexOfShape(shape));
	}
}