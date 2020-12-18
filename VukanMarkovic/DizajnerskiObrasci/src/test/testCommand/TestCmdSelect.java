package test.testCommand;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import command.CmdSelect;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class TestCmdSelect {
	private Shape shape;
	private DrawingModel model;
	private CmdSelect cmdSelect;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point();
		model.add(shape);
		cmdSelect = new CmdSelect(shape, model);
	}

	@Test
	public void testExecuteShapeUnselected() {
		cmdSelect.execute();
		assertTrue(model.get(model.indexOfShape(shape)).isSelected());
	}

	@Test
	public void testExecuteShapeRemovedFromSelectedShapes() {
		cmdSelect.execute();
		assertTrue(model.getSelectedShapes().contains(shape));
	}

	@Test
	public void testUnexecuteShapeSelected() {
		cmdSelect.unexecute();
		assertFalse(model.get(model.indexOfShape(shape)).isSelected());
	}

	@Test
	public void testUnexecuteShapeAddedToSelectedShapes() {
		cmdSelect.unexecute();
		assertFalse(model.getSelectedShapes().contains(shape));
	}
}