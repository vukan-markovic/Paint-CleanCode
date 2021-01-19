package dialogs;

import java.awt.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;

public class DialogCircleTests {
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
	public void testBtnOuterColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogCircle.getBtnOuterColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogCircle.getOuterColor(), dialogCircle.getBtnOuterColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnInnerColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogCircle.getBtnInnerColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogCircle.getInnerColor(), dialogCircle.getBtnInnerColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogCircle.getXcoordinate().setText(String.valueOf(1));
				dialogCircle.getYcoordinate().setText(String.valueOf(2));
				dialogCircle.getBtnOk().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertFalse(dialogCircle.isAccepted());
				assertFalse(dialogCircle.isVisible());
			}
		});
	}

	@Test
	public void testBtnOkClicked() {
		dialogCircle.getXcoordinate().setText(String.valueOf(1));
		dialogCircle.getYcoordinate().setText(String.valueOf(2));
		dialogCircle.getRadius().setText(String.valueOf(3));
		dialogCircle.getBtnOk().doClick();
		assertTrue(dialogCircle.isAccepted());
		assertFalse(dialogCircle.isVisible());
	}

	@Test
	public void testBtnCancelClicked() {
		dialogCircleMock.getBtnCancel().doClick();
		verify(dialogCircleMock).dispose();
	}
}