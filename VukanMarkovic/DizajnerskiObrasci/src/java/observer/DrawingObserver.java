package observer;

import java.beans.*;

public class DrawingObserver {
	private PropertyChangeSupport changeSupport;
	private boolean btnSelectEnabled;
	private boolean btnRemoveEnabled;
	private boolean btnModifyEnabled;
	private boolean btnUndoEnabled;
	private boolean btnRedoEnabled;
	private boolean btnToBackEnabled;
	private boolean btnToFrontEnabled;
	private boolean btnBringToBackEnabled;
	private boolean btnBringToFrontEnabled;

	public DrawingObserver() {
		changeSupport = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener changeListener) {
		changeSupport.addPropertyChangeListener(changeListener);
	}

	public void removePropertyChangeListener(PropertyChangeListener changeListener) {
		changeSupport.removePropertyChangeListener(changeListener);
	}

	public void setBtnSelectEnabled(boolean btnSelectEnabled) {
		changeSupport.firePropertyChange("btnSelect", this.btnSelectEnabled, btnSelectEnabled);
		this.btnSelectEnabled = btnSelectEnabled;
	}

	public void setBtnRemoveEnabled(boolean btnRemoveEnabled) {
		changeSupport.firePropertyChange("btnRemove", this.btnRemoveEnabled, btnRemoveEnabled);
		this.btnRemoveEnabled = btnRemoveEnabled;
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

	public void setBtnBringToBackEnabled(boolean btnBringToBackEnabled) {
		changeSupport.firePropertyChange("btnBringToBack", this.btnBringToBackEnabled, btnBringToBackEnabled);
		this.btnBringToBackEnabled = btnBringToBackEnabled;
	}

	public void setBtnBringToFrontEnabled(boolean btnBringToFrontEnabled) {
		changeSupport.firePropertyChange("btnBringToFront", this.btnBringToFrontEnabled, btnBringToFrontEnabled);
		this.btnBringToFrontEnabled = btnBringToFrontEnabled;
	}
}