package toolbars;

import static org.mockito.Mockito.*;
import org.junit.*;
import controller.DrawingController;
import toolbars.TopToolbar;

public class TopToolbarTests {
	private TopToolbar topToolbar;
	private DrawingController controller;

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