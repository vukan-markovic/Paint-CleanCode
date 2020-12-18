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

	public Point(int x, int y, boolean selected, Color b) {
		this.x = x;
		this.y = y;
		setSelected(selected);
		setBorder_Color(b);
	}

	public Point clone() {
		return new Point(x, y, isSelected(), getBorder_Color());
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getBorder_Color());
		g.drawLine(x - 2, y, x + 2, y);
		g.drawLine(x, y - 2, x, y + 2);
		g.setColor(Color.BLUE);

		if (isSelected())
			g.drawRect(x - 3, y - 3, 6, 6);
	}

	@Override
	public void moveBy(int byX, int byY) {
		x += byX;
		y += byY;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point start = new Point(0, 0, false, getBorder_Color());
			return (int) (distance(start.x, start.y) - ((Point) o).distance(start.x, start.y));
		}

		return 0;
	}

	public boolean contains(int x, int y) {
		if (distance(x, y) <= 3)
			return true;
		return false;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;

			if (x == p.x && y == p.y)
				return true;
			return false;
		}

		return false;
	}

	public double distance(int x2, int y2) {
		double dx = x - x2;
		double dy = y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
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
		return "(x: " + x + " , y: " + y + " , Border color: " + getBorder_Color().getRGB() + " )";
	}
}