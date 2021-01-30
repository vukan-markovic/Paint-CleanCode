package shapes;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import java.awt.*;
import hexagon.Hexagon;

public class HexagonAdapterTests {
	private Graphics graphics;
	private HexagonAdapter hexagonAdapter;

	@Before
	public void setUp() {
		graphics = mock(Graphics.class);
		hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3), false, Color.BLACK, Color.WHITE);
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