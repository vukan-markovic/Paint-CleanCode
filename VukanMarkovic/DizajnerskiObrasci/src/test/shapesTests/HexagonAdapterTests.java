package test.shapesTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import hexagon.Hexagon;
import shapes.*;
import java.awt.Color;
import java.awt.Graphics;

public class HexagonAdapterTests {
	private HexagonAdapter hexagonAdapter;
	private Hexagon hexagon;
	private Graphics graphics;

	@Before
	public void setUp() {
		hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3), Color.WHITE, Color.BLACK);
		hexagon = mock(Hexagon.class);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDraw() {
		hexagonAdapter = new HexagonAdapter(hexagon, Color.WHITE, Color.BLACK);
		hexagonAdapter.draw(graphics);
		verify(hexagon).paint(graphics);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testDrawSelection() {
		hexagonAdapter.drawSelection(graphics);
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
		assertEquals(
				"Center: " + new Point(hexagonAdapter.getXcoordinate(), hexagonAdapter.getYcoordinate()) + ", radius: "
						+ hexagonAdapter.getRadius() + " , Inner color: " + hexagonAdapter.getInnerColor().getRGB()
						+ " , Outer color: " + hexagonAdapter.getOuterColor().getRGB(),
				hexagonAdapter.toString());
	}
}