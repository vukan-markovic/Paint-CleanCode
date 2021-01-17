package shapesTests;

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
	private Graphics graphics;

	@Before
	public void setUp() {
		radius = 3;
		center = new Point(1, 2, false, Color.BLACK);
		circle = new Circle(center, radius, false, Color.BLACK, Color.WHITE);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDrawShapeNotSelected() {
		circle.draw(graphics);
		verify(graphics).setColor(circle.getOuterColor());

		verify(graphics).drawOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, radius * 2,
				radius * 2);

		verify(graphics).setColor(circle.getInnerColor());
		verify(graphics).fillOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, radius * 2,
				radius * 2);
	}

	@Test
	public void testDrawShapeSelected() {
		circle.setSelected(true);
		circle.draw(graphics);
		verify(graphics).setColor(circle.getOuterColor());

		verify(graphics).drawOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, radius * 2,
				radius * 2);

		verify(graphics).setColor(circle.getInnerColor());
		verify(graphics).fillOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, radius * 2,
				radius * 2);

		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(center.getXcoordinate() - 3, center.getYcoordinate() - 3, 6, 6);
		verify(graphics).drawRect(center.getXcoordinate() + circle.getRadius() - 3, center.getYcoordinate() - 3, 6, 6);
		verify(graphics).drawRect(center.getXcoordinate() - circle.getRadius() - 3, center.getYcoordinate() - 3, 6, 6);
		verify(graphics).drawRect(center.getXcoordinate() - 3, center.getYcoordinate() + radius - 3, 6, 6);
		verify(graphics).drawRect(center.getXcoordinate() - 3, center.getYcoordinate() - radius - 3, 6, 6);
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
		assertFalse(circle.equals(new Point(1, 2, false, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpectedRadius() {
		assertFalse(circle.equals(new Circle(new Point(1, 2, false, Color.BLACK), 1, false, Color.BLACK, Color.WHITE)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(circle.equals(new Circle(new Point(1, 2, false, Color.BLACK), 3, false, Color.BLACK, Color.WHITE)));
	}

	@Test
	public void testEqualsFalseExpected() {
		assertFalse(circle.equals(new Circle(new Point(2, 3, false, Color.BLACK), 1, false, Color.BLACK, Color.WHITE)));
	}

	@Test
	public void testToString() {
		assertEquals("Center: " + center + ", radius: " + radius + " , Outer color: " + circle.getOuterColor().getRGB()
				+ " , Inner color: " + circle.getInnerColor().getRGB(), circle.toString());
	}
}