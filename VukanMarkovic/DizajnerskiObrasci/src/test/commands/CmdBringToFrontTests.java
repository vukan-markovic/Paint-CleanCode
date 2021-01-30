package commands;

import static org.junit.Assert.*;
import org.junit.*;
import shapes.*;
import model.DrawingModel;

public class CmdBringToFrontTests {
	private DrawingModel model;
	private Point point;
	private int indexOfShape;
	private CmdBringToFront cmdBringToFront;

	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(1, 2);
		model.addShape(point);
		indexOfShape = model.getIndexOfShape(point);
		cmdBringToFront = new CmdBringToFront(model, point);
	}

	@Test
	public void testExecute() {
		cmdBringToFront.execute();
		assertTrue(model.getShapes().contains(point));
	}

	@Test
	public void testUnexecuteExecuteNotCalled() {
		cmdBringToFront.unexecute();
		assertEquals(indexOfShape, model.getIndexOfShape(point));
	}

	@Test
	public void testUnexecuteExecuteCalled() {
		cmdBringToFront.execute();
		cmdBringToFront.unexecute();
		assertEquals(indexOfShape, model.getIndexOfShape(point));
	}
}