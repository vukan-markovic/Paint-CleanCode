package test.testCommand;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import command.CmdAdd;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class TestCmdAdd {
	private CmdAdd cmdAdd;
	private DrawingModel model;
	private Shape shape;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point();
		cmdAdd = new CmdAdd(model, shape);
	}

	@Test
	public void testExecuteShapeUnselected() {
		cmdAdd.execute();
		assertFalse(shape.isSelected());
	}

	@Test
	public void testExecuteShapeAddedToModelShapes() {
		cmdAdd.execute();
		assertTrue(model.getShapes().contains(shape));
	}

	@Test
	public void testUnexecuteShapeRemovedFromModelShapesExecuteNotCalled() {
		cmdAdd.unexecute();
		assertFalse(model.getShapes().contains(shape));
	}
	
	@Test
	public void testUnexecuteShapeRemovedFromModelShapes() {
		cmdAdd.execute();
		cmdAdd.unexecute();
		assertFalse(model.getShapes().contains(shape));
	}
}