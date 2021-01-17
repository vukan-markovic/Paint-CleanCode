package commandsTests;

import org.junit.*;
import shapes.*;
import static org.junit.Assert.assertEquals;

import java.awt.Color;

import commands.CmdToBack;
import model.DrawingModel;

public class CmdToBackTests {
	private DrawingModel model;
	private Shape point;
	private Shape line;
	private int indexOfShape;
	private CmdToBack cmdToBack;

	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(1, 2, false, Color.BLACK);
		line = new Line(new Point(1, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK), false, Color.BLACK);
	}

	@Test
	public void testExecuteIndexIsZero() {
		model.addShape(point);
		indexOfShape = model.getIndexOfShape(point);
		cmdToBack = new CmdToBack(model, point);
		cmdToBack.execute();
		assertEquals(0, model.getIndexOfShape(point));
	}

	@Test
	public void testExecute() {
		model.addShape(line);
		model.addShape(point);
		indexOfShape = model.getIndexOfShape(point);
		cmdToBack = new CmdToBack(model, point);
		cmdToBack.execute();
		assertEquals(indexOfShape - 1, model.getIndexOfShape(point));
		assertEquals(indexOfShape, model.getIndexOfShape(line));
	}

	public void testUnexecuteIndexIsZero() {
		model.addShape(point);
		indexOfShape = model.getIndexOfShape(point);
		cmdToBack = new CmdToBack(model, point);
		cmdToBack.unexecute();
		assertEquals(indexOfShape, model.getIndexOfShape(point));
	}

	@Test
	public void testUnexecuteExecuteNotCalled() {
		model.addShape(line);
		model.addShape(point);
		indexOfShape = model.getIndexOfShape(point);
		cmdToBack = new CmdToBack(model, point);
		cmdToBack.unexecute();
		assertEquals(indexOfShape - 1, model.getIndexOfShape(point));
		assertEquals(indexOfShape, model.getIndexOfShape(line));
	}

	@Test
	public void testUnexecute() {
		model.addShape(line);
		model.addShape(point);
		indexOfShape = model.getIndexOfShape(point);
		cmdToBack = new CmdToBack(model, point);
		cmdToBack.execute();
		cmdToBack.unexecute();
		assertEquals(indexOfShape, model.getIndexOfShape(point));
		assertEquals(indexOfShape - 1, model.getIndexOfShape(line));
	}
}