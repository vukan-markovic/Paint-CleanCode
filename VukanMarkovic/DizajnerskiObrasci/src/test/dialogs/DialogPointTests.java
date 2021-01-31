package dialogs;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DialogPointTests {
	private DialogPoint dialogPoint;
	private DialogPoint dialogPointMock;

	@Before
	public void setUp() {
		dialogPoint = new DialogPoint();
		dialogPointMock = spy(DialogPoint.class);
	}

	@Test
	public void testBtnBorderColorClicked() {
		dialogPoint.getBtnBorderColor().doClick();
		assertEquals(dialogPoint.getBorderColor(), dialogPoint.getBtnBorderColor().getBackground());
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		dialogPoint.getXcoordinate().setText("1");
		dialogPoint.getBtnOk().doClick();
		assertFalse(dialogPoint.isAccepted());
		assertFalse(dialogPoint.isVisible());
	}

	@Test
	public void testBtnOkClicked() {
		dialogPoint.getXcoordinate().setText("1");
		dialogPoint.getYcoordinate().setText("2");
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