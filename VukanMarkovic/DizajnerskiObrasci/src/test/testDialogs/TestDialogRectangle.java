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

import dialogs.DialogRectangle;

public class TestDialogRectangle {
	private DialogRectangle dialogRectangle;
	private DialogRectangle dialogRectangleMock;
	private Robot robot;

	@Before
	public void setUp() throws AWTException {
		dialogRectangle = new DialogRectangle();
		dialogRectangleMock = spy(DialogRectangle.class);
		robot = new Robot();
	}

	@Test
	public void testXcoordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.setVisible(true);
				dialogRectangle.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogRectangle.getXcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testXcoordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.setVisible(true);
				dialogRectangle.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogRectangle.getXcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testYcoordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.setVisible(true);
				dialogRectangle.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogRectangle.getYcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testYcoordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.setVisible(true);
				dialogRectangle.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogRectangle.getYcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testWidthInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.setVisible(true);
				dialogRectangle.getwidth().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogRectangle.getwidth().getText().contains("W"));
			}
		});
	}

	@Test
	public void testWidth() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.setVisible(true);
				dialogRectangle.getwidth().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertFalse(dialogRectangle.getwidth().getText().contains("3"));
			}
		});
	}

	@Test
	public void testHeightInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.setVisible(true);
				dialogRectangle.getheight().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogRectangle.getheight().getText().contains("W"));
			}
		});
	}

	@Test
	public void testHeight() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.setVisible(true);
				dialogRectangle.getheight().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertFalse(dialogRectangle.getheight().getText().contains("3"));
			}
		});
	}

	@Test
	public void testBtnSetOuterColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.getBtnSetBorderColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogRectangle.getOuterColor(), dialogRectangle.getBtnSetBorderColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnSetInnerColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.getBtnSetFillColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogRectangle.getInnerColor(), dialogRectangle.getBtnSetFillColor().getBackground());
			}
		});
	}

	@Test
	public void testOKButtonClickedInvalidValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.getXcoordinate().setText(String.valueOf(1));
				dialogRectangle.getYcoordinate().setText(String.valueOf(2));
				dialogRectangle.getOkButton().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertFalse(dialogRectangle.isAccepted());
				assertFalse(dialogRectangle.isVisible());
			}
		});
	}

	@Test
	public void testOKButtonClicked() {
		dialogRectangle.getXcoordinate().setText(String.valueOf(1));
		dialogRectangle.getYcoordinate().setText(String.valueOf(2));
		dialogRectangle.getwidth().setText(String.valueOf(3));
		dialogRectangle.getheight().setText(String.valueOf(4));
		dialogRectangle.getOkButton().doClick();
		assertTrue(dialogRectangle.isAccepted());
		assertFalse(dialogRectangle.isVisible());
	}

	@Test
	public void testCancelButtonClicked() {
		dialogRectangleMock.getCancelButton().doClick();
		verify(dialogRectangleMock).dispose();
	}
}