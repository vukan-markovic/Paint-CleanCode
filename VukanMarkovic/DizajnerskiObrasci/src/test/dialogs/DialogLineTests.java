package dialogs;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DialogLineTests {
	private DialogLine dialogLine;
	private DialogLine dialogLineMock;

	@Before
	public void setUp() {
		dialogLine = new DialogLine();
		dialogLineMock = spy(DialogLine.class);
	}

	@Test
	public void testBtnOuterColorClicked() {
		dialogLine.getBtnOuterColor().doClick();
		assertEquals(dialogLine.getBorderColor(), dialogLine.getBtnOuterColor().getBackground());
	}

	@Test
	public void testBtnOKClickedInvalidValues() {
		dialogLine.getXcoordinate().setText("1");
		dialogLine.getYcoordinate().setText("2");
		dialogLine.getYCoordinateOfEndPoint().setText("4");
		dialogLine.getBtnOk().doClick();
		assertFalse(dialogLine.isAccepted());
		assertFalse(dialogLine.isVisible());
	}

	@Test
	public void testBtnOkClicked() {
		dialogLine.getXcoordinate().setText("1");
		dialogLine.getYcoordinate().setText("2");
		dialogLine.getXCoordinateOfEndPoint().setText("3");
		dialogLine.getYCoordinateOfEndPoint().setText("4");
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