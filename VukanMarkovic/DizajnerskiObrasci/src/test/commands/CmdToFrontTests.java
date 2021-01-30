package commands;

import org.junit.*;
import shapes.*;
import model.DrawingModel;
import static org.junit.Assert.assertEquals;

public class CmdToFrontTests {
	private DrawingModel model;
	private Point point;
	private Line line;
	private int indexOfShape;
	private CmdToFront cmdToFront;

	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(1, 2);
		line = new Line(new Point(1, 2), new Point(3, 4));
		model.addShape(point);
	}

	@Test
	public void testExecuteIndexIsZero() {
		indexOfShape = model.getIndexOfShape(point);
		cmdToFront = new CmdToFront(model, point);
		cmdToFront.execute();
		assertEquals(0, model.getIndexOfShape(point));
	}

	@Test
	public void testExecute() {
		model.addShape(line);
		indexOfShape = model.getIndexOfShape(point);
		cmdToFront = new CmdToFront(model, point);
		cmdToFront.execute();
		assertEquals(indexOfShape + 1, model.getIndexOfShape(point));
		assertEquals(indexOfShape, model.getIndexOfShape(line));
	}

	@Test
	public void testUnexecuteIndexIsZero() {
		indexOfShape = model.getIndexOfShape(point);
		cmdToFront = new CmdToFront(model, point);
		cmdToFront.unexecute();
		assertEquals(0, model.getIndexOfShape(point));
	}

	@Test
	public void testUnexecuteExecuteNotCalled() {
		model.addShape(line);
		indexOfShape = model.getIndexOfShape(point);
		cmdToFront = new CmdToFront(model, point);
		cmdToFront.unexecute();
		assertEquals(indexOfShape, model.getIndexOfShape(line));
		assertEquals(indexOfShape + 1, model.getIndexOfShape(point));
	}

	@Test
	public void testUnexecute() {
		model.addShape(line);
		indexOfShape = model.getIndexOfShape(point);
		cmdToFront = new CmdToFront(model, point);
		cmdToFront.execute();
		cmdToFront.unexecute();
		assertEquals(indexOfShape, model.getIndexOfShape(point));
		assertEquals(indexOfShape + 1, model.getIndexOfShape(line));
	}
}