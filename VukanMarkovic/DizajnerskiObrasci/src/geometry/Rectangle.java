package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends FillShape {
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private int height;
	private int width;

	public Rectangle() {
	}

	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color borderColor,
			Color fillColor) {
		this(upperLeftPoint, height, width);
		setSelected(selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}

	public Rectangle clone() {
		return new Rectangle(upperLeftPoint.clone(), height, width, isSelected(), getBorderColor(), getFillColor());
	}

	@Override
	public void draw(Graphics graphics) {
		fillShape(graphics);
		graphics.setColor(getBorderColor());
		graphics.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		graphics.setColor(Color.BLUE);

		if (isSelected()) {
			graphics.drawRect(upperLeftPoint.getX() - 3, upperLeftPoint.getY() - 3, 6, 6);
			graphics.drawRect(upperLeftPoint.getX() - 3 + width, upperLeftPoint.getY() - 3, 6, 6);
			graphics.drawRect(upperLeftPoint.getX() - 3, upperLeftPoint.getY() - 3 + height, 6, 6);
			graphics.drawRect(upperLeftPoint.getX() + width - 3, upperLeftPoint.getY() + height - 3, 6, 6);
		}
	}

	@Override
	public void fillShape(Graphics graphics) {
		graphics.setColor(getFillColor());
		graphics.fillRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
	}

	public boolean contains(int x, int y) {
		if (upperLeftPoint.getX() <= x && x <= upperLeftPoint.getX() + width && upperLeftPoint.getY() <= y
				&& y <= upperLeftPoint.getY() + height)
			return true;
		return false;
	}

	public boolean equals(Object object) {
		if (object instanceof Rectangle) {
			Rectangle r = (Rectangle) object;

			if (upperLeftPoint.equals(r.upperLeftPoint) && height == r.height && width == r.width)
				return true;
		}

		return false;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String toString() {
		return "Upper left point: " + upperLeftPoint + ", height: " + height + " , width: " + width
				+ " , Border color: " + getBorderColor().getRGB() + " , Fill color: " + getFillColor().getRGB();
	}
}