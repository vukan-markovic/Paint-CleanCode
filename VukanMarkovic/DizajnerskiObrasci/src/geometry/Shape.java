package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable<Object>, Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private boolean selected;
	private Color borderColor = Color.BLACK;

	public Shape() {
	}

	public Shape(boolean selected, Color borderColor) {
		this.selected = selected;
		this.borderColor = borderColor;
	}

	public abstract boolean contains(int x, int y);

	public abstract void draw(Graphics graphics);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
}