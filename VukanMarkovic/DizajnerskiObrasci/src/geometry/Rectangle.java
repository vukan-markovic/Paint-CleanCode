package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends FillShape {
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

	public Rectangle(Point p, int height, int width, boolean selected, Color b, Color f) {
		upperLeftPoint = p;
		setHeight(height);
		setWidth(width);
		setSelected(selected);
		setBorder_Color(b);
		setFill_Color(f);
	}

	public Rectangle clone() {
		Rectangle cloneRect = new Rectangle(upperLeftPoint.clone(), height, width,
				isSelected(), getBorder_Color(), getFill_Color());
		return cloneRect;
	}

	@Override
	public void draw(Graphics g) {
		fill_shape(g);
		g.setColor(getBorder_Color());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		g.setColor(Color.BLUE);

		if (isSelected()) {
			g.drawRect(upperLeftPoint.getX() - 3, upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(upperLeftPoint.getX() - 3 + width, upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(upperLeftPoint.getX() - 3, upperLeftPoint.getY() - 3 + height, 6, 6);
			g.drawRect(upperLeftPoint.getX() + width - 3,
					upperLeftPoint.getY() + height - 3, 6, 6);
		}
	}

	@Override
	public void fill_shape(Graphics g) {
		g.setColor(getFill_Color());
		g.fillRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);

	}

	@Override
	public void moveBy(int byX, int byY) {
		upperLeftPoint.moveBy(byX, byY);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Rectangle)
			return (int) (this.area() - ((Rectangle) o).area());
		return 0;
	}

	public boolean contains(int x, int y) {
		if (upperLeftPoint.getX() <= x && x <= upperLeftPoint.getX() + width
				&& upperLeftPoint.getY() <= y && y <= upperLeftPoint.getY() + height)
			return true;
		return false;
	}

	public boolean contains(Point p) {
		if (upperLeftPoint.getX() <= p.getX() && p.getX() <= upperLeftPoint.getX() + width
				&& upperLeftPoint.getY() <= p.getY() && p.getY() <= upperLeftPoint.getY() + height)
			return true;
		return false;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle r = (Rectangle) obj;

			if (upperLeftPoint.equals(r.upperLeftPoint) && height == r.height && width == r.width)
				return true;
		}

		return false;
	}

	public int area() {
		return width * height;
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
		return "Upper left point: " + upperLeftPoint + ", height: " + height + " , width: " + width
				+ " , Border color: " + getBorder_Color().getRGB() + " , Fill color: " + getFill_Color().getRGB();
	}
}