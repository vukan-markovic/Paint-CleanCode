package test.commandsTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import commands.CmdBringToFront;
import mvc.DrawingModel;
import shapes.Point;
import shapes.Shape;

public class CmdBringToFrontTests {
	private DrawingModel model;
	private Shape shape;
	private int indexOfShape;
	private CmdBringToFront cmdBringToFront;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point();
		model.addShape(shape);
		indexOfShape = model.getIndexOfShape(shape);
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
		assertEquals(indexOfShape, model.getIndexOfShape(shape));
	}

	@Test
	public void testUnexecuteShapeAddedToModelShapesAtIndex() {
		cmdBringToFront.execute();
		cmdBringToFront.unexecute();
		assertEquals(indexOfShape, model.getIndexOfShape(shape));
	}
}