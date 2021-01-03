package test.testDialogs;

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

public class TestDialogDonut {
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
	public void testRadiusInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.setVisible(true);
				dialogDonut.getRadius().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogDonut.getRadius().getText().contains("W"));
			}
		});
	}

	@Test
	public void testRadius() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.setVisible(true);
				dialogDonut.getRadius().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogDonut.getRadius().getText().contains("3"));
			}
		});
	}

	@Test
	public void testSmallRadiusInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.setVisible(true);
				dialogDonut.getSmallRadius().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogDonut.getSmallRadius().getText().contains("W"));
			}
		});
	}

	@Test
	public void testSmallRadius() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.setVisible(true);
				dialogDonut.getSmallRadius().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogDonut.getSmallRadius().getText().contains("3"));
			}
		});
	}

	@Test
	public void testBtnSetOuterColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.getBtnSetOuterColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogDonut.getOuterColor(), dialogDonut.getBtnSetOuterColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnSetInnerColorClicked() throws AWTException {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.getBtnSetInnerColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogDonut.getInnerColor(), dialogDonut.getBtnSetInnerColor().getBackground());
			}
		});
	}

	@Test
	public void testOKButtonClickedInvalidValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.getXcoordinate().setText(String.valueOf(1));
				dialogDonut.getYcoordinate().setText(String.valueOf(2));
				dialogDonut.getRadius().setText(String.valueOf(3));
				dialogDonut.getOkButton().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertFalse(dialogDonut.isAccepted());
				assertFalse(dialogDonut.isVisible());
			}
		});
	}

	@Test
	public void testOKButtonClickedInvalidRadiusValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogDonut.getXcoordinate().setText(String.valueOf(1));
				dialogDonut.getYcoordinate().setText(String.valueOf(2));
				dialogDonut.getRadius().setText(String.valueOf(3));
				dialogDonut.getSmallRadius().setText(String.valueOf(4));
				dialogDonut.getOkButton().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertFalse(dialogDonut.isAccepted());
				assertFalse(dialogDonut.isVisible());
			}
		});
	}

	@Test
	public void testOKButtonClicked() {
		dialogDonut.getXcoordinate().setText(String.valueOf(1));
		dialogDonut.getYcoordinate().setText(String.valueOf(2));
		dialogDonut.getRadius().setText(String.valueOf(4));
		dialogDonut.getSmallRadius().setText(String.valueOf(3));
		dialogDonut.getOkButton().doClick();
		assertTrue(dialogDonut.isAccepted());
		assertFalse(dialogDonut.isVisible());
	}

	@Test
	public void testCancelButtonClicked() {
		dialogDonutMock.getCancelButton().doClick();
		verify(dialogDonutMock).dispose();
	}
}