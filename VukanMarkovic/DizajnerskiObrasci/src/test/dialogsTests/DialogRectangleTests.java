package test.dialogsTests;

import java.awt.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;
import dialogs.DialogRectangle;

public class DialogRectangleTests {
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
	public void testBtnOuterColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.getBtnOuterColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogRectangle.getOuterColor(), dialogRectangle.getBtnOuterColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnInnerColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.getBtnInnerColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogRectangle.getInnerColor(), dialogRectangle.getBtnInnerColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogRectangle.getXcoordinate().setText(String.valueOf(1));
				dialogRectangle.getYcoordinate().setText(String.valueOf(2));
				dialogRectangle.getBtnOk().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertFalse(dialogRectangle.isAccepted());
				assertFalse(dialogRectangle.isVisible());
			}
		});
	}

	@Test
	public void testBtnOkClicked() {
		dialogRectangle.getXcoordinate().setText(String.valueOf(1));
		dialogRectangle.getYcoordinate().setText(String.valueOf(2));
		dialogRectangle.getwidth().setText(String.valueOf(3));
		dialogRectangle.getheight().setText(String.valueOf(4));
		dialogRectangle.getBtnOk().doClick();
		assertTrue(dialogRectangle.isAccepted());
		assertFalse(dialogRectangle.isVisible());
	}

	@Test
	public void testBtnCancelClicked() {
		dialogRectangleMock.getBtnCancel().doClick();
		verify(dialogRectangleMock).dispose();
	}
}