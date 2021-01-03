package test.mvcTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import mvc.MainApp;

public class MainAppTests {
	private DrawingModel model;
	private DrawingFrame frame;
	private DrawingController controller;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		controller = new DrawingController();
		controller.setController(model, frame);
	}

	@Test
	public void testMain() {
		MainApp.main(null);
	}

	@Test
	public void testCreateAndShowGUI() {
		MainApp.createAndShowGUI(model, frame, controller);
		assertEquals(model, frame.getView().getModel());
		assertEquals(controller, frame.getController());
		assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
		assertTrue(frame.isVisible());
	}
}