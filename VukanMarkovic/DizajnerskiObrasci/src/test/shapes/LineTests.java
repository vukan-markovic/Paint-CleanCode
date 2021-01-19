package shapes;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import java.awt.Color;
import java.awt.Graphics;

public class LineTests {
	private Line line;
	private Graphics graphics;

	@Before
	public void setUp() {
		line = new Line(new Point(1, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK), false, Color.BLACK);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDrawShapeNotSelected() {
		line.draw(graphics);
		verify(graphics).setColor(line.getOuterColor());

		verify(graphics).drawLine(line.getStartPoint().getXcoordinate(), line.getStartPoint().getYcoordinate(),
				line.getEndPoint().getXcoordinate(), line.getEndPoint().getYcoordinate());
	}

	@Test
	public void testDrawShapeSelected() {
		line.setSelected(true);
		line.draw(graphics);
		verify(graphics).setColor(line.getOuterColor());

		verify(graphics).drawLine(line.getStartPoint().getXcoordinate(), line.getStartPoint().getYcoordinate(),
				line.getEndPoint().getXcoordinate(), line.getEndPoint().getYcoordinate());

		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(line.getStartPoint().getXcoordinate() - 3, line.getStartPoint().getYcoordinate() - 3,
				6, 6);
		verify(graphics).drawRect(line.getEndPoint().getXcoordinate() - 3, line.getEndPoint().getYcoordinate() - 3, 6,
				6);
		verify(graphics).drawRect(line.getMiddleOfLine().getXcoordinate() - 3,
				line.getMiddleOfLine().getYcoordinate() - 3, 6, 6);
	}

	@Test
	public void testMiddleOfLine() {
		assertEquals(new Point((line.getStartPoint().getXcoordinate() + line.getEndPoint().getXcoordinate()) / 2,
				(line.getStartPoint().getYcoordinate() + line.getEndPoint().getYcoordinate()) / 2, false,
				line.getOuterColor()), line.getMiddleOfLine());
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
		assertEquals(line.getStartPoint().calculateDistance(line.getEndPoint().getXcoordinate(),
				line.getEndPoint().getYcoordinate()), line.calculateLength(), 0);
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(line.equals(new Point(1, 2, false, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpectedStartPoint() {
		assertFalse(line.equals(new Line(new Point(2, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK), false, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpectedEndPoint() {
		assertFalse(line.equals(new Line(new Point(1, 2, false, Color.BLACK), new Point(3, 5, false, Color.BLACK), false, Color.BLACK)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(line.equals(new Line(new Point(1, 2, false, Color.BLACK), new Point(3, 4, false, Color.BLACK), false, Color.BLACK)));
	}

	@Test
	public void testToString() {
		assertEquals("Start point: " + line.getStartPoint() + ", " + "End point: " + line.getEndPoint()
				+ ", Outer color: " + line.getOuterColor().getRGB(), line.toString());
	}
}