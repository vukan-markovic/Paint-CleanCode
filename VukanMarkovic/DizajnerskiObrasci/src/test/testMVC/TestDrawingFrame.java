package test.testMVC;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import mvc.DrawingController;
import mvc.DrawingFrame;

public class TestDrawingFrame {
	private DrawingFrame drawingFrame;
	private DrawingController drawingController;
	
	@Before 
	public void setUp() {
		drawingController = mock(DrawingController.class);
		drawingFrame = new DrawingFrame();
		drawingFrame.setController(drawingController);
	}
	
	@Test 
	public void testViewClicked() {
		
	}
	
	@Test 
	public void testBtnModifyClicked() {
		drawingFrame.getBtnModify().setEnabled(true);
		drawingFrame.getBtnModify().doClick();
		verify(drawingController).modify();
	}
	
	@Test 
	public void testBtnDeleteClicked() {
		drawingFrame.getBtnDelete().setEnabled(true);
		drawingFrame.getBtnDelete().doClick();
		verify(drawingController).delete();
	}
	
	@Test 
	public void testBtnOuterColorClicked() {
		drawingFrame.getBtnOuterCol().doClick();
		verify(drawingController).outerColor();
	}
	
	@Test 
	public void testBtnInnerColorClicked() {
		drawingFrame.getBtnInnerCol().doClick();
		verify(drawingController).innerColor();
	}
	
	@Test 
	public void testBtnUndoClicked() {
		drawingFrame.getBtnUndo().setEnabled(true);
		drawingFrame.getBtnUndo().doClick();
		verify(drawingController).undo();
	}
	
	@Test 
	public void testBtnRedoClicked() {
		drawingFrame.getBtnRedo().setEnabled(true);
		drawingFrame.getBtnRedo().doClick();
		verify(drawingController).redo();
	}
	
	@Test 
	public void testBtnSendToBackClicked() {
		drawingFrame.getBtnSendToBack().setEnabled(true);
		drawingFrame.getBtnSendToBack().doClick();
		verify(drawingController).sendToBack();
	}
	
	@Test 
	public void testBtnBringToFrontClicked() {
		drawingFrame.getBtnBringToFront().setEnabled(true);
		drawingFrame.getBtnBringToFront().doClick();
		verify(drawingController).bringToFront();
	}
	
	@Test 
	public void testBtnToBackClicked() {
		drawingFrame.getBtnToBack().setEnabled(true);
		drawingFrame.getBtnToBack().doClick();
		verify(drawingController).toBack();
	}
	
	@Test 
	public void testBtnToFrontClicked() {
		drawingFrame.getBtnToFront().setEnabled(true);
		drawingFrame.getBtnToFront().doClick();
		verify(drawingController).toFront();
	}
	
	@Test 
	public void testBtnSaveClicked() {
		drawingFrame.getBtnSave().doClick();
		verify(drawingController).save();
	}
	
	@Test 
	public void testBtnLoadLogClicked() {
		drawingFrame.getBtnLoadLog().setEnabled(true);
		drawingFrame.getBtnLoadLog().doClick();
		verify(drawingController).loadLog();
	}
	
	@Test 
	public void testBtnLoadPaintingClicked() {
		drawingFrame.getBtnLoadPainting().doClick();
		verify(drawingController).loadPainting();
	}
	
	@Test 
	public void testBtnNextClicked() {
		drawingFrame.getBtnNext().setEnabled(true);
		drawingFrame.getBtnNext().doClick();
		verify(drawingController).nextCommand();
	}
}