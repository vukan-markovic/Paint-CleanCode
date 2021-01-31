package shapes;

import java.awt.*;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape {
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;

	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public HexagonAdapter(Hexagon hexagon, boolean selected, Color borderColor, Color fillColor) {
		this(hexagon);
		setSelected(selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
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

	@Override
	public boolean equals(Object object) {
		if (object instanceof HexagonAdapter) {
			HexagonAdapter hexagonAdapter = (HexagonAdapter) object;

			return getRadius() == hexagonAdapter.getRadius() && getXcoordinate() == hexagonAdapter.getXcoordinate()
					&& getYcoordinate() == hexagonAdapter.getYcoordinate();
		}

		return false;
	}

	@Override
	public HexagonAdapter clone() {
		return new HexagonAdapter(new Hexagon(getXcoordinate(), getYcoordinate(), getRadius()), isSelected(),
				getBorderColor(), getFillColor());
	}

	@Override
	public String toString() {
		return "Center: " + new Point(hexagon.getX(), hexagon.getY(), false, Color.BLACK) + ", radius: "
				+ hexagon.getR() + " , Border color: " + getBorderColor().getRGB() + " , Fill color: "
				+ getFillColor().getRGB();
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

	public Color getFillColor() {
		return hexagon.getAreaColor();
	}

	@Override
	public Color getBorderColor() {
		return hexagon.getBorderColor();
	}

	@Override
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

	public void setFillColor(Color fillColor) {
		hexagon.setAreaColor(fillColor);
	}

	@Override
	public void setBorderColor(Color borderColor) {
		hexagon.setBorderColor(borderColor);
	}

	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}
}