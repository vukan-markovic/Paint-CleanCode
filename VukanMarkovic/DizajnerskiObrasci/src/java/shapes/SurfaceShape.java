package shapes;

import java.awt.*;

abstract class SurfaceShape extends Shape {
	private static final long serialVersionUID = 1L;
	private Color fillColor;

	public SurfaceShape() {
	}

	protected abstract void fillShape(Graphics graphics);

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
}