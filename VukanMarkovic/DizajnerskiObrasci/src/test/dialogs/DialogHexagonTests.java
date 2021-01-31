package dialogs;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DialogHexagonTests {
	private DialogHexagon dialogHexagon;
	private DialogHexagon dialogHexagonMock;

	@Before
	public void setUp() {
		dialogHexagon = new DialogHexagon();
		dialogHexagonMock = spy(DialogHexagon.class);
	}

	@Test
	public void testBtnBorderColorClicked() {
		dialogHexagon.getBtnBorderColor().doClick();
		assertEquals(dialogHexagon.getBorderColor(), dialogHexagon.getBtnBorderColor().getBackground());
	}

	@Test
	public void testBtnFillColorClicked() {
		dialogHexagon.getBtnFillColor().doClick();
		assertEquals(dialogHexagon.getFillColor(), dialogHexagon.getBtnFillColor().getBackground());
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		dialogHexagon.getXcoordinate().setText("1");
		dialogHexagon.getYcoordinate().setText("2");
		dialogHexagon.getBtnOk().doClick();
		assertFalse(dialogHexagon.isAccepted());
		assertFalse(dialogHexagon.isVisible());
	}

	@Test
	public void testBtnOkClicked() {
		dialogHexagon.getXcoordinate().setText("1");
		dialogHexagon.getYcoordinate().setText("2");
		dialogHexagon.getRadius().setText("3");
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