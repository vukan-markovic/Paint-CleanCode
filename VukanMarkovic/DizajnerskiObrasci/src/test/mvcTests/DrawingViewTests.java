package test.mvcTests;

import static org.mockito.Mockito.*;
import org.junit.*;
import mvc.*;
import shapes.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class DrawingViewTests {
	private DrawingModel model;
	private DrawingView view;
	private Point point;
	private Donut donut;
	private Graphics2D graphics;

	@Before
	public void setUp() {
		model = new DrawingModel();
		view = new DrawingView();
		point = mock(Point.class);
		donut = mock(Donut.class);
		model.addShape(point);
		model.addShape(donut);
		view.setModel(model);
		graphics = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).createGraphics();
	}

	@Test
	public void testPaintComponent() {
		view.paint(graphics);
		verify(point).draw(graphics);
		verify(donut).draw(graphics, graphics);
	}
}