package toolbars;

import org.junit.*;
import static org.mockito.Mockito.*;
import controller.DrawingController;

public class TopToolbarTests {
	private DrawingController controller;
	private TopToolbar topToolbar;

	@Before
	public void setUp() {
		controller = mock(DrawingController.class);
		topToolbar = new TopToolbar();
		topToolbar.setController(controller);
	}

	@Test
	public void testBtnModifyClicked() {
		topToolbar.getBtnModify().setEnabled(true);
		topToolbar.getBtnModify().doClick();
		verify(controller).modifyShape();
	}

	@Test
	public void testBtnDeleteClicked() {
		topToolbar.getBtnDelete().setEnabled(true);
		topToolbar.getBtnDelete().doClick();
		verify(controller).removeShapesIfUserConfirm();
	}
}