package dialogs;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DialogRectangleTests {
	private DialogRectangle dialogRectangle;
	private DialogRectangle dialogRectangleMock;

	@Before
	public void setUp() {
		dialogRectangle = new DialogRectangle();
		dialogRectangleMock = spy(DialogRectangle.class);
	}

	@Test
	public void testBtnBorderColorClicked() {
		dialogRectangle.getBtnBorderColor().doClick();
		assertEquals(dialogRectangle.getBorderColor(), dialogRectangle.getBtnBorderColor().getBackground());
	}

	@Test
	public void testBtnFillColorClicked() {
		dialogRectangle.getBtnFillColor().doClick();
		assertEquals(dialogRectangle.getFillColor(), dialogRectangle.getBtnFillColor().getBackground());
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		dialogRectangle.getXcoordinate().setText("1");
		dialogRectangle.getYcoordinate().setText("2");
		dialogRectangle.getBtnOk().doClick();
		assertFalse(dialogRectangle.isAccepted());
		assertFalse(dialogRectangle.isVisible());
	}

	@Test
	public void testBtnOkClickedWidthIsZero() {
		dialogRectangle.getXcoordinate().setText("1");
		dialogRectangle.getYcoordinate().setText("2");
		dialogRectangle.getwidth().setText("0");
		dialogRectangle.getheight().setText("4");
		dialogRectangle.getBtnOk().doClick();
		assertFalse(dialogRectangle.isAccepted());
		assertFalse(dialogRectangle.isVisible());
	}

	@Test
	public void testBtnOkClickedHeightIsZero() {
		dialogRectangle.getXcoordinate().setText("1");
		dialogRectangle.getYcoordinate().setText("2");
		dialogRectangle.getwidth().setText("3");
		dialogRectangle.getheight().setText("0");
		dialogRectangle.getBtnOk().doClick();
		assertFalse(dialogRectangle.isAccepted());
		assertFalse(dialogRectangle.isVisible());
	}

	@Test
	public void testBtnOkClicked() {
		dialogRectangle.getXcoordinate().setText("1");
		dialogRectangle.getYcoordinate().setText("2");
		dialogRectangle.getwidth().setText("3");
		dialogRectangle.getheight().setText("4");
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