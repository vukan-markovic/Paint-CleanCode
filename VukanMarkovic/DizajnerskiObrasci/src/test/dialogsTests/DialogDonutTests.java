package test.dialogsTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;

import dialogs.DialogDonut;

public class DialogDonutTests {
	private DialogDonut dialogDonut;
	private DialogDonut dialogDonutMock;
	private Robot robot;

	@Before
	public void setUp() throws AWTException {
		dialogDonut = new DialogDonut();
		dialogDonutMock = spy(DialogDonut.class);
		robot = new Robot();
	}

	@Test
	public void testXcoordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.setVisible(true);
				dialogDonut.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogDonut.getXcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testXcoordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.setVisible(true);
				dialogDonut.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogDonut.getXcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testYcoordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.setVisible(true);
				dialogDonut.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogDonut.getYcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testYcoordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.setVisible(true);
				dialogDonut.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogDonut.getYcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testOuterRadiusInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.setVisible(true);
				dialogDonut.getOuterRadius().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogDonut.getOuterRadius().getText().contains("W"));
			}
		});
	}

	@Test
	public void testOuterRadius() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.setVisible(true);
				dialogDonut.getOuterRadius().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogDonut.getOuterRadius().getText().contains("3"));
			}
		});
	}

	@Test
	public void testInnerRadiusInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.setVisible(true);
				dialogDonut.getInnerRadius().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogDonut.getInnerRadius().getText().contains("W"));
			}
		});
	}

	@Test
	public void testInnerRadius() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.setVisible(true);
				dialogDonut.getInnerRadius().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogDonut.getInnerRadius().getText().contains("3"));
			}
		});
	}

	@Test
	public void testBtnOuterColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.getBtnOuterColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogDonut.getOuterColor(), dialogDonut.getBtnOuterColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnInnerColorClicked() throws AWTException {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.getBtnInnerColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogDonut.getInnerColor(), dialogDonut.getBtnInnerColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.getXcoordinate().setText(String.valueOf(1));
				dialogDonut.getYcoordinate().setText(String.valueOf(2));
				dialogDonut.getOuterRadius().setText(String.valueOf(3));
				dialogDonut.getBtnOk().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertFalse(dialogDonut.isAccepted());
				assertFalse(dialogDonut.isVisible());
			}
		});
	}

	@Test
	public void testBtnOkClickedInvalidRadiusValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.getXcoordinate().setText(String.valueOf(1));
				dialogDonut.getYcoordinate().setText(String.valueOf(2));
				dialogDonut.getOuterRadius().setText(String.valueOf(3));
				dialogDonut.getInnerRadius().setText(String.valueOf(4));
				dialogDonut.getBtnOk().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertFalse(dialogDonut.isAccepted());
				assertFalse(dialogDonut.isVisible());
			}
		});
	}

	@Test
	public void testBtnOkClicked() {
		dialogDonut.getXcoordinate().setText(String.valueOf(1));
		dialogDonut.getYcoordinate().setText(String.valueOf(2));
		dialogDonut.getOuterRadius().setText(String.valueOf(4));
		dialogDonut.getInnerRadius().setText(String.valueOf(3));
		dialogDonut.getBtnOk().doClick();
		assertTrue(dialogDonut.isAccepted());
		assertFalse(dialogDonut.isVisible());
	}

	@Test
	public void testBtnCancelClicked() {
		dialogDonutMock.getBtnCancel().doClick();
		verify(dialogDonutMock).dispose();
	}
}