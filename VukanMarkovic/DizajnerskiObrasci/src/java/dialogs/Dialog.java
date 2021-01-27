package dialogs;

import shapes.Shape;

public interface Dialog {
	void buildLayout();

	void setIcon();

	boolean isInputValid();

	void setModifyDialog(Shape shape);
}