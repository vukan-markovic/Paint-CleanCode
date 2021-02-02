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
		topToolbar.setDrawingController(controller);
	}

	@Test
	public void testBtnModifyClicked() {
		topToolbar.getBtnModify().setEnabled(true);
		topToolbar.getBtnModify().doClick();
		verify(controller).modifyShapeIfAccepted();
	}

	@Test
	public void testBtnRemoveClicked() {
		topToolbar.getBtnRemove().setEnabled(true);
		topToolbar.getBtnRemove().doClick();
		verify(controller).removeShapesIfUserConfirm();
	}
}