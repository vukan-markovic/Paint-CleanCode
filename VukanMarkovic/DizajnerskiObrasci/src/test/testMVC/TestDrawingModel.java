package test.testMVC;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class TestDrawingModel {
	private DrawingModel drawingModel;
	private Point firstPoint;
	private Point secondPoint;

	@Before
	public void setUp() {
		drawingModel = new DrawingModel();
		firstPoint = new Point(1, 2);
	}

	@Test
	public void testGetOneSelectedShape() {
		secondPoint = new Point(1, 2, true, Color.WHITE);
		drawingModel.setShapes(new ArrayList<Shape>(Arrays.asList(firstPoint, secondPoint)));
		assertEquals(secondPoint, drawingModel.getOneSelectedShape());
	}

	@Test
	public void testGetOneSelectedShapeShapeIsNull() {
		drawingModel.setShapes(new ArrayList<Shape>(Arrays.asList(firstPoint, secondPoint)));
		assertNull(drawingModel.getOneSelectedShape());
	}
}