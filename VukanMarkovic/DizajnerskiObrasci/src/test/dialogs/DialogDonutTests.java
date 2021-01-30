package dialogs;

import java.awt.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DialogDonutTests {
	private DialogDonut dialogDonut;
	private DialogDonut dialogDonutMock;

	@Before
	public void setUp() {
		dialogDonut = new DialogDonut();
		dialogDonutMock = spy(DialogDonut.class);
	}

	@Test
	public void testBtnOuterColorClicked() {
		dialogDonut.getBtnOuterColor().doClick();
		assertEquals(dialogDonut.getBorderColor(), dialogDonut.getBtnOuterColor().getBackground());
	}

	@Test
	public void testBtnInnerColorClicked() throws AWTException {
		dialogDonut.getBtnFillColor().doClick();
		assertEquals(dialogDonut.getFillColor(), dialogDonut.getBtnFillColor().getBackground());
	}

	@Test
	public void testBtnOkClickedInvalidValues() {
		dialogDonut.getXcoordinate().setText("1");
		dialogDonut.getYcoordinate().setText("2");
		dialogDonut.getRadius().setText("3");
		dialogDonut.getBtnOk().doClick();
		assertFalse(dialogDonut.isAccepted());
		assertFalse(dialogDonut.isVisible());
	}

	@Test
	public void testBtnOkClickedInvalidRadiusValues() {
		dialogDonut.getXcoordinate().setText("1");
		dialogDonut.getYcoordinate().setText("2");
		dialogDonut.getRadius().setText("3");
		dialogDonut.getInnerRadius().setText("4");
		dialogDonut.getBtnOk().doClick();
		assertFalse(dialogDonut.isAccepted());
		assertFalse(dialogDonut.isVisible());
	}

	@Test
	public void testBtnOkClicked() {
		dialogDonut.getXcoordinate().setText("1");
		dialogDonut.getYcoordinate().setText("2");
		dialogDonut.getRadius().setText("4");
		dialogDonut.getInnerRadius().setText("3");
		dialogDonut.getBtnOk().doClick();
		assertTrue(dialogDonut.isAccepted());
		assertFalse(dialogDonut.isVisible());
	}

	@Test
	public void testBtnCancelClicked() {
		dialogDonutMock.getBtnCancel().doClick();
		verify(dialogDonutMock).dispose();
	}
}