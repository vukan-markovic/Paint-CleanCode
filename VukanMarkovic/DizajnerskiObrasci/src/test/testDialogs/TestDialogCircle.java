package test.testDialogs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import dialogs.DialogCircle;

public class TestDialogCircle {
	private DialogCircle dialogCircle;
	private DialogCircle dialogCircleMock;
	private Robot robot;

	@Before
	public void setUp() throws AWTException {
		dialogCircle = new DialogCircle();
		dialogCircleMock = spy(DialogCircle.class);
		robot = new Robot();
	}

	@Test
	public void testXcoordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogCircle.setVisible(true);
				dialogCircle.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogCircle.getXcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testXcoordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogCircle.setVisible(true);
				dialogCircle.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogCircle.getXcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testYcoordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogCircle.setVisible(true);
				dialogCircle.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogCircle.getYcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testYcoordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogCircle.setVisible(true);
				dialogCircle.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogCircle.getYcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testRadiusInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogCircle.setVisible(true);
				dialogCircle.getRadius().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogCircle.getRadius().getText().contains("W"));
			}
		});
	}

	@Test
	public void testRadius() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogCircle.setVisible(true);
				dialogCircle.getRadius().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogCircle.getRadius().getText().contains("3"));
			}
		});
	}

	@Test
	public void testBtnSetOuterColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogCircle.getBtnSetOuterColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogCircle.getOuterColor(), dialogCircle.getBtnSetOuterColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnSetInnerColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogCircle.getBtnSetInnerColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogCircle.getInnerColor(), dialogCircle.getBtnSetInnerColor().getBackground());
			}
		});
	}

	@Test
	public void testOKButtonClickedInvalidValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogCircle.getXcoordinate().setText(String.valueOf(1));
				dialogCircle.getYcoordinate().setText(String.valueOf(2));
				dialogCircle.getOkButton().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertFalse(dialogCircle.isAccepted());
				assertFalse(dialogCircle.isVisible());
			}
		});
	}

	@Test
	public void testOKButtonClicked() {
		dialogCircle.getXcoordinate().setText(String.valueOf(1));
		dialogCircle.getYcoordinate().setText(String.valueOf(2));
		dialogCircle.getRadius().setText(String.valueOf(3));
		dialogCircle.getOkButton().doClick();
		assertTrue(dialogCircle.isAccepted());
		assertFalse(dialogCircle.isVisible());
	}

	@Test
	public void testCancelButtonClicked() {
		dialogCircleMock.getCancelButton().doClick();
		verify(dialogCircleMock).dispose();
	}
}