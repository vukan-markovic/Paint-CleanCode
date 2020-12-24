package test.testGeometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import geometry.Circle;
import geometry.Point;

public class TestCircle {
	private Point center;
	private int radius;
	private Circle circle;
	private Graphics graphics;

	@Before
	public void setUp() {
		radius = 3;
		center = new Point(1, 2);
		circle = new Circle(center, radius);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDrawShapeNotSelected() {
		circle.draw(graphics);
		verify(graphics).setColor(circle.getBorderColor());
		verify(graphics).drawOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
		verify(graphics).setColor(circle.getFillColor());
		verify(graphics).fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
		verify(graphics).setColor(Color.BLUE);
	}

	@Test
	public void testDrawShapeSelected() {
		circle.setSelected(true);
		circle.draw(graphics);
		verify(graphics).setColor(circle.getBorderColor());
		verify(graphics).drawOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
		verify(graphics).setColor(circle.getFillColor());
		verify(graphics).fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(center.getX() - 3, center.getY() - 3, 6, 6);
		verify(graphics).drawRect(center.getX() + circle.getRadius() - 3, center.getY() - 3, 6, 6);
		verify(graphics).drawRect(center.getX() - circle.getRadius() - 3, center.getY() - 3, 6, 6);
		verify(graphics).drawRect(center.getX() - 3, center.getY() + radius - 3, 6, 6);
		verify(graphics).drawRect(center.getX() - 3, center.getY() - radius - 3, 6, 6);
	}

	@Test
	public void testFillShape() {
		circle.fillShape(graphics);
		verify(graphics).setColor(circle.getFillColor());
		verify(graphics).fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(circle.contains(1, 1));
	}

	@Test
	public void testContainsFalseExcepted() {
		assertFalse(circle.contains(21, 61));
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(circle.equals(new Point(1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedRadius() {
		assertFalse(circle.equals(new Circle(new Point(1, 2), 1)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(circle.equals(new Circle(new Point(1, 2), 3)));
	}

	@Test
	public void testEqualsFalseExpected() {
		assertFalse(circle.equals(new Circle(new Point(2, 3), 1)));
	}

	@Test
	public void testArea() {
		assertEquals(radius * radius * Math.PI, circle.area(), 0);
	}

	@Test
	public void testToString() {
		assertEquals("Center: " + center + ", radius: " + radius + " , Border color: "
				+ circle.getBorderColor().getRGB() + " , Fill color: " + circle.getFillColor().getRGB(),
				circle.toString());
	}
}