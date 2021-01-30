package shapes;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import java.awt.Color;
import java.awt.Graphics;

public class LineTests {
	private Graphics graphics;
	int xCoordinateOfStartPoint;
	int yCoordinateOfStartPoint;
	int xCoordinateOfEndPoint;
	int yCoordinateOfEndPoint;
	Color borderColor;
	private Line line;

	@Before
	public void setUp() {
		graphics = mock(Graphics.class);
		xCoordinateOfStartPoint = 1;
		yCoordinateOfStartPoint = 2;
		xCoordinateOfEndPoint = 3;
		yCoordinateOfEndPoint = 4;
		borderColor = Color.BLACK;

		line = new Line(new Point(xCoordinateOfStartPoint, yCoordinateOfStartPoint, false, borderColor),
				new Point(xCoordinateOfEndPoint, yCoordinateOfEndPoint, false, borderColor), false, borderColor);
	}

	@Test
	public void testDrawShapeNotSelected() {
		line.draw(graphics);
		verify(graphics).setColor(borderColor);
		verify(graphics).drawLine(xCoordinateOfStartPoint, yCoordinateOfStartPoint, xCoordinateOfEndPoint,
				yCoordinateOfEndPoint);
	}

	@Test
	public void testDrawShapeSelected() {
		line.setSelected(true);
		line.draw(graphics);
		verify(graphics).setColor(line.getBorderColor());

		verify(graphics).drawLine(xCoordinateOfStartPoint, yCoordinateOfStartPoint, xCoordinateOfEndPoint,
				yCoordinateOfEndPoint);

		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(xCoordinateOfStartPoint - 3, yCoordinateOfStartPoint - 3, 6, 6);
		verify(graphics).drawRect(xCoordinateOfEndPoint - 3, yCoordinateOfEndPoint - 3, 6, 6);

		verify(graphics).drawRect(line.getMiddleOfLine().getXcoordinate() - 3,
				line.getMiddleOfLine().getYcoordinate() - 3, 6, 6);
	}

	@Test
	public void testMiddleOfLine() {
		assertEquals(
				new Point((xCoordinateOfStartPoint + xCoordinateOfEndPoint) / 2,
						(yCoordinateOfStartPoint + yCoordinateOfEndPoint) / 2, false, borderColor),
				line.getMiddleOfLine());
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
	public void testLength() {
		assertEquals(line.getStartPoint().calculateDistance(xCoordinateOfEndPoint, yCoordinateOfEndPoint),
				line.calculateLength(), 0);
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(line.equals(new Point(1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedStartPoint() {
		assertFalse(line.equals(new Line(new Point(2, 2), new Point(3, 4))));
	}

	@Test
	public void testEqualsFalseExpectedEndPoint() {
		assertFalse(line.equals(new Line(new Point(1, 2), new Point(3, 5))));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(line.equals(new Line(new Point(1, 2), new Point(3, 4))));
	}

	@Test
	public void testToString() {
		assertEquals("Start point: " + line.getStartPoint() + ", " + "End point: " + line.getEndPoint()
				+ ", Border color: " + borderColor.getRGB(), line.toString());
	}
}