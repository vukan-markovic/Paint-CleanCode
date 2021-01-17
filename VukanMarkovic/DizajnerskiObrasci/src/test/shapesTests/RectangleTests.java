package shapesTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import shapes.*;
import java.awt.Color;
import java.awt.Graphics;

public class RectangleTests {
	private Rectangle rectangle;
	private Graphics graphics;

	@Before
	public void setUp() {
		rectangle = new Rectangle(new Point(1, 2, false, Color.BLACK), 1, 2, false, Color.BLACK, Color.WHITE);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDrawShapeNotSelected() {
		rectangle.draw(graphics);
		verify(graphics).setColor(rectangle.getOuterColor());

		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getXcoordinate(),
				rectangle.getUpperLeftPoint().getYcoordinate(), rectangle.getWidth(), rectangle.getHeight());

		verify(graphics).setColor(rectangle.getInnerColor());

		verify(graphics).fillRect(rectangle.getUpperLeftPoint().getXcoordinate(),
				rectangle.getUpperLeftPoint().getYcoordinate(), rectangle.getWidth(), rectangle.getHeight());
	}

	@Test
	public void testDrawShapeSelected() {
		rectangle.setSelected(true);
		rectangle.draw(graphics);

		verify(graphics).setColor(rectangle.getOuterColor());

		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getXcoordinate(),
				rectangle.getUpperLeftPoint().getYcoordinate(), rectangle.getWidth(), rectangle.getHeight());

		verify(graphics).setColor(rectangle.getInnerColor());

		verify(graphics).fillRect(rectangle.getUpperLeftPoint().getXcoordinate(),
				rectangle.getUpperLeftPoint().getYcoordinate(), rectangle.getWidth(), rectangle.getHeight());

		verify(graphics).setColor(Color.BLUE);

		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getXcoordinate() - 3,
				rectangle.getUpperLeftPoint().getYcoordinate() - 3, 6, 6);

		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getXcoordinate() - 3 + rectangle.getWidth(),
				rectangle.getUpperLeftPoint().getYcoordinate() - 3, 6, 6);

		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getXcoordinate() - 3,
				rectangle.getUpperLeftPoint().getYcoordinate() - 3 + rectangle.getHeight(), 6, 6);

		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getXcoordinate() + rectangle.getWidth() - 3,
				rectangle.getUpperLeftPoint().getYcoordinate() + rectangle.getHeight() - 3, 6, 6);
	}

	@Test
	public void testContainsTrueExcepted() {
		assertTrue(rectangle.contains(1, 2));
	}

	@Test
	public void testContainsFalseExcepted() {
		assertFalse(rectangle.contains(21, 61));
	}

	@Test
	public void testEqualsNotSameType() {
		assertFalse(rectangle.equals(new Point(1, 2, false, Color.BLACK)));
	}

	@Test
	public void testEqualsFalseExpectedUpperLeftPoint() {
		assertFalse(rectangle.equals(new Rectangle(new Point(2, 2, false, Color.BLACK), 1, 2, false, Color.BLACK, Color.WHITE)));
	}

	@Test
	public void testEqualsFalseExpectedWidth() {
		assertFalse(rectangle.equals(new Rectangle(new Point(1, 2, false, Color.BLACK), 1, 3, false, Color.BLACK, Color.WHITE)));
	}

	@Test
	public void testEqualsFalseExpectedHeight() {
		assertFalse(rectangle.equals(new Rectangle(new Point(1, 2, false, Color.BLACK), 3, 2, false, Color.BLACK, Color.WHITE)));
	}

	@Test
	public void testEqualsTrueExpected() {
		assertTrue(rectangle.equals(new Rectangle(new Point(1, 2, false, Color.BLACK), 1, 2, false, Color.BLACK, Color.WHITE)));
	}

	@Test
	public void testToString() {
		assertEquals("Upper left point: " + rectangle.getUpperLeftPoint() + ", height: " + rectangle.getHeight()
				+ " , width: " + rectangle.getWidth() + " , Outer color: " + rectangle.getOuterColor().getRGB()
				+ " , Inner color: " + rectangle.getInnerColor().getRGB(), rectangle.toString());
	}
}