package shapes;

import java.awt.*;

abstract class SurfaceShape extends Shape {
	private static final long serialVersionUID = 1L;
	private Color innerColor;

	protected abstract void fillShape(Graphics graphics);

	SurfaceShape(boolean selected, Color outerColor, Color innerColor) {
		super(selected, outerColor);
		this.innerColor = innerColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
}