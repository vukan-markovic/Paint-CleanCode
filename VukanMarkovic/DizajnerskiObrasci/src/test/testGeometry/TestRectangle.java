package test.testGeometry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import geometry.Point;
import geometry.Rectangle;

public class TestRectangle {
	private Rectangle rectangle;
	private Graphics graphics;

	@Before
	public void setUp() {
		rectangle = new Rectangle(new Point(1, 2), 1, 2);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDrawShapeNotSelected() {
		rectangle.draw(graphics);
		verify(graphics).setColor(rectangle.getBorderColor());
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX(), rectangle.getUpperLeftPoint().getY(),
				rectangle.getWidth(), rectangle.getHeight());
		verify(graphics).setColor(Color.BLUE);
	}

	@Test
	public void testDrawShapeSelected() {
		rectangle.setSelected(true);
		rectangle.draw(graphics);
		verify(graphics).setColor(rectangle.getBorderColor());

		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX(), rectangle.getUpperLeftPoint().getY(),
				rectangle.getWidth(), rectangle.getHeight());

		verify(graphics).setColor(Color.BLUE);

		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX() - 3, rectangle.getUpperLeftPoint().getY() - 3, 6,
				6);

		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX() - 3 + rectangle.getWidth(),
				rectangle.getUpperLeftPoint().getY() - 3, 6, 6);

		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX() - 3,
				rectangle.getUpperLeftPoint().getY() - 3 + rectangle.getHeight(), 6, 6);

		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX() + rectangle.getWidth() - 3,
				rectangle.getUpperLeftPoint().getY() + rectangle.getHeight() - 3, 6, 6);
	}

	@Test
	public void testFillShape() {
		rectangle.fillShape(graphics);
		verify(graphics).setColor(rectangle.getFillColor());

		verify(graphics).fillRect(rectangle.getUpperLeftPoint().getX(), rectangle.getUpperLeftPoint().getY(),
				rectangle.getWidth(), rectangle.getHeight());
	}

	@Test
	public void testMoveBy() {
		rectangle.moveBy(1, 2);
		assertEquals(2, rectangle.getUpperLeftPoint().getX());
		assertEquals(4, rectangle.getUpperLeftPoint().getY());
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(rectangle.contains(1, 2));
	}

	@Test
	public void testContainsFalseExcepted() {
		assertFalse(rectangle.contains(21, 61));
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(rectangle.equals(new Point(1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedUpperLeftPoint() {
		assertFalse(rectangle.equals(new Rectangle(new Point(2, 2), 1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedWidth() {
		assertFalse(rectangle.equals(new Rectangle(new Point(1, 2), 1, 3)));
	}

	@Test
	public void testEqualsFalseExpectedHeight() {
		assertFalse(rectangle.equals(new Rectangle(new Point(1, 2), 3, 2)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(rectangle.equals(new Rectangle(new Point(1, 2), 1, 2)));
	}

	@Test
	public void testArea() {
		assertEquals(rectangle.getWidth() * rectangle.getHeight(), rectangle.area());
	}

	@Test
	public void testToString() {
		assertEquals(
				"Upper left point: " + rectangle.getUpperLeftPoint() + ", height: " + rectangle.getHeight()
						+ " , width: " + rectangle.getWidth() + " , Border color: "
						+ rectangle.getBorderColor().getRGB() + " , Fill color: " + rectangle.getFillColor().getRGB(),
				rectangle.toString());
	}

	@Test
	public void testCompareToNotSameType() {
		assertEquals(0, rectangle.compareTo(new Point()));
	}

	@Test
	public void testCompareToEquals() {
		assertEquals(0, rectangle.compareTo(new Rectangle(new Point(1, 2), 1, 2)));
	}

	@Test
	public void testCompareToLessThan() {
		assertEquals(1, rectangle.compareTo(new Rectangle(new Point(1, 2), 1, 1)));
	}

	@Test
	public void testCompareToGreaterThan() {
		assertEquals(-2, rectangle.compareTo(new Rectangle(new Point(1, 2), 2, 2)));
	}
}