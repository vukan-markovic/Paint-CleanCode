package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;

	public Point() {
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, boolean selected, Color color) {
		super(selected, color);
		this.x = x;
		this.y = y;
	}

	public Point clone() {
		return new Point(x, y, isSelected(), getBorderColor());
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getBorderColor());
		graphics.drawLine(x - 2, y, x + 2, y);
		graphics.drawLine(x, y - 2, x, y + 2);
		graphics.setColor(Color.BLUE);

		if (isSelected())
			graphics.drawRect(x - 3, y - 3, 6, 6);
	}

	public boolean contains(int x, int y) {
		if (distance(x, y) <= 3)
			return true;
		return false;
	}

	public boolean equals(Object object) {
		if (object instanceof Point) {
			Point p = (Point) object;

			if (x == p.x && y == p.y)
				return true;
		}

		return false;
	}

	public double distance(int x2, int y2) {
		double dx = x - x2;
		double dy = y - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return "(x: " + x + " , y: " + y + " , Border color: " + getBorderColor().getRGB() + " )";
	}
}