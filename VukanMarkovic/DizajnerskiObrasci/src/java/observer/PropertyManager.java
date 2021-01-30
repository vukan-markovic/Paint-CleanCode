package observer;

import java.beans.*;
import frame.DrawingFrame;
import toolbars.RightToolbar;
import toolbars.TopToolbar;

public class PropertyManager implements PropertyChangeListener {
	private TopToolbar topToolbar;
	private RightToolbar rightToolbar;

	public PropertyManager(DrawingFrame frame) {
		topToolbar = frame.getTopToolbar();
		rightToolbar = frame.getRightToolbar();
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent) {
		boolean newValue = (boolean) changeEvent.getNewValue();

		switch (changeEvent.getPropertyName()) {
		case "btnDelete":
			topToolbar.getBtnDelete().setEnabled(newValue);
			break;
		case "btnModify":
			topToolbar.getBtnModify().setEnabled(newValue);
			break;
		case "btnUndo":
			rightToolbar.getBtnUndo().setEnabled(newValue);
			break;
		case "btnRedo":
			rightToolbar.getBtnRedo().setEnabled(newValue);
			break;
		case "btnToBack":
			rightToolbar.getBtnToBack().setEnabled(newValue);
			break;
		case "btnToFront":
			rightToolbar.getBtnToFront().setEnabled(newValue);
			break;
		case "btnSendToBack":
			rightToolbar.getBtnSendToBack().setEnabled(newValue);
			break;
		case "btnBringToFront":
			rightToolbar.getBtnBringToFront().setEnabled(newValue);
			break;
		}
	}

	public TopToolbar getTopToolbar() {
		return topToolbar;
	}

	public RightToolbar getRightToolbar() {
		return rightToolbar;
	}
}