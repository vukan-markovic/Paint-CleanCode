package test.shapesTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import shapes.Point;
import shapes.Rectangle;

public class RectangleTests {
	private Rectangle rectangle;
	private Rectangle rectangleMock;
	private Graphics graphics;

	@Before
	public void setUp() {
		rectangle = new Rectangle(new Point(1, 2), 1, 2);
		rectangleMock = spy(Rectangle.class);
		rectangleMock.setUpperLeftPoint(new Point(1, 2));
		rectangleMock.setHeight(1);
		rectangleMock.setWidth(2);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDrawShapeNotSelected() {
		rectangleMock.draw(graphics);
		verify(graphics).setColor(rectangleMock.getOuterColor());

		verify(graphics).drawRect(rectangleMock.getUpperLeftPoint().getXcoordinate(),
				rectangleMock.getUpperLeftPoint().getYcoordinate(), rectangleMock.getWidth(),
				rectangleMock.getHeight());

		verify(rectangleMock).fillShape(graphics);
	}

	@Test
	public void testDrawShapeSelected() {
		rectangleMock.setSelected(true);
		rectangleMock.draw(graphics);

		verify(graphics).setColor(rectangleMock.getOuterColor());

		verify(graphics).drawRect(rectangleMock.getUpperLeftPoint().getXcoordinate(),
				rectangleMock.getUpperLeftPoint().getYcoordinate(), rectangleMock.getWidth(),
				rectangleMock.getHeight());

		verify(rectangleMock).fillShape(graphics);
		verify(graphics).setColor(Color.BLUE);

		verify(graphics).drawRect(rectangleMock.getUpperLeftPoint().getXcoordinate() - 3,
				rectangleMock.getUpperLeftPoint().getYcoordinate() - 3, 6, 6);

		verify(graphics).drawRect(rectangleMock.getUpperLeftPoint().getXcoordinate() - 3 + rectangleMock.getWidth(),
				rectangleMock.getUpperLeftPoint().getYcoordinate() - 3, 6, 6);

		verify(graphics).drawRect(rectangleMock.getUpperLeftPoint().getXcoordinate() - 3,
				rectangleMock.getUpperLeftPoint().getYcoordinate() - 3 + rectangleMock.getHeight(), 6, 6);

		verify(graphics).drawRect(rectangleMock.getUpperLeftPoint().getXcoordinate() + rectangleMock.getWidth() - 3,
				rectangleMock.getUpperLeftPoint().getYcoordinate() + rectangleMock.getHeight() - 3, 6, 6);
	}

	@Test
	public void testFillShape() {
		rectangle.fillShape(graphics);
		verify(graphics).setColor(rectangle.getInnerColor());

		verify(graphics).fillRect(rectangle.getUpperLeftPoint().getXcoordinate(),
				rectangle.getUpperLeftPoint().getYcoordinate(), rectangle.getWidth(), rectangle.getHeight());
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
	public void testToString() {
		assertEquals("Upper left point: " + rectangle.getUpperLeftPoint() + ", height: " + rectangle.getHeight()
				+ " , width: " + rectangle.getWidth() + " , Inner color: " + rectangle.getInnerColor().getRGB()
				+ " , Outer color: " + rectangle.getOuterColor().getRGB(), rectangle.toString());
	}
}