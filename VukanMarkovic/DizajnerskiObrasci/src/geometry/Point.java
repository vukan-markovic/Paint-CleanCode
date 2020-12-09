package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, boolean selected, Color b) {
		this.x = x;
		this.y=y;
		setSelected(selected);
		setBorder_Color(b);
	}
	
	public Point clone() {
		
		Point clonePoint = new Point(this.getX(), this.getY(), this.isSelected(), this.getBorder_Color());
		return clonePoint;
	}
	
	
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getBorder_Color());
		g.drawLine(this.x-2, y, this.x+2, y);
		g.drawLine(x, this.y-2, x, this.y+2);
		g.setColor(Color.BLUE);
		if (isSelected()) {
			g.drawRect(this.x-3, this.y-3, 6, 6);
			
		}
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.x = this.x + byX;
		this.y += byY;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point start = new Point(0, 0, false, getBorder_Color());
			return (int) (this.distance(start.getX(), start.getY()) - ((Point) o).distance(start.getX(), start.getY()));
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
		if(this.distance(x, y) <= 3)
			return true;
		return false;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			if (this.x == p.getX() && 
					this.y == p.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public double distance (int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt (dx*dx + dy*dy);
		return d;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return "(x: " + x + " , y: " + y + " , Border color: " + getBorder_Color().getRGB() + " )";
	}



	
}