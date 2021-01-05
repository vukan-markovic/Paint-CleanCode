package test.shapesTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import shapes.*;
import java.awt.Color;
import java.awt.Graphics;

public class CircleTests {
	private int radius;
	private Point center;
	private Circle circle;
	private Circle circleMock;
	private Graphics graphics;

	@Before
	public void setUp() {
		radius = 3;
		center = new Point(1, 2);
		circle = new Circle(center, radius);
		circleMock = spy(Circle.class);
		circleMock.setCenter(center);
		circleMock.setRadius(radius);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDrawShapeNotSelected() {
		circleMock.draw(graphics);
		verify(graphics).setColor(circleMock.getOuterColor());

		verify(graphics).drawOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, radius * 2,
				radius * 2);

		verify(circleMock).fillShape(graphics);
	}

	@Test
	public void testDrawShapeSelected() {
		circleMock.setSelected(true);
		circleMock.draw(graphics);
		verify(graphics).setColor(circleMock.getOuterColor());

		verify(graphics).drawOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, radius * 2,
				radius * 2);

		verify(circleMock).fillShape(graphics);
		verify(circleMock).drawSelection(graphics);
	}

	@Test
	public void testDrawSelection() {
		circle.drawSelection(graphics);
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(center.getXcoordinate() - 3, center.getYcoordinate() - 3, 6, 6);
		verify(graphics).drawRect(center.getXcoordinate() + circle.getRadius() - 3, center.getYcoordinate() - 3, 6, 6);
		verify(graphics).drawRect(center.getXcoordinate() - circle.getRadius() - 3, center.getYcoordinate() - 3, 6, 6);
		verify(graphics).drawRect(center.getXcoordinate() - 3, center.getYcoordinate() + radius - 3, 6, 6);
		verify(graphics).drawRect(center.getXcoordinate() - 3, center.getYcoordinate() - radius - 3, 6, 6);
	}

	@Test
	public void testFillShape() {
		circle.fillShape(graphics);
		verify(graphics).setColor(circle.getInnerColor());
		verify(graphics).fillOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, radius * 2,
				radius * 2);
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
	public void testToString() {
		assertEquals("Center: " + center + ", radius: " + radius + " , Inner color: " + circle.getInnerColor().getRGB()
				+ " , Outer color: " + circle.getOuterColor().getRGB(), circle.toString());
	}
}