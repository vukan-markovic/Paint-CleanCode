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

	public Point(int xCoordinate, int yCoordinate, boolean selected, Color borderColor) {
		this(xCoordinate, yCoordinate);
		setSelected(selected);
		setBorderColor(borderColor);
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getBorderColor());
		graphics.drawLine(xCoordinate - POINT_LINE_GAP, yCoordinate, xCoordinate + POINT_LINE_GAP, yCoordinate);
		graphics.drawLine(xCoordinate, yCoordinate - POINT_LINE_GAP, xCoordinate, yCoordinate + POINT_LINE_GAP);

		if (isSelected()) {
			graphics.setColor(getSelectionColor());
			drawSelection(graphics);
		}
	}

	@Override
	protected void drawSelection(Graphics graphics) {
		graphics.drawRect(xCoordinate - SELECT_RECTANGLE_GAP, yCoordinate - SELECT_RECTANGLE_GAP,
				SELECT_RECTANGLE_SIDE_LENGTH, SELECT_RECTANGLE_SIDE_LENGTH);
	}

	@Override
	public boolean contains(int xCoordinate, int yCoordinate) {
		return calculateDistance(xCoordinate, yCoordinate) <= POINT_CLICK_TRESHOLD;
	}

	public double calculateDistance(int xCoodinate, int yCoodinate) {
		double distanceXsquare = Math.pow(xCoordinate - xCoodinate, 2);
		double distanceYsquare = Math.pow(yCoordinate - yCoodinate, 2);
		return Math.sqrt(distanceXsquare + distanceYsquare);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Point) {
			Point point = (Point) object;
			return xCoordinate == point.xCoordinate && yCoordinate == point.yCoordinate;
		}

		return false;
	}

	@Override
	public Point clone() {
		return new Point(xCoordinate, yCoordinate, isSelected(), getBorderColor());
	}

	@Override
	public String toString() {
		return "(xCoordinate: " + xCoordinate + " , yCoordinate: " + yCoordinate + " , Border color: "
				+ getBorderColor().getRGB() + " )";
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