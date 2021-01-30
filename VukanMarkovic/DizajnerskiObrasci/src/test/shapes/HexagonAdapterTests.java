package shapes;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.*;
import hexagon.Hexagon;
import java.awt.Color;
import java.awt.Graphics;

public class HexagonAdapterTests {
	private HexagonAdapter hexagonAdapter;
	private Graphics graphics;

	@Before
	public void setUp() {
		hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3), false, Color.BLACK, Color.WHITE);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDraw() {
		Hexagon hexagon = mock(Hexagon.class);
		HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon);
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
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(1, 2, 4))));
	}

	@Test
	public void testEqualsFalseExpectedXCoordinate() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(2, 2, 3))));
	}

	@Test
	public void testEqualsFalseExpectedYCoordinate() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(1, 1, 3))));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(1, 2, 3))));
	}

	@Test
	public void testToString() {
		assertEquals("Center: "
				+ new Point(hexagonAdapter.getXcoordinate(), hexagonAdapter.getYcoordinate(), false, Color.BLACK)
				+ ", radius: " + hexagonAdapter.getRadius() + " , Border color: "
				+ hexagonAdapter.getBorderColor().getRGB() + " , Fill color: " + hexagonAdapter.getFillColor().getRGB(),
				hexagonAdapter.toString());
	}
}