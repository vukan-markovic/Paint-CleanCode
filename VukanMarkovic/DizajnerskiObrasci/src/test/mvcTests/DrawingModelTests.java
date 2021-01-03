package test.mvcTests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import mvc.DrawingModel;
import shapes.Point;
import shapes.Shape;

public class DrawingModelTests {
	private DrawingModel model;
	private Point firstPoint;
	private Point secondPoint;

	@Before
	public void setUp() {
		model = new DrawingModel();
		firstPoint = new Point(1, 2);
	}

	@Test
	public void testGetOneSelectedShape() {
		secondPoint = new Point(1, 2, true, Color.WHITE);
		model.setShapes(new ArrayList<Shape>(Arrays.asList(firstPoint, secondPoint)));
		assertEquals(secondPoint, model.getFirstSelectedShape());
	}

	@Test
	public void testGetOneSelectedShapeShapeIsNull() {
		model.setShapes(new ArrayList<Shape>(Arrays.asList(firstPoint, secondPoint)));
		assertNull(model.getFirstSelectedShape());
	}
}