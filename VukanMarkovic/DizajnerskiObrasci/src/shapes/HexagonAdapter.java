package shapes;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends Shape {
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;

	public HexagonAdapter() {
		hexagon = new Hexagon(0, 0, 0);
	}

	public HexagonAdapter(Hexagon hexagon, Color outerColor, Color innerColor) {
		this.hexagon = hexagon;
		hexagon.setBorderColor(outerColor);
		hexagon.setAreaColor(innerColor);
	}

	public HexagonAdapter(int xCoordinate, int yCoordinate, int radius, Color outerColor, Color innerColor,
			boolean selected) {
		this(new Hexagon(xCoordinate, yCoordinate, radius), outerColor, innerColor);
		setSelected(selected);
	}

	public HexagonAdapter clone() {
		return new HexagonAdapter(getXcoordinate(), getYcoordinate(), getRadius(), getOuterColor(), getInnerColor(),
				isSelected());
	}

	@Override
	public boolean contains(int xCoordinate, int yCoordinate) {
		return hexagon.doesContain(xCoordinate, yCoordinate);
	}

	@Override
	public void draw(Graphics graphics) {
		hexagon.paint(graphics);
	}

	public boolean equals(Object object) {
		if (object instanceof HexagonAdapter) {
			HexagonAdapter c = (HexagonAdapter) object;

			if (getRadius() == (c.getRadius()) && getXcoordinate() == c.getXcoordinate()
					&& getYcoordinate() == c.getYcoordinate())
				return true;
		}

		return false;
	}

	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}

	public void setInnerColor(Color innerColor) {
		hexagon.setAreaColor(innerColor);
	}

	public Color getOuterColor() {
		return hexagon.getBorderColor();
	}

	public void setOuterColor(Color outerColor) {
		hexagon.setBorderColor(outerColor);
	}

	public int getXcoordinate() {
		return hexagon.getX();
	}

	public void setXcoordinate(int xCoordinate) {
		hexagon.setX(xCoordinate);
	}

	public int getYcoordinate() {
		return hexagon.getY();
	}

	public void setYcoordinate(int yCoordinate) {
		hexagon.setY(yCoordinate);
	}

	public int getRadius() {
		return hexagon.getR();
	}

	public void setRadius(int radius) {
		hexagon.setR(radius);
	}

	public boolean isSelected() {
		return hexagon.isSelected();
	}

	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}

	public String toString() {
		return "Center: " + new Point(hexagon.getX(), hexagon.getY()) + ", radius: " + hexagon.getR()
				+ " , Inner color: " + getInnerColor().getRGB() + " , Outer color: " + getOuterColor().getRGB();
	}
}