package commands;

import shapes.*;
import static org.junit.Assert.*;
import org.junit.*;
import model.DrawingModel;

public class CmdBringToBackTests {
	private DrawingModel model;
	private Point point;
	private int indexOfShape;
	private CmdBringToBack cmdBringToBack;

	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(1, 2);
		model.addShape(new Line(new Point(1, 2), new Point(3, 4)));
		model.addShape(point);
		indexOfShape = model.getIndexOfShape(point);
		cmdBringToBack = new CmdBringToBack(model, point);
	}

	@Test
	public void testExecute() {
		cmdBringToBack.execute();
		assertEquals(0, model.getIndexOfShape(point));
	}

	@Test
	public void testUnexecuteExecuteNotCalled() {
		cmdBringToBack.unexecute();
		assertNotEquals(indexOfShape, model.getIndexOfShape(point));
	}

	@Test
	public void testUnexecuteExecuteCalled() {
		cmdBringToBack.execute();
		cmdBringToBack.unexecute();
		assertEquals(indexOfShape, model.getIndexOfShape(point));
	}
}