package shapes;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import java.awt.*;
import java.awt.geom.*;

public class DonutTests {
	private Graphics2D graphics;
	private int xCoordinate;
	private int yCoordinate;
	private int outerRadius;
	private int innerRadius;
	private Color borderColor;
	private Color fillColor;
	private Donut donut;
	private Area area;

	@Before
	public void setUp() {
		graphics = mock(Graphics2D.class);
		xCoordinate = 1;
		yCoordinate = 2;
		outerRadius = 3;
		innerRadius = 2;
		borderColor = Color.BLACK;
		fillColor = Color.WHITE;

		donut = new Donut(new Point(xCoordinate, yCoordinate, false, Color.BLACK), outerRadius, innerRadius, false,
				borderColor, fillColor);

		area = new Area(new Ellipse2D.Double(xCoordinate - outerRadius, yCoordinate - outerRadius, outerRadius * 2,
				outerRadius * 2));

		area.subtract(new Area(new Ellipse2D.Double((xCoordinate - innerRadius), (yCoordinate - innerRadius),
				innerRadius * 2, innerRadius * 2)));
	}

	@Test
	public void testDrawShapeNotSelected() {
		donut.draw(graphics);
		verify(graphics).setColor(borderColor);
		verify(graphics).draw(donut.getArea());
		verify(graphics).setColor(fillColor);
		verify(graphics).fill(donut.getArea());
	}

	@Test
	public void testDrawShapeSelected() {
		donut.setSelected(true);
		donut.draw(graphics);
		verify(graphics).setColor(borderColor);
		verify(graphics).draw(donut.getArea());
		verify(graphics).setColor(fillColor);
		verify(graphics).fill(donut.getArea());
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(xCoordinate - 3, yCoordinate - 3, 6, 6);
		verify(graphics).drawRect(xCoordinate + outerRadius - 3, yCoordinate - 3, 6, 6);
		verify(graphics).drawRect(xCoordinate - outerRadius - 3, yCoordinate - 3, 6, 6);
		verify(graphics).drawRect(xCoordinate - 3, yCoordinate + outerRadius - 3, 6, 6);
		verify(graphics).drawRect(xCoordinate - 3, yCoordinate - outerRadius - 3, 6, 6);
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
		assertFalse(donut.equals(new Point(1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedRadius() {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedInnerRadius() {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 3, 1)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(donut.equals(new Donut(new Point(1, 2), 3, 2)));
	}

	@Test
	public void testEqualsFalseExpected() {
		assertFalse(donut.equals(new Donut(new Point(1, 2), 8, 5)));
	}

	@Test
	public void testToString() {
		assertEquals(
				"Center: " + donut.getCenter() + ", radius: " + outerRadius + " , Border color: " + borderColor.getRGB()
						+ " , Fill color: " + fillColor.getRGB() + " , inner radius: " + innerRadius
						+ " , Border color: " + borderColor.getRGB() + " , Fill color: " + fillColor.getRGB(),
				donut.toString());
	}
}