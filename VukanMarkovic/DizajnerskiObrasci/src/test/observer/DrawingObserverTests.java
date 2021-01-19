package observer;

import org.junit.*;
import frame.DrawingFrame;
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
		assertTrue(manager.getFrame().getTopToolbar().getBtnDelete().isEnabled());
	}

	@Test
	public void testSetBtnModifyEnabled() {
		observer.setBtnModifyEnabled(true);
		assertTrue(manager.getFrame().getTopToolbar().getBtnModify().isEnabled());
	}

	@Test
	public void testSetBtnUndoEnabled() {
		observer.setBtnUndoEnabled(true);
		assertTrue(manager.getFrame().getRightToolbar().getBtnUndo().isEnabled());
	}

	@Test
	public void testSetBtnRedoEnabled() {
		observer.setBtnRedoEnabled(true);
		assertTrue(manager.getFrame().getRightToolbar().getBtnRedo().isEnabled());
	}

	@Test
	public void testSetBtnToBackEnabled() {
		observer.setBtnToBackEnabled(true);
		assertTrue(manager.getFrame().getRightToolbar().getBtnToBack().isEnabled());
	}

	@Test
	public void testSetBtnToFrontEnabled() {
		observer.setBtnToFrontEnabled(true);
		assertTrue(manager.getFrame().getRightToolbar().getBtnToFront().isEnabled());
	}

	@Test
	public void testSetBtnSendToBackEnabled() {
		observer.setBtnSendToBackEnabled(true);
		assertTrue(manager.getFrame().getRightToolbar().getBtnSendToBack().isEnabled());
	}

	@Test
	public void testSetBtnBringToFrontEnabled() {
		observer.setBtnBringToFrontEnabled(true);
		assertTrue(manager.getFrame().getRightToolbar().getBtnBringToFront().isEnabled());
	}

	@After
	public void tearDown() {
		observer.removePropertyChangeListener(manager);
	}
}