package model;

import static org.junit.Assert.*;
import java.awt.Color;
import java.util.*;
import org.junit.*;
import shapes.*;

public class DrawingModelTests {
	private DrawingModel model;
	private Point firstPoint;
	private Point secondPoint;

	@Before
	public void setUp() {
		model = new DrawingModel();
		firstPoint = new Point(1, 2, false, Color.BLACK);
		secondPoint = new Point(1, 2, true, Color.WHITE);
	}

	@Test
	public void testGetOneSelectedShape() {
		model.setShapes(new ArrayList<Shape>(Arrays.asList(firstPoint, secondPoint)));
		assertEquals(secondPoint, model.getFirstSelectedShape());
	}

	@Test
	public void testGetOneSelectedShapeShapeIsNull() {
		model.setShapes(new ArrayList<Shape>(Arrays.asList(firstPoint, secondPoint)));
		assertNull(model.getFirstSelectedShape());
	}
}