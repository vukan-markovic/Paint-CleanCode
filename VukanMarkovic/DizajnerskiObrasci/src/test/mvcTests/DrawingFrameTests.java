package test.mvcTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class DrawingFrameTests {
	private DrawingFrame frame;
	private DrawingController controller;
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

	@Test
	public void testBtnModifyClicked() {
		frame.getBtnModify().setEnabled(true);
		frame.getBtnModify().doClick();
		verify(controller).btnModifyClicked();
	}

	@Test
	public void testBtnDeleteClicked() {
		frame.getBtnDelete().setEnabled(true);
		frame.getBtnDelete().doClick();
		verify(controller).btnRemoveClicked();
	}

	@Test
	public void testBtnOuterColorClicked() {
		frame.getBtnOuterColor().doClick();
		verify(controller).chooseOuterColor();
	}

	@Test
	public void testBtnInnerColorClicked() {
		frame.getBtnInnerColor().doClick();
		verify(controller).chooseInnerColor();
	}

	@Test
	public void testBtnUndoClicked() {
		frame.getBtnUndo().setEnabled(true);
		frame.getBtnUndo().doClick();
		verify(controller).btnUndoClicked();
	}

	@Test
	public void testBtnRedoClicked() {
		frame.getBtnRedo().setEnabled(true);
		frame.getBtnRedo().doClick();
		verify(controller).btnRedoClicked();
	}

	@Test
	public void testBtnSendToBackClicked() {
		frame.getBtnSendToBack().setEnabled(true);
		frame.getBtnSendToBack().doClick();
		verify(controller).btnSendToBackClicked();
	}

	@Test
	public void testBtnBringToFrontClicked() {
		frame.getBtnBringToFront().setEnabled(true);
		frame.getBtnBringToFront().doClick();
		verify(controller).btnBringToFrontClicked();
	}

	@Test
	public void testBtnToBackClicked() {
		frame.getBtnToBack().setEnabled(true);
		frame.getBtnToBack().doClick();
		verify(controller).btnToBackClicked();
	}

	@Test
	public void testBtnToFrontClicked() {
		frame.getBtnToFront().setEnabled(true);
		frame.getBtnToFront().doClick();
		verify(controller).btnToFrontClicked();
	}

	@Test
	public void testBtnSaveClicked() {
		frame.getBtnSave().doClick();
		verify(controller).save();
	}

	@Test
	public void testBtnLoadLogClicked() {
		frame.getBtnLoadLog().setEnabled(true);
		frame.getBtnLoadLog().doClick();
		verify(controller).loadLog();
	}

	@Test
	public void testBtnLoadPaintingClicked() {
		frame.getBtnLoadPainting().doClick();
		verify(controller).loadPainting();
	}

	@Test
	public void testBtnNextClicked() {
		frame.getBtnNext().setEnabled(true);
		frame.getBtnNext().doClick();
		verify(controller).executeCommandFromLog();
	}
}