package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	private static final long serialVersionUID = 1L;
	private int xCoordinate;
	private int yCoordinate;

	public Point() {
	}

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
		graphics.drawLine(xCoordinate - 2, yCoordinate, xCoordinate + 2, yCoordinate);
		graphics.drawLine(xCoordinate, yCoordinate - 2, xCoordinate, yCoordinate + 2);

		if (isSelected())
			drawSelection(graphics);
	}

	@Override
	public void drawSelection(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		graphics.drawRect(xCoordinate - 3, yCoordinate - 3, 6, 6);
	}

	public boolean contains(int xCoordinate, int yCoordinate) {
		if (calculateDistance(xCoordinate, yCoordinate) <= 3)
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