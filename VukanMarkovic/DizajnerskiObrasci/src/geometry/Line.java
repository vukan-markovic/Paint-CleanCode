package geometry;

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

	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint);
		setSelected(selected);
		setBorderColor(color);
	}

	public Line clone() {
		return new Line(startPoint.clone(), endPoint.clone(), isSelected(), getBorderColor());
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(getBorderColor());
		graphics.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		graphics.setColor(Color.BLUE);

		if (isSelected()) {
			graphics.drawRect(startPoint.getX() - 3, startPoint.getY() - 3, 6, 6);
			graphics.drawRect(endPoint.getX() - 3, endPoint.getY() - 3, 6, 6);
			graphics.drawRect(middleOfLine().getX() - 3, middleOfLine().getY() - 3, 6, 6);
		}
	}

	public Point middleOfLine() {
		return new Point((startPoint.getX() + endPoint.getX()) / 2, (startPoint.getY() + endPoint.getY()) / 2, false,
				getBorderColor());
	}

	@Override
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}

	@Override
	public int compareTo(Object object) {
		if (object instanceof Line)
			return (int) (length() - ((Line) object).length());
		return 0;
	}

	public boolean contains(int x, int y) {
		if ((startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <= 0.05)
			return true;
		return false;
	}

	public boolean equals(Object object) {
		if (object instanceof Line) {
			Line l = (Line) object;

			if (this.startPoint.equals(l.startPoint) && this.endPoint.equals(l.endPoint))
				return true;
		}

		return false;
	}

	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		return "Start point: " + startPoint + ", " + "End point: " + endPoint + ", Border color: "
				+ getBorderColor().getRGB();
	}
}