package shapes;

import java.awt.*;

abstract class SurfaceShape extends Shape {
	private static final long serialVersionUID = 1L;
	private Color innerColor = Color.WHITE;

	public abstract void fillShape(Graphics graphics);

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
}