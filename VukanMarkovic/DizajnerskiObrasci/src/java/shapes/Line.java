package shapes;

import java.awt.*;

public class Line extends Shape {
	private static final long serialVersionUID = 1L;
	private final double LINE_CLICK_TRESHOLD = 0.05;
	private Point startPoint;
	private Point endPoint;

	public Line(Point startPoint, Point endPoint, boolean selected, Color outerColor) {
		super(selected, outerColor);
		this.startPoint = startPoint;
		this.endPoint = endPoint;
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
	protected void drawSelection(Graphics graphics) {
		startPoint.drawSelection(graphics);
		endPoint.drawSelection(graphics);
		getMiddleOfLine().drawSelection(graphics);
	}

	public Point getMiddleOfLine() {
		return new Point((startPoint.getXcoordinate() + endPoint.getXcoordinate()) / 2,
				(startPoint.getYcoordinate() + endPoint.getYcoordinate()) / 2, false, getOuterColor());
	}

	public boolean contains(int xCoordinate, int yCoordinate) {
		return (startPoint.calculateDistance(xCoordinate, yCoordinate)
				+ endPoint.calculateDistance(xCoordinate, yCoordinate)) - calculateLength() <= LINE_CLICK_TRESHOLD;
	}

	public double calculateLength() {
		return startPoint.calculateDistance(endPoint.getXcoordinate(), endPoint.getYcoordinate());
	}

	public boolean equals(Object object) {
		if (object instanceof Line) {
			Line line = (Line) object;
			return this.startPoint.equals(line.startPoint) && this.endPoint.equals(line.endPoint);
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