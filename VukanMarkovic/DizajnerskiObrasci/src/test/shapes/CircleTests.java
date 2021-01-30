package shapes;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import java.awt.Color;
import java.awt.Graphics;

public class CircleTests {
	private Graphics graphics;
	private int xCoordinate;
	private int yCoordinate;
	private int radius;
	private Point center;
	private Color borderColor;
	private Color fillColor;
	private Circle circle;

	@Before
	public void setUp() {
		graphics = mock(Graphics.class);
		xCoordinate = 1;
		yCoordinate = 2;
		radius = 3;
		borderColor = Color.BLACK;
		fillColor = Color.WHITE;
		center = new Point(xCoordinate, yCoordinate, false, borderColor);
		circle = new Circle(center, radius, false, borderColor, fillColor);
	}

	@Test
	public void testDrawShapeNotSelected() {
		circle.draw(graphics);
		verify(graphics).setColor(borderColor);
		verify(graphics).drawOval(xCoordinate - radius, yCoordinate - radius, radius * 2, radius * 2);
		verify(graphics).setColor(fillColor);
		verify(graphics).fillOval(xCoordinate - radius + 1, yCoordinate - radius + 1, radius * 2 -2, radius * 2 - 2);
	}

	@Test
	public void testDrawShapeSelected() {
		circle.setSelected(true);
		circle.draw(graphics);
		verify(graphics).setColor(borderColor);
		verify(graphics).drawOval(xCoordinate - radius, yCoordinate - radius, radius * 2, radius * 2);
		verify(graphics).setColor(fillColor);
		verify(graphics).fillOval(xCoordinate - radius + 1, yCoordinate - radius + 1, radius * 2 -2, radius * 2 - 2);
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(xCoordinate - 3, yCoordinate - 3, 6, 6);
		verify(graphics).drawRect(xCoordinate + radius - 3, yCoordinate - 3, 6, 6);
		verify(graphics).drawRect(xCoordinate - radius - 3, yCoordinate - 3, 6, 6);
		verify(graphics).drawRect(xCoordinate - 3, yCoordinate + radius - 3, 6, 6);
		verify(graphics).drawRect(xCoordinate - 3, yCoordinate - radius - 3, 6, 6);
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
		assertEquals("Center: " + center + ", radius: " + radius + " , Border color: "
				+ circle.getBorderColor().getRGB() + " , Fill color: " + circle.getFillColor().getRGB(),
				circle.toString());
	}
}