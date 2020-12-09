package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Observer {

	private boolean btnDeleteEnable;
	private boolean btnModifyEnable;
	private boolean btnUndoEnable;
	private boolean btnRedoEnable;
	private boolean btnToBackEnable;
	private boolean btnToFrontEnable;
	private boolean btnSendToBackEnable;
	private boolean btnBringToFrontEnable;
	private PropertyChangeSupport propertyChangeSupport;
	
	public Observer() {
		super();
		this.propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.removePropertyChangeListener(pcl);
	}

	public void setBtnDeleteEnable(boolean btnDeleteEnable) {
		propertyChangeSupport.firePropertyChange("btnDelete", this.btnDeleteEnable, btnDeleteEnable);
		this.btnDeleteEnable = btnDeleteEnable;
	}

	public void setBtnModifyEnable(boolean btnModifyEnable) {
		propertyChangeSupport.firePropertyChange("btnModify", this.btnModifyEnable, btnModifyEnable);
		this.btnModifyEnable = btnModifyEnable;
	}

	public void setBtnUndoEnable(boolean btnUndoEnable) {
		propertyChangeSupport.firePropertyChange("btnUndo", this.btnUndoEnable, btnUndoEnable);
		this.btnUndoEnable = btnUndoEnable;
	}

	public void setBtnRedoEnable(boolean btnRedoEnable) {
		propertyChangeSupport.firePropertyChange("btnRedo", this.btnRedoEnable, btnRedoEnable);
		this.btnRedoEnable = btnRedoEnable;
	}

	public void setBtnToBackEnable(boolean btnToBackEnable) {
		propertyChangeSupport.firePropertyChange("btnToBack", this.btnToBackEnable, btnToBackEnable);
		this.btnToBackEnable = btnToBackEnable;
	}

	public void setBtnToFrontEnable(boolean btnToFrontEnable) {
		propertyChangeSupport.firePropertyChange("btnToFront", this.btnToFrontEnable, btnToFrontEnable);
		this.btnToFrontEnable = btnToFrontEnable;
	}

	public void setBtnSendToBackEnable(boolean btnSendToBackEnable) {
		propertyChangeSupport.firePropertyChange("btnSendToBack", this.btnSendToBackEnable, btnSendToBackEnable);
		this.btnSendToBackEnable = btnSendToBackEnable;
	}

	public void setBtnBringToFrontEnable(boolean btnBringToFrontEnable) {
		propertyChangeSupport.firePropertyChange("btnBringToFront", this.btnBringToFrontEnable, btnBringToFrontEnable);
		this.btnBringToFrontEnable = btnBringToFrontEnable;
	}
	
	
	
	
	
	
}
