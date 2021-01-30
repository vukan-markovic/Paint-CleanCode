package shapes;

import java.awt.*;

public class Line extends Shape {
	private static final long serialVersionUID = 1L;
	private final double LINE_CLICK_TRESHOLD = 0.05;
	private Point startPoint;
	private Point endPoint;

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, boolean selected, Color borderColor) {
		this(startPoint, endPoint);
		setSelected(selected);
		setBorderColor(borderColor);
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getBorderColor());

		graphics.drawLine(startPoint.getXcoordinate(), startPoint.getYcoordinate(), endPoint.getXcoordinate(),
				endPoint.getYcoordinate());

		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			drawSelection(graphics);
		}
	}

	@Override
	protected void drawSelection(Graphics graphics) {
		startPoint.drawSelection(graphics);
		endPoint.drawSelection(graphics);
		getMiddleOfLine().drawSelection(graphics);
	}

	public Point getMiddleOfLine() {
		int xCoordinate = (startPoint.getXcoordinate() + endPoint.getXcoordinate()) / 2;
		int yCoordinate = (startPoint.getYcoordinate() + endPoint.getYcoordinate()) / 2;
		return new Point(xCoordinate, yCoordinate);
	}

	@Override
	public boolean contains(int xCoordinate, int yCoordinate) {
		double startPointDistance = startPoint.calculateDistance(xCoordinate, yCoordinate);
		double endPointDistance = endPoint.calculateDistance(xCoordinate, yCoordinate);
		return startPointDistance + endPointDistance - calculateLength() <= LINE_CLICK_TRESHOLD;
	}

	public double calculateLength() {
		return startPoint.calculateDistance(endPoint.getXcoordinate(), endPoint.getYcoordinate());
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Line) {
			Line line = (Line) object;
			return startPoint.equals(line.startPoint) && endPoint.equals(line.endPoint);
		}

		return false;
	}

	@Override
	public Line clone() {
		return new Line(startPoint.clone(), endPoint.clone(), isSelected(), getBorderColor());
	}

	@Override
	public String toString() {
		return "Start point: " + startPoint + ", " + "End point: " + endPoint + ", Border color: "
				+ getBorderColor().getRGB();
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