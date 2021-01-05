package test.observerTests;

import org.junit.*;
import observer.*;
import mvc.DrawingFrame;
import static org.junit.Assert.assertTrue;

public class DrawingObserverTests {
	private PropertyManager manager;
	private DrawingObserver observer;

	@Before
	public void setUp() {
		manager = new PropertyManager(new DrawingFrame());
		observer = new DrawingObserver();
		observer.addPropertyChangeListener(manager);
	}

	@Test
	public void testSetBtnDeleteEnabled() {
		observer.setBtnDeleteEnabled(true);
		assertTrue(manager.getFrame().getBtnDelete().isEnabled());
	}

	@Test
	public void testSetBtnModifyEnabled() {
		observer.setBtnModifyEnabled(true);
		assertTrue(manager.getFrame().getBtnModify().isEnabled());
	}

	@Test
	public void testSetBtnUndoEnabled() {
		observer.setBtnUndoEnabled(true);
		assertTrue(manager.getFrame().getBtnUndo().isEnabled());
	}

	@Test
	public void testSetBtnRedoEnabled() {
		observer.setBtnRedoEnabled(true);
		assertTrue(manager.getFrame().getBtnRedo().isEnabled());
	}

	@Test
	public void testSetBtnToBackEnabled() {
		observer.setBtnToBackEnabled(true);
		assertTrue(manager.getFrame().getBtnToBack().isEnabled());
	}

	@Test
	public void testSetBtnToFrontEnabled() {
		observer.setBtnToFrontEnabled(true);
		assertTrue(manager.getFrame().getBtnToFront().isEnabled());
	}

	@Test
	public void testSetBtnSendToBackEnabled() {
		observer.setBtnSendToBackEnabled(true);
		assertTrue(manager.getFrame().getBtnSendToBack().isEnabled());
	}

	@Test
	public void testSetBtnBringToFrontEnabled() {
		observer.setBtnBringToFrontEnabled(true);
		assertTrue(manager.getFrame().getBtnBringToFront().isEnabled());
	}

	@After
	public void tearDown() {
		observer.removePropertyChangeListener(manager);
	}
}