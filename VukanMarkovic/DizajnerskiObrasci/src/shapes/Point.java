package shapes;

import java.awt.*;

public class Point extends Shape {
	private static final long serialVersionUID = 1L;
	private final int POINT_CLICK_TRESHOLD = 3;
	private final int POINT_LINE_GAP = 2;
	private int xCoordinate;
	private int yCoordinate;

	public Point(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public Point(int xCoordinate, int yCoordinate, boolean selected, Color outerColor) {
		super(selected, outerColor);
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getOuterColor());
		graphics.drawLine(xCoordinate - POINT_LINE_GAP, yCoordinate, xCoordinate + POINT_LINE_GAP, yCoordinate);
		graphics.drawLine(xCoordinate, yCoordinate - POINT_LINE_GAP, xCoordinate, yCoordinate + POINT_LINE_GAP);

		if (isSelected())
			drawSelection(graphics);
	}

	@Override
	public void drawSelection(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		graphics.drawRect(xCoordinate - SELECT_RECTANGLE_GAP, yCoordinate - SELECT_RECTANGLE_GAP, 6, 6);
	}

	public boolean contains(int xCoordinate, int yCoordinate) {
		if (calculateDistance(xCoordinate, yCoordinate) <= POINT_CLICK_TRESHOLD)
			return true;
		return false;
	}

	public double calculateDistance(int xCoodinate, int yCoodinate) {
		double xCoodinatesDifference = xCoordinate - xCoodinate;
		double yCoodinatesDifference = yCoordinate - yCoodinate;
		return Math.sqrt(xCoodinatesDifference * xCoodinatesDifference + yCoodinatesDifference * yCoodinatesDifference);
	}

	public boolean equals(Object object) {
		if (object instanceof Point) {
			Point point = (Point) object;

			if (xCoordinate == point.xCoordinate && yCoordinate == point.yCoordinate)
				return true;
		}

		return false;
	}

	public Point clone() {
		return new Point(xCoordinate, yCoordinate, isSelected(), getOuterColor());
	}

	public String toString() {
		return "(xCoordinate: " + xCoordinate + " , yCoordinate: " + yCoordinate + " , Outer color: "
				+ getOuterColor().getRGB() + " )";
	}

	public int getXcoordinate() {
		return xCoordinate;
	}

	public int getYcoordinate() {
		return yCoordinate;
	}

	public void setXcoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setYcoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
}