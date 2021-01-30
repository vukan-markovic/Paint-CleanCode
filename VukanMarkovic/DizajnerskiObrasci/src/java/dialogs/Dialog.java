package dialogs;

import shapes.Shape;

public interface Dialog {
	void setIcon();

	void buildLayout();

	boolean areAllFieldsFilled();
	
	boolean areValuesValid();

	void setModifyDialog(Shape shape);
}