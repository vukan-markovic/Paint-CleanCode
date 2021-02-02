package shapes;

import java.awt.*;
import java.io.Serializable;

public abstract class Shape implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	public final int SELECT_RECTANGLE_GAP = 3;
	public final int SELECT_RECTANGLE_SIDE_LENGTH = 6;
	private boolean selected;
	private Color borderColor;

	public Shape() {
	}

	public abstract boolean contains(int xCoordinate, int yCoordinate);

	public abstract void draw(Graphics graphics);

	protected abstract void drawSelection(Graphics graphics);

	public Color getSelectionColor() {
		return Color.BLUE;
	}

	public String getClassName() {
		return this.getClass().getSimpleName();
	}

	public boolean isSelected() {
		return selected;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
}