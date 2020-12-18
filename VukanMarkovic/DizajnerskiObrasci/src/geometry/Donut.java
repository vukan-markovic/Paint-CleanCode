package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	private static final long serialVersionUID = 1L;
	private int innerRadius;

	public Donut() {
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color border, Color fill) {
		super(center, radius, false, border, fill);
		this.innerRadius = innerRadius;
		setSelected(selected);
	}

	public Donut clone() {
		Donut cloneDonut = new Donut(getCenter(), getRadius(), innerRadius, isSelected(), getBorder_Color(),
				getFill_Color());
		return cloneDonut;
	}

	public void draw(Graphics g) {
		/*
		 * Ellipse2D outer=new Ellipse2D.Double(this.getCenter().getX() -
		 * this.getRadius(), this.getCenter().getY() - this.getRadius(),
		 * this.getRadius() * 2, this.getRadius() * 2); Ellipse2D inner=new
		 * Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius(),
		 * this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius() * 2,
		 * this.getInnerRadius() * 2); Area donut=new Area(outer); donut.subtract(new
		 * Area(inner)); Graphics2D gr=(Graphics2D)g; gr.setColor(getFill_Color());
		 * gr.fill(donut); super.draw(g); g.setColor(getBorder_Color());
		 * g.drawOval(this.getCenter().getX() - this.getInnerRadius(),
		 * this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius()*2,
		 * this.getInnerRadius()*2);
		 */

		Ellipse2D innerCircle = new Ellipse2D.Double((getCenter().getX() - innerRadius),
				(getCenter().getY() - innerRadius), innerRadius * 2, innerRadius * 2);
		Area inner = new Area(innerCircle);
		Ellipse2D outerCircle = new Ellipse2D.Double(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(),
				getRadius() * 2, getRadius() * 2);
		Area donut = new Area(outerCircle);
		donut.subtract(inner);
		Graphics2D gr = (Graphics2D) g;
		g.setColor(getBorder_Color());
		gr.draw(donut);
		g.setColor(getFill_Color());
		gr.fill(donut);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() + getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() + getRadius() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - getRadius() - 3, 6, 6);

		}
	}

	public int compareTo(Object o) {
		if (o instanceof Donut)
			return (int) (area() - ((Donut) o).area());
		return 0;
	}

	public boolean contains(int x, int y) {
		double dFromCenter = getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}

	public boolean contains(Point p) {
		double dFromCenter = getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;

			if (getCenter().equals(d.getCenter()) && getRadius() == d.getRadius() && innerRadius == d.innerRadius)
				return true;
		}

		return false;
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public String toString() {
		return super.toString() + " , inner radius: " + innerRadius + " , Border color: " + getBorder_Color().getRGB()
				+ " , Fill Color: " + getFill_Color().getRGB();
	}
}