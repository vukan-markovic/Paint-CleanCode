package test.shapesTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import shapes.Line;
import shapes.Point;

public class PointTests {
	private Point point;
	private Point pointMock;
	private Graphics graphics;

	@Before
	public void setUp() {
		point = new Point(1, 2);
		pointMock = spy(Point.class);
		pointMock.setXcoordinate(1);
		pointMock.setYcoordinate(2);
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
		pointMock.setSelected(true);
		pointMock.draw(graphics);
		verify(graphics).setColor(pointMock.getOuterColor());

		verify(graphics).drawLine(pointMock.getXcoordinate() - 2, pointMock.getYcoordinate(),
				pointMock.getXcoordinate() + 2, pointMock.getYcoordinate());

		verify(graphics).drawLine(pointMock.getXcoordinate(), pointMock.getYcoordinate() - 2,
				pointMock.getXcoordinate(), pointMock.getYcoordinate() + 2);

		verify(pointMock).drawSelection(graphics);
	}

	@Test
	public void testDrawSelection() {
		point.drawSelection(graphics);
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