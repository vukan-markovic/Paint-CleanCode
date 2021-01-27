package commands;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.*;
import shapes.*;
import model.DrawingModel;

public class CmdSelectTests {
	private DrawingModel model;
	private Shape shape;
	private CmdSelect cmdSelect;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point(1, 2, false, Color.BLACK);
		model.addShape(shape);
		cmdSelect = new CmdSelect(model, shape);
	}

	@Test
	public void testExecuteShapeIsNotEqual() {
		Line line = new Line(new Point(1, 1, false, Color.BLACK), new Point(2, 2, false, Color.BLACK), false, Color.BLACK);
		model.addShape(line);
		cmdSelect = new CmdSelect(model, shape);
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