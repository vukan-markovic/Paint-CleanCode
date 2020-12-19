package test.testCommand;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import command.CmdDeselect;
import geometry.Line;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class TestCmdDeselect {
	private Shape shape;
	private DrawingModel model;
	private CmdDeselect cmdDeselect;
	
	@Before 
	public void setUp() {
		model = new DrawingModel();
		shape = new Point();
		model.add(shape);
		model.selectShape(shape);
		cmdDeselect = new CmdDeselect(shape, model);
	}
	
	@Test 
	public void testExecuteShapeIsNotEqual() {
		Line line = new Line(new Point(1, 1), new Point(2, 2));
		model.add(line);
		model.selectShape(line);
		cmdDeselect = new CmdDeselect(shape, model);
		cmdDeselect.execute();
		assertTrue(model.get(model.indexOfShape(line)).isSelected());
		assertTrue(model.getSelectedShapes().contains(line));
	}

	@Test 
	public void testExecuteShapeUnselected() {
		cmdDeselect.execute();
		assertFalse(model.get(model.indexOfShape(shape)).isSelected());
	}
	
	@Test 
	public void testExecuteShapeRemovedFromSelectedShapes() {
		cmdDeselect.execute();
		assertFalse(model.getSelectedShapes().contains(shape));
	}
	
	@Test 
	public void testUnexecuteShapeIsNotEqual() {
		Line line = new Line(new Point(1, 1), new Point(2, 2));
		model.add(line);
		cmdDeselect = new CmdDeselect(shape, model);
		cmdDeselect.unexecute();
		assertFalse(model.get(model.indexOfShape(line)).isSelected());
		assertFalse(model.getSelectedShapes().contains(line));
	}
	
	@Test 
	public void testUnexecuteShapeSelected() {
		cmdDeselect.unexecute();
		assertTrue(model.get(model.indexOfShape(shape)).isSelected());
	}
	
	@Test 
	public void testUnexecuteShapeAddedToSelectedShapes() {
		cmdDeselect.unexecute();
		assertTrue(model.getSelectedShapes().contains(shape));
	}
}