package test.commandsTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import commands.CmdSendToBack;
import mvc.DrawingModel;
import shapes.Line;
import shapes.Point;
import shapes.Shape;

public class CmdSendToBackTests {
	private CmdSendToBack cmdSendToBack;
	private DrawingModel model;
	private Shape shape;
	private int indexOfShape;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point();
		model.addShape(new Line());
		model.addShape(shape);
		indexOfShape = model.getIndexOfShape(shape);
		cmdSendToBack = new CmdSendToBack(model, shape);
	}

	@Test
	public void testExecuteShapeAddedToModelShapes() {
		cmdSendToBack.execute();
		assertEquals(0, model.getIndexOfShape(shape));
	}

	@Test
	public void testUnexecuteShapeAddedToModelShapesAtIndexExecuteNotCalled() {
		cmdSendToBack.unexecute();
		assertNotEquals(indexOfShape, model.getIndexOfShape(shape));
	}

	@Test
	public void testUnexecuteShapeAddedToModelShapesAtIndex() {
		cmdSendToBack.execute();
		cmdSendToBack.unexecute();
		assertEquals(indexOfShape, model.getIndexOfShape(shape));
	}
}