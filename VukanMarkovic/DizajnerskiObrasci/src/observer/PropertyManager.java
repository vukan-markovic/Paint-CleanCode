package observer;

import java.beans.*;
import mvc.DrawingFrame;

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
			frame.getBtnDelete().setEnabled(newValue);
			break;
		case "btnModify":
			frame.getBtnModify().setEnabled(newValue);
			break;
		case "btnUndo":
			frame.getBtnUndo().setEnabled(newValue);
			break;
		case "btnRedo":
			frame.getBtnRedo().setEnabled(newValue);
			break;
		case "btnToBack":
			frame.getBtnToBack().setEnabled(newValue);
			break;
		case "btnToFront":
			frame.getBtnToFront().setEnabled(newValue);
			break;
		case "btnSendToBack":
			frame.getBtnSendToBack().setEnabled(newValue);
			break;
		case "btnBringToFront":
			frame.getBtnBringToFront().setEnabled(newValue);
			break;
		}
	}

	public DrawingFrame getFrame() {
		return frame;
	}
}