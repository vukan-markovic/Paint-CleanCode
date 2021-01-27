package dialogs;

import shapes.Shape;

public interface Dialog {
	void setIcon();

	void buildLayout();

	boolean isInputValid();

	void setModifyDialog(Shape shape);
}