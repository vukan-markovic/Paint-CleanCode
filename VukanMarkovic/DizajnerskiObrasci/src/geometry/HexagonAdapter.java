package geometry;

import java.awt.Color;
import java.awt.Graphics;
import hexagon.*;

public class HexagonAdapter extends FillShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Hexagon hexagon;

	public HexagonAdapter() {
		hexagon = new Hexagon(0, 0, 0);
	}

	public HexagonAdapter(int x, int y, int r, Color outerColor, Color innerColor) {
		hexagon = new Hexagon(x, y, r);
		hexagon.setBorderColor(outerColor);
		hexagon.setAreaColor(innerColor);
		
	}
	
	public HexagonAdapter(int x, int y, int r, Color outerColor, Color innerColor, boolean selected) {
		hexagon = new Hexagon(x, y, r);
		hexagon.setBorderColor(outerColor);
		hexagon.setAreaColor(innerColor);
		
		setSelected(selected);
	}
	
	public HexagonAdapter clone() {
		HexagonAdapter cloneHex = new HexagonAdapter(this.getX(), this.getY(), this.getR(), this.getBorder_Color(), this.getInnerColor(), this.isSelected());
		return cloneHex;
	}

	@Override
	public void moveBy(int byX, int byY) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
	}

	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}

	public Color getBorder_Color() {
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

	public void setInnerColor(Color innerColor) {
		hexagon.setAreaColor(innerColor);
	}

	public void setBorderColor(Color outerColor) {
		hexagon.setBorderColor(outerColor);
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

		return "Center: " + (new Point(hexagon.getX(), hexagon.getY(), false, new Color(250, 128, 114))) + ", radius: " + hexagon.getR() + " , Border color: " + getBorder_Color().getRGB()
				+ " , Fill color: " + getInnerColor().getRGB();
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter c = (HexagonAdapter) obj;
			if (getR() == (c.getR()) && getX() == c.getX() && getY() == c.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void fill_shape(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
