package commands;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import model.DrawingModel;

public class CmdDeselectTests {
	private DrawingModel model;
	private Point point;
	private CmdDeselect cmdDeselect;

	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(1, 2);
		model.addShape(point);
		cmdDeselect = new CmdDeselect(model, point);
	}

	@Test
	public void testExecuteShapeUnselected() {
		cmdDeselect.execute();
		assertFalse(point.isSelected());
	}

	@Test
	public void testExecuteShapeRemovedFromSelectedShapes() {
		cmdDeselect.execute();
		assertFalse(model.doesContainSelectedShape(point));
	}

	@Test
	public void testUnexecuteShapeSelected() {
		cmdDeselect.unexecute();
		assertTrue(point.isSelected());
	}

	@Test
	public void testUnexecuteShapeAddedToSelectedShapes() {
		cmdDeselect.unexecute();
		assertTrue(model.doesContainSelectedShape(point));
	}
}