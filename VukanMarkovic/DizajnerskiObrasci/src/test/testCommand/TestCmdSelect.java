package test.testCommand;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import command.CmdSelect;
import geometry.Line;
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
	public void testExecuteShapeIsNotEqual() {
		Line line = new Line(new Point(1, 1), new Point(2, 2));
		model.add(line);
		cmdSelect = new CmdSelect(shape, model);
		cmdSelect.execute();
		assertFalse(model.get(model.indexOfShape(line)).isSelected());
		assertFalse(model.getSelectedShapes().contains(line));
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
	public void testUnexecuteShapeIsNotEqual() {
		Line line = new Line(new Point(1, 1), new Point(2, 2));
		model.add(line);
		model.selectShape(line);
		cmdSelect = new CmdSelect(shape, model);
		cmdSelect.unexecute();
		assertTrue(model.get(model.indexOfShape(line)).isSelected());
		assertTrue(model.getSelectedShapes().contains(line));
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