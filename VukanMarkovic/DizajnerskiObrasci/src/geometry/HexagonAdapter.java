package geometry;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends FillShape {
	private static final long serialVersionUID = 1L;
	Hexagon hexagon;

	public HexagonAdapter() {
		hexagon = new Hexagon(0, 0, 0);
	}

	public HexagonAdapter(Hexagon hexagon, Color borderColor, Color fillColor) {
		this.hexagon = hexagon;
		hexagon.setBorderColor(borderColor);
		hexagon.setAreaColor(fillColor);
	}

	public HexagonAdapter(int x, int y, int r, Color borderColor, Color fillColor, boolean selected) {
		this(new Hexagon(x, y, r), borderColor, fillColor);
		setSelected(selected);
	}

	public HexagonAdapter clone() {
		return new HexagonAdapter(getX(), getY(), getR(), getBorderColor(), getFillColor(), isSelected());
	}

	@Override
	public void moveBy(int byX, int byY) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int compareTo(Object object) {
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics graphics) {
		hexagon.paint(graphics);
	}

	public Color getFillColor() {
		return hexagon.getAreaColor();
	}

	public Color getBorderColor() {
		return hexagon.getBorderColor();
	}

	public int getX() {
		return hexagon.getX();
	}

	public int getY() {
		return hexagon.getY();
	}

	public int getR() {
		return hexagon.getR();
	}

	public void setFillColor(Color fillColor) {
		hexagon.setAreaColor(fillColor);
	}

	public void setBorderColor(Color borderColor) {
		hexagon.setBorderColor(borderColor);
	}

	public void setX(int x) {
		hexagon.setX(x);
	}

	public void setY(int y) {
		hexagon.setY(y);
	}

	public void setR(int r) {
		hexagon.setR(r);
	}

	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}

	public boolean isSelected() {
		return hexagon.isSelected();
	}

	public String toString() {
		return "Center: " + (new Point(hexagon.getX(), hexagon.getY(), false, new Color(250, 128, 114))) + ", radius: "
				+ hexagon.getR() + " , Border color: " + getBorderColor().getRGB() + " , Fill color: "
				+ getFillColor().getRGB();
	}

	public boolean equals(Object object) {
		if (object instanceof HexagonAdapter) {
			HexagonAdapter c = (HexagonAdapter) object;

			if (getR() == (c.getR()) && getX() == c.getX() && getY() == c.getY())
				return true;
		}

		return false;
	}

	@Override
	public void fillShape(Graphics graphics) {
		throw new UnsupportedOperationException();
	}
}