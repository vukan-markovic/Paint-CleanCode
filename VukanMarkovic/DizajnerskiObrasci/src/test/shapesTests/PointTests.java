package test.shapesTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import shapes.Line;
import shapes.Point;

public class PointTests {
	private Point point;
	private Graphics graphics;

	@Before
	public void setUp() {
		point = new Point(1, 2);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDrawShapeNotSelected() {
		point.draw(graphics);
		verify(graphics).setColor(point.getOuterColor());

		verify(graphics).drawLine(point.getXcoordinate() - 2, point.getYcoordinate(), point.getXcoordinate() + 2,
				point.getYcoordinate());

		verify(graphics).drawLine(point.getXcoordinate(), point.getYcoordinate() - 2, point.getXcoordinate(),
				point.getYcoordinate() + 2);
	}

	@Test
	public void testDrawShapeSelected() {
		point.setSelected(true);
		point.draw(graphics);
		verify(graphics).setColor(point.getOuterColor());

		verify(graphics).drawLine(point.getXcoordinate() - 2, point.getYcoordinate(), point.getXcoordinate() + 2,
				point.getYcoordinate());

		verify(graphics).drawLine(point.getXcoordinate(), point.getYcoordinate() - 2, point.getXcoordinate(),
				point.getYcoordinate() + 2);

		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(point.getXcoordinate() - 3, point.getYcoordinate() - 3, 6, 6);
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(point.contains(1, 1));
	}

	@Test
	public void testContainsFalseExcepted() {
		assertFalse(point.contains(21, 61));
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(point.equals(new Line()));
	}

	@Test
	public void testEqualsFalseExpectedXcoordinate() {
		assertFalse(point.equals(new Point(0, 2)));
	}

	@Test
	public void testEqualsFalseExpectedYcoordinate() {
		assertFalse(point.equals((new Point(1, 3))));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(point.equals(new Point(1, 2)));
	}
}