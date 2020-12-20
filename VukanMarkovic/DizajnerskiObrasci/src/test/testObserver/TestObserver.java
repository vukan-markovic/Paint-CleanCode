package test.testObserver;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mvc.DrawingFrame;
import observer.Observer;
import observer.PropertyManager;

public class TestObserver {
	private PropertyManager propertyManager;
	private Observer observer;

	@Before
	public void setUp() {
		propertyManager = new PropertyManager(new DrawingFrame());
		observer = new Observer();
		observer.addPropertyChangeListener(propertyManager);
	}

	@Test
	public void testSetBtnDeleteEnable() {
		observer.setBtnDeleteEnable(true);
		assertTrue(propertyManager.getMainFrame().getBtnDelete().isEnabled());
	}

	@Test
	public void testSetBtnModifyEnable() {
		observer.setBtnModifyEnable(true);
		assertTrue(propertyManager.getMainFrame().getBtnModify().isEnabled());
	}

	@Test
	public void testSetBtnUndoEnable() {
		observer.setBtnUndoEnable(true);
		assertTrue(propertyManager.getMainFrame().getBtnUndo().isEnabled());
	}

	@Test
	public void testSetBtnRedoEnable() {
		observer.setBtnRedoEnable(true);
		assertTrue(propertyManager.getMainFrame().getBtnRedo().isEnabled());
	}

	@Test
	public void testSetBtnToBackEnable() {
		observer.setBtnToBackEnable(true);
		assertTrue(propertyManager.getMainFrame().getBtnToBack().isEnabled());
	}

	@Test
	public void testSetBtnToFrontEnable() {
		observer.setBtnToFrontEnable(true);
		assertTrue(propertyManager.getMainFrame().getBtnToFront().isEnabled());
	}

	@Test
	public void testSetBtnSendToBackEnable() {
		observer.setBtnSendToBackEnable(true);
		assertTrue(propertyManager.getMainFrame().getBtnSendToBack().isEnabled());
	}

	@Test
	public void testSetBtnBringToFrontEnable() {
		observer.setBtnBringToFrontEnable(true);
		assertTrue(propertyManager.getMainFrame().getBtnBringToFront().isEnabled());
	}

	@After
	public void cleanUp() {
		observer.removePropertyChangeListener(propertyManager);
	}
}