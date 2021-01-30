package observer;

import org.junit.*;
import toolbars.*;
import frame.DrawingFrame;
import static org.junit.Assert.assertTrue;

public class DrawingObserverTests {
	private PropertyManager manager;
	private TopToolbar topToolbar;
	private RightToolbar rightToolbar;
	private DrawingObserver observer;

	@Before
	public void setUp() {
		manager = new PropertyManager(new DrawingFrame());
		topToolbar = manager.getTopToolbar();
		rightToolbar = manager.getRightToolbar();
		observer = new DrawingObserver();
		observer.addPropertyChangeListener(manager);
	}

	@Test
	public void testSetBtnDeleteEnabled() {
		observer.setBtnDeleteEnabled(true);
		assertTrue(topToolbar.getBtnDelete().isEnabled());
	}

	@Test
	public void testSetBtnModifyEnabled() {
		observer.setBtnModifyEnabled(true);
		assertTrue(topToolbar.getBtnModify().isEnabled());
	}

	@Test
	public void testSetBtnUndoEnabled() {
		observer.setBtnUndoEnabled(true);
		assertTrue(rightToolbar.getBtnUndo().isEnabled());
	}

	@Test
	public void testSetBtnRedoEnabled() {
		observer.setBtnRedoEnabled(true);
		assertTrue(rightToolbar.getBtnRedo().isEnabled());
	}

	@Test
	public void testSetBtnToBackEnabled() {
		observer.setBtnToBackEnabled(true);
		assertTrue(rightToolbar.getBtnToBack().isEnabled());
	}

	@Test
	public void testSetBtnToFrontEnabled() {
		observer.setBtnToFrontEnabled(true);
		assertTrue(rightToolbar.getBtnToFront().isEnabled());
	}

	@Test
	public void testSetBtnSendToBackEnabled() {
		observer.setBtnSendToBackEnabled(true);
		assertTrue(rightToolbar.getBtnSendToBack().isEnabled());
	}

	@Test
	public void testSetBtnBringToFrontEnabled() {
		observer.setBtnBringToFrontEnabled(true);
		assertTrue(rightToolbar.getBtnBringToFront().isEnabled());
	}

	@After
	public void tearDown() {
		observer.removePropertyChangeListener(manager);
	}
}