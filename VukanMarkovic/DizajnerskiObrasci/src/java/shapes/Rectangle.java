package shapes;

import java.awt.*;

public class Rectangle extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private int height;
	private int width;

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color outerColor,
			Color innerColor) {
		super(selected, outerColor, innerColor);
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getOuterColor());
		graphics.drawRect(upperLeftPoint.getXcoordinate(), upperLeftPoint.getYcoordinate(), width, height);
		fillShape(graphics);

		if (isSelected())
			drawSelection(graphics);
	}

	@Override
	protected void fillShape(Graphics graphics) {
		graphics.setColor(getInnerColor());
		graphics.fillRect(upperLeftPoint.getXcoordinate(), upperLeftPoint.getYcoordinate(), width, height);
	}

	@Override
	protected void drawSelection(Graphics graphics) {
		graphics.setColor(getSelectionColor());

		graphics.drawRect(upperLeftPoint.getXcoordinate() - SELECT_RECTANGLE_GAP,
				upperLeftPoint.getYcoordinate() - SELECT_RECTANGLE_GAP, SELECT_RECTANGLE_SIDE_LENGTH,
				SELECT_RECTANGLE_SIDE_LENGTH);

		graphics.drawRect(upperLeftPoint.getXcoordinate() - SELECT_RECTANGLE_GAP + width,
				upperLeftPoint.getYcoordinate() - SELECT_RECTANGLE_GAP, SELECT_RECTANGLE_SIDE_LENGTH,
				SELECT_RECTANGLE_SIDE_LENGTH);

		graphics.drawRect(upperLeftPoint.getXcoordinate() - SELECT_RECTANGLE_GAP,
				upperLeftPoint.getYcoordinate() - SELECT_RECTANGLE_GAP + height, SELECT_RECTANGLE_SIDE_LENGTH,
				SELECT_RECTANGLE_SIDE_LENGTH);

		graphics.drawRect(upperLeftPoint.getXcoordinate() + width - SELECT_RECTANGLE_GAP,
				upperLeftPoint.getYcoordinate() + height - SELECT_RECTANGLE_GAP, SELECT_RECTANGLE_SIDE_LENGTH,
				SELECT_RECTANGLE_SIDE_LENGTH);
	}

	public boolean contains(int xCoordinate, int yCoordinate) {
		if (upperLeftPoint.getXcoordinate() <= xCoordinate && xCoordinate <= upperLeftPoint.getXcoordinate() + width
				&& upperLeftPoint.getYcoordinate() <= yCoordinate
				&& yCoordinate <= upperLeftPoint.getYcoordinate() + height)
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

	public Rectangle clone() {
		return new Rectangle(upperLeftPoint.clone(), height, width, isSelected(), getOuterColor(), getInnerColor());
	}

	public String toString() {
		return "Upper left point: " + upperLeftPoint + ", height: " + height + " , width: " + width + " , Outer color: "
				+ getOuterColor().getRGB() + " , Inner color: " + getInnerColor().getRGB();
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}