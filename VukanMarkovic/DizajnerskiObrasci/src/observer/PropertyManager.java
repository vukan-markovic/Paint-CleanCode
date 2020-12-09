package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class PropertyManager implements PropertyChangeListener{
	
	private DrawingFrame main_frame;
	
	

	public PropertyManager(DrawingFrame main_frame) {
		super();
		this.main_frame = main_frame;
	}



	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		switch (evt.getPropertyName()) {
		case "btnDelete" :
			main_frame.getBtnDelete().setEnabled((boolean)evt.getNewValue());
			break;
		case "btnModify" :
			main_frame.getBtnModify().setEnabled((boolean)evt.getNewValue());
			break;
		case "btnUndo" :
			main_frame.getBtnUndo().setEnabled((boolean)evt.getNewValue());
			break;
		case "btnRedo" :
			main_frame.getBtnRedo().setEnabled((boolean)evt.getNewValue());
			break;
		case "btnToBack" :
			main_frame.getBtnToBack().setEnabled((boolean)evt.getNewValue());
			break;
		case "btnToFront" :
			main_frame.getBtnToFront().setEnabled((boolean)evt.getNewValue());
			break;
		case "btnSendToBack" :
			main_frame.getBtnSendToBack().setEnabled((boolean)evt.getNewValue());
			break;
		case "btnBringToFront" :
			main_frame.getBtnBringToFront().setEnabled((boolean)evt.getNewValue());
			break;
		}
		
	}

}
