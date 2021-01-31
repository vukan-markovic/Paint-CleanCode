package dialogs;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DialogCircleTests {
	private DialogCircle dialogCircle;
	private DialogCircle dialogCircleMock;

	@Before
	public void setUp() {
		dialogCircle = new DialogCircle();
		dialogCircleMock = spy(DialogCircle.class);
	}

	@Test
	public void testBtnBorderColorClicked() {
		dialogCircle.getBtnBorderColor().doClick();
		assertEquals(dialogCircle.getBorderColor(), dialogCircle.getBtnBorderColor().getBackground());
	}

	@Test
	public void testBtnFillColorClicked() {
		dialogCircle.getBtnFillColor().doClick();
		assertEquals(dialogCircle.getFillColor(), dialogCircle.getBtnFillColor().getBackground());
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		dialogCircle.getXcoordinate().setText("1");
		dialogCircle.getYcoordinate().setText("2");
		dialogCircle.getBtnOk().doClick();
		assertFalse(dialogCircle.isAccepted());
		assertFalse(dialogCircle.isVisible());
	}

	@Test
	public void testBtnOkClickedRadiusZero() {
		dialogCircle.getXcoordinate().setText("1");
		dialogCircle.getYcoordinate().setText("2");
		dialogCircle.getRadius().setText("0");
		dialogCircle.getBtnOk().doClick();
		assertFalse(dialogCircle.isAccepted());
		assertFalse(dialogCircle.isVisible());
	}

	@Test
	public void testBtnOkClicked() {
		dialogCircle.getXcoordinate().setText("1");
		dialogCircle.getYcoordinate().setText("2");
		dialogCircle.getRadius().setText("3");
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