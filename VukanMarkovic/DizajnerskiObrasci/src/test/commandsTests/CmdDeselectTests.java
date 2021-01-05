package test.commandsTests;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import commands.CmdDeselect;
import mvc.DrawingModel;

public class CmdDeselectTests {
	private DrawingModel model;
	private Shape shape;
	private CmdDeselect cmdDeselect;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point(1, 2);
		model.addShape(shape);
		model.selectShape(shape);
		cmdDeselect = new CmdDeselect(model, shape);
	}

	@Test
	public void testExecuteShapeIsNotEqual() {
		Line line = new Line(new Point(1, 1), new Point(2, 2));
		model.addShape(line);
		model.selectShape(line);
		cmdDeselect = new CmdDeselect(model, shape);
		cmdDeselect.execute();
		assertTrue(model.getShapeByIndex(model.getIndexOfShape(line)).isSelected());
		assertTrue(model.getSelectedShapes().contains(line));
	}

	@Test
	public void testExecuteShapeUnselected() {
		cmdDeselect.execute();
		assertFalse(model.getShapeByIndex(model.getIndexOfShape(shape)).isSelected());
	}

	@Test
	public void testExecuteShapeRemovedFromSelectedShapes() {
		cmdDeselect.execute();
		assertFalse(model.getSelectedShapes().contains(shape));
	}

	@Test
	public void testUnexecuteShapeIsNotEqual() {
		Line line = new Line(new Point(1, 1), new Point(2, 2));
		model.addShape(line);
		cmdDeselect = new CmdDeselect(model, shape);
		cmdDeselect.unexecute();
		assertFalse(model.getShapeByIndex(model.getIndexOfShape(line)).isSelected());
		assertFalse(model.getSelectedShapes().contains(line));
	}

	@Test
	public void testUnexecuteShapeSelected() {
		cmdDeselect.unexecute();
		assertTrue(model.getShapeByIndex(model.getIndexOfShape(shape)).isSelected());
	}

	@Test
	public void testUnexecuteShapeAddedToSelectedShapes() {
		cmdDeselect.unexecute();
		assertTrue(model.getSelectedShapes().contains(shape));
	}
}