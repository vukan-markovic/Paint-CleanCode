package test.commandsTests;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import commands.CmdBringToFront;
import mvc.DrawingModel;

public class CmdBringToFrontTests {
	private DrawingModel model;
	private Shape shape;
	private int indexOfShape;
	private CmdBringToFront cmdBringToFront;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point(1, 2);
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