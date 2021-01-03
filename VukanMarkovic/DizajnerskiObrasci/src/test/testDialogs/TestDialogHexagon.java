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

import dialogs.DialogHexagon;

public class TestDialogHexagon {
	private DialogHexagon dialogHexagon;
	private DialogHexagon dialogHexagonMock;
	private Robot robot;

	@Before
	public void setUp() throws AWTException {
		dialogHexagon = new DialogHexagon();
		dialogHexagonMock = spy(DialogHexagon.class);
		robot = new Robot();
	}

	@Test
	public void testXcoordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogHexagon.setVisible(true);
				dialogHexagon.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogHexagon.getXcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testXcoordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogHexagon.setVisible(true);
				dialogHexagon.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogHexagon.getXcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testYcoordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogHexagon.setVisible(true);
				dialogHexagon.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogHexagon.getYcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testYcoordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogHexagon.setVisible(true);
				dialogHexagon.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogHexagon.getYcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testRadiusInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogHexagon.setVisible(true);
				dialogHexagon.getRadius().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogHexagon.getRadius().getText().contains("W"));
			}
		});
	}

	@Test
	public void testRadius() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogHexagon.setVisible(true);
				dialogHexagon.getRadius().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogHexagon.getRadius().getText().contains("3"));
			}
		});
	}

	@Test
	public void testBtnSetOuterColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogHexagon.getBtnSetOuterColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogHexagon.getOuterColor(), dialogHexagon.getBtnSetOuterColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnSetInnerColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogHexagon.getBtnSetInnerColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogHexagon.getInnerColor(), dialogHexagon.getBtnSetInnerColor().getBackground());
			}
		});
	}

	@Test
	public void testOKButtonClickedInvalidValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogHexagon.getXcoordinate().setText(String.valueOf(1));
				dialogHexagon.getYcoordinate().setText(String.valueOf(2));
				dialogHexagon.getOkButton().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertFalse(dialogHexagon.isAccepted());
				assertFalse(dialogHexagon.isVisible());
			}
		});
	}

	@Test
	public void testOKButtonClicked() {
		dialogHexagon.getXcoordinate().setText(String.valueOf(1));
		dialogHexagon.getYcoordinate().setText(String.valueOf(2));
		dialogHexagon.getRadius().setText(String.valueOf(3));
		dialogHexagon.getOkButton().doClick();
		assertTrue(dialogHexagon.isAccepted());
		assertFalse(dialogHexagon.isVisible());
	}

	@Test
	public void testCancelButtonClicked() {
		dialogHexagonMock.getCancelButton().doClick();
		verify(dialogHexagonMock).dispose();
	}
}