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

	public Circle(Point center, int radius, boolean selected, Color borderColor, Color fillColor) {
		this(center, radius);
		setSelected(selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}

	public Circle clone() {
		return new Circle(center.clone(), radius, isSelected(), getBorderColor(), getFillColor());
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getBorderColor());
		graphics.drawOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
		fillShape(graphics);
		graphics.setColor(Color.BLUE);

		if (isSelected()) {
			graphics.drawRect(center.getX() - 3, center.getY() - 3, 6, 6);
			graphics.drawRect(center.getX() + getRadius() - 3, center.getY() - 3, 6, 6);
			graphics.drawRect(center.getX() - getRadius() - 3, center.getY() - 3, 6, 6);
			graphics.drawRect(center.getX() - 3, center.getY() + radius - 3, 6, 6);
			graphics.drawRect(center.getX() - 3, center.getY() - radius - 3, 6, 6);
		}
	}

	@Override
	public void fillShape(Graphics graphics) {
		graphics.setColor(getFillColor());
		graphics.fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
	}

	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}

	public boolean equals(Object object) {
		if (object instanceof Circle) {
			Circle c = (Circle) object;
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
		return "Center: " + center + ", radius: " + radius + " , Border color: " + getBorderColor().getRGB()
				+ " , Fill color: " + getFillColor().getRGB();
	}
}