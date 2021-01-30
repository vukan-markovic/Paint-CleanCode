package shapes;

import java.awt.*;

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

	public Circle(Point center, int radius, boolean selected, Color borderColor, Color fillColor) {
		this(center, radius);
		setSelected(selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getBorderColor());
		graphics.drawOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, radius * 2, radius * 2);
		fillShape(graphics);

		if (isSelected())
			drawSelection(graphics);
	}

	@Override
	protected void fillShape(Graphics graphics) {
		graphics.setColor(getFillColor());
		graphics.fillOval(center.getXcoordinate() - radius, center.getYcoordinate() - radius, radius * 2, radius * 2);
	}

	@Override
	protected void drawSelection(Graphics graphics) {
		graphics.setColor(getSelectionColor());
		int xCoordinate = center.getXcoordinate();
		int yCoordinate = center.getYcoordinate();
		int radius = getRadius();
		graphics.drawRect(xCoordinate - 3, yCoordinate - 3, 6, 6);
		graphics.drawRect(xCoordinate + radius - 3, yCoordinate - 3, 6, 6);
		graphics.drawRect(xCoordinate - radius - 3, yCoordinate - 3, 6, 6);
		graphics.drawRect(xCoordinate - 3, yCoordinate + radius - 3, 6, 6);
		graphics.drawRect(xCoordinate - 3, yCoordinate - radius - 3, 6, 6);
	}

	@Override
	public boolean contains(int xCoordinate, int yCoordinate) {
		return center.calculateDistance(xCoordinate, yCoordinate) <= radius;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Circle) {
			Circle circle = (Circle) object;
			return center.equals(circle.center) && this.radius == circle.radius;
		}

		return false;
	}

	@Override
	public Circle clone() {
		return new Circle(center.clone(), radius, isSelected(), getBorderColor(), getFillColor());
	}

	@Override
	public String toString() {
		return "Center: " + center + ", radius: " + radius + " , Border color: " + getBorderColor().getRGB()
				+ " , Fill color: " + getFillColor().getRGB();
	}

	public Point getCenter() {
		return center;
	}

	public int getRadius() {
		return radius;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}