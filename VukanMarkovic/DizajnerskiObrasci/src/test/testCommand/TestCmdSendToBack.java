package test.testCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import command.CmdSendToBack;
import geometry.Line;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class TestCmdSendToBack {
	private CmdSendToBack cmdSendToBack;
	private DrawingModel model;
	private Shape shape;
	private int index;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point();
		model.add(new Line());
		model.add(shape);
		index = model.indexOfShape(shape);
		cmdSendToBack = new CmdSendToBack(model, shape);
	}

	@Test
	public void testExecuteShapeAddedToModelShapes() {
		cmdSendToBack.execute();
		assertEquals(0, model.indexOfShape(shape));
	}

	@Test
	public void testUnexecuteShapeAddedToModelShapesAtIndexExecuteNotCalled() {
		cmdSendToBack.unexecute();
		assertNotEquals(index, model.indexOfShape(shape));
	}

	@Test
	public void testUnexecuteShapeAddedToModelShapesAtIndex() {
		cmdSendToBack.execute();
		cmdSendToBack.unexecute();
		assertEquals(index, model.indexOfShape(shape));
	}
}