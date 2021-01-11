package test.shapesTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import java.awt.*;
import java.awt.geom.*;
import shapes.Donut;
import shapes.Point;

public class DonutTests {
	private Donut donut;
	private Graphics graphics;
	private Graphics2D graphics2d;
	private Area area;

	@Before
	public void setUp() {
		donut = new Donut(new Point(1, 2, false, Color.BLACK), 3, 2, false, Color.WHITE, Color.BLACK);
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
		verify(graphics2d).draw(donut.getArea());
		verify(graphics).setColor(donut.getInnerColor());
		verify(graphics2d).fill(donut.getArea());
	}

	@Test
	public void testDrawShapeSelected() {
		donut.setSelected(true);
		donut.draw(graphics, graphics2d);
		verify(graphics).setColor(donut.getOuterColor());
		verify(graphics2d).draw(donut.getArea());
		verify(graphics).setColor(donut.getInnerColor());
		verify(graphics2d).fill(donut.getArea());
		verify(graphics).setColor(donut.getInnerColor());
		verify(graphics2d).fill(donut.getArea());
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
	public void testCalculateArea() {
		donut.calculateArea();
		assertTrue(area.equals(donut.getArea()));
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
		assertFalse(donut.equals(new Point(1, 2, false, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpectedRadius() {
		assertFalse(donut.equals(new Donut(new Point(1, 2, false, Color.BLACK), 1, 2, false, Color.WHITE, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpectedInnerRadius() {
		assertFalse(donut.equals(new Donut(new Point(1, 2, false, Color.BLACK), 3, 1, false, Color.WHITE, Color.BLACK)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(donut.equals(new Donut(new Point(1, 2, false, Color.BLACK), 3, 2, false, Color.WHITE, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpected() {
		assertFalse(donut.equals(new Donut(new Point(1, 2, false, Color.BLACK), 8, 5, false, Color.WHITE, Color.BLACK)));
	}

	@Test
	public void testToString() {
		assertEquals(
				"Center: " + donut.getCenter() + ", radius: " + donut.getRadius() + " , Outer color: "
						+ donut.getOuterColor().getRGB() + " , Inner color: " + donut.getInnerColor().getRGB()
						+ " , inner radius: " + donut.getInnerRadius() + " , Outer color: "
						+ donut.getOuterColor().getRGB() + " , Inner color: " + donut.getInnerColor().getRGB(),
				donut.toString());
	}
}