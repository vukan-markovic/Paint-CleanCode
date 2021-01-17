package shapesTests;

import static org.junit.Assert.*;
import org.junit.*;
import hexagon.Hexagon;
import shapes.*;
import java.awt.Color;

public class HexagonAdapterTests {
	private HexagonAdapter hexagonAdapter;

	@Before
	public void setUp() {
		hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3), false, Color.BLACK, Color.WHITE);
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
		assertFalse(hexagonAdapter.equals(new Point(1, 2, false, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpectedRadius() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(1, 2, 4), false, Color.BLACK, Color.WHITE)));
	}

	@Test
	public void testEqualsFalseExpectedXCoordinate() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(2, 2, 3), false, Color.BLACK, Color.WHITE)));
	}

	@Test
	public void testEqualsFalseExpectedYCoordinate() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(1, 1, 3), false, Color.BLACK, Color.WHITE)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(1, 2, 3), false, Color.BLACK, Color.WHITE)));
	}

	@Test
	public void testToString() {
		assertEquals(
				"Center: "
						+ new Point(hexagonAdapter.getXcoordinate(), hexagonAdapter.getYcoordinate(), false,
								Color.BLACK)
						+ ", radius: " + hexagonAdapter.getRadius() + " , Outer color: "
						+ hexagonAdapter.getOuterColor().getRGB() + " , Inner color: "
						+ hexagonAdapter.getInnerColor().getRGB(),
				hexagonAdapter.toString());
	}
}