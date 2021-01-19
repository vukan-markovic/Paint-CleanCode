package dialogs;

import java.awt.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;

public class DialogLineTests {
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
	public void testXcoordinateOfStartPointInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogLine.getXcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testXcoordinateOfStartPoint() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogLine.getXcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testYcoordinateOfStartPointInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogLine.getYcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testYcoordinateOfStartPoint() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogLine.getYcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testXcoordinateOfEndPointInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getXCoordinateOfEndPoint().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogLine.getXCoordinateOfEndPoint().getText().contains("W"));
			}
		});
	}

	@Test
	public void testXcoordinateOfEndPoint() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getXCoordinateOfEndPoint().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogLine.getXCoordinateOfEndPoint().getText().contains("3"));
			}
		});
	}

	@Test
	public void testYcoordinateOfEndPointInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getYCoordinateOfEndPoint().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogLine.getYCoordinateOfEndPoint().getText().contains("W"));
			}
		});
	}

	@Test
	public void testYcoordinateOfEndPoint() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.setVisible(true);
				dialogLine.getYCoordinateOfEndPoint().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogLine.getYCoordinateOfEndPoint().getText().contains("3"));
			}
		});
	}

	@Test
	public void testBtnOuterColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.getBtnOuterColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogLine.getOuterColor(), dialogLine.getBtnOuterColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnOKClickedInvalidValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogLine.getXcoordinate().setText(String.valueOf(1));
				dialogLine.getYcoordinate().setText(String.valueOf(2));
				dialogLine.getYCoordinateOfEndPoint().setText(String.valueOf(4));
				dialogLine.getBtnOk().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertTrue(dialogLine.isAccepted());
				assertFalse(dialogLine.isVisible());
			}
		});
	}

	@Test
	public void testBtnOkClicked() {
		dialogLine.getXcoordinate().setText(String.valueOf(1));
		dialogLine.getYcoordinate().setText(String.valueOf(2));
		dialogLine.getXCoordinateOfEndPoint().setText(String.valueOf(3));
		dialogLine.getYCoordinateOfEndPoint().setText(String.valueOf(4));
		dialogLine.getBtnOk().doClick();
		assertTrue(dialogLine.isAccepted());
		assertFalse(dialogLine.isVisible());
	}

	@Test
	public void testBtnCancelClicked() {
		dialogLineMock.getBtnCancel().doClick();
		verify(dialogLineMock).dispose();
	}
}