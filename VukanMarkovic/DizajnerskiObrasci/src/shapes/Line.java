package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;

	public Line() {
	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, boolean selected, Color outerColor) {
		this(startPoint, endPoint);
		setSelected(selected);
		setOuterColor(outerColor);
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getOuterColor());

		graphics.drawLine(startPoint.getXcoordinate(), startPoint.getYcoordinate(), endPoint.getXcoordinate(),
				endPoint.getYcoordinate());

		if (isSelected())
			drawSelection(graphics);
	}

	@Override
	public void drawSelection(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		graphics.drawRect(startPoint.getXcoordinate() - 3, startPoint.getYcoordinate() - 3, 6, 6);
		graphics.drawRect(endPoint.getXcoordinate() - 3, endPoint.getYcoordinate() - 3, 6, 6);
		graphics.drawRect(getMiddleOfLine().getXcoordinate() - 3, getMiddleOfLine().getYcoordinate() - 3, 6, 6);
	}

	public Point getMiddleOfLine() {
		return new Point((startPoint.getXcoordinate() + endPoint.getXcoordinate()) / 2,
				(startPoint.getYcoordinate() + endPoint.getYcoordinate()) / 2, false, getOuterColor());
	}

	public boolean contains(int xCoordinate, int yCoordinate) {
		if ((startPoint.calculateDistance(xCoordinate, yCoordinate)
				+ endPoint.calculateDistance(xCoordinate, yCoordinate)) - calculateLength() <= 0.05)
			return true;
		return false;
	}

	public double calculateLength() {
		return startPoint.calculateDistance(endPoint.getXcoordinate(), endPoint.getYcoordinate());
	}

	public boolean equals(Object object) {
		if (object instanceof Line) {
			Line line = (Line) object;

			if (this.startPoint.equals(line.startPoint) && this.endPoint.equals(line.endPoint))
				return true;
		}

		return false;
	}

	public Line clone() {
		return new Line(startPoint.clone(), endPoint.clone(), isSelected(), getOuterColor());
	}

	public String toString() {
		return "Start point: " + startPoint + ", " + "End point: " + endPoint + ", Outer color: "
				+ getOuterColor().getRGB();
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
}