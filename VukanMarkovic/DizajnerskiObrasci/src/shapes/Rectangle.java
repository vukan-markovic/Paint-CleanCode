package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape {
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

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color outerColor,
			Color innerColor) {
		this(upperLeftPoint, height, width);
		setSelected(selected);
		setOuterColor(outerColor);
		setInnerColor(innerColor);
	}

	public Rectangle clone() {
		return new Rectangle(upperLeftPoint.clone(), height, width, isSelected(), getOuterColor(), getInnerColor());
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getOuterColor());
		graphics.drawRect(upperLeftPoint.getXcoordinate(), upperLeftPoint.getYcoordinate(), width, height);
		fillShape(graphics);

		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			graphics.drawRect(upperLeftPoint.getXcoordinate() - 3, upperLeftPoint.getYcoordinate() - 3, 6, 6);
			graphics.drawRect(upperLeftPoint.getXcoordinate() - 3 + width, upperLeftPoint.getYcoordinate() - 3, 6, 6);
			graphics.drawRect(upperLeftPoint.getXcoordinate() - 3, upperLeftPoint.getYcoordinate() - 3 + height, 6, 6);
			graphics.drawRect(upperLeftPoint.getXcoordinate() + width - 3, upperLeftPoint.getYcoordinate() + height - 3, 6, 6);
		}
	}

	@Override
	public void fillShape(Graphics graphics) {
		graphics.setColor(getInnerColor());
		graphics.fillRect(upperLeftPoint.getXcoordinate(), upperLeftPoint.getYcoordinate(), width, height);
	}

	public boolean contains(int xCoordinate, int yCoordinate) {
		if (upperLeftPoint.getXcoordinate() <= xCoordinate && xCoordinate <= upperLeftPoint.getXcoordinate() + width
				&& upperLeftPoint.getYcoordinate() <= yCoordinate && yCoordinate <= upperLeftPoint.getYcoordinate() + height)
			return true;
		return false;
	}

	public boolean equals(Object object) {
		if (object instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) object;

			if (upperLeftPoint.equals(rectangle.upperLeftPoint) && height == rectangle.height
					&& width == rectangle.width)
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
		return "Upper left point: " + upperLeftPoint + ", height: " + height + " , width: " + width + " , Inner color: "
				+ getInnerColor().getRGB() + " , Outer color: " + getOuterColor().getRGB();
	}
}