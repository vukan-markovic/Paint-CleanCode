package commands;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.*;
import shapes.*;
import model.DrawingModel;

public class CmdBringToFrontTests {
	private DrawingModel model;
	private Shape shape;
	private int indexOfShape;
	private CmdBringToFront cmdBringToFront;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point(1, 2, false, Color.BLACK);
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