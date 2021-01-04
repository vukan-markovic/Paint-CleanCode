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

	@Override
	public void draw(Graphics graphics) {
		hexagon.paint(graphics);
	}

	@Override
	public void drawSelection(Graphics graphics) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(int xCoordinate, int yCoordinate) {
		return hexagon.doesContain(xCoordinate, yCoordinate);
	}

	public boolean equals(Object object) {
		if (object instanceof HexagonAdapter) {
			HexagonAdapter hexagonAdapter = (HexagonAdapter) object;

			if (getRadius() == (hexagonAdapter.getRadius()) && getXcoordinate() == hexagonAdapter.getXcoordinate()
					&& getYcoordinate() == hexagonAdapter.getYcoordinate())
				return true;
		}

		return false;
	}

	public HexagonAdapter clone() {
		return new HexagonAdapter(getXcoordinate(), getYcoordinate(), getRadius(), getOuterColor(), getInnerColor(),
				isSelected());
	}

	public String toString() {
		return "Center: " + new Point(hexagon.getX(), hexagon.getY()) + ", radius: " + hexagon.getR()
				+ " , Inner color: " + getInnerColor().getRGB() + " , Outer color: " + getOuterColor().getRGB();
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