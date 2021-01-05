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
		switch (changeEvent.getPropertyName()) {
		case "btnDelete":
			frame.getBtnDelete().setEnabled((boolean) changeEvent.getNewValue());
			break;
		case "btnModify":
			frame.getBtnModify().setEnabled((boolean) changeEvent.getNewValue());
			break;
		case "btnUndo":
			frame.getBtnUndo().setEnabled((boolean) changeEvent.getNewValue());
			break;
		case "btnRedo":
			frame.getBtnRedo().setEnabled((boolean) changeEvent.getNewValue());
			break;
		case "btnToBack":
			frame.getBtnToBack().setEnabled((boolean) changeEvent.getNewValue());
			break;
		case "btnToFront":
			frame.getBtnToFront().setEnabled((boolean) changeEvent.getNewValue());
			break;
		case "btnSendToBack":
			frame.getBtnSendToBack().setEnabled((boolean) changeEvent.getNewValue());
			break;
		case "btnBringToFront":
			frame.getBtnBringToFront().setEnabled((boolean) changeEvent.getNewValue());
			break;
		}
	}

	public DrawingFrame getFrame() {
		return frame;
	}
}