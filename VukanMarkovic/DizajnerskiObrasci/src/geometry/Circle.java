package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends FillShape {
	private static final long serialVersionUID = 1L;
	private Point center;
	private int radius;

	public Circle() {
	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected, Color border, Color fill) {
		this.center = center;
		this.radius = radius;
		setSelected(selected);
		setBorder_Color(border);
		setFill_Color(fill);
	}

	public Circle clone() {
		return new Circle(center.clone(), radius, isSelected(), getBorder_Color(), getFill_Color());
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getBorder_Color());
		g.drawOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
		fill_shape(g);
		g.setColor(Color.BLUE);

		if (isSelected()) {
			g.drawRect(center.getX() - 3, center.getY() - 3, 6, 6);
			g.drawRect(center.getX() + getRadius() - 3, center.getY() - 3, 6, 6);
			g.drawRect(center.getX() - getRadius() - 3, center.getY() - 3, 6, 6);
			g.drawRect(center.getX() - 3, center.getY() + radius - 3, 6, 6);
			g.drawRect(center.getX() - 3, center.getY() - radius - 3, 6, 6);
		}
	}

	@Override
	public void fill_shape(Graphics g) {
		g.setColor(getFill_Color());
		g.fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle)
			return (radius - ((Circle) o).radius);
		return 0;
	}

	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
	}

	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}

	public boolean contains(Point p) {
		if (p.distance(center.getX(), center.getY()) <= radius)
			return true;
		return false;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle c = (Circle) obj;
			return center.equals(c.center) && this.radius == c.radius;
		}

		return false;
	}

	public double area() {
		return radius * radius * Math.PI;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String toString() {
		return "Center: " + center + ", radius: " + radius + " , Border color: " + getBorder_Color().getRGB()
				+ " , Fill color: " + getFill_Color().getRGB();
	}
}