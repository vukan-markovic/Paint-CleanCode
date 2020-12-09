package geometry;

import java.awt.Color;
import java.awt.Graphics;


public class Circle extends FillShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point center;
	private int radius;
	
	public Circle() {

	}

	
	
	public Circle(Point center, int radius) {
		super();
		this.center = center;
		this.radius = radius;
	}



	public Circle(Point center, int radius, boolean selected, Color border, Color fill) {
		this.center = center;
		this.radius = radius;
		setSelected(selected);
		setBorder_Color(border);
		setFill_Color(fill);
		
	}
	
	
	public Circle clone() {
		
		Circle cloneCircle = new Circle(this.getCenter(), this.getRadius(), this.isSelected(), this.getBorder_Color(), this.getFill_Color());
		return cloneCircle;
	}


	@Override
	public void draw(Graphics g) {
		g.setColor(getBorder_Color());
		g.drawOval(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(), this.getRadius()*2, this.getRadius()*2);
		fill_shape(g);
	g.setColor(Color.BLUE);
		if (isSelected()) {
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() + getRadius() - 3, this.getCenter().getY()-3, 6, 6);
			g.drawRect(this.getCenter().getX() - getRadius() - 3, this.getCenter().getY()-3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + getRadius() -3, 6, 6);
			g.drawRect(this.getCenter().getX()  - 3, this.getCenter().getY() - getRadius() -3, 6, 6);
			
		}
	}
	
	@Override
	public void fill_shape(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getFill_Color());
		g.fillOval(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(), this.getRadius()*2, this.getRadius()*2);
		
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return (this.radius - ((Circle) o).radius);
		}
		return 0;
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
		
	}
	
	public boolean contains(int x, int y) {
		return this.getCenter().distance(x, y) <= radius ;
	}
	
	public boolean contains(Point p) {
		if (p.distance(getCenter().getX(), getCenter().getY()) <= radius) {
			return true;
		} else {
			return false;
		} 
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle c = (Circle) obj;
			if (this.center.equals(c.getCenter()) && this.radius == c.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public double area() {
		return radius * radius * Math.PI;
	}
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) throws Exception {
		if (radius > 0) {
			this.radius = radius;
		} else {
			throw new NumberFormatException("Radius has to be a value greater than 0");
		}
	}
	
	public String toString() {
		return "Center: " + center + ", radius: " + radius + " , Border color: " + getBorder_Color().getRGB()
				+ " , Fill color: " + getFill_Color().getRGB(); 
	}

	


	
}

