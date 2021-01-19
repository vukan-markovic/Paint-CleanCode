package commands;

import shapes.*;
import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.*;
import model.DrawingModel;

public class CmdBringToBackTests {
	private DrawingModel model;
	private Shape shape;
	private int indexOfShape;
	private CmdBringToBack cmdBringToBack;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point(1, 2, false, Color.BLACK);
		model.addShape(new Line(new Point(1, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK), false, Color.BLACK));
		model.addShape(shape);
		indexOfShape = model.getIndexOfShape(shape);
		cmdBringToBack = new CmdBringToBack(model, shape);
	}

	@Test
	public void testExecuteShapeAddedToModelShapes() {
		cmdBringToBack.execute();
		assertEquals(0, model.getIndexOfShape(shape));
	}

	@Test
	public void testUnexecuteShapeAddedToModelShapesAtIndexExecuteNotCalled() {
		cmdBringToBack.unexecute();
		assertNotEquals(indexOfShape, model.getIndexOfShape(shape));
	}

	@Test
	public void testUnexecuteShapeAddedToModelShapesAtIndex() {
		cmdBringToBack.execute();
		cmdBringToBack.unexecute();
		assertEquals(indexOfShape, model.getIndexOfShape(shape));
	}
}