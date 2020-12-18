package test.testCommand;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import command.CmdToBack;
import geometry.Line;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class TestCmdToBack {
	private DrawingModel model;
	private Shape point;
	private Shape line;
	private int index;
	private CmdToBack cmdToBack;

	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point();
		line = new Line(new Point(), new Point());
	}

	@Test
	public void testExecuteIndexIsZero() {
		model.add(point);
		index = model.indexOfShape(point);
		cmdToBack = new CmdToBack(model, point);
		cmdToBack.execute();
		assertEquals(0, model.indexOfShape(point));
	}

	@Test
	public void testExecute() {
		model.add(line);
		model.add(point);
		index = model.indexOfShape(point);
		cmdToBack = new CmdToBack(model, point);
		cmdToBack.execute();
		assertEquals(index - 1, model.indexOfShape(point));
		assertEquals(index, model.indexOfShape(line));
	}

	@Test
	public void testUnexecuteIndexIsZero() {
		model.add(point);
		index = model.indexOfShape(point);
		cmdToBack = new CmdToBack(model, point);
		cmdToBack.unexecute();
		assertEquals(0, model.indexOfShape(point));
	}

	@Test
	public void testUnexecuteExecuteNotCalled() {
		model.add(line);
		model.add(point);
		index = model.indexOfShape(point);
		cmdToBack = new CmdToBack(model, point);
		cmdToBack.unexecute();
		assertEquals(index, model.indexOfShape(point));
		assertEquals(index - 1, model.indexOfShape(line));
	}

	@Test
	public void testUnexecute() {
		model.add(line);
		model.add(point);
		index = model.indexOfShape(point);
		cmdToBack = new CmdToBack(model, point);
		cmdToBack.execute();
		cmdToBack.unexecute();
		assertEquals(index, model.indexOfShape(point));
		assertEquals(index - 1, model.indexOfShape(line));
	}
}