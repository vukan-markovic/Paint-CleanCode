package test.testMVC;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import geometry.Donut;
import geometry.Point;
import mvc.DrawingModel;
import mvc.DrawingView;

public class TestDrawingView {
	private DrawingView drawingView;
	private DrawingModel drawingModel;
	private Point point;
	private Donut donut;
	private Graphics2D graphics;

	@Before
	public void setUp() {
		drawingModel = new DrawingModel();
		point = mock(Point.class);
		donut = mock(Donut.class);
		drawingModel.add(point);
		drawingModel.add(donut);
		drawingView = new DrawingView();
		drawingView.setModel(drawingModel);
		graphics = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).createGraphics();
	}

	@Test
	public void testPaintComponent() {
		drawingView.paint(graphics);
		verify(point).draw(graphics);
		verify(donut).draw(graphics, graphics);
	}
}