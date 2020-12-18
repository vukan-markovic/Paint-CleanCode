package test.testCommand;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import command.CmdToFront;
import geometry.Line;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class TestCmdToFront {
	private DrawingModel model;
	private Shape point;
	private Shape line;
	private int index;
	private CmdToFront cmdToFront;

	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point();
		line = new Line(new Point(), new Point());
		model.add(point);
	}

	@Test
	public void testExecuteIndexIsZero() {
		index = model.indexOfShape(point);
		cmdToFront = new CmdToFront(model, point);
		cmdToFront.execute();
		assertEquals(0, model.indexOfShape(point));
	}

	@Test
	public void testExecute() {
		model.add(line);
		index = model.indexOfShape(point);
		cmdToFront = new CmdToFront(model, point);
		cmdToFront.execute();
		assertEquals(index + 1, model.indexOfShape(point));
		assertEquals(index, model.indexOfShape(line));
	}

	@Test
	public void testUnexecuteIndexIsZero() {
		index = model.indexOfShape(point);
		cmdToFront = new CmdToFront(model, point);
		cmdToFront.unexecute();
		assertEquals(0, model.indexOfShape(point));
	}

	@Test
	public void testUnexecuteExecuteNotCalled() {
		model.add(line);
		index = model.indexOfShape(point);
		cmdToFront = new CmdToFront(model, point);
		cmdToFront.unexecute();
		assertEquals(index, model.indexOfShape(line));
		assertEquals(index + 1, model.indexOfShape(point));
	}

	@Test
	public void testUnexecute() {
		model.add(line);
		index = model.indexOfShape(point);
		cmdToFront = new CmdToFront(model, point);
		cmdToFront.execute();
		cmdToFront.unexecute();
		assertEquals(index, model.indexOfShape(point));
		assertEquals(index + 1, model.indexOfShape(line));
	}
}