package geometry;

import java.awt.Color;
import java.awt.Graphics;
public class Rectangle extends FillShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private int height;
	private int width;
	
	
	public Rectangle() {

	}

	
	


	public Rectangle(Point upperLeftPoint, int height, int width) {
		super();
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}





	public Rectangle(Point p, int height, int width, boolean selected, Color b, Color f) {
		this.upperLeftPoint = p;
		setHeight(height);
		setWidth(width);
		setSelected(selected);
		setBorder_Color(b);
		setFill_Color(f);
		
	}
	
	public Rectangle clone() {
		
		Rectangle cloneRect = new Rectangle(this.getUpperLeftPoint(), this.getHeight(), this.getWidth(), this.isSelected(), this.getBorder_Color(), this.getFill_Color());
		return cloneRect;
	}


	@Override
	public void draw(Graphics g) {
		fill_shape(g);
		g.setColor(getBorder_Color());
		g.drawRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.getWidth(), this.getHeight());
		g.setColor(Color.BLUE);
		if (isSelected()) {
			g.drawRect(this.getUpperLeftPoint().getX() - 3, this.getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() - 3 + getWidth(), this.getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() - 3, this.getUpperLeftPoint().getY() - 3 + getHeight(), 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() + getWidth() - 3, this.getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
		}
	}
	
	@Override
	public void fill_shape(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getFill_Color());
		g.fillRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.getWidth(), this.getHeight());
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		upperLeftPoint.moveBy(byX, byY);
		
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) o).area());
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
		if (this.getUpperLeftPoint().getX() <= x 
				&& x <= this.getUpperLeftPoint().getX() + width
				&& this.getUpperLeftPoint().getY() <= y
				&& y <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}

	public boolean contains(Point p) {
		if (this.getUpperLeftPoint().getX() <= p.getX()
				&& p.getX() <= this.getUpperLeftPoint().getX() + width
				&& this.getUpperLeftPoint().getY() <= p.getY()
				&& p.getY() <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle r = (Rectangle) obj;
			if (this.upperLeftPoint.equals(r.getUpperLeftPoint()) && this.height == r.getHeight()
					&& this.width == r.getWidth()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
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
		return "Upper left point: " + upperLeftPoint + ", height: " + height + " , width: " + width + " , Border color: "
				+ getBorder_Color().getRGB() + " , Fill color: " + getFill_Color().getRGB();
	}


	


}