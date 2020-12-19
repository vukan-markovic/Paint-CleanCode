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

import geometry.Line;
import geometry.Point;

public class TestLine {
	private Line line;
	private Graphics graphics;

	@Before
	public void setUp() {
		line = new Line(new Point(1, 2), new Point(3, 4), false, Color.BLACK);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDrawShapeNotSelected() {
		line.draw(graphics);
		verify(graphics).setColor(line.getBorderColor());

		verify(graphics).drawLine(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getEndPoint().getX(),
				line.getEndPoint().getY());

		verify(graphics).setColor(Color.BLUE);
	}

	@Test
	public void testDrawShapeSelected() {
		line.setSelected(true);
		line.draw(graphics);
		verify(graphics).setColor(line.getBorderColor());

		verify(graphics).drawLine(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getEndPoint().getX(),
				line.getEndPoint().getY());

		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(line.getStartPoint().getX() - 3, line.getStartPoint().getY() - 3, 6, 6);
		verify(graphics).drawRect(line.getEndPoint().getX() - 3, line.getEndPoint().getY() - 3, 6, 6);
		verify(graphics).drawRect(line.middleOfLine().getX() - 3, line.middleOfLine().getY() - 3, 6, 6);
	}

	@Test
	public void testMiddleOfLine() {
		assertEquals(
				new Point((line.getStartPoint().getX() + line.getEndPoint().getX()) / 2,
						(line.getStartPoint().getY() + line.getEndPoint().getY()) / 2, false, line.getBorderColor()),
				line.middleOfLine());
	}

	@Test
	public void testMoveBy() {
		line.moveBy(1, 2);
		assertEquals(2, line.getStartPoint().getX());
		assertEquals(4, line.getStartPoint().getY());
		assertEquals(4, line.getEndPoint().getX());
		assertEquals(6, line.getEndPoint().getY());
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(line.contains(1, 2));
	}

	@Test
	public void testContainsFalseExcepted() {
		assertFalse(line.contains(21, 61));
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(line.equals(new Point(1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedStartPoint() {
		assertFalse(line.equals(new Line(new Point(2, 2), new Point(3, 4), false, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpectedEndPoint() {
		assertFalse(line.equals(new Line(new Point(1, 2), new Point(3, 5), false, Color.BLACK)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(line.equals(new Line(new Point(1, 2), new Point(3, 4), false, Color.BLACK)));
	}

	@Test
	public void testLength() {
		assertEquals(line.getStartPoint().distance(line.getEndPoint().getX(), line.getEndPoint().getY()),
				line.length());
	}

	@Test
	public void testToString() {
		assertEquals("Start point: " + line.getStartPoint() + ", " + "End point: " + line.getEndPoint()
				+ ", Border color: " + line.getBorderColor().getRGB(), line.toString());
	}

	@Test
	public void testCompareToNotSameType() {
		assertEquals(0, line.compareTo(new Point()));
	}

	@Test
	public void testCompareToEquals() {
		assertEquals(0, line.compareTo(new Line(new Point(1, 2), new Point(3, 4), false, Color.BLACK)));
	}

	@Test
	public void testCompareToLessThan() {
		assertEquals(1, line.compareTo(new Line(new Point(0, 1), new Point(1, 1), false, Color.BLACK)));
	}

	@Test
	public void testCompareToGreaterThan() {
		assertEquals(-1, line.compareTo(new Line(new Point(1, 5), new Point(5, 4), false, Color.BLACK)));
	}
}