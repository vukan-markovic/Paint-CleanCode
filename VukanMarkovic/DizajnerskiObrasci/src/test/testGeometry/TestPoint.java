package test.testGeometry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import geometry.Line;
import geometry.Point;

public class TestPoint {
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
		verify(graphics).setColor(point.getBorderColor());
		verify(graphics).drawLine(point.getX() - 2, point.getY(), point.getX() + 2, point.getY());
		verify(graphics).drawLine(point.getX(), point.getY() - 2, point.getX(), point.getY() + 2);
		verify(graphics).setColor(Color.BLUE);
	}

	@Test
	public void testDrawShapeSelected() {
		point.setSelected(true);
		point.draw(graphics);
		verify(graphics).setColor(point.getBorderColor());
		verify(graphics).drawLine(point.getX() - 2, point.getY(), point.getX() + 2, point.getY());
		verify(graphics).drawLine(point.getX(), point.getY() - 2, point.getX(), point.getY() + 2);
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(point.getX() - 3, point.getY() - 3, 6, 6);
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