package toolbars;

import static org.mockito.Mockito.*;
import org.junit.*;
import controller.*;

public class RightToolbarTests {
	private OptionsController optionsController;
	private FileController fileController;
	private RightToolbar rightToolbar;

	@Before
	public void setUp() {
		optionsController = mock(OptionsController.class);
		fileController = mock(FileController.class);
		rightToolbar = new RightToolbar();
		rightToolbar.setFileController(fileController);
		rightToolbar.setOptionsController(optionsController);
	}

	@Test
	public void testBtnOuterColorClicked() {
		rightToolbar.getBtnBorderColor().doClick();
		verify(optionsController).setBorderColor();
	}

	@Test
	public void testBtnInnerColorClicked() {
		rightToolbar.getBtnFillColor().doClick();
		verify(optionsController).setFillColor();
	}

	@Test
	public void testBtnUndoClicked() {
		rightToolbar.getBtnUndo().setEnabled(true);
		rightToolbar.getBtnUndo().doClick();
		verify(optionsController).undoCommand();
	}

	@Test
	public void testBtnRedoClicked() {
		rightToolbar.getBtnRedo().setEnabled(true);
		rightToolbar.getBtnRedo().doClick();
		verify(optionsController).redoCommand();
	}

	@Test
	public void testBtnSendToBackClicked() {
		rightToolbar.getBtnSendToBack().setEnabled(true);
		rightToolbar.getBtnSendToBack().doClick();
		verify(optionsController).bringShapeToBack();
	}

	@Test
	public void testBtnBringToFrontClicked() {
		rightToolbar.getBtnBringToFront().setEnabled(true);
		rightToolbar.getBtnBringToFront().doClick();
		verify(optionsController).bringShapeToFront();
	}

	@Test
	public void testBtnToBackClicked() {
		rightToolbar.getBtnToBack().setEnabled(true);
		rightToolbar.getBtnToBack().doClick();
		verify(optionsController).moveShapeToBack();
	}

	@Test
	public void testBtnToFrontClicked() {
		rightToolbar.getBtnToFront().setEnabled(true);
		rightToolbar.getBtnToFront().doClick();
		verify(optionsController).moveShapeToFront();
	}

	@Test
	public void testBtnSaveClicked() {
		rightToolbar.getBtnSave().doClick();
		verify(fileController).save();
	}

	@Test
	public void testBtnLoadLogClicked() {
		rightToolbar.getBtnLoadLog().setEnabled(true);
		rightToolbar.getBtnLoadLog().doClick();
		verify(fileController).loadLog();
	}

	@Test
	public void testBtnLoadPaintingClicked() {
		rightToolbar.getBtnLoadDrawing().doClick();
		verify(fileController).loadDrawing();
	}

	@Test
	public void testBtnNextClicked() {
		rightToolbar.getBtnNextCommand().setEnabled(true);
		rightToolbar.getBtnNextCommand().doClick();
		verify(optionsController).executeCommandFromLog();
	}
}