package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Point center;
	private int radius;

	public Circle() {
	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected, Color outerColor, Color innerColor) {
		this(center, radius);
		setSelected(selected);
		setOuterColor(outerColor);
		setInnerColor(innerColor);
	}

	public Circle clone() {
		return new Circle(center.clone(), radius, isSelected(), getInnerColor(), getOuterColor());
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getOuterColor());
		graphics.drawOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, radius * 2, radius * 2);
		fillShape(graphics);

		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			graphics.drawRect(center.getXcoordinate() - 3, center.getYcoordinate() - 3, 6, 6);
			graphics.drawRect(center.getXcoordinate() + getRadius() - 3, center.getYcoordinate() - 3, 6, 6);
			graphics.drawRect(center.getXcoordinate() - getRadius() - 3, center.getYcoordinate() - 3, 6, 6);
			graphics.drawRect(center.getXcoordinate() - 3, center.getYcoordinate() + radius - 3, 6, 6);
			graphics.drawRect(center.getXcoordinate() - 3, center.getYcoordinate() - radius - 3, 6, 6);
		}
	}

	@Override
	public void fillShape(Graphics graphics) {
		graphics.setColor(getInnerColor());
		graphics.fillOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, radius * 2, radius * 2);
	}

	public boolean contains(int xCoordinate, int yCoordinate) {
		return center.distance(xCoordinate, yCoordinate) <= radius;
	}

	public boolean equals(Object object) {
		if (object instanceof Circle) {
			Circle circle = (Circle) object;
			return center.equals(circle.center) && this.radius == circle.radius;
		}

		return false;
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
		return "Center: " + center + ", radius: " + radius + " , Inner color: " + getInnerColor().getRGB()
				+ " , Outer color: " + getOuterColor().getRGB();
	}
}