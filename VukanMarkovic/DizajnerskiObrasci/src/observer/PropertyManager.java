package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class PropertyManager implements PropertyChangeListener {
	private DrawingFrame mainFrame;

	public PropertyManager(DrawingFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public DrawingFrame getMainFrame() {
		return mainFrame;
	}

	@Override
	public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
		switch (propertyChangeEvent.getPropertyName()) {
		case "btnDelete":
			mainFrame.getBtnDelete().setEnabled((boolean) propertyChangeEvent.getNewValue());
			break;
		case "btnModify":
			mainFrame.getBtnModify().setEnabled((boolean) propertyChangeEvent.getNewValue());
			break;
		case "btnUndo":
			mainFrame.getBtnUndo().setEnabled((boolean) propertyChangeEvent.getNewValue());
			break;
		case "btnRedo":
			mainFrame.getBtnRedo().setEnabled((boolean) propertyChangeEvent.getNewValue());
			break;
		case "btnToBack":
			mainFrame.getBtnToBack().setEnabled((boolean) propertyChangeEvent.getNewValue());
			break;
		case "btnToFront":
			mainFrame.getBtnToFront().setEnabled((boolean) propertyChangeEvent.getNewValue());
			break;
		case "btnSendToBack":
			mainFrame.getBtnSendToBack().setEnabled((boolean) propertyChangeEvent.getNewValue());
			break;
		case "btnBringToFront":
			mainFrame.getBtnBringToFront().setEnabled((boolean) propertyChangeEvent.getNewValue());
			break;
		}
	}
}