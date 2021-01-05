package observer;

import java.beans.*;

public class DrawingObserver {
	private boolean btnDeleteEnabled;
	private boolean btnModifyEnabled;
	private boolean btnUndoEnabled;
	private boolean btnRedoEnabled;
	private boolean btnToBackEnabled;
	private boolean btnToFrontEnabled;
	private boolean btnSendToBackEnabled;
	private boolean btnBringToFrontEnabled;
	private PropertyChangeSupport changeSupport;

	public DrawingObserver() {
		changeSupport = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener changeListener) {
		changeSupport.addPropertyChangeListener(changeListener);
	}

	public void removePropertyChangeListener(PropertyChangeListener changeListener) {
		changeSupport.removePropertyChangeListener(changeListener);
	}

	public void setBtnDeleteEnabled(boolean btnDeleteEnabled) {
		changeSupport.firePropertyChange("btnDelete", this.btnDeleteEnabled, btnDeleteEnabled);
		this.btnDeleteEnabled = btnDeleteEnabled;
	}

	public void setBtnModifyEnabled(boolean btnModifyEnabled) {
		changeSupport.firePropertyChange("btnModify", this.btnModifyEnabled, btnModifyEnabled);
		this.btnModifyEnabled = btnModifyEnabled;
	}

	public void setBtnUndoEnabled(boolean btnUndoEnabled) {
		changeSupport.firePropertyChange("btnUndo", this.btnUndoEnabled, btnUndoEnabled);
		this.btnUndoEnabled = btnUndoEnabled;
	}

	public void setBtnRedoEnabled(boolean btnRedoEnabled) {
		changeSupport.firePropertyChange("btnRedo", this.btnRedoEnabled, btnRedoEnabled);
		this.btnRedoEnabled = btnRedoEnabled;
	}

	public void setBtnToBackEnabled(boolean btnToBackEnabled) {
		changeSupport.firePropertyChange("btnToBack", this.btnToBackEnabled, btnToBackEnabled);
		this.btnToBackEnabled = btnToBackEnabled;
	}

	public void setBtnToFrontEnabled(boolean btnToFrontEnabled) {
		changeSupport.firePropertyChange("btnToFront", this.btnToFrontEnabled, btnToFrontEnabled);
		this.btnToFrontEnabled = btnToFrontEnabled;
	}

	public void setBtnSendToBackEnabled(boolean btnSendToBackEnabled) {
		changeSupport.firePropertyChange("btnSendToBack", this.btnSendToBackEnabled, btnSendToBackEnabled);
		this.btnSendToBackEnabled = btnSendToBackEnabled;
	}

	public void setBtnBringToFrontEnabled(boolean btnBringToFrontEnabled) {
		changeSupport.firePropertyChange("btnBringToFront", this.btnBringToFrontEnabled, btnBringToFrontEnabled);
		this.btnBringToFrontEnabled = btnBringToFrontEnabled;
	}
}