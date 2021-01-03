package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	private static final long serialVersionUID = 1L;
	private int innerRadius;
	private Area area;

	public Donut() {
	}

	public Donut(Point center, int outerRadius, int innerRadius, boolean selected, Color innerColor, Color outerColor) {
		super(center, outerRadius, false, innerColor, outerColor);
		this.innerRadius = innerRadius;
		setSelected(selected);
	}

	public Donut clone() {
		return new Donut(getCenter().clone(), getRadius(), innerRadius, isSelected(), getInnerColor(), getOuterColor());
	}

	public void draw(Graphics graphics, Graphics2D graphics2d) {
		area = new Area(new Ellipse2D.Double(getCenter().getXcoordinate() - getRadius(), getCenter().getYcoordinate() - getRadius(),
				getRadius() * 2, getRadius() * 2));

		area.subtract(new Area(new Ellipse2D.Double((getCenter().getXcoordinate() - innerRadius),
				(getCenter().getYcoordinate() - innerRadius), innerRadius * 2, innerRadius * 2)));

		graphics.setColor(getOuterColor());
		graphics2d.draw(area);
		graphics.setColor(getInnerColor());
		graphics2d.fill(area);

		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			graphics.drawRect(getCenter().getXcoordinate() - 3, getCenter().getYcoordinate() - 3, 6, 6);
			graphics.drawRect(getCenter().getXcoordinate() + getRadius() - 3, getCenter().getYcoordinate() - 3, 6, 6);
			graphics.drawRect(getCenter().getXcoordinate() - getRadius() - 3, getCenter().getYcoordinate() - 3, 6, 6);
			graphics.drawRect(getCenter().getXcoordinate() - 3, getCenter().getYcoordinate() + getRadius() - 3, 6, 6);
			graphics.drawRect(getCenter().getXcoordinate() - 3, getCenter().getYcoordinate() - getRadius() - 3, 6, 6);
		}
	}

	public boolean contains(int xCoordinate, int yCoordinate) {
		return super.contains(xCoordinate, yCoordinate) && getCenter().distance(xCoordinate, yCoordinate) > innerRadius;
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
		return area;
	}

	public String toString() {
		return super.toString() + " , inner radius: " + innerRadius + " , Inner color: " + getInnerColor().getRGB()
				+ " , Outer color: " + getOuterColor().getRGB();
	}
}