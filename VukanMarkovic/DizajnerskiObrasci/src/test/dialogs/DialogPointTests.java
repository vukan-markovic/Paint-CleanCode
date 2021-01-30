package dialogs;

import java.awt.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;

public class DialogPointTests {
	private DialogPoint dialogPoint;
	private DialogPoint dialogPointMock;
	private Robot robot;

	@Before
	public void setUp() throws AWTException {
		dialogPoint = new DialogPoint();
		dialogPointMock = spy(DialogPoint.class);
		robot = new Robot();
	}

	@Test
	public void testXcoordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogPoint.setVisible(true);
				dialogPoint.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogPoint.getXcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testXcoordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogPoint.setVisible(true);
				dialogPoint.getXcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogPoint.getXcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testYcoordinateInvalidInput() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogPoint.setVisible(true);
				dialogPoint.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_W);
				assertFalse(dialogPoint.getYcoordinate().getText().contains("W"));
			}
		});
	}

	@Test
	public void testYcoordinate() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogPoint.setVisible(true);
				dialogPoint.getYcoordinate().requestFocusInWindow();
				robot.keyPress(KeyEvent.VK_3);
				assertTrue(dialogPoint.getYcoordinate().getText().contains("3"));
			}
		});
	}

	@Test
	public void testBtnOuterColorClicked() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogPoint.getBtnOuterColor().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertEquals(dialogPoint.getBorderColor(), dialogPoint.getBtnOuterColor().getBackground());
			}
		});
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dialogPoint.getXcoordinate().setText(String.valueOf(1));
				dialogPoint.getBtnOk().doClick();
				robot.keyPress(KeyEvent.VK_ENTER);
				assertTrue(dialogPoint.isAccepted());
				assertFalse(dialogPoint.isVisible());
			}
		});
	}

	@Test
	public void testBtnOkClicked() {
		dialogPoint.getXcoordinate().setText(String.valueOf(1));
		dialogPoint.getYcoordinate().setText(String.valueOf(2));
		dialogPoint.getBtnOk().doClick();
		assertTrue(dialogPoint.isAccepted());
		assertFalse(dialogPoint.isVisible());
	}

	@Test
	public void testBtnCancelClicked() {
		dialogPointMock.getBtnCancel().doClick();
		verify(dialogPointMock).dispose();
	}
}