package test.commandsTests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.*;
import shapes.*;
import commands.CmdAdd;
import mvc.DrawingModel;

public class CmdAddTests {
	private DrawingModel model;
	private Shape shape;
	private CmdAdd cmdAdd;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point(1, 2, false, Color.BLACK);
		cmdAdd = new CmdAdd(model, shape);
	}

	@Test
	public void testExecuteShapeUnselected() {
		cmdAdd.execute();
		assertFalse(shape.isSelected());
	}

	@Test
	public void testExecuteShapeAddedToModelShapes() {
		cmdAdd.execute();
		assertTrue(model.getShapes().contains(shape));
	}

	@Test
	public void testUnexecuteShapeRemovedFromModelShapesExecuteNotCalled() {
		cmdAdd.unexecute();
		assertFalse(model.getShapes().contains(shape));
	}

	@Test
	public void testUnexecuteShapeRemovedFromModelShapes() {
		cmdAdd.execute();
		cmdAdd.unexecute();
		assertFalse(model.getShapes().contains(shape));
	}
}