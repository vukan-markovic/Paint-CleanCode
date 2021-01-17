package appTests;

import static org.junit.Assert.*;
import org.junit.*;

import app.DrawingApp;
import controller.*;
import frame.DrawingFrame;
import model.DrawingModel;

import javax.swing.JFrame;

public class DrawingAppTests {
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
		DrawingApp.main(new String[1]);
	}

	@Test
	public void testCreateAndShowGUI() {
		DrawingApp.createAndShowGUI(model, frame, controller);
		assertEquals(model, frame.getView().getModel());
		assertEquals(controller, frame.getController());
		assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
		assertTrue(frame.isVisible());
	}
}