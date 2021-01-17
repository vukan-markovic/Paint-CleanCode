package frameTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import controller.DrawingController;
import frame.RightToolbar;

public class RightToolbarTests {
	private RightToolbar rightToolbar;
	private DrawingController controller;

	@Before
	public void setUp() {
		controller = mock(DrawingController.class);
		rightToolbar = new RightToolbar(controller);
	}

	@Test
	public void testBtnOuterColorClicked() {
		rightToolbar.getBtnOuterColor().doClick();
		verify(controller).chooseOuterColor();
	}

	@Test
	public void testBtnInnerColorClicked() {
		rightToolbar.getBtnInnerColor().doClick();
		verify(controller).chooseInnerColor();
	}

	@Test
	public void testBtnUndoClicked() {
		rightToolbar.getBtnUndo().setEnabled(true);
		rightToolbar.getBtnUndo().doClick();
		verify(controller).btnUndoClicked();
	}

	@Test
	public void testBtnRedoClicked() {
		rightToolbar.getBtnRedo().setEnabled(true);
		rightToolbar.getBtnRedo().doClick();
		verify(controller).btnRedoClicked();
	}

	@Test
	public void testBtnSendToBackClicked() {
		rightToolbar.getBtnSendToBack().setEnabled(true);
		rightToolbar.getBtnSendToBack().doClick();
		verify(controller).btnSendToBackClicked();
	}

	@Test
	public void testBtnBringToFrontClicked() {
		rightToolbar.getBtnBringToFront().setEnabled(true);
		rightToolbar.getBtnBringToFront().doClick();
		verify(controller).btnBringToFrontClicked();
	}

	@Test
	public void testBtnToBackClicked() {
		rightToolbar.getBtnToBack().setEnabled(true);
		rightToolbar.getBtnToBack().doClick();
		verify(controller).btnToBackClicked();
	}

	@Test
	public void testBtnToFrontClicked() {
		rightToolbar.getBtnToFront().setEnabled(true);
		rightToolbar.getBtnToFront().doClick();
		verify(controller).btnToFrontClicked();
	}

	@Test
	public void testBtnSaveClicked() {
		rightToolbar.getBtnSave().doClick();
		verify(controller).save();
	}

	@Test
	public void testBtnLoadLogClicked() {
		rightToolbar.getBtnLoadLog().setEnabled(true);
		rightToolbar.getBtnLoadLog().doClick();
		verify(controller).loadLog();
	}

	@Test
	public void testBtnLoadPaintingClicked() {
		rightToolbar.getBtnLoadPainting().doClick();
		verify(controller).loadPainting();
	}

	@Test
	public void testBtnNextClicked() {
		rightToolbar.getBtnNext().setEnabled(true);
		rightToolbar.getBtnNext().doClick();
		verify(controller).executeCommandFromLog();
	}
}