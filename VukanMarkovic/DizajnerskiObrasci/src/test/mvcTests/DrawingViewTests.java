package test.mvcTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import mvc.DrawingModel;
import mvc.DrawingView;
import shapes.Donut;
import shapes.Point;

public class DrawingViewTests {
	private DrawingView view;
	private DrawingModel model;
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