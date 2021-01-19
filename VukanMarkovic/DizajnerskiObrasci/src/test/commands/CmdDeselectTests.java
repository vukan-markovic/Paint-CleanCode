package commands;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.*;
import shapes.*;
import model.DrawingModel;

public class CmdDeselectTests {
	private DrawingModel model;
	private Shape shape;
	private CmdDeselect cmdDeselect;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point(1, 2, false, Color.BLACK);
		model.addShape(shape);
		new CmdSelect(model, shape).execute();
		cmdDeselect = new CmdDeselect(model, shape);
	}

	@Test
	public void testExecuteShapeIsNotEqual() {
		Line line = new Line(new Point(1, 1, false, Color.BLACK), new Point(2, 2, false, Color.BLACK), false,
				Color.BLACK);
		model.addShape(line);
		new CmdSelect(model, line).execute();
		new CmdDeselect(model, shape).execute();
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
		Line line = new Line(new Point(1, 1, false, Color.BLACK), new Point(2, 2, false, Color.BLACK), false,
				Color.BLACK);
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