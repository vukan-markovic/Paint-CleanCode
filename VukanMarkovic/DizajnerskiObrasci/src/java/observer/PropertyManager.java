package observer;

import java.beans.*;

import frame.DrawingFrame;

public class PropertyManager implements PropertyChangeListener {
	private DrawingFrame frame;

	public PropertyManager(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent) {
		boolean newValue = (boolean) changeEvent.getNewValue();

		switch (changeEvent.getPropertyName()) {
		case "btnDelete":
			frame.getTopToolbar().getBtnDelete().setEnabled(newValue);
			break;
		case "btnModify":
			frame.getTopToolbar().getBtnModify().setEnabled(newValue);
			break;
		case "btnUndo":
			frame.getRightToolbar().getBtnUndo().setEnabled(newValue);
			break;
		case "btnRedo":
			frame.getRightToolbar().getBtnRedo().setEnabled(newValue);
			break;
		case "btnToBack":
			frame.getRightToolbar().getBtnToBack().setEnabled(newValue);
			break;
		case "btnToFront":
			frame.getRightToolbar().getBtnToFront().setEnabled(newValue);
			break;
		case "btnSendToBack":
			frame.getRightToolbar().getBtnSendToBack().setEnabled(newValue);
			break;
		case "btnBringToFront":
			frame.getRightToolbar().getBtnBringToFront().setEnabled(newValue);
			break;
		}
	}

	public DrawingFrame getFrame() {
		return frame;
	}
}