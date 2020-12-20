package test.testGeometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import geometry.HexagonAdapter;
import geometry.Point;
import hexagon.Hexagon;

public class TestHexagonAdapter {
	private HexagonAdapter hexagonAdapter;
	private Hexagon hexagonMock;
	private Graphics graphics;

	@Before
	public void setUp() {
		hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3), Color.WHITE, Color.BLACK);
		graphics = mock(Graphics.class);
		hexagonMock = mock(Hexagon.class);
	}

	@Test
	public void testDraw() {
		hexagonAdapter = new HexagonAdapter(hexagonMock, Color.WHITE, Color.BLACK);
		hexagonAdapter.draw(graphics);
		verify(hexagonMock).paint(graphics);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testMoveBy() {
		hexagonAdapter.moveBy(1, 2);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testFillShape() {
		hexagonAdapter.fillShape(graphics);
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(hexagonAdapter.contains(1, 1));
	}

	@Test
	public void testContainsFalseExcepted() {
		assertFalse(hexagonAdapter.contains(21, 61));
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(hexagonAdapter.equals(new Point(1, 2)));
	}

	@Test
	public void testEqualsFalseExpectedRadius() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(1, 2, 4), Color.WHITE, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpectedXCoordinate() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(2, 2, 3), Color.WHITE, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpectedYCoordinate() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(1, 1, 3), Color.WHITE, Color.BLACK)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(1, 2, 3), Color.WHITE, Color.BLACK)));
	}

	@Test
	public void testToString() {
		assertEquals("Center: "
				+ (new Point(hexagonAdapter.getX(), hexagonAdapter.getY(), false, new Color(250, 128, 114)))
				+ ", radius: " + hexagonAdapter.getR() + " , Border color: " + hexagonAdapter.getBorderColor().getRGB()
				+ " , Fill color: " + hexagonAdapter.getFillColor().getRGB(), hexagonAdapter.toString());
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, hexagonAdapter.compareTo(new Point()));
	}
}