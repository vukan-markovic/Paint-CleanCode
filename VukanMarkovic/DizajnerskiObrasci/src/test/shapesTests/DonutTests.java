package test.shapesTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import org.junit.Before;
import org.junit.Test;

import shapes.Donut;
import shapes.Point;

public class DonutTests {
	private Donut donut;
	private Graphics graphics;
	private Graphics2D graphics2d;
	private Area area;

	@Before
	public void setUp() {
		donut = new Donut(new Point(1, 2), 3, 2, false, Color.WHITE, Color.BLACK);
		graphics = mock(Graphics.class);
		graphics2d = mock(Graphics2D.class);

		area = new Area(new Ellipse2D.Double(donut.getCenter().getXcoordinate() - donut.getRadius(),
				donut.getCenter().getYcoordinate() - donut.getRadius(), donut.getRadius() * 2, donut.getRadius() * 2));

		area.subtract(new Area(new Ellipse2D.Double((donut.getCenter().getXcoordinate() - donut.getInnerRadius()),
				(donut.getCenter().getYcoordinate() - donut.getInnerRadius()), donut.getInnerRadius() * 2,
				donut.getInnerRadius() * 2)));
	}

	@Test
	public void testDrawShapeNotSelected() {
		donut.draw(graphics, graphics2d);
		verify(graphics).setColor(donut.getOuterColor());
		verify(graphics2d).draw(donut.getDonutArea());
		verify(graphics).setColor(donut.getInnerColor());
		verify(graphics2d).fill(donut.getDonutArea());
	}

	@Test
	public void testDrawShapeSelected() {
		donut.setSelected(true);
		donut.draw(graphics, graphics2d);
		verify(graphics).setColor(donut.getOuterColor());
		verify(graphics2d).draw(donut.getDonutArea());
		verify(graphics).setColor(donut.getInnerColor());
		verify(graphics2d).fill(donut.getDonutArea());
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(donut.getCenter().getXcoordinate() - 3, donut.getCenter().getYcoordinate() - 3, 6, 6);

		verify(graphics).drawRect(donut.getCenter().getXcoordinate() + donut.getRadius() - 3,
				donut.getCenter().getYcoordinate() - 3, 6, 6);

		verify(graphics).drawRect(donut.getCenter().getXcoordinate() - donut.getRadius() - 3,
				donut.getCenter().getYcoordinate() - 3, 6, 6);

		verify(graphics).drawRect(donut.getCenter().getXcoordinate() - 3,
				donut.getCenter().getYcoordinate() + donut.getRadius() - 3, 6, 6);

		verify(graphics).drawRect(donut.getCenter().getXcoordinate() - 3,
				donut.getCenter().getYcoordinate() - donut.getRadius() - 3, 6, 6);
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(donut.contains(3, 3));
	}

	@Test
	public void testContainsFalseExceptedInnerCircle() {
		assertFalse(donut.contains(21, 61));
	}

	@Test
	public void testContainsFalseExceptedOuterCircle() {
		assertFalse(donut.contains(1, 1));
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(donut.equals(new Point(1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedRadius() {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 1, 2, false, Color.WHITE, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpectedInnerRadius() {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 3, 1, false, Color.WHITE, Color.BLACK)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(donut.equals(new Donut(new Point(1, 2), 3, 2, false, Color.WHITE, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpected() {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 8, 5, false, Color.WHITE, Color.BLACK)));
	}

	@Test
	public void testToString() {
		assertEquals(
				"Center: " + donut.getCenter() + ", radius: " + donut.getRadius() + " , Inner color: "
						+ donut.getInnerColor().getRGB() + " , Outer color: " + donut.getOuterColor().getRGB()
						+ " , inner radius: " + donut.getInnerRadius() + " , Inner color: "
						+ donut.getInnerColor().getRGB() + " , Outer color: " + donut.getOuterColor().getRGB(),
				donut.toString());
	}
}