package test.commandsTests;

import shapes.*;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.*;
import commands.CmdSendToBack;
import mvc.DrawingModel;

public class CmdSendToBackTests {
	private DrawingModel model;
	private Shape shape;
	private int indexOfShape;
	private CmdSendToBack cmdSendToBack;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point(1, 2, false, Color.BLACK);
		model.addShape(new Line(new Point(1, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK), false, Color.BLACK));
		model.addShape(shape);
		indexOfShape = model.getIndexOfShape(shape);
		cmdSendToBack = new CmdSendToBack(model, shape);
	}

	@Test
	public void testExecuteShapeAddedToModelShapes() {
		cmdSendToBack.execute();
		assertEquals(0, model.getIndexOfShape(shape));
	}

	@Test
	public void testUnexecuteShapeAddedToModelShapesAtIndexExecuteNotCalled() {
		cmdSendToBack.unexecute();
		assertNotEquals(indexOfShape, model.getIndexOfShape(shape));
	}

	@Test
	public void testUnexecuteShapeAddedToModelShapesAtIndex() {
		cmdSendToBack.execute();
		cmdSendToBack.unexecute();
		assertEquals(indexOfShape, model.getIndexOfShape(shape));
	}
}