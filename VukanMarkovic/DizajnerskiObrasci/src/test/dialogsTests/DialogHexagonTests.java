package test.dialogsTests;

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

public class DialogHexagonTests {
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
	public void testBtnOuterColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogHexagon.getBtnOuterColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogHexagon.getOuterColor(), dialogHexagon.getBtnOuterColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnInnerColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogHexagon.getBtnInnerColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogHexagon.getInnerColor(), dialogHexagon.getBtnInnerColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogHexagon.getXcoordinate().setText(String.valueOf(1));
				dialogHexagon.getYcoordinate().setText(String.valueOf(2));
				dialogHexagon.getBtnOk().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertFalse(dialogHexagon.isAccepted());
				assertFalse(dialogHexagon.isVisible());
			}
		});
	}

	@Test
	public void testBtnOkClicked() {
		dialogHexagon.getXcoordinate().setText(String.valueOf(1));
		dialogHexagon.getYcoordinate().setText(String.valueOf(2));
		dialogHexagon.getRadius().setText(String.valueOf(3));
		dialogHexagon.getBtnOk().doClick();
		assertTrue(dialogHexagon.isAccepted());
		assertFalse(dialogHexagon.isVisible());
	}

	@Test
	public void testBtnCancelClicked() {
		dialogHexagonMock.getBtnCancel().doClick();
		verify(dialogHexagonMock).dispose();
	}
}