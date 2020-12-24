package test.testMVC;

import org.junit.Before;
import org.junit.Test;

import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class TestDrawingController {
	private DrawingController drawingController;
	private DrawingModel drawingModel;
	private DrawingFrame drawingFrame;
	
	@Before 
	public void setUp() {
		drawingModel = new DrawingModel();
		drawingFrame = new DrawingFrame();
		drawingController = new DrawingController(drawingModel, drawingFrame);
	}
	
	@Test 
	public void testMouseClicked() {
		drawingController.mouseClicked(null);
	}
	
	@Test 
	public void testModify() {
		drawingController.modify();
	}
	
	@Test 
	public void testDelete() {
		drawingController.delete();
	}
	
	@Test 
	public void testDeleteForLogs() {
		drawingController.deleteForLogs();
	}
	
	@Test 
	public void testUndo() {
		drawingController.undo();
	}
	
	@Test 
	public void testRedo() {
		drawingController.redo();
	}
	
	@Test 
	public void testRedoForLog() {
		drawingController.redoForLog();
	}
	
	@Test 
	public void testOuterColor() {
		drawingController.outerColor();
	}
	
	@Test 
	public void testInnerColor() {
		drawingController.innerColor();
	}	
	
	@Test 
	public void testToBack() {
		drawingController.toBack();
	}
	
	@Test 
	public void testToFront() {
		drawingController.toFront();
	}
	
	@Test 
	public void testSendToBack() {
		drawingController.sendToBack();
	}
	
	@Test 
	public void testBringToFront() {
		drawingController.bringToFront();
	}
	
	@Test 
	public void testFireEvents() {
		drawingController.fireEvents();
	}
	
	@Test 
	public void testFireUndoRedo() {
		drawingController.fireUndoRedo();
	}
	
	@Test 
	public void testSave() {
		drawingController.save();
	}
	
	@Test 
	public void testLoadLog() {
		drawingController.loadLog();
	}
	
	@Test 
	public void testLoadPainting() {
		drawingController.loadPainting();
	}
	
	@Test 
	public void testNextCommand() {
		drawingController.nextCommand();
	}
}