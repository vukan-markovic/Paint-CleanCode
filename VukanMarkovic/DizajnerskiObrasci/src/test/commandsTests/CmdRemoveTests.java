package test.commandsTests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.*;
import shapes.*;
import commands.CmdRemove;
import mvc.DrawingModel;

public class CmdRemoveTests {
	private DrawingModel model;
	private Shape shape;
	private CmdRemove cmdRemove;

	@Before
	public void setUp() {
		model = new DrawingModel();
		shape = new Point(1, 2, false, Color.BLACK);
		cmdRemove = new CmdRemove(model, shape);
	}

	@Test
	public void testExecuteShapeRemovedListEmpty() {
		cmdRemove.execute();
		assertFalse(model.getShapes().contains(shape));
	}

	@Test
	public void testExecuteShapeRemoved() {
		model.addShape(shape);
		cmdRemove.execute();
		assertFalse(model.getShapes().contains(shape));
	}

	@Test
	public void testUnexecuteShapeAddedExecuteNotCalled() {
		cmdRemove.unexecute();
		assertTrue(model.getShapes().contains(shape));
	}

	@Test
	public void testUnexecuteShapeAdded() {
		model.addShape(shape);
		cmdRemove.execute();
		cmdRemove.unexecute();
		assertTrue(model.getShapes().contains(shape));
	}
}