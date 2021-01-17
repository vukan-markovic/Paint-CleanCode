package frameTests;

import static org.mockito.Mockito.*;
import org.junit.*;
import controller.*;
import frame.DrawingFrame;
import model.DrawingModel;

public class DrawingFrameTests {
	private DrawingController controller;
	private DrawingFrame frame;
	private DrawingModel model;

	@Before
	public void setUp() {
		controller = mock(DrawingController.class);
		frame = new DrawingFrame();
		model = new DrawingModel();
		controller.setController(model, frame);
		frame.setController(controller);
	}

	@Test
	public void testViewClicked() {
		frame.getView();
	}
}