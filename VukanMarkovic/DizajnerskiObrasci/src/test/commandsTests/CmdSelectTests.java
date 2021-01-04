package test.commandsTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import commands.CmdSelect;
import mvc.DrawingModel;
import shapes.Line;
import shapes.Point;
import shapes.Shape;

public class CmdSelectTests {
	private DrawingModel model;
	private Shape shape;
	private CmdSelect cmdSelect;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point();
		model.addShape(shape);
		cmdSelect = new CmdSelect(shape, model);
	}

	@Test
	public void testExecuteShapeIsNotEqual() {
		Line line = new Line(new Point(1, 1), new Point(2, 2));
		model.addShape(line);
		cmdSelect = new CmdSelect(shape, model);
		cmdSelect.execute();
		assertFalse(model.getShapeByIndex(model.getIndexOfShape(line)).isSelected());
		assertFalse(model.getSelectedShapes().contains(line));
	}

	@Test
	public void testExecuteShapeUnselected() {
		cmdSelect.execute();
		assertTrue(model.getShapeByIndex(model.getIndexOfShape(shape)).isSelected());
	}

	@Test
	public void testExecuteShapeRemovedFromSelectedShapes() {
		cmdSelect.execute();
		assertTrue(model.getSelectedShapes().contains(shape));
	}

	@Test
	public void testUnexecuteShapeIsNotEqual() {
		Line line = new Line(new Point(1, 1), new Point(2, 2));
		model.addShape(line);
		model.selectShape(line);
		cmdSelect = new CmdSelect(shape, model);
		cmdSelect.unexecute();
		assertTrue(model.getShapeByIndex(model.getIndexOfShape(line)).isSelected());
		assertTrue(model.getSelectedShapes().contains(line));
	}

	@Test
	public void testUnexecuteShapeSelected() {
		cmdSelect.unexecute();
		assertFalse(model.getShapeByIndex(model.getIndexOfShape(shape)).isSelected());
	}

	@Test
	public void testUnexecuteShapeAddedToSelectedShapes() {
		cmdSelect.unexecute();
		assertFalse(model.getSelectedShapes().contains(shape));
	}
}