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

	public Line(Point startPoint, Point endPoint, boolean selected, Color b) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		setSelected(selected);
		setBorder_Color(b);
	}

	public Line clone() {
		return new Line(startPoint.clone(), endPoint.clone(), isSelected(), getBorder_Color());
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getBorder_Color());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		g.setColor(Color.BLUE);

		if (isSelected()) {
			g.drawRect(startPoint.getX() - 3, startPoint.getY() - 3, 6, 6);
			g.drawRect(endPoint.getX() - 3, endPoint.getY() - 3, 6, 6);
			g.drawRect(middleOfLine().getX() - 3, middleOfLine().getY() - 3, 6, 6);
		}
	}

	public Point middleOfLine() {
		return new Point((startPoint.getX() + endPoint.getX()) / 2, (startPoint.getY() + endPoint.getY()) / 2, false,
				getBorder_Color());
	}

	@Override
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Line)
			return (int) (length() - ((Line) o).length());
		return 0;
	}

	public boolean contains(int x, int y) {
		if ((startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <= 0.05)
			return true;
		return false;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line l = (Line) obj;

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
				+ getBorder_Color().getRGB();
	}
}