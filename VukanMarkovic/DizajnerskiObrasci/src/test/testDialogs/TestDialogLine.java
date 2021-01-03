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

import dialogs.DialogLine;

public class TestDialogLine {
	private DialogLine dialogLine;
	private DialogLine dialogLineMock;
	private Robot robot;

	@Before
	public void setUp() throws AWTException {
		dialogLine = new DialogLine();
		dialogLineMock = spy(DialogLine.class);
		robot = new Robot();
	}

	@Test
	public void testX1coordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getX1coordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogLine.getX1coordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testX1coordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getX1coordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogLine.getX1coordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testY1coordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getY1coordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogLine.getY1coordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testY1coordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getY1coordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogLine.getY1coordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testX2coordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getX2coordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogLine.getX2coordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testX2coordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getX2coordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogLine.getX2coordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testY2coordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getY2coordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogLine.getY2coordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testY2coordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getY2coordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogLine.getY2coordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testBtnSetOuterColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.getBtnSetOuterColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogLine.getOuterColor(), dialogLine.getBtnSetOuterColor().getBackground());
			}
		});
	}

	@Test
	public void testOKButtonClickedInvalidValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.getX1coordinate().setText(String.valueOf(1));
				dialogLine.getY1coordinate().setText(String.valueOf(2));
				dialogLine.getY2coordinate().setText(String.valueOf(4));
				dialogLine.getOkButton().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertTrue(dialogLine.isAccepted());
				assertFalse(dialogLine.isVisible());
			}
		});
	}

	@Test
	public void testOKButtonClicked() {
		dialogLine.getX1coordinate().setText(String.valueOf(1));
		dialogLine.getY1coordinate().setText(String.valueOf(2));
		dialogLine.getX2coordinate().setText(String.valueOf(3));
		dialogLine.getY2coordinate().setText(String.valueOf(4));
		dialogLine.getOkButton().doClick();
		assertTrue(dialogLine.isAccepted());
		assertFalse(dialogLine.isVisible());
	}

	@Test
	public void testCancelButtonClicked() {
		dialogLineMock.getCancelButton().doClick();
		verify(dialogLineMock).dispose();
	}
}