package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	private static final long serialVersionUID = 1L;
	private int innerRadius;
	private Area donutArea;

	public Donut() {
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color borderColor, Color fillColor) {
		super(center, radius, false, borderColor, fillColor);
		this.innerRadius = innerRadius;
		setSelected(selected);
	}

	public Donut clone() {
		return new Donut(getCenter().clone(), getRadius(), innerRadius, isSelected(), getBorderColor(), getFillColor());
	}

	public void draw(Graphics graphics, Graphics2D graphics2d) {
		donutArea = new Area(new Ellipse2D.Double(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(),
				getRadius() * 2, getRadius() * 2));

		donutArea.subtract(new Area(new Ellipse2D.Double((getCenter().getX() - innerRadius),
				(getCenter().getY() - innerRadius), innerRadius * 2, innerRadius * 2)));

		/*
		 * gr.setColor(getFill_Color()); gr.fill(donut); super.draw(g);
		 * g.setColor(getBorder_Color()); g.drawOval(this.getCenter().getX() -
		 * this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(),
		 * this.getInnerRadius()*2, this.getInnerRadius()*2);
		 */
		graphics.setColor(getBorderColor());
		graphics2d.draw(donutArea);
		graphics.setColor(getFillColor());
		graphics2d.fill(donutArea);

		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			graphics.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			graphics.drawRect(getCenter().getX() + getRadius() - 3, getCenter().getY() - 3, 6, 6);
			graphics.drawRect(getCenter().getX() - getRadius() - 3, getCenter().getY() - 3, 6, 6);
			graphics.drawRect(getCenter().getX() - 3, getCenter().getY() + getRadius() - 3, 6, 6);
			graphics.drawRect(getCenter().getX() - 3, getCenter().getY() - getRadius() - 3, 6, 6);
		}
	}

	public int compareTo(Object object) {
		if (object instanceof Donut)
			return (int) (area() - ((Donut) object).area());
		return 0;
	}

	public boolean contains(int x, int y) {
		return super.contains(x, y) && getCenter().distance(x, y) > innerRadius;
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean equals(Object object) {
		if (object instanceof Donut) {
			Donut donut = (Donut) object;

			if (getCenter().equals(donut.getCenter()) && getRadius() == donut.getRadius()
					&& innerRadius == donut.innerRadius)
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

	public Area getDonutArea() {
		return donutArea;
	}

	public String toString() {
		return super.toString() + " , inner radius: " + innerRadius + " , Border color: " + getBorderColor().getRGB()
				+ " , Fill Color: " + getFillColor().getRGB();
	}
}