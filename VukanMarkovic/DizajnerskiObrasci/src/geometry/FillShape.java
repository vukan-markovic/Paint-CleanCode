package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class FillShape extends Shape {
	private static final long serialVersionUID = 1L;
	public Color fillColor = new Color(0, 0, 0, 0);

	public abstract void fillShape(Graphics graphics);

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
}