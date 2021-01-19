package shapes;

import java.awt.*;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape {
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;

	public HexagonAdapter(Hexagon hexagon, boolean selected, Color outerColor, Color innerColor) {
		super(selected, innerColor);
		setOuterColor(outerColor);
		this.hexagon = hexagon;
	}

	@Override
	public void draw(Graphics graphics) {
		hexagon.paint(graphics);
	}

	@Override
	protected void drawSelection(Graphics graphics) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(int xCoordinate, int yCoordinate) {
		return hexagon.doesContain(xCoordinate, yCoordinate);
	}

	public boolean equals(Object object) {
		if (object instanceof HexagonAdapter) {
			HexagonAdapter hexagonAdapter = (HexagonAdapter) object;

			return getRadius() == hexagonAdapter.getRadius() && getXcoordinate() == hexagonAdapter.getXcoordinate()
					&& getYcoordinate() == hexagonAdapter.getYcoordinate();
		}

		return false;
	}

	public HexagonAdapter clone() {
		return new HexagonAdapter(new Hexagon(getXcoordinate(), getYcoordinate(), getRadius()), isSelected(), getOuterColor(),
				getInnerColor());
	}

	public String toString() {
		return "Center: " + new Point(hexagon.getX(), hexagon.getY(), false, Color.BLACK) + ", radius: "
				+ hexagon.getR() + " , Outer color: " + getOuterColor().getRGB() + " , Inner color: "
				+ getInnerColor().getRGB();
	}

	public int getXcoordinate() {
		return hexagon.getX();
	}

	public int getYcoordinate() {
		return hexagon.getY();
	}

	public int getRadius() {
		return hexagon.getR();
	}

	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}

	public Color getOuterColor() {
		return hexagon.getBorderColor();
	}

	public boolean isSelected() {
		return hexagon.isSelected();
	}

	public void setXcoordinate(int xCoordinate) {
		hexagon.setX(xCoordinate);
	}

	public void setYcoordinate(int yCoordinate) {
		hexagon.setY(yCoordinate);
	}

	public void setRadius(int radius) {
		hexagon.setR(radius);
	}

	public void setInnerColor(Color innerColor) {
		hexagon.setAreaColor(innerColor);
	}

	public void setOuterColor(Color outerColor) {
		hexagon.setBorderColor(outerColor);
	}

	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}
}