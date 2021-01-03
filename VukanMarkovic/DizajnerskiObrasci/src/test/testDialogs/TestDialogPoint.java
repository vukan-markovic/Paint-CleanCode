package test.testDialogs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;

import dialogs.DialogPoint;

public class TestDialogPoint {
	private DialogPoint dialogPoint;
	private DialogPoint dialogPointMock;
	private Robot robot;

	@Before
	public void setUp() throws AWTException {
		dialogPoint = new DialogPoint();
		dialogPointMock = spy(DialogPoint.class);
		robot = new Robot();
	}

	@Test
	public void testXcoordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogPoint.setVisible(true);
				dialogPoint.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogPoint.getXcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testXcoordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogPoint.setVisible(true);
				dialogPoint.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogPoint.getXcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testYcoordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogPoint.setVisible(true);
				dialogPoint.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogPoint.getYcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testYcoordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogPoint.setVisible(true);
				dialogPoint.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogPoint.getYcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testBtnSetOuterColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogPoint.getBtnSetOuterColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogPoint.getOuterColor(), dialogPoint.getBtnSetOuterColor().getBackground());
			}
		});
	}

	@Test
	public void testOKButtonClickedInvalidValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogPoint.getXcoordinate().setText(String.valueOf(1));
				dialogPoint.getOkButton().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertTrue(dialogPoint.isAccepted());
				assertFalse(dialogPoint.isVisible());
			}
		});
	}

	@Test
	public void testOKButtonClicked() {
		dialogPoint.getXcoordinate().setText(String.valueOf(1));
		dialogPoint.getYcoordinate().setText(String.valueOf(2));
		dialogPoint.getOkButton().doClick();
		assertTrue(dialogPoint.isAccepted());
		assertFalse(dialogPoint.isVisible());
	}

	@Test
	public void testCancelButtonClicked() {
		dialogPointMock.getCancelButton().doClick();
		verify(dialogPointMock).dispose();
	}
}