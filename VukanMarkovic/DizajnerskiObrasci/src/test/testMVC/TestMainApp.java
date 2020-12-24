package test.testMVC;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import mvc.MainApp;

public class TestMainApp {
	private DrawingModel drawingModel;
	private DrawingFrame drawingFrame;
	private DrawingController drawingController;

	@Before
	public void setUp() {
		drawingModel = new DrawingModel();
		drawingFrame = new DrawingFrame();
		drawingController = new DrawingController(drawingModel, drawingFrame);
	}

	@Test
	public void testMain() {
		MainApp.main(null);
	}

	@Test
	public void testCreateAndShowGUI() {
		MainApp.createAndShowGUI(drawingModel, drawingFrame, drawingController);
		assertEquals(drawingModel, drawingFrame.getView().getModel());
		assertEquals(drawingController, drawingFrame.getController());
		assertEquals(JFrame.EXIT_ON_CLOSE, drawingFrame.getDefaultCloseOperation());
		assertTrue(drawingFrame.isVisible());
	}
}